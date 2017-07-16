package com.powerbridge.bssp.dcr.service.impl;

import com.powerbridge.bssp.dcr.entity.DcrChgoffCusImg;
import com.powerbridge.bssp.dcr.dao.DcrChgoffCusImgMapper;
import com.powerbridge.bssp.dcr.service.IDcrChgoffCusImgService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核料件正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffCusImgService")
public class DcrChgoffCusImgServiceImpl extends BaseServiceImpl<DcrChgoffCusImgMapper, DcrChgoffCusImg> implements IDcrChgoffCusImgService {

    @Autowired
    private DcrChgoffCusImgMapper dcrChgoffCusImgMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffInvtLt>
     */
    public Page<DcrChgoffCusImg> selectByList(Page<DcrChgoffCusImg> page, String seqNo) {
        page.setRecords(dcrChgoffCusImgMapper.selectByList(page, seqNo));
        return page;
    }
}
