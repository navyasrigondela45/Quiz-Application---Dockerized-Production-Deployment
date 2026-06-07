<%@ page import="com.quiz.model.User" %>
<%@ page import="com.quiz.model.UserAttempt" %>
<%@ page import="com.quiz.model.QuestionResult" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    Integer currentScore = (Integer) request.getAttribute("currentScore");
    int totalQuestions = 10;
    List<QuestionResult> detailedResults = (List<QuestionResult>) request.getAttribute("detailedResults");
    List<UserAttempt> historicalAttempts = (List<UserAttempt>) request.getAttribute("historicalAttempts");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Results - Quiz Application</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .results-tabs {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
            border-bottom: 2px solid #e2e8f0;
        }
        .tab-btn {
            padding: 10px 20px;
            background: none;
            border: none;
            cursor: pointer;
            font-size: 16px;
            color: #718096;
            transition: all 0.3s;
        }
        .tab-btn.active {
            color: #4299e1;
            border-bottom: 2px solid #4299e1;
            margin-bottom: -2px;
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
        .question-review {
            background: #f7fafc;
            margin-bottom: 15px;
            padding: 15px;
            border-radius: 8px;
            border-left: 4px solid;
        }
        .question-review.correct {
            border-left-color: #48bb78;
            background: #f0fff4;
        }
        .question-review.incorrect {
            border-left-color: #f56565;
            background: #fff5f5;
        }
        .answer-details {
            margin-top: 10px;
            padding-top: 10px;
            border-top: 1px solid #e2e8f0;
        }
        .user-answer {
            color: #4a5568;
        }
        .correct-answer {
            color: #48bb78;
            font-weight: bold;
        }
        .wrong-answer {
            color: #f56565;
        }
        .attempt-history {
            margin-top: 20px;
        }
        .attempt-item {
            background: #f7fafc;
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 8px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .attempt-score {
            font-size: 20px;
            font-weight: bold;
        }
        .attempt-date {
            color: #718096;
        }
        .stats-card {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        .stats-card h3 {
            margin: 0 0 10px 0;
        }
        .stats-card .big-score {
            font-size: 48px;
            font-weight: bold;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="result-container">
            <div class="stats-card">
                <h2>Quiz Completed!</h2>
                <h3>Welcome, <%= user.getUsername() %>!</h3>
                <div class="big-score">
                    <%= currentScore %>/<%= totalQuestions %>
                </div>
                <p>Percentage: <%= (currentScore * 100 / totalQuestions) %>%</p>
                <p>Grade: 
                    <% 
                        int percentage = currentScore * 100 / totalQuestions;
                        if (percentage >= 90) out.print("A+ (Excellent!)");
                        else if (percentage >= 80) out.print("A (Very Good!)");
                        else if (percentage >= 70) out.print("B (Good!)");
                        else if (percentage >= 60) out.print("C (Average)");
                        else if (percentage >= 50) out.print("D (Below Average)");
                        else out.print("F (Need Improvement)");
                    %>
                </p>
            </div>
            
            <div class="results-tabs">
                <button class="tab-btn active" onclick="showTab('current')">Current Quiz Results</button>
                <button class="tab-btn" onclick="showTab('history')">Attempt History</button>
            </div>
            
            <!-- Current Quiz Results Tab -->
            <div id="current-tab" class="tab-content active">
                <h3>Detailed Answers Review</h3>
                <% if (detailedResults != null && !detailedResults.isEmpty()) { %>
                    <% for (int i = 0; i < detailedResults.size(); i++) { 
                        QuestionResult qr = detailedResults.get(i);
                    %>
                        <div class="question-review <%= qr.isCorrect() ? "correct" : "incorrect" %>">
                            <strong>Question <%= i + 1 %>:</strong> <%= qr.getQuestion().getQuestionText() %><br>
                            <div class="answer-details">
                                <div class="user-answer">
                                    Your answer: 
                                    <span class="<%= qr.isCorrect() ? "correct-answer" : "wrong-answer" %>">
                                        <%= qr.getUserAnswer() != null ? qr.getUserAnswer() + ". " + qr.getUserAnswerText() : "Not answered" %>
                                    </span>
                                </div>
                                <% if (!qr.isCorrect()) { %>
                                    <div class="correct-answer">
                                        Correct answer: <%= qr.getCorrectAnswer() %>. <%= qr.getCorrectAnswerText() %>
                                    </div>
                                <% } %>
                            </div>
                        </div>
                    <% } %>
                <% } %>
                
                <div class="result-actions">
                    <a href="quiz?restart=true" class="btn btn-primary">Take New Quiz</a>
                    <a href="logout" class="btn btn-secondary">Logout</a>
                </div>
            </div>
            
            <!-- History Tab -->
            <div id="history-tab" class="tab-content">
                <h3>Your Previous Attempts</h3>
                <% if (historicalAttempts != null && !historicalAttempts.isEmpty()) { %>
                    <div class="attempt-history">
                        <% 
                            int bestScore = 0;
                            int totalScore = 0;
                            for (UserAttempt attempt : historicalAttempts) {
                                totalScore += attempt.getScore();
                                if (attempt.getScore() > bestScore) bestScore = attempt.getScore();
                        %>
                            <div class="attempt-item">
                                <div>
                                    <strong><%= attempt.getAttemptDate() %></strong><br>
                                    <span class="attempt-date">Quiz ID: #<%= attempt.getId() %></span>
                                </div>
                                <div class="attempt-score">
                                    Score: <%= attempt.getScore() %>/<%= attempt.getTotalQuestions() %>
                                    (<%= String.format("%.1f", attempt.getPercentage()) %>%)
                                </div>
                            </div>
                        <% } %>
                        <div class="stats-card" style="background: linear-gradient(135deg, #48bb78 0%, #38a169 100%); margin-top: 20px;">
                            <h3>Your Statistics</h3>
                            <p>Total Attempts: <%= historicalAttempts.size() %></p>
                            <p>Best Score: <%= bestScore %>/<%= totalQuestions %></p>
                            <p>Average Score: <%= String.format("%.1f", (double)totalScore / historicalAttempts.size()) %>/<%= totalQuestions %></p>
                        </div>
                    </div>
                <% } else { %>
                    <p>No previous attempts found. Take your first quiz!</p>
                <% } %>
            </div>
        </div>
    </div>
    
    <script>
        function showTab(tabName) {
            // Hide all tabs
            document.querySelectorAll('.tab-content').forEach(tab => {
                tab.classList.remove('active');
            });
            document.querySelectorAll('.tab-btn').forEach(btn => {
                btn.classList.remove('active');
            });
            
            // Show selected tab
            document.getElementById(tabName + '-tab').classList.add('active');
            event.target.classList.add('active');
        }
    </script>
</body>
</html>
