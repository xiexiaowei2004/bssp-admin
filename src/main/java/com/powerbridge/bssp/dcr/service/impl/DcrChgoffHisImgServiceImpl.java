package com.powerbridge.bssp.dcr.service.impl;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisImg;
import com.powerbridge.bssp.dcr.dao.DcrChgoffHisImgMapper;
import com.powerbridge.bssp.dcr.service.IDcrChgoffHisImgService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核料件历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffHisImgService")
public class DcrChgoffHisImgServiceImpl extends BaseServiceImpl<DcrChgoffHisImgMapper, DcrChgoffHisImg> implements IDcrChgoffHisImgService {
   
    @Autowired
    private DcrChgoffHisImgMapper dcrChgoffHisImgMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrChgoffHisImg
     * @return Page<DcrChgoffInvtLt>
     */
    public Page<DcrChgoffHisImg> selectByList(Page<DcrChgoffHisImg> page, DcrChgoffHisImg dcrChgoffHisImg) {
        page.setRecords(dcrChgoffHisImgMapper.selectByList(page, dcrChgoffHisImg));
        return page;
    }
}
