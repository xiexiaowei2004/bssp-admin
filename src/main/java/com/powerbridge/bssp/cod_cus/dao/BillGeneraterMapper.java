package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.BillGenerater;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqList;

public interface BillGeneraterMapper extends BaseMapper<BillGenerater>{
	/**
	 * 
	 * @Description 查询最大的序列号数
	 * @param customscode 关区代码
     * @param billCode  单据代码 
	 * @param year  年月份
	 * @return
	 */
	BillGenerater selectNumber(Map paramMap);
	
	/**
	 * 
	 * @Description 查询最大的序列号数
	 * @param customscode 关区代码
     * @param billCode  单据代码 
	 * @param year  年月份
	 * @return
	 */
	BillGenerater selectNumberDate(Map paramMap);
	
	/**
	 * @Description 查询单据参数字段
	 * @param paramMap
	 * @return
	 */
	public List<EdiSeqList> selectEdiSeqList(EdiSeqInfo ediSeqInfo);
	
	
   /* List<BillGenerater> selectRedisDropDown();*/

}