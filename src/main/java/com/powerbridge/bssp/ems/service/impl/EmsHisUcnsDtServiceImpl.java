package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.EmsHisUcnsDtMapper;
import com.powerbridge.bssp.ems.entity.EmsHisUcnsDt;
import com.powerbridge.bssp.ems.service.IEmsHisUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisUcnsDtServiceImpl
 * 类描述：账册备案历史表损耗服务实现
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
@Service("emsHisUcnsDtService")
public class EmsHisUcnsDtServiceImpl extends BaseServiceImpl<EmsHisUcnsDtMapper, EmsHisUcnsDt> implements IEmsHisUcnsDtService {
    @Autowired
    EmsHisUcnsDtMapper emsHisUcnsDtMapper;
    /**
     * 查询账册备案正式表单损耗列表/分页
     * @param page 分页
     * @param emsHisUcnsDt
     * @return EmsHisUcnsDt
     */
    public Page<EmsHisUcnsDt> selectEmsHisUcnsDtList(Page<EmsHisUcnsDt> page, EmsHisUcnsDt emsHisUcnsDt){
        page.setRecords(emsHisUcnsDtMapper.selectEmsHisUcnsDtList(page, emsHisUcnsDt));
        return page;
    }
    /**
     * 根据主键查找单耗数据
     * @param uid
     * @return
     */
    public EmsHisUcnsDt selectByUid(String uid){
        EmsHisUcnsDt emsHisUcnsDt=emsHisUcnsDtMapper.selectByUid(uid);
        return emsHisUcnsDt;
    }
}
