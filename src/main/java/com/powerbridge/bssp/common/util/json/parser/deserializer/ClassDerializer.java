package com.powerbridge.bssp.common.util.json.parser.deserializer;

import java.lang.reflect.Type;

import com.powerbridge.bssp.common.util.TypeUtils;
import com.powerbridge.bssp.common.util.json.JSONException;
import com.powerbridge.bssp.common.util.json.parser.DefaultJSONParser;
import com.powerbridge.bssp.common.util.json.parser.JSONLexer;
import com.powerbridge.bssp.common.util.json.parser.JSONToken;

public class ClassDerializer implements ObjectDeserializer {

    public final static ClassDerializer instance = new ClassDerializer();

    public ClassDerializer(){
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.getLexer();
        
        if (lexer.token() == JSONToken.NULL) {
            lexer.nextToken();
            return null;
        }
        
        if (lexer.token() != JSONToken.LITERAL_STRING) {
            throw new JSONException("expect className");
        }
        String className = lexer.stringVal();
        lexer.nextToken(JSONToken.COMMA);

        return (T) TypeUtils.loadClass(className);
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }

}
