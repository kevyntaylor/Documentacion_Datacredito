package com.ebs.datacredito.repository;

import com.ebs.datacredito.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepository extends JpaRepository<Log, Long> {

}
