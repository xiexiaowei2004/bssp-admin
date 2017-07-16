package com.powerbridge.bssp.dcr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;

import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DcrChgoffBscMapper
 * 类描述：DcrChgoffBsc 账册报核基础表 Mapper 接口
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 下午17:10:17
 */
public interface DcrChgoffBscMapper extends BaseMapper<DcrChgoffBsc> {
    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffBsc 加工贸易账册核销数据
     * @return List<CopEnt>
     */
    List<DcrChgoffBsc> selectByList(Page<DcrChgoffBsc> page, DcrChgoffBsc dcrChgoffBsc);
    
    /**
	 * 
	 * @Description 拼接业务单据sql
	 * @param str
	 * @return
	 */
    public DcrChgoffBsc getBusinessData(@Param(value="str") String str);
}