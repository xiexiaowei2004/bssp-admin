package com.powerbridge.bssp.cod_cus.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.BillGenerater;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqList;
/**
 * 
 * @ClassName IBillGeneraterService
 * @Description 单据生成器
 * @author Simon.xie
 * @Date 2017年5月11日 上午11:22:30
 * @version 1.0.0
 */
public interface IBillGeneraterService extends IService<BillGenerater>{
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
	
	
    /*List<BillGenerater> selectRedisDropDown();*/

}
