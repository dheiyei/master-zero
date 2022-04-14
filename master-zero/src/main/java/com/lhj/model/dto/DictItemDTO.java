package com.lhj.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictItemDTO {
    private static final long serialVersionUID = 7663338382628446934L;

    private String dictType;

    /**
     * 字典数据id
     */
    private Long dictItemId;

    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典数据
     */
    private String dictValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
