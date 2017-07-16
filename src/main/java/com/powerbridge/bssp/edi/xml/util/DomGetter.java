package com.powerbridge.bssp.edi.xml.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * Created by wuxia on 2016/8/27 0027.
 */
public class DomGetter {

    /**
     * 从文件获取DOM
     * @param file
     * @return
     */
    public static Document getDomFromFile(File file) {
        if (!file.exists()) {
            return null;
        }
        try {
            /**
             * 当然SAXReader 不仅仅能读file
             */
            return new SAXReader().read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从URL获取DOM
     * @param url
     * @return
     */
    public static Document getDomFromURL(URL url) {
        if (null == url) {
            return null;
        }
        try {
            return new SAXReader().read(url);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从imputStream中获取文件
     * @param inputStream
     * @return
     */
    public static Document getDomFromInputStream(InputStream inputStream) {
        if (null == inputStream) {
            return null;
        }
        try {
            return new SAXReader().read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从reader中获取文件
     * @param reader
     * @return
     */
    public static Document getDomFromReader(Reader reader) {
        if (null == reader) {
            return null;
        }
        try {
            return new SAXReader().read(reader);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml格式的字符串总获取DOM
     * @param xmlString
     * @return
     */
    public static Document getDomFromXMLStr(String xmlString) {
        if (null == xmlString || "" == xmlString) {
            return null;
        }
        try {
            return DocumentHelper.parseText(xmlString);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


}
