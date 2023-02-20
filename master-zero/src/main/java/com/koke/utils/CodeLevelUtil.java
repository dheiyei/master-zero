package com.koke.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 代码层级工具类
 * 例：
 * 081
 * 081000
 * 081001
 * 081001001251
 * 081001001251251
 * 081001001251291
 */
public class CodeLevelUtil {

    //todo:单位代码可能最终有个顶级单位（099）
    /**
     * 获取代码层级键值对
     *
     * @param codes
     * @return
     */
    public static Map<String, String> getCodeMapWithLevel(List<String> codes) {
        //排序
        Collections.sort(codes, (o1, o2) -> o2.length() - o1.length() == 0 ? o2.compareTo(o1) : o2.length() - o1.length());
        //添加子级代码
        Map<String, String> codeMap = new TreeMap<>((o1, o2) -> o1.length() - o2.length() == 0 ? o1.compareTo(o2) : o1.length() - o2.length());
        for (String code : codes) {
            for (String parentCode : codes) {
                if (code.startsWith(parentCode) && code.length() != parentCode.length()) {
                    codeMap.put(code, parentCode);
                    break;
                }
            }
        }
        //添加顶级代码
        for (String code : codes) {
            String parentCode = codeMap.get(code);
            if (parentCode == null) {
                codeMap.put(code, null);
            }
        }
        return codeMap;
    }

}
