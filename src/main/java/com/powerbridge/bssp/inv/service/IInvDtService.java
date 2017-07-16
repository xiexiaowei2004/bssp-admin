package com.powerbridge.bssp.inv.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvDt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 保税核注清单料件 服务类
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface IInvDtService extends IService<InvDt> {
	
	/**
     * @param map   String
     * @return List<InvImg>
     * @throws
     * @Description:自动合并调整报关单序号
     */
    List<InvDt> autoGetInvtImgList(String seqNo);


    /**
     * @param map   String
     * @return List<InvImg>
     * @throws
     * @Description:手动合并调整报关单序号
     */
    List<InvDt> handlerGetInvtImgList(String seqNo);

    /**
     * @param
     * @return String
     * @throws
     * @Description:报关数据检查
     */
    String checkDeclareData(String seqNo);

    /**
     * 获取最大的序号
     *
     * @return
     */
    BigDecimal getMaxSeqno(String putrecNo,String iEFlag,String mtpckEndprdMarkcd);

    Page<InvDt> selectByInvImg(Page<InvDt>page,InvDt invImg);

    Map checkInvDtList(List<InvDt> invDtList);
}
