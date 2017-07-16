package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.EmsPutrecAcmpFormDtMapper;
import com.powerbridge.bssp.ems.entity.EmsPutrecAcmpFormDt;
import com.powerbridge.bssp.ems.service.IEmsPutrecAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecAcmpFormDtServiceImpl
 * 类描述：账册备案申请附件服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsPutrecAcmpFormDtService")
public class EmsPutrecAcmpFormDtServiceImpl extends BaseServiceImpl<EmsPutrecAcmpFormDtMapper, EmsPutrecAcmpFormDt> implements IEmsPutrecAcmpFormDtService {
    @Autowired
    private EmsPutrecAcmpFormDtMapper emsPutrecAcmpFormDtMapper;
    /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsPutrecAcmpFormDt
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsPutrecAcmpFormDt> selectEmsPutrecAcmpFormDtList(Page<EmsPutrecAcmpFormDt> page, EmsPutrecAcmpFormDt emsPutrecAcmpFormDt){
        page.setRecords(emsPutrecAcmpFormDtMapper.selectEmsPutrecAcmpFormDtList(page, emsPutrecAcmpFormDt));
        return page;
    }
    /**
     * 获取最大的商品序号
     * @param seqNo　单据编号
     * @return　序号
     */
    public BigDecimal getMaxAcmpFormDtSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsPutrecAcmpFormDtMapper.getMaxAcmpFormDtSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }
}
