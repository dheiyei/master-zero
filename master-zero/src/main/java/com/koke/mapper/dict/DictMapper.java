package com.koke.mapper.dict;

import com.koke.model.entity.dict.Dict;

import java.util.List;

public interface DictMapper {

    List<Dict> selectDicts(Dict dict);

    Dict selectDictById(Long dictId);

    Dict selectDictByType(String dictType);

    Dict selectDictByName(String name);

    void createDict(Dict dict);

    void updateDict(Dict dict);

    void deleteDictsByIds(List<Long> dictIds);

}
