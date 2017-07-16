package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.CommonConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.util.HttpClientUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.dao.EmsCusBscMapper;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.ems.service.IEmsCusBscService;
import com.powerbridge.bssp.ems.service.IEmsPutrecBscService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusBscServiceImpl
 * 类描述：账册备案正式表服务实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:23
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:18
 */
@Service("emsCusBscService")
public class EmsCusBscServiceImpl extends BaseServiceImpl<EmsCusBscMapper, EmsCusBsc> implements IEmsCusBscService {
    @Autowired
    private EmsCusBscMapper emsCusBscMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param emsCusBsc
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsCusBsc> selectEmsCusBscList(Page<EmsCusBsc> page, EmsCusBsc emsCusBsc){
        page.setRecords(emsCusBscMapper.selectEmsCusBscList(page, emsCusBsc));
        return page;
    }
    /**
     * 查询企业下的所有账册号
     */
    public List<EmsCusBsc> selectEmsNoList(EmsCusBsc emsCusBsc){
        List<EmsCusBsc> list=emsCusBscMapper.selectEmsNoList(emsCusBsc);
        return list;
    }
    /**
     * 选择账册正式表一条数据获得账册备案变更的数据
     * @param emsCusBsc 账册正式表
     * @param appId
     * @return EmsCusBsc
     */
    public EmsPutrecBsc getEmsPutrecBscChgData(EmsCusBsc emsCusBsc,String appId) {
        //复制表头数据
        EmsPutrecBsc emsPutrecBsc = new EmsPutrecBsc();
        BeanUtils.copyProperties(emsCusBsc, emsPutrecBsc);
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        emsPutrecBsc.setUid(uId);//主键
        emsPutrecBsc.setDecTime(DateUtil.now());//录入日期
        emsPutrecBsc.setDclTime(DateUtil.now());//申报日期
        emsPutrecBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);//单据状态
        emsPutrecBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_2);
        emsPutrecBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);//审批状态
        emsPutrecBsc.setRetChannel("");
        //变更次数,取正式表变更次数+1
        BigDecimal chgTmsCnt = emsCusBsc.getChgTmsCnt();
        chgTmsCnt=chgTmsCnt.add(new BigDecimal(1));
        emsPutrecBsc.setChgTmsCnt(chgTmsCnt);
        emsPutrecBsc.setChkTime("");
        /*try {
            //统一内部编号
            Map<String, String> seqNoMap = new HashMap<>();
            seqNoMap.put("applyId", appId);
            seqNoMap.put("areaCode", emsCusBsc.getAreaCode());
            seqNoMap.put("docType", "A0401SeqNO");
            seqNoMap.put("serverType", "C");
            //企业内部编号
            Map<String, String> etpsPreentNoMap = new HashMap<>();
            etpsPreentNoMap.put("applyId", appId);
            etpsPreentNoMap.put("areaCode", emsCusBsc.getAreaCode());
            etpsPreentNoMap.put("docType", "A0401CopNO");
            etpsPreentNoMap.put("serverType", "C");
            String seqNo = GetBillNo(seqNoMap);
            String etpsPreentNo = GetBillNo(etpsPreentNoMap);
            emsPutrecBsc.setSeqNo(seqNo);//单据编号
            emsPutrecBsc.setEtpsPreentNo(etpsPreentNo);//企业内部编号
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return emsPutrecBsc;
    }

    /**
     *获取单据编号
     * @param paramMap
     * @return
     */
    public String GetBillNo(Map<String, String> paramMap)  {
        String data="";
        //服务地址
        String serverAddr = CommonConstants.billContextPath + "/bill/list";
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        try {
            String str = httpClientUtil.executeByPOST(serverAddr, paramMap);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(str);
            data = jsonObject.get("data").toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
