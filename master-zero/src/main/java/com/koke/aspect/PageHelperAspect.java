package com.koke.aspect;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koke.annotation.EnablePage;
import com.koke.model.ResultInfo;
import com.koke.utils.WebUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
public class PageHelperAspect {

    private final static String[] KEYWORDS = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop", "sleep"};

    /**
     * 仅支持字母、数字、下划线、空格、逗号、小数点
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    @Pointcut("@annotation(com.koke.annotation.EnablePage)")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(enablePage)")
    public Object around(ProceedingJoinPoint joinPoint, EnablePage enablePage) throws Throwable {
        HttpServletRequest request = WebUtil.getHttpServletRequest();
        String pageNumStr = request.getParameter("pageNum");
        String pageSizeStr = request.getParameter("pageSize");
        String[] ascs = request.getParameterValues("ascs");
        String[] descs = request.getParameterValues("descs");
        int pageNum = parseInt(pageNumStr);
        int pageSize = parseInt(pageSizeStr);
        Page<Object> pageResult = null;
        if (pageNum != 0 && pageSize != 0) {
            pageResult = PageHelper.startPage(pageNum, pageSize);
            String orderBy = getOrderBy(ascs, descs);
            pageResult.setOrderBy(orderBy);
        }
        //设置总记录数
        Object proceed = joinPoint.proceed();
        if (proceed instanceof ResultInfo) {
            Object data = ((ResultInfo) proceed).getData();
            if (data instanceof List) {
                if (pageResult == null) {
                    PageInfo pageInfo = new PageInfo((List) data);
                    ((ResultInfo) proceed).setTotal(pageInfo.getTotal());
                } else {
                    ((ResultInfo) proceed).setTotal(pageResult.getTotal());
                }
            }
        }
        return proceed;
    }

    private int parseInt(String s) {
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            i = 0;
        }
        return i;
    }

    private String getOrderBy(String[] ascs, String[] descs) {
        StringBuffer sb = new StringBuffer();
        if (ArrayUtils.isNotEmpty(ascs)) {
            for (String asc : ascs) {
                if (!checkSqlInject(asc)) {
                    return null;
                }
                sb.append(asc)
                        .append(" ")
                        .append("ASC")
                        .append(",")
                        .append(" ");
            }
        }
        if (ArrayUtils.isNotEmpty(descs)) {
            for (String desc : descs) {
                if (!checkSqlInject(desc)) {
                    return null;
                }
                sb.append(desc)
                        .append(" ")
                        .append("DESC")
                        .append(",")
                        .append(" ");
            }
        }
        if (StringUtils.isBlank(sb)) {
            return null;
        }
        return sb.deleteCharAt(sb.length() - 2).toString();
    }

    /**
     * 校验字段防止 sql 注入
     *
     * @param column
     * @return
     */
    private boolean checkSqlInject(String column) {
        for (String keyword : KEYWORDS) {
            if (StringUtils.containsIgnoreCase(column, keyword)) {
                return false;
            }
        }
        return column.matches(SQL_PATTERN);
    }

}
