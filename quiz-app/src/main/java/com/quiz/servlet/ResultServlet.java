package com.quiz.servlet;

import com.quiz.dao.QuestionDao;
import com.quiz.model.User;
import com.quiz.model.UserAttempt;
import com.quiz.model.Question;
import com.quiz.model.QuestionResult;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ResultServlet extends HttpServlet {
    private QuestionDao questionDao = new QuestionDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        Integer currentScore = (Integer) session.getAttribute("score");
        String[] userAnswers = (String[]) session.getAttribute("userAnswers");
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        Boolean quizCompleted = (Boolean) session.getAttribute("quizCompleted");
        
        // If no current quiz result, redirect to quiz
        if (quizCompleted == null || !quizCompleted) {
            response.sendRedirect(request.getContextPath() + "/quiz");
            return;
        }
        
        // Get all historical attempts
        List<UserAttempt> historicalAttempts = questionDao.getUserAttempts(user.getId());
        
        // Prepare detailed results for current quiz
        List<QuestionResult> detailedResults = new ArrayList<>();
        if (currentScore != null && userAnswers != null && questions != null) {
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                String userAnswer = userAnswers[i];
                boolean isCorrect = userAnswer != null && userAnswer.charAt(0) == q.getCorrectAnswer();
                
                QuestionResult qr = new QuestionResult();
                qr.setQuestion(q);
                qr.setUserAnswer(userAnswer);
                qr.setCorrect(isCorrect);
                qr.setCorrectAnswer(String.valueOf(q.getCorrectAnswer()));
                detailedResults.add(qr);
            }
        }
        
        request.setAttribute("detailedResults", detailedResults);
        request.setAttribute("currentScore", currentScore);
        request.setAttribute("totalQuestions", 10);
        request.setAttribute("historicalAttempts", historicalAttempts);
        
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
