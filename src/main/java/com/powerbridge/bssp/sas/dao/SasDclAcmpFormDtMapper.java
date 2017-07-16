package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclAcmpFormDt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 业务申报随附单证表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
public interface SasDclAcmpFormDtMapper extends BaseMapper<SasDclAcmpFormDt> {
    /**
     * 查询/分页
     *
     * @param page  分页
     * @param seqNo 数据
     * @param seqNo 数据
     * @return List<SasDclAcmpFormDt>
     */
    List<SasDclAcmpFormDt> selectByList(Page<SasDclAcmpFormDt> page,String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    int updateAcmpFormSeqno(@Param("seqNo") String seqNo,@Param("acmpFormSeqno") int acmpFormSeqno);
}