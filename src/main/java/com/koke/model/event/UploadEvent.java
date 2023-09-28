package com.koke.model.event;

import com.koke.constant.UploadEnum;
import com.koke.model.dto.CacheUserDTO;
import lombok.Data;

import java.io.InputStream;

@Data
public class UploadEvent {

    private UploadEnum uploadEnum;

    private InputStream inputStream;

    private CacheUserDTO loginUser;
}