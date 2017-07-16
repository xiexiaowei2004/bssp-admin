package com.powerbridge.bssp.common.util.json.parser.deserializer;

import java.lang.reflect.Type;

import com.powerbridge.bssp.common.util.TypeUtils;
import com.powerbridge.bssp.common.util.json.parser.DefaultJSONParser;
import com.powerbridge.bssp.common.util.json.parser.JSONToken;

public class CharacterDeserializer implements ObjectDeserializer {
    public final static CharacterDeserializer instance = new CharacterDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        Object value = parser.parse();

        if (value == null) {
            return null;
        }
        
        return (T) TypeUtils.castToChar(value);
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }
}
