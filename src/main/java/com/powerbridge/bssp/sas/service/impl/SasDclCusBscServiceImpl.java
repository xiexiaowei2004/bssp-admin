package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDclCusBscMapper;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;
import com.powerbridge.bssp.sas.service.ISasDclCusBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Service("sasDclCusBscService")
public class SasDclCusBscServiceImpl extends BaseServiceImpl<SasDclCusBscMapper, SasDclCusBsc> implements ISasDclCusBscService {

    @Autowired
    private SasDclCusBscMapper sasDclCusBscMapper;

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page      分页
     * @param sasDclCusBsc 加工贸易账册核销数据
     * @return Page<SasDclCusBsc>
     */
    public Page<SasDclCusBsc> selectByList(Page<SasDclCusBsc> page, SasDclCusBsc sasDclCusBsc) {
        page.setRecords(sasDclCusBscMapper.selectByList(page, sasDclCusBsc));
        return page;
    }

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page      分页
     * @param SasDclCusBsc 加工贸易账册核销数据
     * @return Page<SasDclCusBsc>
     */
    public Page<SasDclCusBsc> selectBySasDclCusBscList(Page<SasDclCusBsc> page, SasDclCusBsc sasDclCusBsc) {
        page.setRecords(sasDclCusBscMapper.selectBySasDclCusBscList(page, sasDclCusBsc));
        return page;
    }
}
