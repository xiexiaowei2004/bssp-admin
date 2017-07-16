package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockHisDtMapper;
import com.powerbridge.bssp.sas.entity.SasStockHisDt;
import com.powerbridge.bssp.sas.service.ISasStockHisDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库商品历史记录表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockHisDtService")
public class SasStockHisDtServiceImpl extends BaseServiceImpl<SasStockHisDtMapper, SasStockHisDt> implements ISasStockHisDtService {
    @Autowired
    private SasStockHisDtMapper sasStockHisDtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<SasStockHisDt>
     */
    public Page<SasStockHisDt> selectByList(Page<SasStockHisDt> page, String seqNo) {
        page.setRecords(sasStockHisDtMapper.selectByList(page, seqNo));
        return page;
    }
}
