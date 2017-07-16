package com.powerbridge.bssp.dcr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrChgoffInvtLt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DcrChgoffInvtLtMapper
 * 类描述：DcrChgoffInvtLt 账册报核清单 Mapper 接口
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日 下午17:10:17
 */
public interface DcrChgoffInvtLtMapper extends BaseMapper<DcrChgoffInvtLt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrChgoffInvtLt>
     */
    List<DcrChgoffInvtLt> selectByList(Page<DcrChgoffInvtLt> page, String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    int updateLno(@Param("seqNo") String seqNo, @Param("lNo") int lNo);
}