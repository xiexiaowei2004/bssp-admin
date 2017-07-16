package com.powerbridge.bssp.common.util.json.parser.deserializer;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicIntegerArray;

import com.powerbridge.bssp.common.util.json.JSONArray;
import com.powerbridge.bssp.common.util.json.parser.DefaultJSONParser;
import com.powerbridge.bssp.common.util.json.parser.JSONToken;

public class AtomicIntegerArrayDeserializer implements ObjectDeserializer {

    public final static AtomicIntegerArrayDeserializer instance = new AtomicIntegerArrayDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        if (parser.getLexer().token() == JSONToken.NULL) {
            parser.getLexer().nextToken(JSONToken.COMMA);
            return null;
        }

        JSONArray array = new JSONArray();
        parser.parseArray(array);

        AtomicIntegerArray atomicArray = new AtomicIntegerArray(array.size());
        for (int i = 0; i < array.size(); ++i) {
            atomicArray.set(i, array.getInteger(i));
        }

        return (T) atomicArray;
    }

    public int getFastMatchToken() {
        return JSONToken.LBRACKET;
    }
}
