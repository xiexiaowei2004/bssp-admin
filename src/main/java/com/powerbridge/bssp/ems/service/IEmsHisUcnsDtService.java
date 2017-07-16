package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsHisUcnsDt;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsHisUcnsDtService
 * 类描述：账册备案历史表单耗服务
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
public interface IEmsHisUcnsDtService extends IService<EmsHisUcnsDt> {
    /**
     * 查询账册备案正式表单损耗列表
     * @param page
     * @param EmsHisUcnsDt
     * @return EmsHisUcnsDt
     */
    Page<EmsHisUcnsDt> selectEmsHisUcnsDtList(Page<EmsHisUcnsDt> page, EmsHisUcnsDt EmsHisUcnsDt);
    /**
     * 根据主键查找单耗数据
     * @param uid
     * @return
     */
    EmsHisUcnsDt selectByUid(String uid);
}
