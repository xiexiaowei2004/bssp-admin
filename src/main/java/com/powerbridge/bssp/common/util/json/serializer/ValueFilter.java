package com.powerbridge.bssp.common.util.json.serializer;

public interface ValueFilter extends SerializeFilter {

    Object process(Object source, String name, Object value);
}
