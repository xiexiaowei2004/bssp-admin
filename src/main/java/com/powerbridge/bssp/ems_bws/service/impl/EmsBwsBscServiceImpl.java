package com.powerbridge.bssp.ems_bws.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsAcmpFormDtMapper;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsDtMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsBscService;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsBscMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsBwsBscServiceImpl
 * 类描述：账册备案申请表服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsBwsBscService")
public class EmsBwsBscServiceImpl extends BaseServiceImpl<EmsBwsBscMapper, EmsBwsBsc> implements IEmsBwsBscService {

    @Autowired
    private EmsBwsBscMapper emsBwsBscMapper;
    @Autowired
    private EmsBwsDtMapper emsBwsDtMapper;
    @Autowired
    private EmsBwsAcmpFormDtMapper emsBwsAcmpFormDtMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param emsBwsBsc
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsBwsBsc> selectEmsBwsBscList(Page<EmsBwsBsc> page, EmsBwsBsc emsBwsBsc){
        page.setRecords(emsBwsBscMapper.selectEmsBwsBscList(page, emsBwsBsc));
        return page;
    }
	@Override
	public EmsBwsBsc getBusinessData(String str) {
		return emsBwsBscMapper.getBusinessData(str);
	}
    /**
     * 批量删除
     * @param seqNoByList 主键集合
     * @return
     */
    @Transactional
    public Boolean deleteBySeqNoList(List<String> seqNoByList){
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.in("SEQ_NO", seqNoByList);
        emsBwsDtMapper.delete(entityWrapper);//删除料件
        emsBwsAcmpFormDtMapper.delete(entityWrapper);//删除随单附证
        boolean flag = retBool(emsBwsBscMapper.delete(entityWrapper));
        return flag;
    }
}
