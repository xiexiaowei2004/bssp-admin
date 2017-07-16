package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportCusRlt;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SasPassportCusRltMapper extends BaseMapper<SasPassportCusRlt> {

    public List<SasPassportCusRlt> selectByRltNosAndSeqNo(Map<String, Object> map);

    public List<SasPassportCusRlt> selectBySasPassportCusRlt(Page<SasPassportCusRlt> page, SasPassportCusRlt sasPassportCusRlt);
}
