package com.powerbridge.bssp.inv.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.inv.entity.InvExg;

import java.util.List;

/**
 * <p>
  * 保税核注清单成品 Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface InvExgMapper extends BaseMapper<InvExg> {
    /**
     * @param invExgList
     * @return int
     * @throws
     * @Description: 用于批量保存报关商品
     */
    int insertBatch(List<InvExg> invExgList);

    /**
     * @param
     * @param
     * @return List<InvExg>
     * @throws
     * @Description: 自动合并
     */
    List<InvExg> selectByBondInvtExgList();

    /**
     * @param
     * @param
     * @return
     * @throws
     * @Description: 检索存在两条以上的相同报关单商品序号
     */
    List<InvExg> selectSameEntryGdsSeqNoList(String seqNo);

    /**
     * @param
     * @param
     * @return
     * @throws
     * @Description: 检索存在相同报关单商品序号
     */
    List<InvExg> checkSameGdSNoData();
}