package com.powerbridge.bssp.ems_bws.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.CommonConstants;
import com.powerbridge.bssp.common.util.HttpClientUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsBscMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusBscService;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsCusBscMapper;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusDtService;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsDtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 项目名称：bssp-admin
 * 类名称：EmsBwsCusBscServiceImpl
 * 类描述：账册备案申请表服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsBwsCusBscService")
public class EmsBwsCusBscServiceImpl extends BaseServiceImpl<EmsBwsCusBscMapper, EmsBwsCusBsc> implements IEmsBwsCusBscService {

    @Autowired
    private EmsBwsCusBscMapper emsBwsCusBscMapper;
    @Autowired
    private EmsBwsBscMapper emsBwsBscMapper;
    @Autowired
    private IEmsBwsCusDtService emsBwsCusDtService;
    @Autowired
    private IEmsBwsDtService emsBwsDtService;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param emsBwsCusBsc
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsBwsCusBsc> selectEmsBwsCusBscList(Page<EmsBwsCusBsc> page, EmsBwsCusBsc emsBwsCusBsc){
        page.setRecords(emsBwsCusBscMapper.selectEmsBwsCusBscList(page, emsBwsCusBsc));
        return page;
    }
    /**
     * 查询企业下的所有账册号
     */
    public List<EmsBwsCusBsc> selectBwsNoList(EmsBwsCusBsc emsBwsCusBsc){
        List<EmsBwsCusBsc> list=emsBwsCusBscMapper.selectBwsNoList(emsBwsCusBsc);
        return list;
    }
    /**
     * 选择账册正式表一条数据获得账册备案变更的数据
     * @param emsBwsCusBsc 账册正式表
     * @param appId
     * @return EmsCusBsc
     */
    private EmsBwsBsc getEmsBwsBscChgData(EmsBwsCusBsc emsBwsCusBsc, String appId) {
        //复制表头数据
        EmsBwsBsc emsBwsBsc = new EmsBwsBsc();
        BeanUtils.copyProperties(emsBwsCusBsc, emsBwsBsc);
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        emsBwsBsc.setUid(uId);//主键
        emsBwsBsc.setDecTime(DateUtil.now());//录入日期
        emsBwsBsc.setDclTime(DateUtil.now());//申报日期
        emsBwsBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);//单据状态
        emsBwsBsc.setDclTypecd("2");//申报类型为变更
        emsBwsBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_BWS_5);//审批状态
        emsBwsBsc.setRetChannel("");
        //变更次数,取正式表变更次数+1
        BigDecimal chgTmsCnt = emsBwsBsc.getChgTmsCnt();
        chgTmsCnt=chgTmsCnt.add(new BigDecimal(1));
        emsBwsBsc.setChgTmsCnt(chgTmsCnt);
        return emsBwsBsc;
    }
    /**
     * 选择账册正式表一条数据生成备案变更的数据
     *
     * @param emsBwsCusBsc 账册正式表
     * @param appId
     * @return boolean
     */
    @Transactional
    public EmsBwsBsc generateEmsBwsChgData(EmsBwsCusBsc emsBwsCusBsc, String appId) {
        EmsBwsBsc emsBwsBsc = getEmsBwsBscChgData(emsBwsCusBsc, appId);
        int r = emsBwsBscMapper.insert(emsBwsBsc);
        if (r == -1) {
            return null;
        }
        List<EmsBwsDt> emsBwsDtList = emsBwsCusDtService.getEmsBwsDtChgData(emsBwsCusBsc.getBwsNo());
        if(emsBwsDtList.size()>0) {
            emsBwsDtService.insertBatch(emsBwsDtList);
        }
        return emsBwsBsc;
    }
}
