package com.example.scheduling.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

/**
 * @author Milan Rathod
 */
@Service
@Slf4j
public class RetryServiceImpl implements RetryService {

    @Override
    public void retryServiceWithRecovery(String sql) throws SQLException {
        if (StringUtils.isEmpty(sql)) {
            log.info("throw SQLException in retryServiceWithRecovery()");
            throw new SQLException();
        }
    }

    @Override
    public void recover(SQLException ex, String sql) {
        log.info("Inside recover method");
    }
}
