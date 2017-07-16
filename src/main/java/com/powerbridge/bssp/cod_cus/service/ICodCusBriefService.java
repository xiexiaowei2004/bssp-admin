package com.powerbridge.bssp.cod_cus.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusBrief;
import com.powerbridge.bssp.common.Bean.PullDown;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ICodCusBriefService
 * 类描述：CodCusBrief 表业务逻辑层接口
 * 创建人：haihuihuang
 * 创建时间：2017年4月27日 下午10:12:17
 */
public interface ICodCusBriefService extends IService<CodCusBrief> {
    List<PullDown> selectBriefByList();
    /*List<CodCusBrief> selectRedisDropDown();*/

}
