package com.powerbridge.bssp.dcr.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.dcr.dao.DcrChgoffImgMapper;
import com.powerbridge.bssp.dcr.entity.DcrChgoffImg;
import com.powerbridge.bssp.dcr.service.IDcrChgoffImgService;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * <p>
 * 账册报核料件 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
@Service("dcrChgoffImgService")
public class DcrChgoffImgServiceImpl extends BaseServiceImpl<DcrChgoffImgMapper, DcrChgoffImg> implements IDcrChgoffImgService {

    @Autowired
    private DcrChgoffImgMapper dcrChgoffImgMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffInvtLt>
     */
    public Page<DcrChgoffImg> selectByList(Page<DcrChgoffImg> page, String seqNo) {
        page.setRecords(dcrChgoffImgMapper.selectByList(page, seqNo));
        return page;
    }

    /**
     * 核销
     *
     * @param seqNo 单据编号
     * @return boolean
     */
    @Transactional
    public boolean getDcrChgoffImgByList(List<EmsCusImg> emsCusImgList, String seqNo, String chgTmsCnt) {
        boolean flag = true;
        try {
            EntityWrapper<DcrChgoffImg> ewDcrChgoffImg = new EntityWrapper<>();
            ewDcrChgoffImg.eq("SEQ_NO", seqNo);
            dcrChgoffImgMapper.delete(ewDcrChgoffImg);
            int gdsSeqNo = 1;
            for (int i = 0; i < emsCusImgList.size(); i++) {
                EmsCusImg emsCusImg = emsCusImgList.get(i);
                DcrChgoffImg dcrChgoffImg = new DcrChgoffImg();
                dcrChgoffImg.setUid(UUIDGenerator.getUUID());
                dcrChgoffImg.setGdsSeqno(gdsSeqNo + i);   //序号
                dcrChgoffImg.setEmsNo(emsCusImg.getEmsNo());//账册编号
                dcrChgoffImg.setSeqNo(seqNo);    // 预录入统一编码
                dcrChgoffImg.setgNo(emsCusImg.getGdsSeqno().intValue());//料件备案序号
                dcrChgoffImg.setCopGNo(emsCusImg.getGdsMtno());//料号
                dcrChgoffImg.setChgTmsCnt(Integer.parseInt(chgTmsCnt));//报核次数
                dcrChgoffImg.setCodeTs(emsCusImg.getGdecd());   // 商品编码
                dcrChgoffImg.setgName(emsCusImg.getGdsNm());//商品名称
                dcrChgoffImg.setUnit(emsCusImg.getDclUnitcd());//计量单位
                dcrChgoffImg.setTypecd(emsCusImg.getMtpckEndprdTypecd());//料件成品标记
                dcrChgoffImgMapper.insert(dcrChgoffImg);
            }
        } catch (Exception e) {
            flag = false;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return flag;
    }

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    public void updateGdsSeqno(String seqNo,int gdsSeqno){
        dcrChgoffImgMapper.updateGdsSeqno(seqNo,gdsSeqno);
    }
}
