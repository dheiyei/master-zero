package com.koke.service.inter.sys.dict;

import com.koke.model.entity.sys.dict.Dict;

import java.util.List;

public interface DictService {

    List<Dict> selectDicts(Dict dict);

    Dict selectDictById(Long dictId);

    void createDict(Dict dict);

    void updateDict(Dict dict);

    void deleteDictsByIds(List<Long> dictIds);

}
