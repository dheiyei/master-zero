package com.koke.service.inter.sys.dict;

import com.koke.model.dto.DictItemDTO;
import com.koke.model.entity.sys.dict.DictItem;

import java.util.List;

public interface DictItemService {

    List<DictItem> selectDictItems(DictItemDTO dictItem);

    DictItem selectDictItemById(Long dictItemId);

    List<DictItem> selectDictItemsByDictType(String dictType);

    void createDictItem(DictItem dictItem);

    void updateDictItem(DictItem dictItem);

    void deleteDictItemsByIds(List<Long> dictItemIds);

}
