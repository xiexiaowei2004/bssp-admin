package com.powerbridge.bssp.common.util.json.serializer;

public interface PropertyPreFilter extends SerializeFilter {

    boolean apply(JSONSerializer serializer, Object source, String name);
}
