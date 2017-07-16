package com.powerbridge.bssp.cod_cus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("ediSeqInfoMapper")
public interface EdiSeqInfoMapper extends BaseMapper<EdiSeqInfo> {

    List<EdiSeqInfo> selectEdiSeqInfoList(Page<EdiSeqInfo> page, EdiSeqInfo emsBwsDt);
}