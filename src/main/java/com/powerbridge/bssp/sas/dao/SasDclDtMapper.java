package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 业务申报商品表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
public interface SasDclDtMapper extends BaseMapper<SasDclDt> {

    /**
     * 查询/分页
     *
     * @param page  分页
     * @param sasDclDt 数据
     * @return List<SasDclDt>
     */
    List<SasDclDt> selectByList(Page<SasDclDt> page, SasDclDt sasDclDt);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将商品序号重新排序
     */
    int updateBySasDclSeqno(@Param("seqNo") String seqNo,@Param("sasDclSeqno") int sasDclSeqno);
}