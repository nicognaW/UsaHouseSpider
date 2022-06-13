package com.nicognaw.usahousespider;

import com.nicognaw.usahousespider.entity.response.DataRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpiderRepository
        extends JpaRepository<DataRecord, Long>, JpaSpecificationExecutor<DataRecord> {
}