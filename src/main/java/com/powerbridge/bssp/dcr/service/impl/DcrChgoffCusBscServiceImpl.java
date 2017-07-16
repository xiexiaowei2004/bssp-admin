package com.powerbridge.bssp.dcr.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffCusBsc;
import com.powerbridge.bssp.dcr.dao.DcrChgoffCusBscMapper;
import com.powerbridge.bssp.dcr.service.IDcrChgoffCusBscService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Service("dcrChgoffCusBscService")
public class DcrChgoffCusBscServiceImpl extends BaseServiceImpl<DcrChgoffCusBscMapper, DcrChgoffCusBsc> implements IDcrChgoffCusBscService {
    @Autowired
    private DcrChgoffCusBscMapper dcrChgoffCusBscMapper;

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffCusBsc 加工贸易账册核销数据
     * @return Page<DcrChgoffCusBsc>
     */
    public Page<DcrChgoffCusBsc> selectByList(Page<DcrChgoffCusBsc> page, DcrChgoffCusBsc dcrChgoffCusBsc) {
        page.setRecords(dcrChgoffCusBscMapper.selectByList(page, dcrChgoffCusBsc));
        return page;
    }
}
