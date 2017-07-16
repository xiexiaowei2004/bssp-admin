package com.powerbridge.bssp.saspass.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.saspass.dao.SasPassportBscMapper;
import com.powerbridge.bssp.saspass.dao.SasPassportDtMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportRltMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportRltService;
import org.springframework.transaction.annotation.Transactional;

@Service("sasPassportRltService")
@Transactional(readOnly = true)
public class SasPassportRltServiceImpl extends BaseServiceImpl<SasPassportRltMapper, SasPassportRlt> implements ISasPassportRltService {

    @Autowired
    private SasPassportRltMapper sasPassportRltMapper;
    @Autowired
    private SasPassportBscMapper sasPassportBscMapper;
    @Autowired
    private SasPassportDtMapper sasPassportDtMapper;

    @Override
    public Page<SasPassportRlt> selectBySasPassportRlt(Page<SasPassportRlt> page, SasPassportRlt sasPassportRlt){
        page.setRecords(sasPassportRltMapper.selectBySasPassportRlt(page, sasPassportRlt));
        return page;
    }

    @Override
    @Transactional
    public Boolean batchInsert(List<SasPassportRlt> entitys) {

        SasPassportBsc sasPassportBsc = sasPassportBscMapper.selectOne(new SasPassportBsc(entitys.get(0).getSeqNo()));

        StringBuffer rltNo = new StringBuffer();
        if (StringUtil.isNotEmpty(sasPassportBsc.getRltNo())){
            rltNo.append(sasPassportBsc.getRltNo() + ",");
        }
        for (SasPassportRlt entity:entitys){
            entity.setUid(UUIDGenerator.getUUID());
            entity.setChgTmsCnt(0);
            rltNo.append(entity.getRltNo() + ",");

            sasPassportRltMapper.insert(entity);
        }
        //更新核放单表的关联单号
        sasPassportBsc.setRltNo(rltNo.substring(0,rltNo.length()-1));
        sasPassportBscMapper.updateById(sasPassportBsc);
        //新增核放单货物表
        SasPassportDt SasPassportDt = new SasPassportDt();

        sasPassportDtMapper.insert(SasPassportDt);
        return true;
    }

    @Override
    @Transactional
    public Boolean batchAndCascadeInsert(SasPassportBsc sasPass){
        //获取关联单表批量信息
        List<SasPassportRlt> sasPassportRlts = sasPass.getSasPassportRlts();
        //获取货物表批量信息
        List<SasPassportDt> sasPassportDts = sasPass.getSasPassportDts();
        //毛重
        BigDecimal totalGrossWt = BigDecimal.ZERO;
        //净重
        BigDecimal totalNetWt  = BigDecimal.ZERO;
        //总重量
        BigDecimal totalWt = BigDecimal.ZERO;

        //获取原核放单信息
        SasPassportBsc sasPassportBsc = null;

        if (StringUtil.isNotEmpty(sasPass.getUid())){
            sasPassportBsc = sasPassportBscMapper.selectById(sasPass.getUid());
        }else{
            return false;
        }

        StringBuffer sb = new StringBuffer();
        //原核放单中存在关联单编号，在其尾部追加分隔符
        if (StringUtil.isNotEmpty(sasPassportBsc.getRltNo())){
            sb.append(sasPassportBsc.getRltNo() + "\\");
        }

        //计算原净重
        if (sasPassportBsc.getTotalNetWt() != null){
            totalNetWt = totalNetWt.add(sasPassportBsc.getTotalNetWt());//加原净重
        }
        //计算原毛重
        if (sasPassportBsc.getTotalGrossWt() != null){
            totalGrossWt = totalGrossWt.add(sasPassportBsc.getTotalGrossWt());//加原毛重
        }
        //计算原总重 = 车架重 + 集装箱重 + 毛重 + 车自重
        /*if (sasPassportBsc.getTotaLWt() != null){
            totalWt = totalWt.add(sasPassportBsc.getTotaLWt());
        }*/
        totalWt = totalWt.add(totalGrossWt);//毛重
        if (sasPassportBsc.getVehicleFrameWt() != null){
            totalWt = totalWt.add(sasPassportBsc.getVehicleFrameWt());//车架重
        }
        if (sasPassportBsc.getContainerWt() != null){
            totalWt = totalWt.add(sasPassportBsc.getContainerWt());//集装箱重
        }
        if (sasPassportBsc.getVehicleWt() != null){
            totalWt = totalWt.add(sasPassportBsc.getVehicleWt());//车自重
		}

        if (sasPassportRlts.size() > 0 && sasPassportDts.size() > 0){
            for (SasPassportRlt entity:sasPassportRlts){
                sb.append(entity.getRltNo() + "\\");
                entity.setUid(UUIDGenerator.getUUID());
                /*entity.setChgTmsCnt(0);*/ //从对应合注清单货物中或出入库单货物中获取
            }
            for (SasPassportDt entity : sasPassportDts) {
                entity.setUid(UUIDGenerator.getUUID());
                BigDecimal grossWt = entity.getGrossWt();//获取毛重
                BigDecimal netWt = entity.getNetWt();//获取净重
                if (grossWt != null){
                    totalGrossWt = totalGrossWt.add(grossWt);
                    //加新增的获取重量
                    totalWt = totalWt.add(grossWt);
                }
                if (netWt != null){
                    totalNetWt = totalNetWt.add(netWt);
                }
                /*entity.setChgTmsCnt(0);*/ //从对应合注清单货物中或出入库单货物中获取
            }
            //批量插入关联单表
            sasPassportRltMapper.insertByBatch(sasPassportRlts);
            //批量插入货物表
            sasPassportDtMapper.insertByBatch(sasPassportDts);
        }else{
            return false;
        }
        //更新核放单毛重，净重，总重
        sasPassportBsc.setTotalGrossWt(totalGrossWt);
        sasPassportBsc.setTotalNetWt(totalNetWt);
        sasPassportBsc.setTotaLWt(totalWt);

        //更新核放单关联单编号
        sasPassportBsc.setRltNo(sb.substring(0,sb.length()-1));
        sasPassportBsc.setMasterCuscd(sasPass.getMasterCuscd());//设置主管关区
        //持久化核放单
        sasPassportBscMapper.updateById(sasPassportBsc);
        return true;
    }

