package com.ainexushub.tcp_monitor.controller;

import com.ainexushub.tcp_monitor.dto.ConnectionStatsDTO;
import com.ainexushub.tcp_monitor.service.TcpConnectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/connections")
@CrossOrigin(origins = "*") // 允许所有跨域请求，便于前端开发
public class TcpConnectionController {

    @Autowired
    private TcpConnectionService tcpConnectionService;

    /**
     * 获取TCP连接统计信息
     * @param startTime 开始时间（格式：yyyy-MM-dd HH:mm:ss）
     * @param endTime 结束时间（格式：yyyy-MM-dd HH:mm:ss）
     * @return 连接统计列表
     */
    @GetMapping("/stats")
    public ResponseEntity<List<ConnectionStatsDTO>> getConnectionStats(
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime) {
        
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        
        try {
            // 解析开始时间
            if (startTime != null && !startTime.trim().isEmpty()) {
                // 移除可能的引号
                String cleanedStartTime = startTime.replaceAll("^['\"]|['\"]$", "");
                startDateTime = LocalDateTime.parse(cleanedStartTime, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } else {
                startDateTime = LocalDateTime.now().minusHours(24);
            }
            
            // 解析结束时间
            if (endTime != null && !endTime.trim().isEmpty()) {
                // 移除可能的引号
                String cleanedEndTime = endTime.replaceAll("^['\"]|['\"]$", "");
                endDateTime = LocalDateTime.parse(cleanedEndTime, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } else {
                endDateTime = LocalDateTime.now();
            }

            List<ConnectionStatsDTO> stats = tcpConnectionService.getConnectionStats(startDateTime, endDateTime);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
