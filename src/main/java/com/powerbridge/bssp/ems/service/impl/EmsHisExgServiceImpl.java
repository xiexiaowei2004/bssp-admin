package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.EmsHisExgMapper;
import com.powerbridge.bssp.ems.entity.EmsHisExg;
import com.powerbridge.bssp.ems.service.IEmsHisExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisExgServiceImpl
 * 类描述：账册备案历史表成品服务实现
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
@Service("emsHisExgService")
public class EmsHisExgServiceImpl extends BaseServiceImpl<EmsHisExgMapper, EmsHisExg> implements IEmsHisExgService {

    @Autowired
    EmsHisExgMapper emsHisExgMapper;
    /**
     * 查询加工账册备案成品/分页
     *
     * @param page 分页
     * @param emsHisExg
     * @return Page<EmsHisExg>
     */
    @Override
    public Page<EmsHisExg> selectEmsHisExgList(Page<EmsHisExg> page, EmsHisExg emsHisExg){
        page.setRecords(emsHisExgMapper.selectEmsHisExgList(page, emsHisExg));
        return page;
    }
}
