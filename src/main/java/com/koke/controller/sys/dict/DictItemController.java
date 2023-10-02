package com.koke.controller.sys.dict;

import com.koke.aspect.annotation.EnablePage;
import com.koke.model.dto.DictItemDTO;
import com.koke.model.entity.common.ResultInfo;
import com.koke.model.entity.sys.dict.DictItem;
import com.koke.service.inter.sys.dict.DictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 字典项管理控制层
 * @author koke
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/dictItem")
@Api(tags = "字典项管理")
public class DictItemController {

    private final DictItemService dictItemService;

    @ApiOperation(value = "获取字典项列表")
    @EnablePage
    @GetMapping
    public ResultInfo<List<DictItem>> getDictItems(DictItemDTO dictItem) {
        List<DictItem> dictItems = dictItemService.selectDictItems(dictItem);
        return ResultInfo.success(dictItems);
    }

    @ApiOperation(value = "根据id获取字典项数据")
    @GetMapping("/{dictItemId}")
    public ResultInfo<DictItem> getDictItemById(@PathVariable("dictItemId") Long dictItemId) {
        DictItem dictItem = dictItemService.selectDictItemById(dictItemId);
        return ResultInfo.success(dictItem);
    }

    @ApiOperation(value = "根据字典类型获取字典数据")
    @GetMapping("/dictType")
        public ResultInfo<List<DictItem>> getDictItemsByDictType(@RequestParam("dictType") String dictType) {
        List<DictItem> dictItems = dictItemService.selectDictItemsByDictType(dictType);
        return ResultInfo.success(dictItems);
    }

    @ApiOperation(value = "新增字典项数据")
    @PostMapping
    public ResultInfo<Void> createDictItem(@Valid @RequestBody DictItem dictItem) {
        dictItemService.createDictItem(dictItem);
        return ResultInfo.success();
    }

    @ApiOperation(value = "更新字典项数据")
    @PutMapping
    public ResultInfo<Void> updateDictItem(@Valid @RequestBody DictItem dictItem) {
        dictItemService.updateDictItem(dictItem);
        return ResultInfo.success();
    }

    @ApiOperation(value = "根据id列表删除字典项数据")
    @DeleteMapping("/{dictItemIds}")
    public ResultInfo<Void> deleteDictItemsByIds(@PathVariable("dictItemIds") List<Long> dictItemIds) {
        dictItemService.deleteDictItemsByIds(dictItemIds);
        return ResultInfo.success();
    }

}
