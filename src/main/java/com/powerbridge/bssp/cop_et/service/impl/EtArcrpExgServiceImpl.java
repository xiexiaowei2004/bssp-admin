package com.powerbridge.bssp.cop_et.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.dao.EtArcrpExgMapper;
import com.powerbridge.bssp.cop_et.dao.EtCusExgMapper;
import com.powerbridge.bssp.cop_et.entity.EtArcrpExg;
import com.powerbridge.bssp.cop_et.service.IEtArcrpExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联网企业档案库成品 服务实现类
 *
 * @author willChen
 * @since 2017-05-22
 */
@Service("etArcrpExgService")
public class EtArcrpExgServiceImpl extends BaseServiceImpl<EtArcrpExgMapper, EtArcrpExg> implements IEtArcrpExgService {

    @Autowired
    private EtArcrpExgMapper etArcrpExgMapper;
    @Autowired
    private EtCusExgMapper etCusExgMapper;

    /**
     * 根据条件查询表数据
     *
     * @param etArcrpExg
     * @return
     * @throws Exception
     */
    @Override
    public Page<EtArcrpExg> selectEtArcrpExgList(Page<EtArcrpExg> page, EtArcrpExg etArcrpExg) throws Exception {
        page.setRecords(etArcrpExgMapper.selectEtArcrpExgList(page, etArcrpExg));
        return page;
    }

    /**
     * 选取新增数据
     *
     * @param etArcrpExg
     * @param codCusComplexList
     * @return
     * @throws Exception
     */
    @Override
    public Integer chooseAddEtArcrpExg(EtArcrpExg etArcrpExg, List<CodCusComplex> codCusComplexList) throws Exception {
        Integer count = 0;
        Map codeTMap = new HashMap();

        //获取正式表和申请表中最大的序号
        Integer maxGdsSeqno = getMaxGdsSeqno(etArcrpExg.getSeqNo(), etArcrpExg.getChgTmsCnt());

        for (CodCusComplex codCusComplex : codCusComplexList) {
            String codeT = codCusComplex.getCodeT();
            if (StringUtil.isNotEmpty(codeT)) {
                //截取商品编码前四位
                codeT = StringUtil.sub(codeT, 0, 4);
                if (!codeTMap.containsKey(codeT)) {
                    //筛选掉商品编码前四位重复的
                    codeTMap.put(codeT, null);
                    String uid = UUIDGenerator.getUUID();
                    etArcrpExg.setUid(uid);
                    etArcrpExg.setGdsSeqno(++maxGdsSeqno);
                    etArcrpExg.setGdecd(codeT);
                    etArcrpExg.setGdsNm(codCusComplex.getgName());
                    count += etArcrpExgMapper.insert(etArcrpExg);
                }
            }

        }
        return count;
    }

    /**
     * 获取最大的成品序号
     *
     * @param seqNo 统一编号
     * @param chgTmsCnt 变更次数
     * @return
     */
    @Override
    public Integer getMaxGdsSeqno(String seqNo, Integer chgTmsCnt) {
        //获取正式表当前档案库成品商品序号最大值
        Map map = new HashMap();
        map.put("seqNo", seqNo);
        map.put("chgTmsCnt", chgTmsCnt);
        Integer maxArcrpGdsSeqno = etArcrpExgMapper.getMaxGdsSeqno(map);
        Integer maxCusGdsSeqno = etCusExgMapper.getMaxGdsSeqno(seqNo);
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
     * @param etArcrpExgList
     * @return
     */
    @Override
    public boolean deleteAdd(String uid, List<EtArcrpExg> etArcrpExgList) {
        if (etArcrpExgList.size() > 0) {
            for (EtArcrpExg etArcrpExg : etArcrpExgList) {
                etArcrpExg.setGdsSeqno(etArcrpExg.getGdsSeqno() - 1);
            }
            if (!updateBatchById(etArcrpExgList)) {
                return false;
            }
        }
        return deleteById(uid);
    }

}
