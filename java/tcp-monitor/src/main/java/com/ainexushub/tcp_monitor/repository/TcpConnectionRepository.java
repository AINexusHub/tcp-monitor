package com.ainexushub.tcp_monitor.repository;

import com.ainexushub.tcp_monitor.model.TcpConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TcpConnectionRepository extends JpaRepository<TcpConnection, Long> {

    /**
     * 查询指定时间范围内建立的TCP连接，并过滤掉连接时长小于5秒的记录
     * 返回所有满足条件的TCP连接记录
     */
    @Query("SELECT t " +
           "FROM TcpConnection t " +
           "WHERE t.createdAt BETWEEN :startTime AND :endTime " +
           "AND function('EXTRACT', EPOCH FROM (t.lastActiveAt - t.createdAt)) >= 5 " +
           "ORDER BY t.createdAt DESC")
    List<TcpConnection> findConnectionsByTimeRange(@Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);
}
