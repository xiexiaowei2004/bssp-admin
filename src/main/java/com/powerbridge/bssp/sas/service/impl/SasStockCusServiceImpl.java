package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockCusMapper;
import com.powerbridge.bssp.sas.entity.SasStockCus;
import com.powerbridge.bssp.sas.service.ISasStockCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库单正式表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockCusService")
public class SasStockCusServiceImpl extends BaseServiceImpl<SasStockCusMapper, SasStockCus> implements ISasStockCusService {
    @Autowired
    private SasStockCusMapper sasStockCusMapper;

    /**
     * 查询出入库单/分页
     *
     * @param page        分页
     * @param sasStockCus
     * @return Page<SasStockCus>
     */
    public Page<SasStockCus> selectBySasStockCus(Page<SasStockCus> page, SasStockCus sasStockCus) {
        page.setRecords(sasStockCusMapper.selectBySasStockCus(page, sasStockCus));
        return page;
    }

}
