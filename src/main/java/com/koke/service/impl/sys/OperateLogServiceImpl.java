package com.koke.service.impl.sys;

import com.koke.mapper.sys.OperateLogMapper;
import com.koke.model.dto.OperateLogDTO;
import com.koke.model.entity.sys.OperateLog;
import com.koke.service.inter.sys.OperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OperateLogServiceImpl implements OperateLogService {

    private final OperateLogMapper operateLogMapper;

    @Override
    public List<OperateLog> selectOperateLogs(OperateLogDTO operateLogDTO) {
        return operateLogMapper.selectOperateLogs(operateLogDTO);
    }

    @Override
    public OperateLog selectOperateLogById(Long logId) {
        return operateLogMapper.selectOperateLogById(logId);
    }

    @Transactional
    @Override
    public void createOperateLog(OperateLog operateLog) {
        operateLogMapper.createOperateLog(operateLog);
    }

    @Transactional
    @Override
    public void deleteOperateLogsByIds(List<Long> logIds) {
        operateLogMapper.deleteOperateLogsByIds(logIds);
    }

}
