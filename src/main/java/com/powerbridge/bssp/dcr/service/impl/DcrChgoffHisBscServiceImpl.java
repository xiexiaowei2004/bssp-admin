package com.powerbridge.bssp.dcr.service.impl;

import com.powerbridge.bssp.dcr.entity.DcrChgoffHisBsc;
import com.powerbridge.bssp.dcr.dao.DcrChgoffHisBscMapper;
import com.powerbridge.bssp.dcr.service.IDcrChgoffHisBscService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffHisBscService")
public class DcrChgoffHisBscServiceImpl extends BaseServiceImpl<DcrChgoffHisBscMapper, DcrChgoffHisBsc> implements IDcrChgoffHisBscService {

    @Autowired
    private DcrChgoffHisBscMapper dcrChgoffHisBscMapper;

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffHisBsc 加工贸易账册核销数据
     * @return Page<DcrChgoffCusBsc>
     */
    public Page<DcrChgoffHisBsc> selectByList(Page<DcrChgoffHisBsc> page, DcrChgoffHisBsc dcrChgoffHisBsc) {
        page.setRecords(dcrChgoffHisBscMapper.selectByList(page, dcrChgoffHisBsc));
        return page;
    }
}
