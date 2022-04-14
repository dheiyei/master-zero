package com.lhj.model.event;

import com.lhj.constant.UploadEnum;
import com.lhj.model.dto.CacheUserDTO;
import lombok.Data;

import java.io.InputStream;

@Data
public class UploadEvent {

    private UploadEnum uploadEnum;

    private InputStream inputStream;

    private CacheUserDTO loginUser;
}
