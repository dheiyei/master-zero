package com.koke.controller.sys;

import com.koke.aspect.annotation.EnablePage;
import com.koke.model.dto.OperateLogDTO;
import com.koke.model.entity.common.ResultInfo;
import com.koke.model.entity.sys.OperateLog;
import com.koke.service.inter.sys.OperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 日志管理控制层
 * @author koke
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/operateLog")
@Api(tags = "日志管理")
public class OperateLogController {

    private final OperateLogService operateLogService;

    @EnablePage
    @GetMapping
    @ApiOperation(value = "获取日志列表")
    public ResultInfo<List<OperateLog>> getOperateLogs(OperateLogDTO operateLogDTO) {
        List<OperateLog> operateLogs = operateLogService.selectOperateLogs(operateLogDTO);
        return ResultInfo.success(operateLogs);
    }

    @GetMapping("/{logId}")
    @ApiOperation(value = "根据id获取日志信息")
    public ResultInfo<OperateLog> getOperateLogById(@PathVariable("logId") Long logId) {
        OperateLog operateLog = operateLogService.selectOperateLogById(logId);
        return ResultInfo.success(operateLog);
    }

    @DeleteMapping("/{logIds}")
    @ApiOperation(value = "根据id列表删除日志信息")
    public ResultInfo<Void> deleteOperateLogsByIds(@PathVariable("logIds") List<Long> logIds) {
        operateLogService.deleteOperateLogsByIds(logIds);
        return ResultInfo.success();
    }

}
