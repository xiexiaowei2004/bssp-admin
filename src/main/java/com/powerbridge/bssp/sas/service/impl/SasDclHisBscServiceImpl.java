package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDclHisBscMapper;
import com.powerbridge.bssp.sas.entity.SasDclHisBsc;
import com.powerbridge.bssp.sas.service.ISasDclHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报历史表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Service("sasDclHisBscService")
public class SasDclHisBscServiceImpl extends BaseServiceImpl<SasDclHisBscMapper, SasDclHisBsc> implements ISasDclHisBscService {

    @Autowired
    private SasDclHisBscMapper sasDclHisBscMapper;

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page      分页
     * @param sasDclHisBsc 加工贸易账册核销数据
     * @return Page<SasDclHisBsc>
     */
    public Page<SasDclHisBsc> selectByList(Page<SasDclHisBsc> page, SasDclHisBsc sasDclHisBsc) {
        page.setRecords(sasDclHisBscMapper.selectByList(page, sasDclHisBsc));
        return page;
    }
}
