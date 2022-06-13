package com.nicognaw.usahousespider.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SystemStatusRepository
        extends JpaRepository<SystemStatus, Long>, JpaSpecificationExecutor<SystemStatus> {
    /**
     * 查询获取最新系统状态记录，若不存在则初始化系统状态
     *
     * @return 最新系统状态记录
     */
    default SystemStatus getLatestStatus() {
        var optional = findAllByOrderByChangedAtDesc();
        if (optional.isPresent()) return optional.get();
        var initStatus = new SystemStatus();
        initStatus.setNeedInit(true);
        this.saveAndFlush(initStatus);
        return initStatus;
    }

    Optional<SystemStatus> findAllByOrderByChangedAtDesc();
}