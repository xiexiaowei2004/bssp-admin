package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockHisMapper;
import com.powerbridge.bssp.sas.entity.SasStockHis;
import com.powerbridge.bssp.sas.service.ISasStockHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库单历史记录表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockHisService")
public class SasStockHisServiceImpl extends BaseServiceImpl<SasStockHisMapper, SasStockHis> implements ISasStockHisService {
    @Autowired
    private SasStockHisMapper sasStockHisMapper;

    /**
     * 查询出入库单/分页
     *
     * @param page        分页
     * @param sasStockHis
     * @return Page<SasStockHis>
     */
    public Page<SasStockHis> selectBySasStockHis(Page<SasStockHis> page, SasStockHis sasStockHis) {
        page.setRecords(sasStockHisMapper.selectBySasStockHis(page, sasStockHis));
        return page;
    }

}
