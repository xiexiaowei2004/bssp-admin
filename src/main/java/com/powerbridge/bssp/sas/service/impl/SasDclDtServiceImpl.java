package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDclDtMapper;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import com.powerbridge.bssp.sas.service.ISasDclDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报商品表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@Service("sasDclDtService")
public class SasDclDtServiceImpl extends BaseServiceImpl<SasDclDtMapper, SasDclDt> implements ISasDclDtService {

    @Autowired
    private SasDclDtMapper sasDclDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param sasDclDt     数据
     * @return Page<SasDclDt>
     */
    public Page<SasDclDt> selectByList(Page<SasDclDt> page, SasDclDt sasDclDt) {
        page.setRecords(sasDclDtMapper.selectByList(page, sasDclDt));
        return page;
    }

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    public void updateBySasDclSeqno(String seqNo,int sasDclSeqno){
        sasDclDtMapper.updateBySasDclSeqno(seqNo,sasDclSeqno);
    }
}
