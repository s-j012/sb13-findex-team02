package com.findex.team02.syncjob.repository;

import com.findex.team02.global.entity.SyncJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SyncJobRepository extends JpaRepository<SyncJob, Long> ,
    JpaSpecificationExecutor<SyncJob>
{
}
