package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportHisRlt;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SasPassportHisRltMapper extends BaseMapper<SasPassportHisRlt> {

    public List<SasPassportHisRlt> selectByRltNosAndSeqNo(Map<String, Object> map);

    public List<SasPassportHisRlt> selectBySasPassportHisRlt(Page<SasPassportHisRlt> page, SasPassportHisRlt sasPassportHisRlt);
}
