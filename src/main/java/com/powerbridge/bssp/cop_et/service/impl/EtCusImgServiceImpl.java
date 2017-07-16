package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtCusImgMapper;
import com.powerbridge.bssp.cop_et.entity.EtCusImg;
import com.powerbridge.bssp.cop_et.service.IEtCusImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库正式表料件 服务实现类
 *
 * @author willChen
 * @since 2017-06-01
 */
@Service("etCusImgService")
public class EtCusImgServiceImpl extends BaseServiceImpl<EtCusImgMapper, EtCusImg> implements IEtCusImgService {

    @Autowired
    private EtCusImgMapper etCusImgMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etCusImg
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtCusImg> selectEtCusImgList(Page<EtCusImg> page, EtCusImg etCusImg) throws Exception {
        page.setRecords(etCusImgMapper.selectEtCusImgList(page, etCusImg));
        return page;
    }
}
