package com.powerbridge.bssp.edi.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;

import java.util.List;
import java.util.Map;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ICodStdAreaCodeService  
* 类描述：CodStdAreaCode表业务逻辑层接口   
* 创建人：xwq
* 创建时间：2017年5月10日19:21:36
* @version
 */
public interface IEdiXmlMapDbService extends IService<EdiXmlMapDb> {

	/**
	 * 
	 * @Description 查询表对应字段
     * @param ediXmlMapDb
     * @return
	 */
    public String getFieldParameter(EdiXmlMapDb ediXmlMapDb);

    /**
	 * 
	 * @Description 拼接业务单据sql
	 * @param str
	 * @return
	 */
	public EtArcrpBsc getBusinessData(String str);
	
	/**
	 * 
	 * @Description 根据单据类型，业务类型，表名，字段名查询对应报文字段名
	 * @param paramMap
	 * @return
	 */
    public List<EdiXmlMapDb> getEdiXmlMapDb(Map paramMap);

}
  
    