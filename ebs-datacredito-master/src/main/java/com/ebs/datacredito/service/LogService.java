package com.ebs.datacredito.service;

import com.ebs.datacredito.model.Log;
import com.ebs.datacredito.repository.ILogRepository;
import com.ebs.datacredito.service.interfaces.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LogService implements ILogService {

    private final ILogRepository logRepository;

    @Autowired
    public LogService(ILogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Page<Log> get(PageRequest pageRequest) {
        return logRepository.findAll(pageRequest);
    }

    @Override
    public void create(Log log) {
        log.setEndRequest(new Timestamp(System.currentTimeMillis()));
        logRepository.save(log);
    }

}
