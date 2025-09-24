package com.ainexushub.tcp_monitor.service;

import com.ainexushub.tcp_monitor.dto.ConnectionStatsDTO;
import com.ainexushub.tcp_monitor.model.TcpConnection;
import com.ainexushub.tcp_monitor.repository.TcpConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TcpConnectionService {

    @Autowired
    private TcpConnectionRepository tcpConnectionRepository;

    /**
     * 获取TCP连接统计信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 连接统计列表
     */
    public List<ConnectionStatsDTO> getConnectionStats(LocalDateTime startTime, LocalDateTime endTime) {
        List<TcpConnection> rawStats = tcpConnectionRepository.findConnectionsByTimeRange(startTime, endTime);
        Map<String, ConnectionStatsDTO> connectionStats = new HashMap<String, ConnectionStatsDTO>();
        rawStats.forEach(stat -> {
            String key = stat.getLocalIp() + ":" + stat.getLocalPort();
            long duration = stat.getConnectionDurationSeconds();
            if (connectionStats.containsKey(key)) {
                ConnectionStatsDTO existing = connectionStats.get(key);
                existing.setDuration(existing.getDuration() + duration);
            } else {
                connectionStats.put(key, new ConnectionStatsDTO(key, duration));
            }
        });
        return new ArrayList<>(connectionStats.values());
    }
}
