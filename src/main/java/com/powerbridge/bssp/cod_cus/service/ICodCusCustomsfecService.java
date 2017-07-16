package com.powerbridge.bssp.cod_cus.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsfec;

public interface ICodCusCustomsfecService extends IService<CodCusCustomsfec> {
	/**
	 * @Title: selectBriefByList
	 * @Description: 获取所有主管海关信息 
	 * @param: @return 
	 * @return: List<CodCusCustomsfec>
	 */
	List<CodCusCustomsfec> selectRedisDropDown();
}
