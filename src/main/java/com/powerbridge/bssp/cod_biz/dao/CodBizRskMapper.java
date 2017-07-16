package com.powerbridge.bssp.cod_biz.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cod_biz.entity.CodBizRsk;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodBizRskMapper
 * 类描述：cod_biz_rsk_param 表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:54:17
 */
public interface CodBizRskMapper extends BaseMapper<CodBizRsk> {
    /**
     * 查询风险参数/分页
     *
     * @param page 分页
     * @param codBizRsk
     * @return List<CodBizRsk>
     */
    List<CodBizRsk> selectByCodBizRsk(Page<CodBizRsk> page, CodBizRsk codBizRsk);
    
    List<CodBizRsk> selectRedisDropDown();

}