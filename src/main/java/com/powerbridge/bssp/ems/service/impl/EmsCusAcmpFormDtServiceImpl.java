package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.EmsCusAcmpFormDtMapper;
import com.powerbridge.bssp.ems.entity.EmsCusAcmpFormDt;
import com.powerbridge.bssp.ems.service.IEmsCusAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecAcmpFormDtServiceImpl
 * 类描述：账册备案正式表附件服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsCusAcmpFormDtService")
public class EmsCusAcmpFormDtServiceImpl extends BaseServiceImpl<EmsCusAcmpFormDtMapper, EmsCusAcmpFormDt> implements IEmsCusAcmpFormDtService {
    @Autowired
    private EmsCusAcmpFormDtMapper emsCusAcmpFormDtMapper;
    /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsPutrecAcmpFormDt
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsCusAcmpFormDt> selectEmsCusAcmpFormDtList(Page<EmsCusAcmpFormDt> page, EmsCusAcmpFormDt emsPutrecAcmpFormDt){
        page.setRecords(emsCusAcmpFormDtMapper.selectEmsCusAcmpFormDtList(page, emsPutrecAcmpFormDt));
        return page;
    }
    /**
     * 获取最大的商品序号
     * @param seqNo　单据编号
     * @return　序号
     */
    public BigDecimal getMaxAcmpFormDtSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsCusAcmpFormDtMapper.getMaxAcmpFormDtSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }
}
