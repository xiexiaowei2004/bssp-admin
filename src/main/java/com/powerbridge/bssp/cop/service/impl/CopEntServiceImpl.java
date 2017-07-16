package com.powerbridge.bssp.cop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cop.dao.CopBusinessMapper;
import com.powerbridge.bssp.cop.dao.CopEntMapper;
import com.powerbridge.bssp.cop.entity.CopBusiness;
import com.powerbridge.bssp.cop.entity.CopEnt;
import com.powerbridge.bssp.cop.service.ICopEntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CopEntServiceImpl
 * 类描述：CopEnt 表业务逻辑层接口 实现类
 * 创建时间：2017年5月9日 下午10:51:17
 */
@Service("copEntService")
public class CopEntServiceImpl extends BaseServiceImpl<CopEntMapper, CopEnt> implements ICopEntService {

    @Autowired
    private CopEntMapper copEntMapper;

    @Autowired
    private CopBusinessMapper copBusinessMapper;

    /**
     * 查询企业备案/分页
     *
     * @param page   分页
     * @param copEnt 企业备案数据
     * @return List<CopEnt>
     */
    public Page<CopEnt> selectByCopEnt(Page<CopEnt> page, CopEnt copEnt) {
        page.setRecords(copEntMapper.selectByCopEnt(page, copEnt));
        return page;
    }

    /**
     * @param copEnt
     * @param copBusinessByList
     * @return boolean
     * @throws
     * @Description: 保存主表时，同时保存字表
     */
    @Transactional
    public boolean txInsertCopEnt(CopEnt copEnt, List<CopBusiness> copBusinessByList) {
        boolean copentFlag = retBool(copEntMapper.insert(copEnt));
//        boolean copBusinessFlag = retBool(copBusinessMapper.insertBatch(copBusinessByList));
        return copentFlag;
    }

    /**
     * @param seqNo
     * @return booleanR
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    @Transactional
    public boolean txDeleteCopEnt(String seqNo) {
        EntityWrapper entityWrapper = new EntityWrapper<CopBusiness>();
        entityWrapper.eq("SEQ_NO", seqNo);
        boolean copBusinessFlag = retBool(copBusinessMapper.delete(entityWrapper));
        boolean copentFlag = retBool(copEntMapper.delete(entityWrapper));
        return copBusinessFlag && copentFlag;
    }

    /**
     * @param seqNoByList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
    @Transactional
    public boolean txDeleteCopEntByList(List<String> seqNoByList) {
        EntityWrapper entityWrapper = new EntityWrapper<CopBusiness>();
        entityWrapper.in("SEQ_NO", seqNoByList);
        boolean copBusinessFlag = retBool(copBusinessMapper.delete(entityWrapper));
        boolean copentFlag = retBool(copEntMapper.delete(entityWrapper));
        return copBusinessFlag && copentFlag;
    }

    @Override
    public List<CopEnt> selectByTradeCode(String tradeCode){
        return copEntMapper.selectByTradeCode(tradeCode);
    }
}
