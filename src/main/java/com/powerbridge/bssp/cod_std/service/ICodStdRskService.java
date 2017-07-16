package com.powerbridge.bssp.cod_std.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdRsk;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ICodStdRskService
 * 类描述：cod_std_rsk_param 表业务逻辑层接口
 * 创建人：xuzuotao
 * 创建时间：2017年4月27日 下午10:12:17
 */
public interface ICodStdRskService extends IService<CodStdRsk> {
    /**
     * 查询业务参数/分页
     *
     * @param page 分页
     * @param codStdRsk
     * @return Page<CodStdRsk>
     */
    Page<CodStdRsk> selectByCodStdRsk(Page<CodStdRsk> page, CodStdRsk codStdRsk);
    
    List<CodStdRsk> selectRedisDropDown();
}
