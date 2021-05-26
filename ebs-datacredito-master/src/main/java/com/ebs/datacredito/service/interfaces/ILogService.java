package com.ebs.datacredito.service.interfaces;

import com.ebs.datacredito.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ILogService {

    Page<Log> get(PageRequest pageRequest);
    void create(Log log);

}
