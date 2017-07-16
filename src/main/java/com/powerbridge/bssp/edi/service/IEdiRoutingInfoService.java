package com.powerbridge.bssp.edi.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.sas.entity.SasDragcarBsc;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： IEdiRoutingInfoService
 * 类描述： 路由配置表业务逻辑接口层
 * 创建人： LC
 * 创建时间： 2017年5月16日
 *@version
 */
public interface IEdiRoutingInfoService extends IService<EdiRoutingInfo> {
    /**
     * 查询/分页
     *
     * @param page         分页
     * @param ediRoutingInfo 数据
     * @return Page<ediRoutingInfo>
     */
    Page<EdiRoutingInfo> selectEdiRoutingInfoList(Page<EdiRoutingInfo> page, EdiRoutingInfo ediRoutingInfo);


}
