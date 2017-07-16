package com.powerbridge.bssp.edi.xml.util;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：XMLUtils
 * 类描述：菜单控制器
 * 创建人：danagao
 * 创建时间：2017/6/10 15:41
 *
 * @version 1.0
 */

import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


public class XMLUtils {


    /**
     *
     * @Description 获取xsd路径
     * @return
     */
    public static File getXsdUrl(){
        File cfgFile=null;
        try {
            cfgFile = ResourceUtils.getFile("classpath:XSD");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cfgFile;
    }


    /**
     * 根据xml文件路径取得document对象
     * @return
     * @throws DocumentException
     */
    public static Document getDocument(File file){

        if(file == null || !file.exists()){
            return null;
        }

        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 将xml类型的字符串转换成document对象进行操作
     *
     * @param xml
     * @return
     */
    public static Document parseStringToXml(String xml) {
        try {
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取xml文件
     *
     * @param filepath
     * @return
     */
    public static Document parseFileToXml(String filepath) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(filepath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 获取webapp 相对路径下的xml文件
     *
     * @param filename
     * @return
     */
    public static Document parseFIOToXml(String filename) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            InputStream in = XMLUtils.class.getResourceAsStream("xml/" + filename);
            doc = saxReader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }


    /**
     * 根据xpath 获取指定的元素
     *
     * @param xpath
     * @param doc
     * @return
     */
    public static Element getElementByXpath(String xpath, Document doc) {
        List<Element> parameterList = doc.selectNodes(xpath);
        if (parameterList != null && parameterList.size() > 0) {
            return (Element) parameterList.get(0);
        }
        return null;
    }

    /**
     * 获取指定id的xml元素(单个xml中id唯一的情况下使用)
     *
     * @param id
     * @param doc
     * @return
     */
    public static Element getElementById(String id, Document doc) {
        return getElementByXpath("//*[@id='" + id + "']", doc);
    }

    /**
     *
     * @Description 根据单据类型，业务类型结果集，过滤数据库名和字段名，取出对应db名字
     * @param ediXmlMapDbs 结果集
     * @param dbtable   数据库名
     * @param xmlNmae   字段名
     * @return
     */
    public static String getDbName(List<EdiXmlMapDb> ediXmlMapDbs, String dbtable, String xmlNmae) {
        String str = "";
        for (EdiXmlMapDb ediXmlMapDb : ediXmlMapDbs) {
            if (ediXmlMapDb.getDbTable().equals(dbtable) && ediXmlMapDb.getXmlName().equals(xmlNmae)) {
                str = ediXmlMapDb.getDbColumn();
                break;
            }
        }
        return str;
    }
}