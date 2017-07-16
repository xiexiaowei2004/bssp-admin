package com.powerbridge.bssp.dcr.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账册报核基础表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
public interface IDcrChgoffBscService extends IService<DcrChgoffBsc> {

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffBsc 加工贸易账册核销数据
     * @return Page<DcrChgoffBsc>
     */
    Page<DcrChgoffBsc> selectByList(Page<DcrChgoffBsc> page, DcrChgoffBsc dcrChgoffBsc);

    /**
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    boolean txDeleteDcrChgoffBsc(String seqNo);

    /**
     * @param seqNoByList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
    boolean txDeleteDcrChgoffBscByList(String seqNoByList);
    
    /**
	 * 
	 * @Description 拼接业务单据sql
	 * @param str
	 * @return
	 */
    public DcrChgoffBsc getBusinessData(@Param(value="str") String str);
}
