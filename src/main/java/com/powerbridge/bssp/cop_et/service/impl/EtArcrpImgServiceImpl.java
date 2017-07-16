package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.dao.EtArcrpImgMapper;
import com.powerbridge.bssp.cop_et.dao.EtCusImgMapper;
import com.powerbridge.bssp.cop_et.entity.EtArcrpImg;
import com.powerbridge.bssp.cop_et.service.IEtArcrpImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联网企业档案库料件 服务实现类
 *
 * @author willChen
 * @since 2017-05-22
 */
@Service("etArcrpImgService")
public class EtArcrpImgServiceImpl extends BaseServiceImpl<EtArcrpImgMapper, EtArcrpImg> implements IEtArcrpImgService {

    @Autowired
    private EtArcrpImgMapper etArcrpImgMapper;
    @Autowired
    private EtCusImgMapper etCusImgMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etArcrpImg
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtArcrpImg> selectEtArcrpImgList(Page<EtArcrpImg> page, EtArcrpImg etArcrpImg) throws Exception {
        page.setRecords(etArcrpImgMapper.selectEtArcrpImgList(page, etArcrpImg));
        return page;
    }

    /**
     * 选取新增数据
     *
     * @param etArcrpImg
     * @param codCusComplexList
     * @return
     * @throws Exception
     */
    @Override
    public Integer chooseAddEtArcrpImg(EtArcrpImg etArcrpImg, List<CodCusComplex> codCusComplexList) throws Exception {
        Integer count = 0;
        Map codeTMap = new HashMap();

        //获取正式表和申请表中最大的序号
        Integer maxGdsSeqno = getMaxGdsSeqno(etArcrpImg.getSeqNo(), etArcrpImg.getChgTmsCnt());

        for (CodCusComplex codCusComplex : codCusComplexList) {
            String codeT = codCusComplex.getCodeT();
            if (StringUtil.isNotEmpty(codeT)) {
                //截取商品编码前四位
                codeT = StringUtil.sub(codeT, 0, 4);
                if (!codeTMap.containsKey(codeT)) {
                    //筛选掉商品编码前四位重复的
                    codeTMap.put(codeT, null);
                    String uid = UUIDGenerator.getUUID();
                    etArcrpImg.setUid(uid);
                    etArcrpImg.setGdsSeqno(++maxGdsSeqno);
                    etArcrpImg.setGdecd(codeT);
                    etArcrpImg.setGdsNm(codCusComplex.getgName());
                    count += etArcrpImgMapper.insert(etArcrpImg);
                }
            }

        }
        return count;
    }

    /**
     * 获取最大的料件序号
     *
     * @param seqNo 统一编号
     * @return
     */
    @Override
    public Integer getMaxGdsSeqno(String seqNo, Integer chgTmsCnt) {
        //获取正式表当前档案库成品商品序号最大值
        Map map = new HashMap();
        map.put("seqNo", seqNo);
        map.put("chgTmsCnt", chgTmsCnt);
        Integer maxArcrpGdsSeqno = etArcrpImgMapper.getMaxGdsSeqno(map);
        Integer maxCusGdsSeqno = etCusImgMapper.getMaxGdsSeqno(seqNo);
        //如果为值为null则改为0，用于比较
        maxArcrpGdsSeqno = maxArcrpGdsSeqno == null ? 0 : maxArcrpGdsSeqno;
        maxCusGdsSeqno = maxCusGdsSeqno == null ? 0 : maxCusGdsSeqno;
        //获取正式表和申请表中最大的序号
        return maxArcrpGdsSeqno > maxCusGdsSeqno ? maxArcrpGdsSeqno : maxCusGdsSeqno;
    }

    /**
     * 删除新增的成品并对其他新增商品重新排序
     *
     * @param uid
     * @param etArcrpImgList
     * @return
     */
    @Override
    public boolean deleteAdd(String uid, List<EtArcrpImg> etArcrpImgList) {
        if (etArcrpImgList.size() > 0) {
            for (EtArcrpImg etArcrpImg : etArcrpImgList) {
                etArcrpImg.setGdsSeqno(etArcrpImg.getGdsSeqno() - 1);
            }
            if (!updateBatchById(etArcrpImgList)) {
                return false;
            }
        }
        return deleteById(uid);
    }

}
