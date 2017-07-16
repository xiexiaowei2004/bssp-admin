package com.powerbridge.bssp.dcr.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.dcr.dao.DcrChgoffExgMapper;
import com.powerbridge.bssp.dcr.entity.DcrChgoffExg;
import com.powerbridge.bssp.dcr.service.IDcrChgoffExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册报核成品 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
@Service("dcrChgoffExgService")
public class DcrChgoffExgServiceImpl extends BaseServiceImpl<DcrChgoffExgMapper, DcrChgoffExg> implements IDcrChgoffExgService {

    @Autowired
    private DcrChgoffExgMapper dcrChgoffExgMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffInvtLt>
     */
    public Page<DcrChgoffExg> selectByList(Page<DcrChgoffExg> page, String seqNo) {
        page.setRecords(dcrChgoffExgMapper.selectByList(page, seqNo));
        return page;
    }

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    public void updateGdsSeqno(String seqNo,int gdsSeqno){
        dcrChgoffExgMapper.updateGdsSeqno(seqNo,gdsSeqno);
    }
}
