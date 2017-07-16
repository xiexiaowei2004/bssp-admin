package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.*;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.ems.service.IEmsPutrecBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecBscServiceImpl
 * 类描述：账册备案申请表服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsPutrecBscService")
public class EmsPutrecBscServiceImpl extends BaseServiceImpl<EmsPutrecBscMapper, EmsPutrecBsc> implements IEmsPutrecBscService {

    @Autowired
    private EmsPutrecBscMapper emsPutrecBscMapper;
    @Autowired
    private EmsPutrecImgMapper emsPutrecImgMapper;
    @Autowired
    private EmsPutrecExgMapper emsPutrecExgMapper;
    @Autowired
    private EmsPutrecUcnsDtMapper emsPutrecUcnsDtMapper;
    @Autowired
    private EmsPutrecAcmpFormDtMapper emsPutrecAcmpFormDtMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param emsPutrecBsc
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsPutrecBsc> selectEmsPutrecBscList(Page<EmsPutrecBsc> page, EmsPutrecBsc emsPutrecBsc){
        page.setRecords(emsPutrecBscMapper.selectEmsPutrecBscList(page, emsPutrecBsc));
        return page;
    }
	@Override
	public EmsPutrecBsc getBusinessData(String str) {
		return emsPutrecBscMapper.getBusinessData(str);
	}
    /**
     * 批量删除
     * @param idList 主键集合
     * @return
     */
    @Transactional
    public boolean deleteBySeqNoList(List<String> idList){
        EntityWrapper entityWrapper = new EntityWrapper();
        //entityWrapper.setSqlSelect("SEQ_NO,CHG_TMS_CNT");
        entityWrapper.in("uid",idList);
        List<EmsPutrecBsc> emsPutrecBscList=emsPutrecBscMapper.selectList(entityWrapper);
        for (EmsPutrecBsc emsPutrecBsc:emsPutrecBscList) {
            //根据表头Id 查找SEQ_NO和CHG_TMS_CNT删除子表
            EntityWrapper delEntityWrapper=new EntityWrapper();
            delEntityWrapper.eq("SEQ_NO", emsPutrecBsc.getSeqNo());
            delEntityWrapper.eq("CHG_TMS_CNT", emsPutrecBsc.getChgTmsCnt());
            emsPutrecImgMapper.delete(delEntityWrapper);//删除料件
            emsPutrecExgMapper.delete(delEntityWrapper);//删除成品
            emsPutrecUcnsDtMapper.delete(delEntityWrapper);//删除单耗
            emsPutrecAcmpFormDtMapper.delete(delEntityWrapper);//删除随单附证
        }
        boolean flag = retBool(emsPutrecBscMapper.delete(entityWrapper));
        return flag;
    }
    /**
     * 根据单据编号 判断单据是否存在
     * @param seqNo
     * @return
     */
    public boolean isExistBsc(String seqNo){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO",seqNo);
        int count = emsPutrecBscMapper.selectCount(entityWrapper);
        if(count == 0)
            return false;
        return  true;
    }
}
