package com.powerbridge.bssp.common.util.json.serializer;

public interface NameFilter extends SerializeFilter {
    String process(Object source, String name, Object value);
}
