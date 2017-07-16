package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import com.powerbridge.bssp.sas.dao.SasDragcarBscMapper;
import com.powerbridge.bssp.sas.service.ISasDragcarBscService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 扡卡备案表 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-21
 */
@Service("sasDragcarBscService")
public class SasDragcarBscServiceImpl extends BaseServiceImpl<SasDragcarBscMapper, SasDragcarBsc> implements ISasDragcarBscService {

    @Autowired
    private SasDragcarBscMapper sasDragcarBscMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @return Page<sasDragcarBsc>
     */
    public Page<SasDragcarBsc> selectSasDragcarBscList(Page<SasDragcarBsc> page, SasDragcarBsc sasDragcarBsc) {
        page.setRecords(sasDragcarBscMapper.selectSasDragcarBscList(page, sasDragcarBsc));
        return page;
    }
}
