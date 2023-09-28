package com.koke.model.entity.sys.dict;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DictItem implements Serializable {

    private static final long serialVersionUID = 7663338382628446934L;

    /**
     * 字典数据id
     */
    private Long dictItemId;

    /**
     * 字典id
     */
    @NotNull(message = "请选择字典")
    private Long dictId;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    private String dictLabel;

    /**
     * 字典数据
     */
    @NotBlank(message = "字典数据不能为空")
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
