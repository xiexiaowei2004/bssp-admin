package com.powerbridge.bssp.cop.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cop.entity.CopEnt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CopEntMapper
 * 类描述：CopEnt 表数据访问层接口
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 下午10:54:17
 */
public interface CopEntMapper extends BaseMapper<CopEnt> {

    /**
     * 查询企业备案/分页
     *
     * @param page   分页
     * @param copEnt 企业备案数据
     * @return List<CopEnt>
     */
    List<CopEnt> selectByCopEnt(Page<CopEnt> page, CopEnt copEnt);

    List<CopEnt> selectByTradeCode(@Param("tradeCode") String tradeCode);
}