package com.powerbridge.bssp.inv.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.*;
import com.powerbridge.bssp.inv.entity.InvBsc;
import com.powerbridge.bssp.inv.entity.InvDt;
import com.powerbridge.bssp.inv.service.IInvBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：InvBscServiceImpl
 * 类描述：InvBsc 表业务逻辑层接口 实现类
 * 创建时间：2017年5月17日 下午10:51:17
 */
@Service("invBscService")
public class InvBscServiceImpl extends BaseServiceImpl<InvBscMapper, InvBsc> implements IInvBscService {

    @Autowired
    private InvBscMapper invBscMapper;

    @Autowired
    private InvAcmpFormDtMapper invAcmpFormDtMapper;

    @Autowired
    private InvDtMapper invImgMapper;

    @Autowired
    private InvDecBscMapper invDecBscMapper;

    @Autowired
    private InvDecDtMapper invDecDtMapper;

    /**
     * @param id
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    @Transactional
    public boolean txDeleteInvBsc(String id, String seqNo) {

        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("SEQ_NO", seqNo);
        invAcmpFormDtMapper.delete(entityWrapper);
        invImgMapper.delete(entityWrapper);
        boolean flag = retBool(invBscMapper.deleteById(id));
        return flag;
    }

    /**
     * @param idList
     * @param seqNoByList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
    @Transactional
    public boolean txDeleteInvBscByList(List<String> idList, List<String> seqNoByList) {
        EntityWrapper entityWrapper = new EntityWrapper<InvDt>();
       // EntityWrapper entityWrapper1 = new EntityWrapper<InvDeclareBill>();
        //EntityWrapper entityWrapper2 = new EntityWrapper<InvDecBsc>();
        entityWrapper.eq("SEQ_NO",seqNoByList);
       // entityWrapper1.eq("SEQ_NO",seqNoByList);
        //entityWrapper2.eq("SEQ_NO",seqNoByList);
        invAcmpFormDtMapper.delete(entityWrapper);
        invImgMapper.delete(entityWrapper);
        invDecBscMapper.delete(entityWrapper);
        invDecDtMapper.delete(entityWrapper);
        boolean flag = retBool(invBscMapper.deleteBatchIds(idList));
        return flag;
    }

    /**
     * 查询核注清单信息
     *
     * @param page 分页
     * @param invBsc
     * @return Page<InvBsc>
     */
    public Page<InvBsc> selectByInveBsc(Page<InvBsc> page, InvBsc invBsc){
        page.setRecords(invBscMapper.selectByInvBsc(page, invBsc));
        return page;
    }
}
