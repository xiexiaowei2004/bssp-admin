package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.ems.dao.EmsCusImgMapper;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;
import com.powerbridge.bssp.ems.service.IEmsCusImgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusImgServiceImpl
 * 类描述：账册备案正式表料件服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
@Service("emsCusImgService")
public class EmsCusImgServiceImpl extends BaseServiceImpl<EmsCusImgMapper, EmsCusImg> implements IEmsCusImgService {

    @Autowired
    EmsCusImgMapper emsCusImgMapper;

    /**
     * 查询加工账册备案料件/分页
     *
     * @param page      分页
     * @param emsCusImg
     * @return Page<EmsCusImg>
     */
    @Override
    public Page<EmsCusImg> selectEmsCusImgList(Page<EmsCusImg> page, EmsCusImg emsCusImg) {
        page.setRecords(emsCusImgMapper.selectEmsCusImgList(page, emsCusImg));
        return page;
    }

    /**
     * 获取最大的商品序号
     *
     * @param seqNo 　单据编号
     * @return　商品序号
     */
    @Override
    public BigDecimal getMaxGdsSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsCusImgMapper.getMaxGdsSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }

    /**
     * 复制账册料件信息
     *
     * @param emsCusImgList
     * @return
     */
    @Override
    public List<EmsCusImg> copyGds(List<EmsCusImg> emsCusImgList) {
        List<EmsCusImg> newEmsCusImgsList = new ArrayList<>();
        for (EmsCusImg emsCusImg : emsCusImgList) {
            String uId = UUIDGenerator.getUUID();
            emsCusImg.setUid(uId);
            BigDecimal gdsSeqno = getMaxGdsSeqno(emsCusImg.getSeqNo());
            gdsSeqno = gdsSeqno.add(new BigDecimal(1));//取最大值加1
            emsCusImg.setGdsSeqno(gdsSeqno);
            emsCusImg.setModfMarkcd("3");
            emsCusImg.setGdsMtno("");//料号置空
            emsCusImg.setMtpckEndprdTypecd("I");
            newEmsCusImgsList.add(emsCusImg);
        }
        return newEmsCusImgsList;
    }

    /**
     * 判断料件序号是否存在
     *
     * @param emsNo
     * @param gdsSeqno
     * @return
     */
    @Override
    public Boolean isExistGdsSeqno(String emsNo, BigDecimal gdsSeqno) {
        Boolean flag = false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("EMS_NO", emsNo);
        entityWrapper.eq("GDS_SEQNO", gdsSeqno);
        List<EmsCusImg> emsCusImgList = selectList(entityWrapper);
        if (emsCusImgList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 选择料件商品复制到申报表bean中
     *
     * @param seqNo
     * @param chgTmsCnt
     * @param idList
     * @return
     */
    @Override
    public List<EmsPutrecImg> getPreImgFromCus(String seqNo, BigDecimal chgTmsCnt, String idList) {
        List<EmsPutrecImg> emsPutrecImgs = new ArrayList<>();
        List<EmsCusImg> emsCusImgs = emsCusImgMapper.selectBatchIds(Arrays.asList(idList.split(",")));
        for (int i = 0; i < emsCusImgs.size(); i++) {
            EmsPutrecImg emsPutrecImg = new EmsPutrecImg();
            BeanUtils.copyProperties(emsCusImgs.get(i), emsPutrecImg);
            String uId = UUIDGenerator.getUUID();
            emsPutrecImg.setUid(uId);
            emsPutrecImg.setModfMarkcd("1");
            emsPutrecImg.setChgTmsCnt(chgTmsCnt);
            emsPutrecImgs.add(emsPutrecImg);
        }
        return emsPutrecImgs;
    }
}
