package com.quiz.model;

public class QuestionResult {
    private Question question;
    private String userAnswer;
    private boolean isCorrect;
    private String correctAnswer;
    
    // Getters and Setters
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    
    public String getUserAnswer() { return userAnswer; }
    public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }
    
    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }
    
    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
    
    public String getUserAnswerText() {
        if (userAnswer == null) return "Not answered";
        switch(userAnswer.charAt(0)) {
            case 'A': return question.getOptionA();
            case 'B': return question.getOptionB();
            case 'C': return question.getOptionC();
            case 'D': return question.getOptionD();
            default: return "Invalid";
        }
    }
    
    public String getCorrectAnswerText() {
        switch(correctAnswer.charAt(0)) {
            case 'A': return question.getOptionA();
            case 'B': return question.getOptionB();
            case 'C': return question.getOptionC();
            case 'D': return question.getOptionD();
            default: return "Unknown";
        }
    }
}
