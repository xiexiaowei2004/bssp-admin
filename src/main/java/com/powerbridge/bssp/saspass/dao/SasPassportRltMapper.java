package com.powerbridge.bssp.saspass.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SasPassportRltMapper extends BaseMapper<SasPassportRlt> {

    public List<SasPassportRlt> selectByRltNosAndSeqNo(Map<String, Object> map/*, @Param("seqNo") String seqNo*/);

    public void insertByBatch(List<SasPassportRlt> entitys);

    public List<SasPassportRlt> selectBySasPassportRlt(Page<SasPassportRlt> page, SasPassportRlt sasPassportRlt);
}
