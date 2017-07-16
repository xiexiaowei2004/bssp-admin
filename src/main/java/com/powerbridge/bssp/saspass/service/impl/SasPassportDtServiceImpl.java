package com.powerbridge.bssp.saspass.service.impl;

import com.powerbridge.bssp.saspass.dao.SasPassportBscMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportDtMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.service.ISasPassportDtService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Service("sasPassportDtService")
public class SasPassportDtServiceImpl extends BaseServiceImpl<SasPassportDtMapper, SasPassportDt> implements ISasPassportDtService {

	@Autowired
    private SasPassportDtMapper sasPassportDtMapper;

	@Autowired
	private SasPassportBscMapper sasPassportBscMapper;

	@Override
	public SasPassportDt selectByUid(String id){
        return sasPassportDtMapper.selectByUid(id);
    }

    @Override
    public Page<SasPassportDt> selectBySasPassportDt(Page<SasPassportDt> page, SasPassportDt sasPassportDt){
        page.setRecords(sasPassportDtMapper.selectBySasPassportDt(page, sasPassportDt));
        return page;
    }

    @Override
    @Transactional
    public boolean insert(SasPassportDt entity) {
        increaseWt(entity);
        return super.insert(entity);
    }

    @Override
    @Transactional
    public boolean updateById(SasPassportDt entity) {
        updateWt(entity);
        return super.updateById(entity);
    }

    @Override
    @Transactional
    public boolean deleteBatchIds(List<? extends Serializable> idList) {
        for (Serializable serializable : idList) {
            SasPassportDt entity = sasPassportDtMapper.selectById(serializable);
            reduceWt(entity);
        }
        return super.deleteBatchIds(idList);
    }

    /**
     * 增加重量
     * @param entity
     */
    private void increaseWt(SasPassportDt entity){
        SasPassportBsc sasPassportBsc = getSasPassportBsc(entity);
        //毛重
        BigDecimal totalGrossWt = BigDecimal.ZERO;
        //净重
        BigDecimal totalNetWt  = BigDecimal.ZERO;
        //总重量
        BigDecimal totalWt = BigDecimal.ZERO;

        //计算原净重
        if (sasPassportBsc.getTotalNetWt() != null){
            totalNetWt = totalNetWt.add(sasPassportBsc.getTotalNetWt());//加原净重
        }
        //计算原毛重
        if (sasPassportBsc.getTotalGrossWt() != null){
            totalGrossWt = totalGrossWt.add(sasPassportBsc.getTotalGrossWt());//加原毛重
        }
        //计算原总重 = 毛重 + 车架重 + 集装箱重 + 车自重
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
        //更新核放单毛重，净重，总重
        updateSasPassportBsc(sasPassportBsc,totalGrossWt,totalNetWt,totalWt);
    }

    /**
     * 减少重量
     * @param entity
     */
    private void reduceWt(SasPassportDt entity){
        SasPassportBsc sasPassportBsc = getSasPassportBsc(entity);
        //毛重
        BigDecimal totalGrossWt = BigDecimal.ZERO;
        //净重
        BigDecimal totalNetWt  = BigDecimal.ZERO;
        //总重量
        BigDecimal totalWt = BigDecimal.ZERO;
        //计算原净重
        if (sasPassportBsc.getTotalNetWt() != null){
            totalNetWt = totalNetWt.add(sasPassportBsc.getTotalNetWt());//加原净重
        }
        //计算原毛重
        if (sasPassportBsc.getTotalGrossWt() != null){
            totalGrossWt = totalGrossWt.add(sasPassportBsc.getTotalGrossWt());//加原毛重
        }
        //计算原总重 = 毛重 + 车架重 + 集装箱重 + 车自重
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
        BigDecimal grossWt = entity.getGrossWt();//获取毛重
        BigDecimal netWt = entity.getNetWt();//获取净重
        if (grossWt != null && totalGrossWt.compareTo(grossWt)>=0 && totalWt.compareTo(grossWt)>=0){
            totalGrossWt = totalGrossWt.subtract(grossWt);
            //加新增的获取重量
            totalWt = totalWt.subtract(grossWt);
        }
        if (netWt != null && totalNetWt.compareTo(netWt)>=0){
            totalNetWt = totalNetWt.subtract(netWt);
        }
        //更新核放单毛重，净重，总重
        updateSasPassportBsc(sasPassportBsc,totalGrossWt,totalNetWt,totalWt);
    }

    /**
     * 更新重量
     * @param entity
     */
    public void updateWt(SasPassportDt entity){
        SasPassportBsc sasPassportBsc = getSasPassportBsc(entity);
        //获取原来的货物信息
        SasPassportDt oldEntity = sasPassportDtMapper.selectById(entity.getUid());
        //毛重
        BigDecimal totalGrossWt = BigDecimal.ZERO;
        //净重
        BigDecimal totalNetWt  = BigDecimal.ZERO;
        //总重量
        BigDecimal totalWt = BigDecimal.ZERO;

        //计算原净重
        if (sasPassportBsc.getTotalNetWt() != null){
            totalNetWt = totalNetWt.add(sasPassportBsc.getTotalNetWt());//加原净重
        }
        //计算原毛重
        if (sasPassportBsc.getTotalGrossWt() != null){
            totalGrossWt = totalGrossWt.add(sasPassportBsc.getTotalGrossWt());//加原毛重
        }
        //计算原总重 = 毛重 + 车架重 + 集装箱重 + 车自重
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
        //减去原来的重量
        BigDecimal oldGrossWt = oldEntity.getGrossWt();//获取毛重
        BigDecimal OldNetWt = oldEntity.getNetWt();//获取净重
        if (oldGrossWt != null && totalGrossWt.compareTo(oldGrossWt)>=0 && totalWt.compareTo(oldGrossWt)>=0){
            totalGrossWt = totalGrossWt.subtract(oldGrossWt);
            //加新增的获取重量
            totalWt = totalWt.subtract(oldGrossWt);
        }
        if (OldNetWt != null && totalNetWt.compareTo(OldNetWt)>=0){
            totalNetWt = totalNetWt.subtract(OldNetWt);
        }
        //增加新的重量
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
        //更新核放单毛重，净重，总重
        updateSasPassportBsc(sasPassportBsc,totalGrossWt,totalNetWt,totalWt);
    }

    /**
     * 获取核放单信息
     * @param entity
     * @return
     */
    private SasPassportBsc getSasPassportBsc(SasPassportDt entity){
        SasPassportBsc sasPassportBsc = new SasPassportBsc();
        sasPassportBsc.setSeqNo(entity.getSeqNo());
        return sasPassportBscMapper.selectOne(sasPassportBsc);
    }

    /**
     * 更新核放单
     * @param sasPassportBsc
     * @param totalGrossWt
     * @param totalNetWt
     * @param totalWt
     */
    private void updateSasPassportBsc(SasPassportBsc sasPassportBsc,BigDecimal totalGrossWt,BigDecimal totalNetWt,BigDecimal totalWt){
        //更新核放单毛重，净重，总重
        sasPassportBsc.setTotalGrossWt(totalGrossWt);
        sasPassportBsc.setTotalNetWt(totalNetWt);
        sasPassportBsc.setTotaLWt(totalWt);
        //持久化核放单
        sasPassportBscMapper.updateById(sasPassportBsc);
    }
}
