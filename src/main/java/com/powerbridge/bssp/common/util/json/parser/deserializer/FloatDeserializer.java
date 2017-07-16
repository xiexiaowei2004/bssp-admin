package com.powerbridge.bssp.common.util.json.parser.deserializer;

import java.lang.reflect.Type;

import com.powerbridge.bssp.common.util.TypeUtils;
import com.powerbridge.bssp.common.util.json.parser.DefaultJSONParser;
import com.powerbridge.bssp.common.util.json.parser.JSONLexer;
import com.powerbridge.bssp.common.util.json.parser.JSONToken;

public class FloatDeserializer implements ObjectDeserializer {

    public final static FloatDeserializer instance = new FloatDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        return (T) deserialze(parser);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialze(DefaultJSONParser parser) {
        final JSONLexer lexer = parser.getLexer();
        if (lexer.token() == JSONToken.LITERAL_INT) {
            String val = lexer.numberString();
            lexer.nextToken(JSONToken.COMMA);
            return (T) Float.valueOf(Float.parseFloat(val));
        }

        if (lexer.token() == JSONToken.LITERAL_FLOAT) {
            float val = lexer.floatValue();
            lexer.nextToken(JSONToken.COMMA);
            return (T) Float.valueOf(val);
        }

        Object value = parser.parse();

        if (value == null) {
            return null;
        }

        return (T) TypeUtils.castToFloat(value);
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
