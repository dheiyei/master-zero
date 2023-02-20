package com.koke.mapper;

import com.koke.model.dto.OperateLogDTO;
import com.koke.model.entity.OperateLog;

import java.util.List;

public interface OperateLogMapper {

    List<OperateLog> selectOperateLogs(OperateLogDTO operateLogDTO);

    OperateLog selectOperateLogById(Long logId);

    void createOperateLog(OperateLog operateLog);

    void deleteOperateLogsByIds(List<Long> logIds);

}
