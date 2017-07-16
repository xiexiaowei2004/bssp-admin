package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.dao.EmsCusImgMapper;
import com.powerbridge.bssp.ems.dao.EmsPutrecImgMapper;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;
import com.powerbridge.bssp.ems.service.IEmsPutrecBscService;
import com.powerbridge.bssp.ems.service.IEmsPutrecImgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecImgServiceImpl
 * 类描述：账册备案申请料件服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/16 22:18
 */
@Service("emsPutrecImgService")
public class EmsPutrecImgServiceImpl extends BaseServiceImpl<EmsPutrecImgMapper, EmsPutrecImg> implements IEmsPutrecImgService {
    @Autowired
    private EmsPutrecImgMapper emsPutrecImgMapper;
    @Autowired
    private EmsCusImgMapper emsCusImgMapper;
    @Autowired
    private IEmsPutrecBscService emsPutrecBscService;

    /**
     * 查询加工账册备案料件/分页
     *
     * @param page         分页
     * @param emsPutrecImg
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsPutrecImg> selectEmsPutrecImgList(Page<EmsPutrecImg> page, EmsPutrecImg emsPutrecImg) {
        page.setRecords(emsPutrecImgMapper.selectEmsPutrecImgList(page, emsPutrecImg));
        return page;
    }

    /**
     * 获取最大的商品序号
     *
     * @return 单据编号
     */
    @Override
    public BigDecimal getMaxGdsSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsPutrecImgMapper.getMaxGdsSeqno(seqNo);
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
    private BigDecimal getCusMaxGdsSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsCusImgMapper.getMaxGdsSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }

    /**
     * 复制当前商品
     *
     * @param emsPutrecImg
     * @return
     */
    @Override
    public EmsPutrecImg copyGds(EmsPutrecImg emsPutrecImg) {
        EmsPutrecImg newEmsPutrecImg = new EmsPutrecImg();
        String uId = UUIDGenerator.getUUID();
        emsPutrecImg.setUid(uId);//主键
        BeanUtils.copyProperties(emsPutrecImg, newEmsPutrecImg);
        newEmsPutrecImg.setGdsMtno(null);//料号不复制
        newEmsPutrecImg.setModfMarkcd("3");
        BigDecimal gdsSeqno = getMaxGdsSeqno(emsPutrecImg.getSeqNo());
        BigDecimal cusGdsSeqno = getCusMaxGdsSeqno(emsPutrecImg.getSeqNo());
        if (cusGdsSeqno.compareTo(gdsSeqno) == 1) {
            gdsSeqno = cusGdsSeqno;
        }
        gdsSeqno = gdsSeqno.add(new BigDecimal(1));//取最大值加1
        newEmsPutrecImg.setGdsSeqno(gdsSeqno);
        return newEmsPutrecImg;
    }

    /**
     * 判断料件序号是否存在
     *
     * @param emsPutrecImg
     * @return boolean
     */
    @Override
    public boolean isExistGdsSeqno(EmsPutrecImg emsPutrecImg) {
        boolean flag = false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", emsPutrecImg.getSeqNo());
        entityWrapper.eq("GDS_SEQNO", emsPutrecImg.getGdsSeqno());
        if (emsPutrecImg.getModfMarkcd().equals("3")) {
            List<EmsCusImg> emsCusImgList = emsCusImgMapper.selectList(entityWrapper);
            if (emsCusImgList.size() > 0) {
                flag = true;
            }
        }
        entityWrapper.eq("CHG_TMS_CNT", emsPutrecImg.getChgTmsCnt());
        entityWrapper.ne("uid", emsPutrecImg.getUid());
        List<EmsPutrecImg> emsPutrecImgList = selectList(entityWrapper);
        if (emsPutrecImgList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断商品料号是否存在
     *
     * @param emsPutrecImg
     * @return boolean
     */
    public boolean isExistGdsMtno(EmsPutrecImg emsPutrecImg) {
        boolean flag = false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", emsPutrecImg.getSeqNo());
        entityWrapper.eq("GDS_MTNO", emsPutrecImg.getGdsMtno());
        if (emsPutrecImg.getModfMarkcd().equals("3")) {
            List<EmsCusImg> emsCusImgList = emsCusImgMapper.selectList(entityWrapper);
            if (emsCusImgList.size() > 0) {
                flag = true;
            }
        }
        entityWrapper.eq("CHG_TMS_CNT", emsPutrecImg.getChgTmsCnt());
        entityWrapper.ne("uid", emsPutrecImg.getUid());
        List<EmsPutrecImg> emsPutrecImgList = selectList(entityWrapper);
        if (emsPutrecImgList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断必填项
     *
     * @param emsPutrecImgs
     * @return
     */
    @Override
    public String checkBeforeInsert(List<EmsPutrecImg> emsPutrecImgs) {
        String msg = "";
        for (EmsPutrecImg emsPutrecImg : emsPutrecImgs) {
            BigDecimal gdsSeqno = emsPutrecImg.getGdsSeqno();
            //检查表头数据是否存在
            if (!emsPutrecBscService.isExistBsc(emsPutrecImg.getSeqNo())) {
                msg = "预录入统一编号为："+ emsPutrecImg.getSeqNo()+"的表头数据不存在";
                break;
            }
            //判断序号是否存在
            if (isExistGdsSeqno(emsPutrecImg)) {
                msg = String.format("料件序号为%s的数据已存在",emsPutrecImg.getGdsSeqno());
                break;
            }
            //判断料号是否存在
            if(isExistGdsMtno(emsPutrecImg)){
                msg = String.format("料件料号为%s的数据已存在",emsPutrecImg.getGdsMtno());
                break;
            }
            //检查必填项
            msg = checkIsEmpty(emsPutrecImg);
            if (StringUtil.isNotEmpty(msg)) {
                break;
            }
            String modfMark = emsPutrecImg.getModfMarkcd();
            //新增、修改、删除”以外的其它标识数据不允许导入
            if(StringUtil.isEmpty(modfMark) ||("1,2,3").indexOf(modfMark) == -1){
                msg = String.format("料件序号为%s的记录修改标记必须为新增、修改、删除",gdsSeqno);
                break;
            }
            if(checkIsDeleteData(emsPutrecImg)){
                msg = String.format("料件序号为%s的记录在正式表中已标记为删除",gdsSeqno);
                break;
            }

        }
        return msg;
    }

    /**
     * 检查必填项
     * @param emsPutrecImgs
     * @return
     */
    @Override
    public String checkListEmpty(List<EmsPutrecImg> emsPutrecImgs){
        String msg = "";
        //判断非空
        for (EmsPutrecImg emsPutrecImg : emsPutrecImgs) {
            msg = checkIsEmpty(emsPutrecImg);
            if(StringUtil.isNotEmpty(msg)){
                break;
            }
        }
        return msg;
    }
    /**
     * 检查必填项
     * @param emsPutrecImg
     * @return
     */
    public String checkIsEmpty(EmsPutrecImg emsPutrecImg){
        String msg = "";
        //判断非空
            if (StringUtil.isEmpty(emsPutrecImg.getSeqNo())) {
                msg = "预录入统一编号不能为空";
            }
            if (emsPutrecImg.getGdsSeqno() == null) {
                msg = "序号不能为空";
            }
            if (StringUtil.isEmpty(emsPutrecImg.getGdsMtno())) {
                msg = "料号不能为空";
            }
            if (StringUtil.isEmpty(emsPutrecImg.getGdecd())) {
                msg = "商品编码不能为空";
            }
            if (StringUtil.isEmpty(emsPutrecImg.getGdsNm())) {
                msg = "商品名称不能为空";
            }
            if (StringUtil.isEmpty(emsPutrecImg.getDclUnitcd())) {
                msg = "申报计量单位不能为空";
            }
            if (StringUtil.isEmpty(emsPutrecImg.getAdjmtrMarkcd())) {
                msg = "主料标记不能为空";
            }
            if (StringUtil.isEmpty(emsPutrecImg.getEtpsExeMarkcd())) {
                msg = "企业执行标记不能为空";
            }
        return msg;
    }

    /**
     * 判断当前商品序号的数据在正式表中是否是已删除状态
     * @param emsPutrecImg
     * @return
     */
    private boolean checkIsDeleteData(EmsPutrecImg emsPutrecImg){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO",emsPutrecImg.getSeqNo());
        entityWrapper.eq("GDS_SEQNO",emsPutrecImg.getGdsSeqno());
        entityWrapper.eq("MODF_MARKCD","2");//修改标志为已删除
        int count = emsCusImgMapper.selectCount(entityWrapper);
        if(count >0){
            return true;
        }
        return false;
    }
}
