package com.lhj.service.impl;

import com.lhj.mapper.OperateLogMapper;
import com.lhj.model.dto.OperateLogDTO;
import com.lhj.model.entity.OperateLog;
import com.lhj.service.inter.OperateLogService;
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
