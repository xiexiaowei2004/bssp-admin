package com.powerbridge.bssp.cop.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cop.entity.CopBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CopBusinessMapper
 * 类描述：CopBusiness 表数据访问层接口
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 下午10:54:17
 */
public interface CopBusinessMapper extends BaseMapper<CopBusiness> {

    Integer insertBatch(List copBusinessByList);

}