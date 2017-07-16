package com.powerbridge.bssp.cod_std.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdEnttype;

/**
 * 项目名称：bssp-admin
 * 类名称：ICodStdEnttypeService
 * 类描述：CodStdEnttype 表业务逻辑层接口
 * 创建人：willChen
 * 创建时间：2017/5/10 20:01
 * 修改人：willChen
 * 修改时间：2017/5/10 20:01
 */
public interface ICodStdEnttypeService extends IService<CodStdEnttype>{
	
	List<CodStdEnttype> selectRedisDropDown();
}
