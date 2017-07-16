package com.powerbridge.bssp.cod_biz.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_biz.entity.CodBizRsk;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ICodBizRskService
 * 类描述：cod_biz_rsk_param 表业务逻辑层接口
 * 创建人：xuzuotao
 * 创建时间：2017年4月27日 下午10:12:17
 */
public interface ICodBizRskService extends IService<CodBizRsk> {
    /**
     * 查询业务参数/分页
     *
     * @param page 分页
     * @param codBizRsk
     * @return Page<CodBizRsk>
     */
    Page<CodBizRsk> selectByCodBizRsk(Page<CodBizRsk> page, CodBizRsk codBizRsk);
    
    List<CodBizRsk> selectRedisDropDown();
}
