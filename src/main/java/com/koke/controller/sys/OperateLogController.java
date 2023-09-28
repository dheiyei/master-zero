package com.koke.controller.sys;

import com.koke.aspect.annotation.EnablePage;
import com.koke.model.dto.OperateLogDTO;
import com.koke.model.entity.common.ResultInfo;
import com.koke.model.entity.sys.OperateLog;
import com.koke.service.inter.sys.OperateLogService;
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
