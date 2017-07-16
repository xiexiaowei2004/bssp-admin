package com.powerbridge.bssp.inv.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvExgMapper;
import com.powerbridge.bssp.inv.entity.InvExg;
import com.powerbridge.bssp.inv.service.IInvExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：InvExgServiceImpl
 * 类描述：InvExg 表业务逻辑层接口 实现类
 * 创建时间：2017年5月17日 下午10:51:17
 */
@Service("invExgService")
public class InvExgServiceImpl extends BaseServiceImpl<InvExgMapper, InvExg> implements IInvExgService {

    @Autowired
    private InvExgMapper invExgMapper;


    /**
     * @param seqNo      String 单据编号
     * @param invExgList List<InvExg> 报关商品数据
     * @param orgFlag    String 归并方式
     * @return boolean
     * @throws
     * @Description: 用与生成报关商品数据
     */
    //@Transactional
    public boolean txInsertByInvExg(List<InvExg> invExgList, String seqNo, String orgFlag) {
        // 第一步  先删除原有报关商品数据
        EntityWrapper<InvExg> invExgEntityWrapper = new EntityWrapper<>();
        invExgEntityWrapper.eq("SEQ_NO", seqNo);
        invExgMapper.delete(invExgEntityWrapper);
        // 第二步  生成报关商品数据
        invExgMapper.insertBatch(invExgList);
        // 第三步 将报关商品序号回填给清单商品
      /*  for (InvExg bondInvtExg : invExgList) {
            invImgMapper.updateByInvImgList(bondInvtExg, orgFlag);
        }*/
        return true;
    }
    /**
     * @param
     * @param
     * @return List<InvExg>
     * @throws
     * @Description:
     */
    public List<InvExg> autoGetInvExgList() {
        return invExgMapper.selectByBondInvtExgList();
    }
}
