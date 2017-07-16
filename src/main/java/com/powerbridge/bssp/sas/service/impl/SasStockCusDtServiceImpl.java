package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasStockCusDtMapper;
import com.powerbridge.bssp.sas.entity.SasStockCusDt;
import com.powerbridge.bssp.sas.service.ISasStockCusDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库商品正式表 服务实现类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
@Service("sasStockCusDtService")
public class SasStockCusDtServiceImpl extends BaseServiceImpl<SasStockCusDtMapper, SasStockCusDt> implements ISasStockCusDtService {
    @Autowired
    private SasStockCusDtMapper sasStockCusDtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<SasStockCusDt>
     */
    public Page<SasStockCusDt> selectByList(Page<SasStockCusDt> page, String seqNo) {
        page.setRecords(sasStockCusDtMapper.selectByList(page, seqNo));
        return page;
    }

    /**
     * 查询商品总数
     *
     * @param sasDclNo    申报表编号
     * @param sasDclSeqno 申报序号
     * @return Integer
     */
    public Double selectSum(String sasDclNo, int sasDclSeqno) {
        return sasStockCusDtMapper.selectSum(sasDclNo, sasDclSeqno);
    }
}
