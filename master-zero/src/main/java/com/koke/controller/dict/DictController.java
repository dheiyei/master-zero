package com.koke.controller.dict;

import com.koke.annotation.EnablePage;
import com.koke.model.ResultInfo;
import com.koke.model.entity.dict.Dict;
import com.koke.service.inter.dict.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/dict")
public class DictController {

    private final DictService dictService;

    @EnablePage
    @GetMapping
    public ResultInfo<List<Dict>> getDicts(Dict dict) {
        List<Dict> dicts = dictService.selectDicts(dict);
        return ResultInfo.success(dicts);
    }

    @GetMapping("/{dictId}")
    public ResultInfo<Dict> getDictById(@PathVariable("dictId") Long dictId) {
        Dict dict = dictService.selectDictById(dictId);
        return ResultInfo.success(dict);
    }

    @PostMapping
    public ResultInfo<Void> createDict(@Valid @RequestBody Dict dict) {
        dictService.createDict(dict);
        return ResultInfo.success();
    }

    @PutMapping
    public ResultInfo<Void> updateDict(@Valid @RequestBody Dict dict) {
        dictService.updateDict(dict);
        return ResultInfo.success();
    }

    @DeleteMapping("/{dictIds}")
    public ResultInfo<Void> deleteDictsByIds(@PathVariable("dictIds") List<Long> dictIds) {
        dictService.deleteDictsByIds(dictIds);
        return ResultInfo.success();
    }

}
