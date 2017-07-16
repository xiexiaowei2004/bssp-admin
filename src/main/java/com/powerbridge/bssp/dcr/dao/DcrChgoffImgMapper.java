package com.powerbridge.bssp.dcr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DcrChgoffImgMapper
 * 类描述：DcrChgoffImg 账册报核料件 Mapper 接口
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 下午17:10:17
 */
public interface DcrChgoffImgMapper extends BaseMapper<DcrChgoffImg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrChgoffImg>
     */
    List<DcrChgoffImg> selectByList(Page<DcrChgoffImg> page, String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    int updateGdsSeqno(@Param("seqNo") String seqNo, @Param("gdsSeqno") int gdsSeqno);
}