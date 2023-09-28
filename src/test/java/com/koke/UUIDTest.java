package com.koke;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

public class UUIDTest {

    @Test
    void geUUID() {
        String s = IdUtil.fastSimpleUUID();
        System.out.println(s);
    }
}
