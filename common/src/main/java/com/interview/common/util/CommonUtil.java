package com.interview.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String mapObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            log.error("[EXCEPTION]: {}", ex);
        }
        return null;
    }

    public static <T> T mapJsonToObject(String jsonObject, Class<T> clazz) throws RuntimeException {
        try {
            T returnValue = objectMapper.readValue(jsonObject, clazz);
            return returnValue;
        } catch (Exception var3) {
            log.error("Map: {}", var3);
            throw new RuntimeException(var3);
        }
    }
}
