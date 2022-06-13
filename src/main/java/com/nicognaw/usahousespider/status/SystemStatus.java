package com.nicognaw.usahousespider.status;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "system_status")
public class SystemStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private boolean needInit;

    private LocalDateTime changedAt;

    public SystemStatus() {
        this.changedAt = LocalDateTime.now();
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public boolean getNeedInit() {
        return needInit;
    }

    public void setNeedInit(boolean needInit) {
        this.needInit = needInit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}