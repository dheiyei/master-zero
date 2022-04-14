package com.lhj.controller;

import com.lhj.annotation.EnablePage;
import com.lhj.model.ResultInfo;
import com.lhj.model.dto.OperateLogDTO;
import com.lhj.model.entity.OperateLog;
import com.lhj.service.inter.OperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/operateLog")
public class OperateLogController {

    private final OperateLogService operateLogService;

    @EnablePage
    @GetMapping
//    @PreAuthorize("hasAuthority('admin:operatelog:read')")
    public ResultInfo<List<OperateLog>> getOperateLogs(OperateLogDTO operateLogDTO) {
        List<OperateLog> operateLogs = operateLogService.selectOperateLogs(operateLogDTO);
        return ResultInfo.success(operateLogs);
    }

    @GetMapping("/{logId}")
//    @PreAuthorize("hasAuthority('admin:operatelog:read')")
    public ResultInfo<OperateLog> getOperateLogById(@PathVariable("logId") Long logId) {
        OperateLog operateLog = operateLogService.selectOperateLogById(logId);
        return ResultInfo.success(operateLog);
    }

    @DeleteMapping("/{logIds}")
//    @PreAuthorize("hasAuthority('admin:operatelog:delete')")
    public ResultInfo<Void> deleteOperateLogsByIds(@PathVariable("logIds") List<Long> logIds) {
        operateLogService.deleteOperateLogsByIds(logIds);
        return ResultInfo.success();
    }

}
