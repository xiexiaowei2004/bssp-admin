package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsHisAcmpFormDt;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsPutrecAcmpFormDtService
 * 类描述：账册备案正式表附件服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：willChen
 * 修改时间：2017/5/16 22:00
 */
public interface IEmsHisAcmpFormDtService extends IService<EmsHisAcmpFormDt> {
    /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsHisAcmpFormDt
     * @return List<EmsHisAcmpFormDt>
     */
    Page<EmsHisAcmpFormDt> selectEmsHisAcmpFormDtList(Page<EmsHisAcmpFormDt> page, EmsHisAcmpFormDt emsHisAcmpFormDt);
}
