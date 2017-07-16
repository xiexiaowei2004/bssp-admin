package com.powerbridge.bssp.dcr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.dcr.dao.DcrChgoffInvtLtMapper;
import com.powerbridge.bssp.dcr.entity.DcrChgoffInvtLt;
import com.powerbridge.bssp.dcr.service.IDcrChgoffInvtLtService;
import com.powerbridge.bssp.inv.entity.InvCusBsc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * <p>
 * 账册报核清单 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
@Service("dcrChgoffInvtLtService")
public class DcrChgoffInvtLtServiceImpl extends BaseServiceImpl<DcrChgoffInvtLtMapper, DcrChgoffInvtLt> implements IDcrChgoffInvtLtService {

    @Autowired
    private DcrChgoffInvtLtMapper dcrChgoffInvtLtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffInvtLt>
     */
    public Page<DcrChgoffInvtLt> selectByList(Page<DcrChgoffInvtLt> page, String seqNo) {
        page.setRecords(dcrChgoffInvtLtMapper.selectByList(page, seqNo));
        return page;
    }

    /**
     * 选取
     *
     * @param seqNo 单据编号
     * @return boolean
     */
    @Transactional
    public boolean getDcrChgoffInvtLtByList(List<InvCusBsc> invCusBscList, String seqNo, String dclTypecd, String chgTmsCnt) {
        boolean flag = true;
        try {
            EntityWrapper<DcrChgoffInvtLt> ewDcrChgoffImg = new EntityWrapper<>();
            ewDcrChgoffImg.eq("SEQ_NO", seqNo);
            dcrChgoffInvtLtMapper.delete(ewDcrChgoffImg);
            int lNo = 1;
            for (int i = 0; i < invCusBscList.size(); i++) {
                DcrChgoffInvtLt dcrChgoffInvtLt = new DcrChgoffInvtLt();
                dcrChgoffInvtLt.setUid(UUIDGenerator.getUUID());
                dcrChgoffInvtLt.setlNo(lNo + i);  // 序号
                dcrChgoffInvtLt.setSeqNo(seqNo);    // 预录入统一编码
                dcrChgoffInvtLt.setBondInvtNo(invCusBscList.get(i).getBondInvtNo());   // 报核清单编号
                dcrChgoffInvtLt.setEmsNo(invCusBscList.get(i).getPutrecNo());  //账册编号
                dcrChgoffInvtLt.setChgTmsCnt(Integer.parseInt(chgTmsCnt));    // 报核次数
                dcrChgoffInvtLt.setDclTypecd(dclTypecd);    // 申报类型
                dcrChgoffInvtLt.setIeFlag(invCusBscList.get(i).getImpexpMarkcd());   //出入标记
                dcrChgoffInvtLt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_3); //修改标记 3-新增
                dcrChgoffInvtLtMapper.insert(dcrChgoffInvtLt);
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
    public void updateLno(String seqNo,int lNo){
        dcrChgoffInvtLtMapper.updateLno(seqNo,lNo);
    }
}
