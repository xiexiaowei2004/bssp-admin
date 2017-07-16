package com.powerbridge.bssp.sas.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasStockBsc;

import java.util.List;

/**
 * <p>
 * 出入库单表 服务类
 * </p>
 *
 * @author xuzuotao
 * @since 2017-06-01
 */
public interface ISasStockBscService extends IService<SasStockBsc> {
    /**
     * 查询出入库单/分页
     *
     * @param page 分页
     * @param sasStockBsc
     * @return Page<SasStockBsc>
     */
    Page<SasStockBsc> selectBySasStockBsc(Page<SasStockBsc> page, SasStockBsc sasStockBsc);

    /**
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除子表(单条删除)
     */
    boolean txDeleteSasStockBsc(String seqNo);

    /**
     * @param seqNoByList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除子表(批量删除)
     */
    boolean txDeleteSasStockBscByList(List<String> seqNoByList);

    /**
     * @param sasStockBsc
     * @return boolean
     * @throws
     * @Description: 用于作废申请时，同时复制正式表的数据（包括表体)
     */
    boolean txCopySasStockCusByList(SasStockBsc sasStockBsc);
}
