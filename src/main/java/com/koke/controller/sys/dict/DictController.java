package com.koke.controller.sys.dict;

import com.koke.aspect.annotation.EnablePage;
import com.koke.model.entity.common.ResultInfo;
import com.koke.model.entity.sys.dict.Dict;
import com.koke.service.inter.sys.dict.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 字典管理控制层
 * @author koke
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/dict")
@Api(tags = "字典管理")
public class DictController {

    private final DictService dictService;

    @ApiOperation(value = "获取字典列表")
    @EnablePage
    @GetMapping
    public ResultInfo<List<Dict>> getDicts(Dict dict) {
        List<Dict> dicts = dictService.selectDicts(dict);
        return ResultInfo.success(dicts);
    }

    @ApiOperation(value = "根据id获取字典数据")
    @GetMapping("/{dictId}")
    public ResultInfo<Dict> getDictById(@PathVariable("dictId") Long dictId) {
        Dict dict = dictService.selectDictById(dictId);
        return ResultInfo.success(dict);
    }

    @ApiOperation(value = "新增字典数据")
    @PostMapping
    public ResultInfo<Void> createDict(@Valid @RequestBody Dict dict) {
        dictService.createDict(dict);
        return ResultInfo.success();
    }

    @ApiOperation(value = "更新字典数据")
    @PutMapping
    public ResultInfo<Void> updateDict(@Valid @RequestBody Dict dict) {
        dictService.updateDict(dict);
        return ResultInfo.success();
    }

    @ApiOperation(value = "根据id列表删除字典数据")
    @DeleteMapping("/{dictIds}")
    public ResultInfo<Void> deleteDictsByIds(@PathVariable("dictIds") List<Long> dictIds) {
        dictService.deleteDictsByIds(dictIds);
        return ResultInfo.success();
    }

}
