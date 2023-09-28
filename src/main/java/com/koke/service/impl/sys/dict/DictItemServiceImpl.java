package com.koke.service.impl.sys.dict;

import com.koke.constant.CommonConstants;
import com.koke.mapper.sys.dict.DictItemMapper;
import com.koke.mapper.sys.dict.DictMapper;
import com.koke.model.dto.DictItemDTO;
import com.koke.model.entity.sys.dict.Dict;
import com.koke.model.entity.sys.dict.DictItem;
import com.koke.service.inter.sys.dict.DictItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DictItemServiceImpl implements DictItemService {

    private final DictItemMapper dictItemMapper;
    private final DictMapper dictMapper;

    @Override
    public List<DictItem> selectDictItems(DictItemDTO dictItem) {
        if (dictItem.getDictType() != null){
            Dict dict = dictMapper.selectDictByType(dictItem.getDictType());
            dictItem.setDictId(dict.getDictId());
        }
        return dictItemMapper.selectDictItems(dictItem);
    }

    @Override
    public DictItem selectDictItemById(Long dictItemId) {
        return dictItemMapper.selectDictItemById(dictItemId);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_DICT_ITEM, key = "#dictType")
    @Override
    public List<DictItem> selectDictItemsByDictType(String dictType) {
        Dict dict = dictMapper.selectDictByType(dictType);
        if (dict == null) {
            return Collections.emptyList();
        }
        return dictItemMapper.selectDictItemsByDictId(dict.getDictId());
    }

    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_DICT_ITEM, allEntries = true)
    @Transactional
    @Override
    public void createDictItem(DictItem dictItem) {
        dictItemMapper.createDictItem(dictItem);
    }

    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_DICT_ITEM, allEntries = true)
    @Transactional
    @Override
    public void updateDictItem(DictItem dictItem) {
        dictItemMapper.updateDictItem(dictItem);
    }

    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_DICT_ITEM, allEntries = true)
    @Transactional
    @Override
    public void deleteDictItemsByIds(List<Long> dictItemIds) {
        dictItemMapper.deleteDictItemsByIds(dictItemIds);
    }

}
