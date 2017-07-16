package com.powerbridge.bssp.edi.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("ediXmlMapDbMapper")
public interface EdiXmlMapDbMapper extends BaseMapper<EdiXmlMapDb> {

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
	public EtArcrpBsc getBusinessData(@Param(value="str") String str);
	
	/**
	 * 
	 * @Description 根据单据类型，业务类型，表名，字段名查询对应报文字段名
	 * @param paramMap
	 * @return
	 */
    public List<EdiXmlMapDb> getEdiXmlMapDb(Map paramMap);

}
  
    