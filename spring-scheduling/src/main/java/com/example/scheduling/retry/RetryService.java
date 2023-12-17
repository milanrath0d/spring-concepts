package com.example.scheduling.retry;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.sql.SQLException;

/**
 * @author Milan Rathod
 */
public interface RetryService {

    @Retryable(value = SQLException.class)
    void retryServiceWithRecovery(String sql) throws SQLException;

    @Recover
    void recover(SQLException ex, String sql);
}
