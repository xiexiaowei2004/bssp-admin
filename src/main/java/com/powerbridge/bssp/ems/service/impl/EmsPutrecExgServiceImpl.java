package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.dao.EmsCusExgMapper;
import com.powerbridge.bssp.ems.dao.EmsPutrecExgMapper;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsPutrecExg;
import com.powerbridge.bssp.ems.service.IEmsPutrecBscService;
import com.powerbridge.bssp.ems.service.IEmsPutrecExgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecExgServiceImpl
 * 类描述：账册备案申请成品服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsPutrecExgService")
public class EmsPutrecExgServiceImpl extends BaseServiceImpl<EmsPutrecExgMapper, EmsPutrecExg> implements IEmsPutrecExgService {
    @Autowired
    private EmsPutrecExgMapper emsPutrecExgMapper;
    @Autowired
    private EmsCusExgMapper emsCusExgMapper;
    @Autowired
    private IEmsPutrecBscService emsPutrecBscService;

    /**
     * 查询加工账册备案成品/分页
     *
     * @param page         分页
     * @param emsPutrecExg
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsPutrecExg> selectEmsPutrecExgList(Page<EmsPutrecExg> page, EmsPutrecExg emsPutrecExg) {
        page.setRecords(emsPutrecExgMapper.selectEmsPutrecExgList(page, emsPutrecExg));
        return page;
    }

    /**
     * 获取最大的商品序号
     *
     * @return 单据编号
     */
    @Override
    public BigDecimal getMaxGdsSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsPutrecExgMapper.getMaxGdsSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }

    /**
     * 获取正式表最大的商品序号
     *
     * @return 单据编号
     */
    public BigDecimal getCusMaxGdsSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsCusExgMapper.getMaxGdsSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }

    /**
     * 复制当前商品
     *
     * @param emsPutrecExg
     * @return
     */
    @Override
    public EmsPutrecExg copyGds(EmsPutrecExg emsPutrecExg) {
        EmsPutrecExg newEmsPutrecImg = new EmsPutrecExg();
        String uId = UUIDGenerator.getUUID();
        emsPutrecExg.setUid(uId);//主键
        BeanUtils.copyProperties(emsPutrecExg, newEmsPutrecImg);
        newEmsPutrecImg.setGdsMtno(null);//料号不复制
        newEmsPutrecImg.setModfMarkcd("3");
        BigDecimal gdsSeqno = getMaxGdsSeqno(emsPutrecExg.getSeqNo());
        BigDecimal cusGdsSeqno = getCusMaxGdsSeqno(emsPutrecExg.getSeqNo());
        if (cusGdsSeqno.compareTo(gdsSeqno) == 1) {
            gdsSeqno = cusGdsSeqno;
        }
        gdsSeqno = gdsSeqno.add(new BigDecimal(1));//取最大值加1
        newEmsPutrecImg.setGdsSeqno(gdsSeqno);
        return newEmsPutrecImg;
    }

    /**
     * 判断成品序号是否存在
     *
     * @param emsPutrecExg
     * @return
     */
    @Override
    public boolean isExistGdsSeqno(EmsPutrecExg emsPutrecExg) {
        boolean flag = false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", emsPutrecExg.getSeqNo());
        entityWrapper.eq("GDS_SEQNO", emsPutrecExg.getGdsSeqno());
        entityWrapper.eq("CHG_TMS_CNT", emsPutrecExg.getChgTmsCnt());
        entityWrapper.ne("uid", emsPutrecExg.getUid());
        List<EmsPutrecExg> emsPutrecExgList = selectList(entityWrapper);
        if (emsPutrecExgList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断商品料号是否存在
     *
     * @param emsPutrecExg
     * @return boolean
     */
    public boolean isExistGdsMtno(EmsPutrecExg emsPutrecExg) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", emsPutrecExg.getSeqNo());
        entityWrapper.eq("GDS_MTNO", emsPutrecExg.getGdsMtno());
        if (emsPutrecExg.getModfMarkcd().equals("3")) {
            List<EmsCusExg> emsCusExgList = emsCusExgMapper.selectList(entityWrapper);
            if (emsCusExgList.size() > 0) {
                return true;
            }
        }
        entityWrapper.eq("CHG_TMS_CNT", emsPutrecExg.getChgTmsCnt());
        entityWrapper.ne("uid", emsPutrecExg.getUid());
        List<EmsPutrecExg> emsPutrecExgList = selectList(entityWrapper);
        if (emsPutrecExgList.size() > 0) {
            return true;
        }
        return false;
    }
    /**
     * 判断必填项
     *
     * @param emsPutrecExgs
     * @return
     */
    @Override
    public String checkBeforeInsert(List<EmsPutrecExg> emsPutrecExgs) {
        String msg = "";
        for (EmsPutrecExg emsPutrecExg : emsPutrecExgs) {
            BigDecimal gdsSeqno = emsPutrecExg.getGdsSeqno();
            //检查表头数据是否存在
            if (!emsPutrecBscService.isExistBsc(emsPutrecExg.getSeqNo())) {
                msg = "预录入统一编号为："+ emsPutrecExg.getSeqNo()+"的表头数据不存在";
                break;
            }
            //判断序号是否存在
            if (isExistGdsSeqno(emsPutrecExg)) {
                msg = String.format("成品序号为%s的数据已存在",emsPutrecExg.getGdsSeqno());
                break;
            }
            //判断料号是否存在
            if(isExistGdsMtno(emsPutrecExg)){
                msg = String.format("成品料号为%s的数据已存在",emsPutrecExg.getGdsMtno());
                break;
            }
            //检查必填项
            msg = checkIsEmpty(emsPutrecExg);
            if (StringUtil.isNotEmpty(msg)) {
                break;
            }
            String modfMark = emsPutrecExg.getModfMarkcd();
            //新增、修改、删除”以外的其它标识数据不允许导入
            if(StringUtil.isEmpty(modfMark) ||("1,2,3").indexOf(modfMark) == -1){
                msg = String.format("成品序号为%s的记录修改标记必须为新增、修改、删除",gdsSeqno);
                break;
            }
            if(checkIsDeleteData(emsPutrecExg)){
                msg = String.format("成品序号为%s的记录在正式表中已标记为删除",gdsSeqno);
                break;
            }

        }
        return msg;
    }
    /**
     * 判断必填项
     *
     * @param emsPutrecExgs
     * @return
     */
    @Override
    public String checkListEmpty(List<EmsPutrecExg> emsPutrecExgs){
        String msg = "";
        //判断非空
        for (EmsPutrecExg emsPutrecExg : emsPutrecExgs) {
            msg = checkIsEmpty(emsPutrecExg);
            if(StringUtil.isNotEmpty(msg)){
                break;
            }
        }
        return msg;
    }
    /**
     * 检查必填项
     * @param emsPutrecExg
     * @return
     */
    public String checkIsEmpty(EmsPutrecExg emsPutrecExg){
        String msg = "";
        //判断非空
        if (StringUtil.isEmpty(emsPutrecExg.getSeqNo())) {
            msg = "预录入统一编号不能为空";
        }
        if (emsPutrecExg.getGdsSeqno() == null) {
            msg = "序号不能为空";
        }
        if (StringUtil.isEmpty(emsPutrecExg.getGdsMtno())) {
            msg = "料号不能为空";
        }
        if (StringUtil.isEmpty(emsPutrecExg.getGdecd())) {
            msg = "商品编码不能为空";
        }
        if (StringUtil.isEmpty(emsPutrecExg.getGdsNm())) {
            msg = "商品名称不能为空";
        }
        if (StringUtil.isEmpty(emsPutrecExg.getDclUnitcd())) {
            msg = "申报计量单位不能为空";
        }
        if (StringUtil.isEmpty(emsPutrecExg.getEtpsExeMarkcd())) {
            msg = "企业执行标记不能为空";
        }
        return msg;
    }

    /**
     * 判断当前商品序号的数据在正式表中是否是已删除状态
     * @param emsPutrecExg
     * @return
     */
    private boolean checkIsDeleteData(EmsPutrecExg emsPutrecExg){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO",emsPutrecExg.getSeqNo());
        entityWrapper.eq("GDS_SEQNO",emsPutrecExg.getGdsSeqno());
        entityWrapper.eq("MODF_MARKCD","2");//修改标志为已删除
        int count = emsCusExgMapper.selectCount(entityWrapper);
        if(count >0){
            return true;
        }
        return false;
    }
}
