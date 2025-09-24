package com.ainexushub.tcp_monitor.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tcp_connections_demo")
public class TcpConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_ip", length = 45)
    private String localIp;

    @Column(name = "local_port")
    private Integer localPort;

    @Column(name = "remote_ip", length = 45)
    private String remoteIp;

    @Column(name = "remote_port")
    private Integer remotePort;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "disconnected_at")
    private LocalDateTime disconnectedAt;

    @Column(name = "last_active_at")
    private LocalDateTime lastActiveAt;

    // Constructors
    public TcpConnection() {
    }

    public TcpConnection(String localIp, Integer localPort, String remoteIp, Integer remotePort, 
                       LocalDateTime createdAt, LocalDateTime lastActiveAt) {
        this.localIp = localIp;
        this.localPort = localPort;
        this.remoteIp = remoteIp;
        this.remotePort = remotePort;
        this.createdAt = createdAt;
        this.lastActiveAt = lastActiveAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public Integer getLocalPort() {
        return localPort;
    }

    public void setLocalPort(Integer localPort) {
        this.localPort = localPort;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public Integer getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDisconnectedAt() {
        return disconnectedAt;
    }

    public void setDisconnectedAt(LocalDateTime disconnectedAt) {
        this.disconnectedAt = disconnectedAt;
    }

    public LocalDateTime getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(LocalDateTime lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

    // Helper method to calculate connection duration in seconds
    public Long getConnectionDurationSeconds() {
        if (lastActiveAt != null && createdAt != null) {
            return java.time.Duration.between(createdAt, lastActiveAt).getSeconds();
        }
        return 0L;
    }
}
