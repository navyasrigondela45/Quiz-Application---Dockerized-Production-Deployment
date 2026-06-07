package com.quiz.model;

import java.sql.Timestamp;

public class UserAttempt {
    private int id;
    private int userId;
    private Timestamp attemptDate;
    private int score;
    private int totalQuestions;
    
    public UserAttempt() {}
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public Timestamp getAttemptDate() { return attemptDate; }
    public void setAttemptDate(Timestamp attemptDate) { this.attemptDate = attemptDate; }
    
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    
    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }
    
    public double getPercentage() {
        return (score * 100.0) / totalQuestions;
    }
}
