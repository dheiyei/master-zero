package com.koke.service.inter.sys;

import com.koke.model.dto.OperateLogDTO;
import com.koke.model.entity.sys.OperateLog;

import java.util.List;

public interface OperateLogService {

    List<OperateLog> selectOperateLogs(OperateLogDTO operateLogDTO);

    OperateLog selectOperateLogById(Long logId);

    void createOperateLog(OperateLog operateLog);

    void deleteOperateLogsByIds(List<Long> logIds);

}
