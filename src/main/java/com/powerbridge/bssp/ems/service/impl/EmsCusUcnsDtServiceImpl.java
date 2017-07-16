package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.ems.dao.EmsCusUcnsDtMapper;
import com.powerbridge.bssp.ems.entity.EmsCusUcnsDt;
import com.powerbridge.bssp.ems.entity.EmsPutrecUcnsDt;
import com.powerbridge.bssp.ems.service.IEmsCusUcnsDtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusUcnsDtServiceImpl
 * 类描述：账册备案正式表损耗服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：willChen
 * 修改时间：2017/5/17 9:23
 */
@Service("emsCusUcnsDtService")
public class EmsCusUcnsDtServiceImpl extends BaseServiceImpl<EmsCusUcnsDtMapper, EmsCusUcnsDt> implements IEmsCusUcnsDtService {
    @Autowired
    EmsCusUcnsDtMapper emsCusUcnsDtMapper;
    /**
     * 查询账册备案正式表单损耗列表/分页
     * @param page 分页
     * @param emsCusUcnsDt
     * @return EmsCusUcnsDt
     */
    public Page<EmsCusUcnsDt> selectEmsCusUcnsDtList(Page<EmsCusUcnsDt> page, EmsCusUcnsDt emsCusUcnsDt){
        page.setRecords(emsCusUcnsDtMapper.selectEmsCusUcnsDtList(page, emsCusUcnsDt));
        return page;
    }

    /**
     *查询单耗版本号
     * @param emsCusUcnsDt
     * @return
     */
    public List<EmsCusUcnsDt> selectUcnsVerno(EmsCusUcnsDt emsCusUcnsDt){
        return emsCusUcnsDtMapper.selectUcnsVerno(emsCusUcnsDt);
    }
    /**
     * 判断序号是否存在
     * @param emsNo
     * @param ucnsSeqno
     * @return
     */
    @Override
    public  Boolean isExistGdsSeqno(String emsNo,BigDecimal ucnsSeqno){
        Boolean flag=false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("EMS_NO", emsNo);
        entityWrapper.eq("UCNS_SEQNO", ucnsSeqno);
        List<EmsCusUcnsDt> emsCusUcnsDtList = selectList(entityWrapper);
        if (emsCusUcnsDtList.size() > 0) {
            flag=true;
        }
        return flag;
    }
    /**
     * 获取最大的商品序号
     * @param seqNo　单据编号
     * @return　序号
     */
    @Override
    public BigDecimal getMaxUcnsSeqno(String seqNo) {
        BigDecimal maxGdsSeqno = emsCusUcnsDtMapper.getMaxUcnsSeqno(seqNo);
        if (null == maxGdsSeqno) {
            maxGdsSeqno = BigDecimal.valueOf(0);
        }
        return maxGdsSeqno;
    }
    /**
     * 根据主键查找单耗数据
     * @param uid
     * @return
     */
    public EmsCusUcnsDt selectByUid(String uid){
        EmsCusUcnsDt emsPutrecUcnsDt=emsCusUcnsDtMapper.selectByUid(uid);
        return emsPutrecUcnsDt;
    }
    /**
     * 选择账册单耗插入单耗申报表中
     * @param seqNo
     * @param chgTmsCnt
     * @param idList
     * @return
     */
    @Override
    public List<EmsPutrecUcnsDt> getPreImgFromCus(String seqNo, BigDecimal chgTmsCnt, String idList) {
        List<EmsPutrecUcnsDt> emsPutrecUcnsDts = new ArrayList<>();
        List<EmsCusUcnsDt> emsCusUcnsDts = emsCusUcnsDtMapper.selectBatchIds(Arrays.asList(idList.split(",")));
        for (int i = 0; i < emsCusUcnsDts.size(); i++) {
            EmsPutrecUcnsDt emsPutrecUcnsDt = new EmsPutrecUcnsDt();
            BeanUtils.copyProperties(emsCusUcnsDts.get(i),emsPutrecUcnsDt);
            String uId = UUIDGenerator.getUUID();
            emsPutrecUcnsDt.setUid(uId);
            emsPutrecUcnsDt.setModfMarkcd("1");
            emsPutrecUcnsDt.setChgTmsCnt(chgTmsCnt);
            emsPutrecUcnsDts.add(emsPutrecUcnsDt);
        }
        return emsPutrecUcnsDts;
    }
}
