package com.ainexushub.tcp_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionStatsDTO {
    private String localEndpoint;
    private Long duration;
}
