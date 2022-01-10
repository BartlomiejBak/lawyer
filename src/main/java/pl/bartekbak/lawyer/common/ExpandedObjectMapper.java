package pl.bartekbak.lawyer.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ExpandedObjectMapper extends ObjectMapper {

    public ExpandedObjectMapper() {
        this.registerModule(new JavaTimeModule());
    }
}
