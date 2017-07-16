package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.dao.EmsCusUcnsDtMapper;
import com.powerbridge.bssp.ems.dao.EmsPutrecUcnsDtMapper;
import com.powerbridge.bssp.ems.entity.EmsPutrecUcnsDt;
import com.powerbridge.bssp.ems.service.IEmsPutrecBscService;
import com.powerbridge.bssp.ems.service.IEmsPutrecUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecUcnsDtServiceImpl
 * 类描述：账册备案申请损耗服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsPutrecUcnsDtService")
public class EmsPutrecUcnsDtServiceImpl extends BaseServiceImpl<EmsPutrecUcnsDtMapper, EmsPutrecUcnsDt> implements IEmsPutrecUcnsDtService {
    @Autowired
    private EmsPutrecUcnsDtMapper emsPutrecUcnsDtMapper;
    @Autowired
    private EmsCusUcnsDtMapper emsCusUcnsDtMapper;
    @Autowired
    private IEmsPutrecBscService emsPutrecBscService;

    /**
     * 查询加工账册备案单损耗/分页
     *
     * @param page            分页
     * @param emsPutrecUcnsDt
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsPutrecUcnsDt> selectEmsPutrecUcnsDtList(Page<EmsPutrecUcnsDt> page, EmsPutrecUcnsDt emsPutrecUcnsDt) {
        page.setRecords(emsPutrecUcnsDtMapper.selectEmsPutrecUcnsDtList(page, emsPutrecUcnsDt));
        return page;
    }

    /**
     * 判断序号是否存在
     *
     * @param emsPutrecUcnsDt
     * @return
     */
    @Override
    public boolean isExistGdsSeqno(EmsPutrecUcnsDt emsPutrecUcnsDt) {
        boolean flag = false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", emsPutrecUcnsDt.getSeqNo());
        entityWrapper.eq("UCNS_SEQNO", emsPutrecUcnsDt.getUcnsSeqno());
        entityWrapper.eq("CHG_TMS_CNT", emsPutrecUcnsDt.getChgTmsCnt());
        entityWrapper.ne("uid", emsPutrecUcnsDt.getUid());
        List<EmsPutrecUcnsDt> emsPutrecUcnsDtList = selectList(entityWrapper);
        if (emsPutrecUcnsDtList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取最大的商品序号
     *
     * @return 单据编号
     */
    @Override
    public BigDecimal getMaxUcnsSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsPutrecUcnsDtMapper.getMaxUcnsSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }

    /**
     * 根据主键查找单耗数据
     *
     * @param uid
     * @return
     */
    public EmsPutrecUcnsDt selectByUid(String uid) {
        EmsPutrecUcnsDt emsPutrecUcnsDt = emsPutrecUcnsDtMapper.selectByUid(uid);
        return emsPutrecUcnsDt;
    }
    /**
     * 判断必填项
     *
     * @param emsPutrecUcnsDts
     * @return
     */
    @Override
    public String checkBeforeInsert(List<EmsPutrecUcnsDt> emsPutrecUcnsDts) {
        String msg = "";
        for (EmsPutrecUcnsDt emsPutrecUcnsDt : emsPutrecUcnsDts) {
            BigDecimal gdsSeqno = emsPutrecUcnsDt.getUcnsSeqno();
            //检查表头数据是否存在
            if (!emsPutrecBscService.isExistBsc(emsPutrecUcnsDt.getSeqNo())) {
                msg = "预录入统一编号为："+ emsPutrecUcnsDt.getSeqNo()+"的表头数据不存在";
                break;
            }
            //判断序号是否存在
            if (isExistGdsSeqno(emsPutrecUcnsDt)) {
                msg = String.format("序号为%s的数据已存在",emsPutrecUcnsDt.getUcnsSeqno());
                break;
            }
            //检查必填项
            msg = checkIsEmpty(emsPutrecUcnsDt);
            if (StringUtil.isNotEmpty(msg)) {
                break;
            }
            String modfMark = emsPutrecUcnsDt.getModfMarkcd();//修改标记
            String ucnsDclStucd = emsPutrecUcnsDt.getUcnsDclStucd(); //单耗申报状态
            //修改标记为新增、修改、删除”以外的其它标识数据不允许导入
            if(StringUtil.isEmpty(modfMark) ||("1,2,3").indexOf(modfMark) == -1){
                msg = String.format("料件序号为%s的记录修改标记必须为新增、修改、删除",gdsSeqno);
                break;
            }
            //修改标记为新增、修改、删除”以外的其它标识数据不允许导入
            if(!("1,2,3").contains(ucnsDclStucd)){
                msg = String.format("序号为%s的单耗申报状态值不合法",emsPutrecUcnsDt.getUcnsSeqno());
            }
            if(checkIsDeleteData(emsPutrecUcnsDt)){
                msg = String.format("序号为%s的记录在正式表中已标记为删除,不能操作",gdsSeqno);
                break;
            }

        }
        return msg;
    }
    /**
     * 批量插入时 判断必填项
     *
     * @param emsPutrecUcnsDts
     * @return
     */
    @Override
    public String checkListEmpty(List<EmsPutrecUcnsDt> emsPutrecUcnsDts) {
        String msg = "";
        for (EmsPutrecUcnsDt emsPutrecUcnsDt : emsPutrecUcnsDts) {
            msg = checkIsEmpty(emsPutrecUcnsDt);
            if(StringUtil.isNotEmpty(msg)){
                break;
            }
        }
        return msg;
    }
    /**
     * 检查必填项
     * @param emsPutrecUcnsDt
     * @return
     */
    public String checkIsEmpty(EmsPutrecUcnsDt emsPutrecUcnsDt){
        String msg = "";
        if (StringUtil.isEmpty(emsPutrecUcnsDt.getSeqNo())) {
            msg = "预录入统一编号不能为空";
        }
        if (emsPutrecUcnsDt.getUcnsSeqno() == null) {
            msg = "序号不能为空";
        }
        if (emsPutrecUcnsDt.getEndprdSeqno() == null) {
            msg = "成品序号不能为空";
        }
        if (emsPutrecUcnsDt.getMtpckSeqno() == null) {
            msg = "料件序号不能为空";
        }
        if (emsPutrecUcnsDt.getNetUseupQty() == null) {
            msg = "单耗数量不能为空";
        }
        if (emsPutrecUcnsDt.getNetUseupQty() == null) {
            msg = "净耗数量不能为空";
        }
        if (emsPutrecUcnsDt.getTgblLossRate() == null) {
            msg = "有形损耗率不能为空";
        }
        if (emsPutrecUcnsDt.getIntgbLossRate() == null) {
            msg = "无形损耗率不能为空";
        }
        if (StringUtil.isEmpty(emsPutrecUcnsDt.getUcnsDclStucd())) {
            msg = "单耗申报状态不能为空";
        }
        if (emsPutrecUcnsDt.getBondMtpckPrpr() == null) {
            msg = "保税料件比例不能为空";
        }
        return msg;
    }
    /**
     * 判断当前商品序号的数据在正式表中是否是已删除状态
     * @param emsPutrecUcnsDt
     * @return
     */
    private boolean checkIsDeleteData(EmsPutrecUcnsDt emsPutrecUcnsDt){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO",emsPutrecUcnsDt.getSeqNo());
        entityWrapper.eq("UCNS_SEQNO",emsPutrecUcnsDt.getUcnsSeqno());
        entityWrapper.eq("MODF_MARKCD","2");//修改标志为已删除
        int count = emsCusUcnsDtMapper.selectCount(entityWrapper);
        if(count >0){
            return true;
        }
        return false;
    }
}
