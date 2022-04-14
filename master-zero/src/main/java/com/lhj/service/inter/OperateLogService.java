package com.lhj.service.inter;

import com.lhj.model.dto.OperateLogDTO;
import com.lhj.model.entity.OperateLog;

import java.util.List;

public interface OperateLogService {

    List<OperateLog> selectOperateLogs(OperateLogDTO operateLogDTO);

    OperateLog selectOperateLogById(Long logId);

    void createOperateLog(OperateLog operateLog);

    void deleteOperateLogsByIds(List<Long> logIds);

}
