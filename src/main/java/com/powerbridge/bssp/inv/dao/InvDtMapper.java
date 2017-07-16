package com.powerbridge.bssp.inv.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.inv.entity.InvExg;
import com.powerbridge.bssp.inv.entity.InvDt;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
  * 保税核注清单料件 Mapper 接口
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface InvDtMapper extends BaseMapper<InvDt> {
	
	/**
     * @param
     * @param
     * @return List<InvExg>
     * @throws
     * @Description: 自动合并
     */
    List<InvDt> selectByBondInvtImgList(String seqNo);

    /**
     * @param
     * @param
     * @return List<InvExg>
     * @throws
     * @Description: 手动合并
     */
    List<InvDt> selectByHandlerInvtImgList(String seqNo);

    /**
     * @param invExg InvExg
     * @param orgFlag  String
     * @return int
     * @throws
     * @Description: 批量更新清单商品序号
     */
    int updateByInvImgList(InvExg invExg, String orgFlag);

    /**
     * @param
     * @param
     * @return
     * @throws
     * @Description: 检索存在两条以上的相同报关单商品序号
     */
    List<InvDt> selectSameEntryGdsSeqNoList(String seqNo);
    /**
     * @param
     * @param
     * @return
     * @throws
     * @Description: 查询相同报关单商品序号记录
     */
    List<InvDt> selectSameGdSNoData(String seqNo, BigDecimal entryGdsSeqno);

    /**
     * 获取最大的序号
     *
     * @return
     */
    BigDecimal getMaxSeqno(@Param("putrecNo") String putrecNo,@Param("iEFlag") String iEFlag,@Param("mtpckEndprdMarkcd") String mtpckEndprdMarkcd);

    /**
     * 查询
     *
     * @param page 分页
     * @param invImg
     * @return List<InvImg>
     */
    List<InvDt> selectByInvImg(Page<InvDt> page, InvDt invImg);

}