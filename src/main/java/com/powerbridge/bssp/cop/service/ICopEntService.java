package com.powerbridge.bssp.cop.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cop.entity.CopBusiness;
import com.powerbridge.bssp.cop.entity.CopEnt;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ICodCusBriefService
 * 类描述：CodCusBrief 表业务逻辑层接口
 * 创建人：haihuihuang
 * 创建时间：2017年4月27日 下午10:12:17
 */
public interface ICopEntService extends IService<CopEnt> {

    /**
     * 查询企业备案/分页
     *
     * @param page   分页
     * @param copEnt 企业备案数据
     * @return List<CopEnt>
     */
    Page<CopEnt> selectByCopEnt(Page<CopEnt> page, CopEnt copEnt);

    /**
     * @param copEnt
     * @param copBusinessByList
     * @return boolean
     * @throws
     * @Description: 保存主表时，同时保存字表
     */
    boolean txInsertCopEnt(CopEnt copEnt, List<CopBusiness> copBusinessByList);

    /**
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    boolean txDeleteCopEnt(String seqNo);

    /**
     * @param seqNoByList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
    boolean txDeleteCopEntByList(List<String> seqNoByList);

    /**
     * 根据企业备案编码获取企业信息
     * 无法取到值，请暂勿使用
     * @param tradeCode
     * @return
     */
    public List<CopEnt> selectByTradeCode(String tradeCode);
}
