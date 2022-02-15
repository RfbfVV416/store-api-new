package org.store_api_new.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.opentest4j.TestAbortedException;
import java.util.List;

public class MockMvcUtil {
    public static <T> List<T> jsonToTypeList(String str, Class<T> type) {
        try{
            return new ObjectMapper().readerForListOf(type).readValue(str);
        }
        catch (Exception e){
            throw new TestAbortedException("Test aborted because of serialization fail");
        }
    }
}
