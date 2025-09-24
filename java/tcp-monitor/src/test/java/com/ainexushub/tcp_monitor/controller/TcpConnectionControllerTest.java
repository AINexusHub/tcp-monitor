package com.ainexushub.tcp_monitor.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.ainexushub.tcp_monitor.dto.ConnectionStatsDTO;
import com.ainexushub.tcp_monitor.service.TcpConnectionService;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TcpConnectionControllerTest {

    @Mock
    private TcpConnectionService tcpConnectionService;

    @InjectMocks
    private TcpConnectionController tcpConnectionController;

    @Test
    void testGetConnectionStats_WithValidDates() {
        // 准备测试数据
        when(tcpConnectionService.getConnectionStats(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Map.of("test", new ConnectionStatsDTO()));

        // 执行测试
        ResponseEntity<Map<String, ConnectionStatsDTO>> response = 
            tcpConnectionController.getConnectionStats("2025-09-01 00:00:00", "2025-09-01 23:59:59");

        // 验证结果
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetConnectionStats_WithQuotedDates() {
        // 准备测试数据
        when(tcpConnectionService.getConnectionStats(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Map.of("test", new ConnectionStatsDTO()));

        // 执行测试 - 带引号的日期字符串
        ResponseEntity<Map<String, ConnectionStatsDTO>> response = 
            tcpConnectionController.getConnectionStats("'2025-09-01 00:00:00'", "'2025-09-01 23:59:59'");

        // 验证结果
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetConnectionStats_WithDoubleQuotedDates() {
        // 准备测试数据
        when(tcpConnectionService.getConnectionStats(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Map.of("test", new ConnectionStatsDTO()));

        // 执行测试 - 带双引号的日期字符串
        ResponseEntity<Map<String, ConnectionStatsDTO>> response = 
            tcpConnectionController.getConnectionStats("\"2025-09-01 00:00:00\"", "\"2025-09-01 23:59:59\"");

        // 验证结果
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetConnectionStats_WithNullDates() {
        // 准备测试数据
        when(tcpConnectionService.getConnectionStats(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Map.of("test", new ConnectionStatsDTO()));

        // 执行测试 - 空日期参数
        ResponseEntity<Map<String, ConnectionStatsDTO>> response = 
            tcpConnectionController.getConnectionStats(null, null);

        // 验证结果
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetConnectionStats_WithEmptyDates() {
        // 准备测试数据
        when(tcpConnectionService.getConnectionStats(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Map.of("test", new ConnectionStatsDTO()));

        // 执行测试 - 空字符串日期参数
        ResponseEntity<Map<String, ConnectionStatsDTO>> response = 
            tcpConnectionController.getConnectionStats("", "");

        // 验证结果
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetConnectionStats_WithInvalidDateFormat() {
        // 执行测试 - 无效的日期格式
        assertThrows(IllegalArgumentException.class, () -> {
            tcpConnectionController.getConnectionStats("invalid-date", "2025-09-01 23:59:59");
        });
    }
}
