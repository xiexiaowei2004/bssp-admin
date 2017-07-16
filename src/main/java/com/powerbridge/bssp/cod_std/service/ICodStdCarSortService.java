package com.powerbridge.bssp.cod_std.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdCarSort;


/**
 *
 * 项目名称：bssp Maven Webapp
 * 类名称：ICodCusBriefService
 * 类描述：CodCusBrief 表业务逻辑层接口
 * 创建人：haihuihuang
 * 创建时间：2017年4月27日 下午10:12:17
 * @version
 */
public interface ICodStdCarSortService extends IService<CodStdCarSort> {
/*    public List<CodStdCarSort> selectCarSortByList();*/
	List<CodStdCarSort> selectRedisDropDown();
}
