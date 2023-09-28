package com.koke.service.impl.sys.dict;

import com.koke.constant.CommonConstants;
import com.koke.constant.Constants;
import com.koke.mapper.sys.dict.DictItemMapper;
import com.koke.mapper.sys.dict.DictMapper;
import com.koke.model.entity.sys.dict.Dict;
import com.koke.service.inter.sys.dict.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DictServiceImpl implements DictService {

    private final DictMapper dictMapper;
    private final DictItemMapper dictItemMapper;

    @Override
    public List<Dict> selectDicts(Dict dict) {
        return dictMapper.selectDicts(dict);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_DICT, key = "#dictId")
    @Override
    public Dict selectDictById(Long dictId) {
        return dictMapper.selectDictById(dictId);
    }

    @Transactional
    @Override
    public void createDict(Dict dict) {
        dictMapper.createDict(dict);
    }

    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_DICT, key = "#dict.dictId")
    @Transactional
    @Override
    public void updateDict(Dict dict) {
        dictMapper.updateDict(dict);
    }

    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_DICT, allEntries = true)
    @Transactional
    @Override
    public void deleteDictsByIds(List<Long> dictIds) {
        for (Long dictId : dictIds) {
            Integer count = dictItemMapper.selectCountByDictId(dictId);
            Assert.isTrue(count <= 0, Constants.DICT_ITEM_EXISTED);
        }
        dictMapper.deleteDictsByIds(dictIds);
    }

}
