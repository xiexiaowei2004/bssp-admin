package com.powerbridge.bssp.dcr.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffExg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DcrChgoffExgMapper
 * 类描述：DcrChgoffExg 账册报核成品 Mapper 接口
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 下午17:10:17
 */
public interface DcrChgoffExgMapper extends BaseMapper<DcrChgoffExg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrChgoffImg>
     */
    List<DcrChgoffExg> selectByList(Page<DcrChgoffExg> page, String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    int updateGdsSeqno(@Param("seqNo") String seqNo, @Param("gdsSeqno") int gdsSeqno);
}