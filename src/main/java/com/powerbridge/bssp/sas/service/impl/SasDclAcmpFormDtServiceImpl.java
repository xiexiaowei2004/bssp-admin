package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasDclAcmpFormDtMapper;
import com.powerbridge.bssp.sas.entity.SasDclAcmpFormDt;
import com.powerbridge.bssp.sas.service.ISasDclAcmpFormDtService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报随附单证表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@Service("sasDclAcmpFormDtService")
public class SasDclAcmpFormDtServiceImpl extends BaseServiceImpl<SasDclAcmpFormDtMapper, SasDclAcmpFormDt> implements ISasDclAcmpFormDtService {

    @Autowired
    private SasDclAcmpFormDtMapper sasDclAcmpFormDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param seqNo        参数数据
     * @return Page<SasDclAcmpFormDt>
     */
    public Page<SasDclAcmpFormDt> selectByList(Page<SasDclAcmpFormDt> page, String seqNo) {
        page.setRecords(sasDclAcmpFormDtMapper.selectByList(page, seqNo));
        return page;
    }

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    public void updateAcmpFormSeqno(String seqNo,int acmpFormSeqno){
        sasDclAcmpFormDtMapper.updateAcmpFormSeqno(seqNo,acmpFormSeqno);
    }
}
