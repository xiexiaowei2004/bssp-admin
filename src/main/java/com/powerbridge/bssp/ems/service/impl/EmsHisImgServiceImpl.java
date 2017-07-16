package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.EmsHisImgMapper;
import com.powerbridge.bssp.ems.entity.EmsHisImg;
import com.powerbridge.bssp.ems.service.IEmsHisImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisImgServiceImpl
 * 类描述：账册备案正式表料件服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
@Service("emsHisImgService")
public class EmsHisImgServiceImpl extends BaseServiceImpl<EmsHisImgMapper, EmsHisImg> implements IEmsHisImgService {

    @Autowired
    EmsHisImgMapper emsHisImgMapper;
    /**
     * 查询加工账册备案料件/分页
     *
     * @param page 分页
     * @param emsHisImg
     * @return Page<EmsHisImg>
     */
    @Override
    public Page<EmsHisImg> selectEmsHisImgList(Page<EmsHisImg> page, EmsHisImg emsHisImg){
        page.setRecords(emsHisImgMapper.selectEmsHisImgList(page, emsHisImg));
        return page;
    }
}
