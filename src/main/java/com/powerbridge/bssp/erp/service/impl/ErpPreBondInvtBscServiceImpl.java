package com.powerbridge.bssp.erp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.CommonConstants;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.HttpClientUtil;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.erp.dao.ErpPreBondInvtBscMapper;
import com.powerbridge.bssp.erp.dao.ErpPreBondInvtDtMapper;
import com.powerbridge.bssp.erp.entity.ErpMidBondInvtBsc;
import com.powerbridge.bssp.erp.entity.ErpMidBondInvtDt;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtBsc;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtDt;
import com.powerbridge.bssp.erp.service.IErpMidBondInvtBscService;
import com.powerbridge.bssp.erp.service.IErpMidBondInvtDtService;
import com.powerbridge.bssp.erp.service.IErpPreBondInvtBscService;
import com.powerbridge.bssp.erp.service.IErpPreBondInvtDtService;
import com.powerbridge.bssp.inv.entity.InvBsc;
import com.powerbridge.bssp.inv.entity.InvDt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 核注清单表头-预处理 服务实现类
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-19
 */
@Service("erpPreBondInvtBscService")
public class ErpPreBondInvtBscServiceImpl extends ErpBaseServiceImpl<ErpPreBondInvtBscMapper, ErpPreBondInvtBsc> implements IErpPreBondInvtBscService {
    @Autowired
    ErpPreBondInvtBscMapper erpPreBondInvtBscMapper;
    @Autowired
    ErpPreBondInvtDtMapper erpPreBondInvtDtMapper;
    @Autowired
    private IErpMidBondInvtBscService erpMidBondInvtBscService;
    @Autowired
    private IErpMidBondInvtDtService erpMidBondInvtDtService;
    @Autowired
    private IErpPreBondInvtDtService erpPreBondInvtDtService;
    /**
     * 查询企业原始清单列表数据
     *
     * @param page   分页
     * @param erpPreBondInvtBsc 企业原始清单
     * @return List<ErpPreBondInvtBsc>
     */
    public Page<ErpPreBondInvtBsc> getListData(Page<ErpPreBondInvtBsc> page, ErpPreBondInvtBsc erpPreBondInvtBsc) {
        page.setRecords(erpPreBondInvtBscMapper.getListData(page, erpPreBondInvtBsc));
        return page;
    }
    /**
     * 删除企业原始清单
     *
     * @param id
     * @return Boolean
     */
    public Boolean deletePreBondInvt(String  id) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("uid",id);
        List<ErpPreBondInvtBsc> erpPreBondInvtBscList=erpPreBondInvtBscMapper.selectList(entityWrapper);
        for (ErpPreBondInvtBsc erpPreBondInvtBsc:erpPreBondInvtBscList) {
            //根据表头Id 查找etpsInnerInvtNo删除子表
            EntityWrapper delEntityWrapper=new EntityWrapper();
            delEntityWrapper.eq("ETPS_INNER_INVT_NO", erpPreBondInvtBsc.getEtpsInnerInvtNo());
            erpPreBondInvtDtMapper.delete(delEntityWrapper);
        }
        return retBool(erpPreBondInvtBscMapper.delete(entityWrapper));
    }

    /**
     * 从清单中间表取数据插入企业原始清单
     *
     * @return String
     */
    public String getMidData() {
        //服务调用
        //Logger logger = LoggerFactory.getLogger(this.getClass());
        //得到中间表的全部数据
        EntityWrapper entityWrapper = new EntityWrapper();
        List<ErpMidBondInvtBsc> erpMidBondInvtBscList = erpMidBondInvtBscService.selectList(entityWrapper);
        //获得中间表所有的数据，取得企业内部编号集合
        List midEtpsInnerInvtNoList = new ArrayList();
        for (ErpMidBondInvtBsc erpMidBondInvtBsc : erpMidBondInvtBscList) {
            String etpsInnerInvtNo = erpMidBondInvtBsc.getEtpsInnerInvtNo();
            midEtpsInnerInvtNoList.add(etpsInnerInvtNo);
        }

        //根据中间表的企业内部编号串过滤企业原始清单表，得到中间表中已备案清单集合（保留）和未备案清单（将删除后导入最新中间表数据）集合
        HashMap<String, ErpPreBondInvtBsc> ybaMap = new HashMap<String, ErpPreBondInvtBsc>();
        entityWrapper = new EntityWrapper();
        entityWrapper.eq("SUCCESS_FLAG","1");
        entityWrapper.in("ETPS_INNER_INVT_NO",midEtpsInnerInvtNoList);
        List<ErpPreBondInvtBsc> erpPreBondInvtBscList = selectList(entityWrapper);
        for (ErpPreBondInvtBsc erpPreBondInvtBsc : erpPreBondInvtBscList) {
            ybaMap.put(erpPreBondInvtBsc.getEtpsInnerInvtNo(),erpPreBondInvtBsc);
        }
        //未备案清单（将删除后导入最新中间表数据）
        entityWrapper = new EntityWrapper();
        entityWrapper.eq("SUCCESS_FLAG","0");
        entityWrapper.in("ETPS_INNER_INVT_NO",midEtpsInnerInvtNoList);
        List<ErpPreBondInvtBsc> delErpPreBondInvtBscList = selectList(entityWrapper);
        List<String> delPreBondInvtBscInvtNos = new ArrayList<String>();
        for (ErpPreBondInvtBsc erpPreBondInvtBsc:delErpPreBondInvtBscList) {
            delPreBondInvtBscInvtNos.add(erpPreBondInvtBsc.getEtpsInnerInvtNo());
        }
        List addErpPreBondInvtBscList = new ArrayList();
        List addErpPreBondInvtDtList = new ArrayList();
        for (ErpMidBondInvtBsc erpMidBondInvtBsc : erpMidBondInvtBscList) {
            String etpsInnerInvtNo = erpMidBondInvtBsc.getEtpsInnerInvtNo();
            if (!ybaMap.containsKey(etpsInnerInvtNo)) {
                ErpPreBondInvtBsc erpPreBondInvtBsc = new ErpPreBondInvtBsc();
                BeanUtils.copyProperties(erpMidBondInvtBsc, erpPreBondInvtBsc);
                erpPreBondInvtBsc.setUid(UUIDGenerator.getUUID());
                erpPreBondInvtBsc.setSuccessFlag("0");
                SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
                erpPreBondInvtBsc.setCreateBy(sysUser.getUserName());
                erpPreBondInvtBsc.setCreateTime(sysUser.getCreateTime());
                erpPreBondInvtBsc.setInputCopNo(sysUser.getInputCopNo());
                erpPreBondInvtBsc.setInputEtpsSccd(sysUser.getCopGbCode());
                erpPreBondInvtBsc.setInputCopName(sysUser.getInputCopName());
                addErpPreBondInvtBscList.add(erpPreBondInvtBsc);

                // 根据清单内部编号取中间表表体数据
                entityWrapper = new EntityWrapper();
                entityWrapper.eq("ETPS_INNER_INVT_NO",etpsInnerInvtNo);
                List<ErpMidBondInvtDt> dtList = erpMidBondInvtDtService.selectList(entityWrapper);
                for (ErpMidBondInvtDt erpMidBondInvtDt : dtList) {
                    ErpPreBondInvtDt erpPreBondInvtDt = new ErpPreBondInvtDt();
                    BeanUtils.copyProperties(erpMidBondInvtDt,erpPreBondInvtDt);
                    erpPreBondInvtDt.setUid(UUIDGenerator.getUUID());
                    addErpPreBondInvtDtList.add(erpPreBondInvtDt);
                }

            }
        }
        JSONObject result = new JSONObject();
        if (addErpPreBondInvtBscList.size()>0) {
            if (delErpPreBondInvtBscList.size()>0) {
                entityWrapper = new EntityWrapper();
                entityWrapper.in("ETPS_INNER_INVT_NO",delPreBondInvtBscInvtNos);
                if (selectList(entityWrapper).size()>0) {
                    if (!delete(entityWrapper)) {
                        result.put("code","0");
                        result.put("message","删除企业原始清单表头冲突数据报错");
                        return result.toJSONString();
                    }
                    else {
                        if (erpPreBondInvtDtService.selectList(entityWrapper).size()>0) {
                            if (!erpPreBondInvtDtService.delete(entityWrapper)) {
                                result.put("code","0");
                                result.put("message","删除企业原始清单表体冲突数据报错");
                                return result.toJSONString();
                            }
                        }
                    }
                }
            }
            try {
                //新增企业原始清单
                if (addErpPreBondInvtBscList.size()>0) {
                    insertBatchErp(addErpPreBondInvtBscList);
                }
                if (addErpPreBondInvtDtList.size()>0) {
                    erpPreBondInvtDtService.insertBatchErp(addErpPreBondInvtDtList);
                }

                entityWrapper = new EntityWrapper();
                if (erpMidBondInvtBscService.delete(entityWrapper) && erpMidBondInvtDtService.delete(entityWrapper)) {
                    if (addErpPreBondInvtBscList.size() == erpMidBondInvtBscList.size()) {
                        result.put("code","1");
                        result.put("message","导入成功！");
                    }
                    else {
                        result.put("code","1");
                        result.put("message","已导入"+ addErpPreBondInvtBscList.size()+"条数据，另外有" + delErpPreBondInvtBscList.size() + "条数据与现有系统冲突，已自动删除！");
                    }
                }
                else {
                    result.put("code","0");
                    result.put("message","清单中间表删除出错！");
                    return result.toJSONString();
                }

            } catch (Exception e) {
                e.printStackTrace();
                result.put("code","0");
                result.put("message",e.getMessage());
                return result.toJSONString();
            }

        }
        else {
            if (erpMidBondInvtBscList.size()>0) {
                entityWrapper = new EntityWrapper();
                if (erpMidBondInvtBscService.delete(entityWrapper) && erpMidBondInvtDtService.delete(entityWrapper)) {
                    result.put("code","1");
                    result.put("message","已导入0条数据，另外有" + erpMidBondInvtBscList.size() + "条数据与现有系统冲突，已自动删除！");
                }
                else {
                    result.put("code","0");
                    result.put("message","清单中间表删除出错！");
                    return result.toJSONString();
                }

            }
            else {
                result.put("code","1");
                result.put("message","中间表没有数据！");
            }
        }

        return result.toJSONString();
    }

    /**
     * 生成核注清单
     *
     * @return String
     */
    public String genBondInvt(String id) {
        JSONObject result = new JSONObject();
        ErpPreBondInvtBsc erpPreBondInvtBsc = selectById(id);
        InvBsc invBsc = new InvBsc();
        BeanUtils.copyProperties(erpPreBondInvtBsc,invBsc);
        invBsc.setUid(UUIDGenerator.getUUID());
        invBsc.setDocType("A0602");
        String head= JSONArray.toJSONString(invBsc);
        Map<String, String> map = new HashMap<String, String>();
        map.put("jasonBsc", head);

        String etpsInnerInvtNo = erpPreBondInvtBsc.getEtpsInnerInvtNo();
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.in("ETPS_INNER_INVT_NO",etpsInnerInvtNo);
        List<InvDt> invImgsList= new ArrayList<InvDt>();
        List <ErpPreBondInvtDt> erpPreBondInvtDtList = erpPreBondInvtDtService.selectList(entityWrapper);
        for (ErpPreBondInvtDt erpPreBondInvtDt:erpPreBondInvtDtList) {
            InvDt invDt = new InvDt();
            BeanUtils.copyProperties(erpPreBondInvtDt,invDt);
            invDt.setUid(UUIDGenerator.getUUID());
            invImgsList.add(invDt);
        }
        String array= JSONArray.toJSONString(invImgsList);
        map.put("jasonImg", array);

        try {
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            String Str = httpClientUtil.executeByPOST(CommonConstants.contextPath +"/inv/list/insertBscBatchData?InvBsc=",map);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(Str);
            String code = jsonObject.get("code").toString();
            if (code.equals("1")) {
                erpPreBondInvtBsc.setSuccessFlag("1");
                Boolean flag = insertOrUpdate(erpPreBondInvtBsc);
                if (flag) {
                    result.put("code","1");
                    result.put("message","生成核注清单成功！");
                }
                else {
                    result.put("code","0");
                    result.put("message","更新企业原始清单报错！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code","0");
            result.put("message",e.getMessage());
            return result.toJSONString();
        }

        return result.toJSONString();
    }
}
