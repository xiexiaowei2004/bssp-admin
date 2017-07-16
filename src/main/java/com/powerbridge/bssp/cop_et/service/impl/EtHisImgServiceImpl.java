package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop_et.dao.EtHisImgMapper;
import com.powerbridge.bssp.cop_et.entity.EtHisImg;
import com.powerbridge.bssp.cop_et.service.IEtHisImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联网企业档案库历史表料件 服务实现类
 *
 * @author willChen
 * @since 2017-07-03 10:13:08
 */
@Service("etHisImgService")
public class EtHisImgServiceImpl extends BaseServiceImpl<EtHisImgMapper, EtHisImg> implements IEtHisImgService {

    @Autowired
    private EtHisImgMapper etHisImgMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etHisImg
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtHisImg> selectEtHisImgList(Page<EtHisImg> page, EtHisImg etHisImg) throws Exception {
        page.setRecords(etHisImgMapper.selectEtHisImgList(page, etHisImg));
        return page;
    }
}
