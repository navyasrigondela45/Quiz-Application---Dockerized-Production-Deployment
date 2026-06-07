package com.quiz.servlet;

import com.quiz.dao.QuestionDao;
import com.quiz.model.Question;
import com.quiz.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class QuizServlet extends HttpServlet {
    private QuestionDao questionDao = new QuestionDao();
    private static final int QUESTIONS_PER_TEST = 10;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Check if restart is requested
        String restart = request.getParameter("restart");
        if ("true".equals(restart)) {
            session.removeAttribute("questions");
            session.removeAttribute("currentIndex");
            session.removeAttribute("answers");
            session.removeAttribute("quizCompleted");
            response.sendRedirect(request.getContextPath() + "/quiz");
            return;
        }
        
        // Check if quiz is already completed
        Boolean quizCompleted = (Boolean) session.getAttribute("quizCompleted");
        if (quizCompleted != null && quizCompleted) {
            response.sendRedirect(request.getContextPath() + "/result");
            return;
        }
        
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        
        if (questions == null) {
            questions = questionDao.getRandomQuestions(QUESTIONS_PER_TEST);
            session.setAttribute("questions", questions);
            session.setAttribute("currentIndex", 0);
            session.setAttribute("answers", new String[QUESTIONS_PER_TEST]);
        }
        
        Integer currentIndex = (Integer) session.getAttribute("currentIndex");
        
        if (currentIndex >= QUESTIONS_PER_TEST) {
            // Quiz completed, calculate score
            String[] answers = (String[]) session.getAttribute("answers");
            int score = 0;
            for (int i = 0; i < QUESTIONS_PER_TEST; i++) {
                Question q = questions.get(i);
                if (answers[i] != null && answers[i].charAt(0) == q.getCorrectAnswer()) {
                    score++;
                }
            }
            
            User user = (User) session.getAttribute("user");
            questionDao.saveUserAttempt(user.getId(), score, QUESTIONS_PER_TEST);
            
            session.setAttribute("score", score);
            session.setAttribute("quizCompleted", true);
            session.setAttribute("userAnswers", answers);
            
            response.sendRedirect(request.getContextPath() + "/result");
            return;
        }
        
        Question currentQuestion = questions.get(currentIndex);
        request.setAttribute("question", currentQuestion);
        request.setAttribute("currentIndex", currentIndex + 1);
        request.setAttribute("totalQuestions", QUESTIONS_PER_TEST);
        request.getRequestDispatcher("/quiz.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String answer = request.getParameter("answer");
        Integer currentIndex = (Integer) session.getAttribute("currentIndex");
        String[] answers = (String[]) session.getAttribute("answers");
        
        if (answer != null && currentIndex != null && answers != null && currentIndex < answers.length) {
            answers[currentIndex] = answer;
            session.setAttribute("currentIndex", currentIndex + 1);
        }
        
        response.sendRedirect(request.getContextPath() + "/quiz");
    }
}
