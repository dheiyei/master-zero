package com.koke.config.filter.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.exception.CustomException;
import com.koke.model.entity.common.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@RequiredArgsConstructor
@Component
public class FilterExceptionHandler {

    private final ObjectMapper objectMapper;

    public void writeError(HttpServletResponse response, CustomException exception) {
        response.setStatus(SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(ResultInfo.error(exception.getCode(), exception.getMessage())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
