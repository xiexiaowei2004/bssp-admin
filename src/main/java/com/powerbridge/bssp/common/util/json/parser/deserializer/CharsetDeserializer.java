package com.powerbridge.bssp.common.util.json.parser.deserializer;

import java.lang.reflect.Type;
import java.nio.charset.Charset;

import com.powerbridge.bssp.common.util.json.parser.DefaultJSONParser;
import com.powerbridge.bssp.common.util.json.parser.JSONToken;

public class CharsetDeserializer implements ObjectDeserializer {
    public final static CharsetDeserializer instance = new CharsetDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        Object value = parser.parse();

        if (value == null) {
            return null;
        }
        
        String charset = (String) value;
        
        return (T) Charset.forName(charset);
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }
}
