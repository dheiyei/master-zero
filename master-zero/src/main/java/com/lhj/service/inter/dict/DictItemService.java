package com.lhj.service.inter.dict;

import com.lhj.model.dto.DictItemDTO;
import com.lhj.model.entity.dict.DictItem;

import java.util.List;

public interface DictItemService {

    List<DictItem> selectDictItems(DictItemDTO dictItem);

    DictItem selectDictItemById(Long dictItemId);

    List<DictItem> selectDictItemsByDictType(String dictType);

    void createDictItem(DictItem dictItem);

    void updateDictItem(DictItem dictItem);

    void deleteDictItemsByIds(List<Long> dictItemIds);

}