    @Override
    public List<SasPassportRlt> selectByRltNos(List<String> rltNos){
        Map<String, Object> rltNosParam = new HashMap<String, Object>();
        rltNosParam.put("rltNos", rltNos);
        rltNosParam.put("seqNo",null);
        return sasPassportRltMapper.selectByRltNosAndSeqNo(rltNosParam);
    }

    @Override
    public List<SasPassportRlt> selectByRltNosAndSeqNo(List<String> rltNos,String seqNo){
        Map<String, Object> rltNosParam = new HashMap<String, Object>();
        rltNosParam.put("rltNos", rltNos);
        rltNosParam.put("seqNo",seqNo);
        return sasPassportRltMapper.selectByRltNosAndSeqNo(rltNosParam);
    }

    /**
     * 重写IService的deleteBatchIds方法
     * 1.批量删除sasPassportRlt<关联单证表></>
     * 2.批量并级联删除sasPassportDt<货物表></>
     * 3.同步sasPassportBsc的关联单编号<核放单></>
     * @param idList
     * @return
     */
    @Override
    @Transactional
    public boolean deleteBatchIds(List<? extends Serializable> idList) {
        for (Serializable serializable : idList) {
            /*******************************核放单操作部分********************************/
            //获取当前删除表-->关联表的对应数据
            SasPassportRlt sasPassportRlt = sasPassportRltMapper.selectById(serializable);
            //根据关联表的seqNo获取主表-->核放单表的对应数据
            SasPassportBsc sasPassportBsc = sasPassportBscMapper.selectOne(new SasPassportBsc(sasPassportRlt.getSeqNo()));
            //从核放单表获取RltNo
            String rltNos = sasPassportBsc.getRltNo();
            //从核放单表获取总重
            BigDecimal totalWt = sasPassportBsc.getTotaLWt();
            //从核放单表获取毛重
            BigDecimal totalGrossWt = sasPassportBsc.getTotalGrossWt();
            //从核放单表获取净重
            BigDecimal totalNetWt = sasPassportBsc.getTotalNetWt();

            //获取核放单中RltNo字段转化数组
            String[] rltNoArray = sasPassportBsc.getRltNo().split("\\\\");
            //以数组缩容的形式同步删除核放单中对应于关联单的关联单证编号
            for (int i = 0; i < rltNoArray.length; i++) {
                //匹配关联表的RltNo在核放单中是否存在
                if(StringUtil.equals(rltNoArray[i],sasPassportRlt.getRltNo())){
                    rltNoArray[i] = rltNoArray[rltNoArray.length-1];
                    //数组缩容
                    rltNoArray = Arrays.copyOf(rltNoArray, rltNoArray.length-1);
                    break;
                }
            }
            String newRltNo = Arrays.toString(rltNoArray);
            newRltNo = newRltNo.replace(",","\\")
                    .replace("[","")
                    .replace("]","")
                    .replace(" ","");
            sasPassportBsc.setRltNo(newRltNo);

            /*******************************货物单操作部分********************************/
            EntityWrapper<SasPassportDt> wrapper = new EntityWrapper<SasPassportDt>();
            wrapper.eq("SEQ_NO",sasPassportRlt.getSeqNo());
            wrapper.eq("RLT_NO",sasPassportRlt.getRltNo());
            List<SasPassportDt> dtList = sasPassportDtMapper.selectList(wrapper);
            for (int i = 0; i < dtList.size(); i++) {
                SasPassportDt sasPassportDt =  dtList.get(i);
                BigDecimal grossWt = sasPassportDt.getGrossWt();
                BigDecimal netWt = sasPassportDt.getNetWt();
                //同步删除货物毛重，净重,总重的数值
                if (grossWt != null){
                    totalGrossWt = totalGrossWt.subtract(grossWt);
                    totalWt = totalWt.subtract(grossWt);
                }
                if (netWt != null){
                    totalNetWt = totalNetWt.subtract(netWt);
                }
            }
            sasPassportDtMapper.delete(wrapper);

            sasPassportBsc.setTotalGrossWt(totalGrossWt);
            sasPassportBsc.setTotalNetWt(totalNetWt);
            sasPassportBsc.setTotaLWt(totalWt);
            sasPassportBscMapper.updateById(sasPassportBsc);
        }
        /*******************************关联单操作部分********************************/
        //继承父类执行删除
        return super.deleteBatchIds(idList);
    }

}
