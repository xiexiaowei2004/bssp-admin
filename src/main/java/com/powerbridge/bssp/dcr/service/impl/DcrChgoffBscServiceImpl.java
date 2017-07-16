package com.powerbridge.bssp.dcr.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.dcr.dao.*;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;
import com.powerbridge.bssp.dcr.service.IDcrChgoffBscService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 账册报核基础表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
@Service("dcrChgoffBscService")
public class DcrChgoffBscServiceImpl extends BaseServiceImpl<DcrChgoffBscMapper, DcrChgoffBsc> implements IDcrChgoffBscService {

    @Autowired
    private DcrChgoffBscMapper dcrChgoffBscMapper;

    @Autowired
    private DcrChgoffExgMapper dcrChgoffExgMapper;

    @Autowired
    private DcrChgoffImgMapper dcrChgoffImgMapper;

    @Autowired
    private DcrChgoffInvtLtMapper dcrChgoffInvtLtMapper;

    @Autowired
    private DcrAdjaccAcmpFormDtMapper dcrAdjaccAcmpFormDtMapper;

    /**
     * 查询加工贸易账册核销备案/分页
     *
     * @param page         分页
     * @param dcrChgoffBsc 加工贸易账册核销数据
     * @return Page<DcrChgoffBsc>
     */
    public Page<DcrChgoffBsc> selectByList(Page<DcrChgoffBsc> page, DcrChgoffBsc dcrChgoffBsc) {
        page.setRecords(dcrChgoffBscMapper.selectByList(page, dcrChgoffBsc));
        return page;
    }

    /**
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    @Transactional
    public boolean txDeleteDcrChgoffBsc(String seqNo) {
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("SEQ_NO", seqNo);
        dcrChgoffExgMapper.delete(entityWrapper);
        dcrChgoffImgMapper.delete(entityWrapper);
        dcrChgoffInvtLtMapper.delete(entityWrapper);
        dcrAdjaccAcmpFormDtMapper.delete(entityWrapper);
        boolean flag = retBool(dcrChgoffBscMapper.delete(entityWrapper));
        return flag;
    }

    /**
     * @param seqNoByList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
    @Transactional
    public boolean txDeleteDcrChgoffBscByList(String seqNoByList) {
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.in("SEQ_NO", seqNoByList);
        dcrChgoffExgMapper.delete(entityWrapper);
        dcrChgoffImgMapper.delete(entityWrapper);
        dcrChgoffInvtLtMapper.delete(entityWrapper);
        dcrAdjaccAcmpFormDtMapper.delete(entityWrapper);
        boolean flag = retBool(dcrChgoffBscMapper.delete(entityWrapper));
        return flag;
    }

	@Override
	public DcrChgoffBsc getBusinessData(@Param(value="str") String str) {
		return dcrChgoffBscMapper.getBusinessData(str);
	}
}
