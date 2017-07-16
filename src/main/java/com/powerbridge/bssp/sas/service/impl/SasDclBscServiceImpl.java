package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.sas.dao.SasDclAcmpFormDtMapper;
import com.powerbridge.bssp.sas.dao.SasDclBscMapper;
import com.powerbridge.bssp.sas.dao.SasDclDtMapper;
import com.powerbridge.bssp.sas.entity.SasDclAcmpFormDt;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import com.powerbridge.bssp.sas.service.ISasDclBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务申报表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@Service("sasDclBscService")
public class SasDclBscServiceImpl extends BaseServiceImpl<SasDclBscMapper, SasDclBsc> implements ISasDclBscService {

    @Autowired
    private SasDclBscMapper sasDclBscMapper;

    @Autowired
    private SasDclDtMapper sasDclDtMapper;

    @Autowired
    private SasDclAcmpFormDtMapper sasDclAcmpFormDtMapper;

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page      分页
     * @param sasDclBsc 加工贸易账册核销数据
     * @return Page<SasDclBsc>
     */
    public Page<SasDclBsc> selectByList(Page<SasDclBsc> page, SasDclBsc sasDclBsc) {
        page.setRecords(sasDclBscMapper.selectByList(page, sasDclBsc));
        return page;
    }

   /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page      分页
     * @param sasDclBsc 加工贸易账册核销数据
     * @return Page<SasDclBsc>
     */
    public Page<SasDclBsc> selectBySasDclBscList(Page<SasDclBsc> page, SasDclBsc sasDclBsc) {
        page.setRecords(sasDclBscMapper.selectBySasDclBscList(page, sasDclBsc));
        return page;
    }

    /**
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    @Transactional
    public boolean txDeleteSasDclBsc(String seqNo) {
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("SEQ_NO", seqNo);
//        entityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
        sasDclDtMapper.delete(entityWrapper);
        sasDclAcmpFormDtMapper.delete(entityWrapper);
        boolean flag = retBool(sasDclBscMapper.delete(entityWrapper));
        return flag;
    }

    /**
     * @param seqNoByList
     * @param chgTmsCntList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
//    @Transactional
//    public boolean txDeleteSasDclBscByList(List<String> seqNoByList,List<String> chgTmsCntList) {
//        boolean flag = true;
//        for (int i=0;i<seqNoByList.size();i++){
//            EntityWrapper entityWrapper = new EntityWrapper<>();
//            entityWrapper.eq("SEQ_NO", seqNoByList);
////            entityWrapper.eq("CHG_TMS_CNT", chgTmsCntList);
//            sasDclBscMapper.delete(entityWrapper);
//            sasDclAcmpFormDtMapper.delete(entityWrapper);
//            boolean deleteFlag = retBool(sasDclBscMapper.delete(entityWrapper));
//            flag = (flag && deleteFlag);
//        }
//        return flag;
//    }

    /**
     * @param sasDclBsc
     * @return boolean
     * @throws
     * @Description: 用于业务变更和业务结案暂存时，同时修改字表
     */
    @Transactional
    public boolean txUpdateById(SasDclBsc sasDclBsc) {
        boolean flag = retBool(sasDclBscMapper.updateById(sasDclBsc));
        if (flag) {
            EntityWrapper entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", sasDclBsc.getSeqNo());

            /***********************附件**************************/
            SasDclAcmpFormDt sasDclAcmpFormDt = new SasDclAcmpFormDt();
            sasDclAcmpFormDt.setChgTmsCnt(sasDclBsc.getChgTmsCnt());
            sasDclAcmpFormDtMapper.update(sasDclAcmpFormDt, entityWrapper);

            /***********************商品**************************/
            //删除标记 修改标记状态不更改,变更次数跟主表相同
            entityWrapper.eq("MODF_MARKCD", ChkStatusConstant.MODF_MARKCD_2);
            SasDclDt sasDclDt = new SasDclDt();
            sasDclDt.setChgTmsCnt(sasDclBsc.getChgTmsCnt());
            sasDclDtMapper.update(sasDclDt, entityWrapper);

            //除删除标记以外 修改标记状态修改0-未修改，变更次数与主表相同
            entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", sasDclBsc.getSeqNo());
            entityWrapper.ne("MODF_MARKCD", ChkStatusConstant.MODF_MARKCD_2);
            sasDclDt = new SasDclDt();
            sasDclDt.setChgTmsCnt(sasDclBsc.getChgTmsCnt());
            sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_0);
            sasDclDtMapper.update(sasDclDt, entityWrapper);
        }
        return flag;
    }
}
