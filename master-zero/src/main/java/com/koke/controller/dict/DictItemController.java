package com.koke.controller.dict;

import com.koke.annotation.EnablePage;
import com.koke.model.ResultInfo;
import com.koke.model.dto.DictItemDTO;
import com.koke.model.entity.dict.DictItem;
import com.koke.service.inter.dict.DictItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/dictItem")
public class DictItemController {

    private final DictItemService dictItemService;

    @EnablePage
    @GetMapping
    public ResultInfo<List<DictItem>> getDictItems(DictItemDTO dictItem) {
        List<DictItem> dictItems = dictItemService.selectDictItems(dictItem);
        return ResultInfo.success(dictItems);
    }

    @GetMapping("/{dictItemId}")
    public ResultInfo<DictItem> getDictItemById(@PathVariable("dictItemId") Long dictItemId) {
        DictItem dictItem = dictItemService.selectDictItemById(dictItemId);
        return ResultInfo.success(dictItem);
    }

    @GetMapping("/dictType")
        public ResultInfo<List<DictItem>> getDictItemsByDictType(@RequestParam("dictType") String dictType) {
        List<DictItem> dictItems = dictItemService.selectDictItemsByDictType(dictType);
        return ResultInfo.success(dictItems);
    }

    @PostMapping
    public ResultInfo<Void> createDictItem(@Valid @RequestBody DictItem dictItem) {
        dictItemService.createDictItem(dictItem);
        return ResultInfo.success();
    }

    @PutMapping
    public ResultInfo<Void> updateDictItem(@Valid @RequestBody DictItem dictItem) {
        dictItemService.updateDictItem(dictItem);
        return ResultInfo.success();
    }

    @DeleteMapping("/{dictItemIds}")
    public ResultInfo<Void> deleteDictItemsByIds(@PathVariable("dictItemIds") List<Long> dictItemIds) {
        dictItemService.deleteDictItemsByIds(dictItemIds);
        return ResultInfo.success();
    }

}
