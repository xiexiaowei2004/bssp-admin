package com.powerbridge.bssp.common.util.json.parser.deserializer;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

import com.powerbridge.bssp.common.util.TypeUtils;
import com.powerbridge.bssp.common.util.json.parser.DefaultJSONParser;
import com.powerbridge.bssp.common.util.json.parser.JSONLexer;
import com.powerbridge.bssp.common.util.json.parser.JSONToken;

public class LongDeserializer implements ObjectDeserializer {

    public final static LongDeserializer instance = new LongDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        final JSONLexer lexer = parser.getLexer();

        Long longObject;
        if (lexer.token() == JSONToken.LITERAL_INT) {
            long longValue = lexer.longValue();
            lexer.nextToken(JSONToken.COMMA);
            longObject = Long.valueOf(longValue);
        } else {

            Object value = parser.parse();

            if (value == null) {
                return null;
            }

            longObject = TypeUtils.castToLong(value);
        }
        
        if (null == longObject) {
			return null;
		}
        
        if (clazz == AtomicLong.class) {
            return (T) new AtomicLong(longObject.longValue());
        }
        
        return (T) longObject;
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
