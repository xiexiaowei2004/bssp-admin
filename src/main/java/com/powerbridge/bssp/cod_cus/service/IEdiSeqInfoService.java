package com.powerbridge.bssp.cod_cus.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;

import java.util.List;

/**
 *
 * 项目名称：bssp Maven Webapp
 * 类名称：IEdiSeqInfoService
 * 类描述：EdiSeqInfo 表业务逻辑层接口
 * 创建人：haihuihuang
 * 创建时间：2017年4月27日 下午10:12:17
 * @version
 */
public interface IEdiSeqInfoService extends IService<EdiSeqInfo> {
    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param emsBwsDt
     * @return
     * @throws Exception
     */
    Page<EdiSeqInfo> selectEdiSeqInfoList(Page<EdiSeqInfo> page, EdiSeqInfo ediSeqInfo) throws Exception;

}
