package com.quiz.dao;

import com.quiz.config.DatabaseConfig;
import com.quiz.model.Question;
import com.quiz.model.UserAttempt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionDao {
    
    public List<Question> getRandomQuestions(int limit) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions ORDER BY RAND() LIMIT ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuestionText(rs.getString("question_text"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectAnswer(rs.getString("correct_answer").charAt(0));
                q.setCategory(rs.getString("category"));
                q.setDifficulty(rs.getString("difficulty"));
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
    
    public List<Question> getQuestionsByCategory(String category, int limit) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE category = ? ORDER BY RAND() LIMIT ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category);
            pstmt.setInt(2, limit);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuestionText(rs.getString("question_text"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectAnswer(rs.getString("correct_answer").charAt(0));
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
    
    public void saveUserAttempt(int userId, int score, int totalQuestions) {
        String sql = "INSERT INTO user_attempts (user_id, score, total_questions) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, score);
            pstmt.setInt(3, totalQuestions);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<UserAttempt> getUserAttempts(int userId) {
        List<UserAttempt> attempts = new ArrayList<>();
        String sql = "SELECT * FROM user_attempts WHERE user_id = ? ORDER BY attempt_date DESC";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                UserAttempt attempt = new UserAttempt();
                attempt.setId(rs.getInt("id"));
                attempt.setUserId(rs.getInt("user_id"));
                attempt.setAttemptDate(rs.getTimestamp("attempt_date"));
                attempt.setScore(rs.getInt("score"));
                attempt.setTotalQuestions(rs.getInt("total_questions"));
                attempts.add(attempt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attempts;
    }
    
    public int getTotalQuestionCount() {
        String sql = "SELECT COUNT(*) FROM questions";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
