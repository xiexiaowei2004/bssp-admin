package com.powerbridge.bssp.edi.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ediRoutingInfoMapper")
public interface EdiRoutingInfoMapper extends BaseMapper<EdiRoutingInfo> {
    /**
     *
     * @param page
     * @param ediRoutingInfo
     * @return  List<EdiRoutingInfo>
     */

    List<EdiRoutingInfo> selectEdiRoutingInfoList(Page<EdiRoutingInfo> page, EdiRoutingInfo ediRoutingInfo);

}
