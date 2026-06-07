<%@ page import="com.quiz.model.Question" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Question question = (Question) request.getAttribute("question");
    int currentIndex = (Integer) request.getAttribute("currentIndex");
    int totalQuestions = (Integer) request.getAttribute("totalQuestions");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz - Question <%= currentIndex %> of <%= totalQuestions %></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <div class="quiz-header">
            <h2>Quiz Application</h2>
            <div class="quiz-info">
                Question <%= currentIndex %> of <%= totalQuestions %>
                <a href="quiz?restart=true" class="btn-restart">Restart Quiz</a>
                <a href="logout" class="btn-logout">Logout</a>
            </div>
        </div>
        
        <div class="quiz-container">
            <form action="quiz" method="post" id="quizForm">
                <div class="question">
                    <h3><%= question.getQuestionText() %></h3>
                </div>
                <div class="options">
                    <div class="option">
                        <input type="radio" name="answer" value="A" id="optionA" required>
                        <label for="optionA">A. <%= question.getOptionA() %></label>
                    </div>
                    <div class="option">
                        <input type="radio" name="answer" value="B" id="optionB">
                        <label for="optionB">B. <%= question.getOptionB() %></label>
                    </div>
                    <div class="option">
                        <input type="radio" name="answer" value="C" id="optionC">
                        <label for="optionC">C. <%= question.getOptionC() %></label>
                    </div>
                    <div class="option">
                        <input type="radio" name="answer" value="D" id="optionD">
                        <label for="optionD">D. <%= question.getOptionD() %></label>
                    </div>
                </div>
                <div class="quiz-actions">
                    <button type="submit" class="btn-next">Next Question</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
