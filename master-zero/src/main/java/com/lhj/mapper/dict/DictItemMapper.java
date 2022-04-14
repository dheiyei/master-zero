package com.lhj.mapper.dict;

import com.lhj.model.dto.DictItemDTO;
import com.lhj.model.entity.dict.DictItem;

import java.util.List;

public interface DictItemMapper {

    List<DictItem> selectDictItems(DictItemDTO dictItem);

    DictItem selectDictItemById(Long dictItemId);

    List<DictItem> selectDictItemsByDictId(Long DictId);

    void createDictItem(DictItem dictItem);

    void updateDictItem(DictItem dictItem);

    void deleteDictItemsByIds(List<Long> dictItemIds);

    int selectCountByDictId(Long dictId);

}
