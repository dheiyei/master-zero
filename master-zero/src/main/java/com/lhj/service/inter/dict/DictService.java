package com.lhj.service.inter.dict;

import com.lhj.model.entity.dict.Dict;

import java.util.List;

public interface DictService {

    List<Dict> selectDicts(Dict dict);

    Dict selectDictById(Long dictId);

    void createDict(Dict dict);

    void updateDict(Dict dict);

    void deleteDictsByIds(List<Long> dictIds);

}
