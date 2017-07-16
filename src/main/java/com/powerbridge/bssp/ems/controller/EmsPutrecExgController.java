package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsPutrecExg;
import com.powerbridge.bssp.ems.service.IEmsCusExgService;
import com.powerbridge.bssp.ems.service.IEmsPutrecExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecExgController
 * 类描述：账册备案申请成品接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：willChen
 * 修改时间：2017/5/17 9:49
 */
@Controller
@RequestMapping("/ems/emsPutrecExg")
@CrossOrigin
public class EmsPutrecExgController extends BaseController {
    private static final Gson gson = new Gson();
    @Autowired
    private IEmsPutrecExgService emsPutrecExgService;
    @Autowired
    private IEmsCusExgService emsCusExgService;

    /**
     * 新增账册备案申请成品
     *
     * @param emsPutrecExg 详细信息
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsPutrecExg(EmsPutrecExg emsPutrecExg) {
        String emsNo = emsPutrecExg.getEmsNo();
        BigDecimal gdsSeqno = emsPutrecExg.getGdsSeqno();
        String modyMark = emsPutrecExg.getModfMarkcd();
        modyMark = modyMark == null ? "" : modyMark;
        //判断序号是否存在
        if (emsPutrecExgService.isExistGdsSeqno(emsPutrecExg)) {
            return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
        }
        if (modyMark.equals("3") && emsCusExgService.isExistGdsSeqno(emsNo, gdsSeqno)) {
            return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
        }
        //判断料号是否存在
        if(emsPutrecExgService.isExistGdsMtno(emsPutrecExg)){
            return setErrorJson("料号已存在,请检查");
        }
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        emsPutrecExg.setUid(uId);
        //料件成品标识，设置为成品
        emsPutrecExg.setMtpckEndprdTypecd("E");
        //默认设置记账清单序号等于商品序号，记账清单编号等于商品编码
        emsPutrecExg.setInvtGNo(emsPutrecExg.getGdsSeqno());
        emsPutrecExg.setInvtNo(emsPutrecExg.getGdecd());

        try {
            //执行新增
            if (emsPutrecExgService.insert(emsPutrecExg)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }

        } catch (Exception e) {
            logger.error("addEmsPutrecExg()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除账册备案申请成品
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecExgById(@PathVariable String id) {
        try {
            EmsPutrecExg emsPutrecExg = emsPutrecExgService.selectById(id);
            if (emsPutrecExg == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }

            //执行删除
            if (emsPutrecExgService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEmsPutrecExgById()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecImgByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            List<EmsPutrecExg> emsPutrecExgList = emsPutrecExgService.selectBatchIds(Arrays.asList(idList.split(",")));
            Boolean flag = emsPutrecExgService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                //删除成功后重新排序
                for (EmsPutrecExg emsPutrecExg : emsPutrecExgList) {
                    String modyMark = emsPutrecExg.getModfMarkcd();
                    //删除新增的数据时，重新排序
                    if (modyMark.equals("3")) {
                        resetOrder(emsPutrecExg);
                    }
                }
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteEmsPutrecImgByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 删除时对新增的数据重新排序
     *
     * @param emsPutrecExg
     */
    private void resetOrder(EmsPutrecExg emsPutrecExg) {
        String seqNo = emsPutrecExg.getSeqNo();
        BigDecimal gdsSeqno = emsPutrecExg.getGdsSeqno();
        if (gdsSeqno.compareTo(BigDecimal.valueOf(1)) == 1) {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.eq("MODF_MARKCD", "3");
            entityWrapper.gt("GDS_SEQNO", gdsSeqno);//查找大于当前序号的记录
            List<EmsPutrecExg> emsPutrecExgList = emsPutrecExgService.selectList(entityWrapper);
            for (EmsPutrecExg emsPutrecExg1 : emsPutrecExgList) {
                BigDecimal gdsSeqno1 = emsPutrecExg1.getGdsSeqno();
                emsPutrecExg1.setGdsSeqno(gdsSeqno1.subtract(BigDecimal.valueOf(1)));//序号减1
                emsPutrecExgService.updateById(emsPutrecExg1);
            }
        }
    }

    /**
     * 修改账册备案申请成品
     *
     * @param emsPutrecExg 详细信息
     * @return
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsPutrecExg(EmsPutrecExg emsPutrecExg) {
        if (StringUtil.isEmpty(emsPutrecExg.getUid())) {
            return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
        }
        try {
            String emsNo = emsPutrecExg.getEmsNo();
            BigDecimal gdsSeqno = emsPutrecExg.getGdsSeqno();
            String modyMark = emsPutrecExg.getModfMarkcd();
            modyMark = modyMark == null ? "" : modyMark;
            //判断序号是否存在
            if (emsPutrecExgService.isExistGdsSeqno(emsPutrecExg)) {
                return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
            }
            if (modyMark.equals("3") && emsCusExgService.isExistGdsSeqno(emsNo, gdsSeqno)) {
                return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
            }
            //判断料号是否存在
            if(emsPutrecExgService.isExistGdsMtno(emsPutrecExg)){
                return setErrorJson("料号已存在,请检查");
            }
            //默认设置记账清单序号等于商品序号，记账清单编号等于商品编码
            emsPutrecExg.setInvtGNo(emsPutrecExg.getGdsSeqno());
            emsPutrecExg.setInvtNo(emsPutrecExg.getGdecd());
            emsPutrecExg.setMtpckEndprdTypecd("E");
            if (emsPutrecExgService.updateById(emsPutrecExg)) {
                return json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExg);
            } else {
                return result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            logger.error("updateEmsPutrecExg()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
    }

    /**
     * 根据id查询账册备案申请成品信息
     *
     * @param id uid主键
     * @return 账册备案申请成品信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecExgsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsPutrecExg emsPutrecExg = emsPutrecExgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecExgsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请成品列表
     *
     * @return 账册备案申请成品列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecExgs(EmsPutrecExg emsPutrecExg) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("gdsSeqno");  // 排序
            }
            Page<EmsPutrecExg> emsPutrecExgPage = emsPutrecExgService.selectEmsPutrecExgList(page, emsPutrecExg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecExgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 获取最大的商品序号
     *
     * @param emsPutrecExg
     * @return 商品序号
     */
    @RequestMapping(value = "/list/selectMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectMaxGdsSeqno(EmsPutrecExg emsPutrecExg) {
        AjaxResult ajaxResult = null;
        try {
            BigDecimal maxGdsSeqno = emsPutrecExgService.getMaxGdsSeqno(emsPutrecExg.getSeqNo());
            BigDecimal cusMaxGdsSeqno = emsCusExgService.getMaxGdsSeqno(emsPutrecExg.getSeqNo());
            if (cusMaxGdsSeqno.compareTo(maxGdsSeqno) == 1) {
                maxGdsSeqno = cusMaxGdsSeqno;
            }
            maxGdsSeqno = maxGdsSeqno.add(BigDecimal.valueOf(1));//取最大值加1
            return json(MessageConstants.BSSP_STATUS_SUCCESS, maxGdsSeqno);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectMaxGdsSeqno()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 复制当前商品
     *
     * @param id 主键
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:copyGds")
    @RequestMapping(value = "/list/copyGds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult copyEmsPutrecImg(String id) {
        AjaxResult ajaxResult = null;
        try {
            EmsPutrecExg emsPutrecExg = emsPutrecExgService.selectById(id);
            EmsPutrecExg newEmsPutrecExg = emsPutrecExgService.copyGds(emsPutrecExg);
            Boolean flag = emsPutrecExgService.insert(newEmsPutrecExg);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("copyEmsPutrecExg()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 根据商品序号查询成品商品
     *
     * @param seqNo    单据编号
     * @param gdsSeqno 序号
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:selectGdsByGdsSeqno")
    @RequestMapping(value = "/list/selectGdsByGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectGdsByGdsSeqno(String seqNo, String gdsSeqno, String chgTmsCnt) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.eq("GDS_SEQNO", gdsSeqno);
            EmsCusExg emsCusExg = emsCusExgService.selectOne(entityWrapper);
            if (emsCusExg == null) {
                entityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
                EmsPutrecExg emsPutrecImg = emsPutrecExgService.selectOne(entityWrapper);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
            } else
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusExg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectGdsByGdsSeqno()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 批量插入数据
     *
     * @param jasonEmsPutrecExgs 数据集合
     * @return
     */
    //@RequiresPermissions("emsPutrecExg:list:insertBatchData")
    @RequestMapping(value = "/list/insertBatchData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertBatchData(String jasonEmsPutrecExgs) {
        Gson gson = new Gson();
        List<EmsPutrecExg> emsPutrecExgs = gson.fromJson(jasonEmsPutrecExgs, new TypeToken<List<EmsPutrecExg>>() {
        }.getType());
        AjaxResult ajaxResult = null;
        try {
            String repeatData = "";
            for (int i = 0; i < emsPutrecExgs.size(); i++) {
                EmsPutrecExg emsPutrecExg = emsPutrecExgs.get(i);
                //判断序号或料号是否重复
                BigDecimal gdsSeqno = emsPutrecExg.getGdsSeqno();
                String gdsMtno = emsPutrecExg.getGdsMtno();
                for (int j = 0; j < emsPutrecExgs.size(); j++) {
                    EmsPutrecExg emsPutrecExg2 = emsPutrecExgs.get(j);
                    BigDecimal gdsSeqno2 = emsPutrecExg2.getGdsSeqno();
                    String gdsMtno2 = emsPutrecExg2.getGdsMtno();
                    if(i!=j && gdsSeqno.equals(gdsSeqno2)){
                        repeatData = String.format("成品序号{0}重复，请检查数据");
                        break;
                    }
                    if(i!=j && gdsMtno.equals(gdsMtno2)){
                        repeatData = String.format("成品料号{0}重复，请检查数据");
                        break;
                    }
                }
                if(StringUtil.isNotEmpty(repeatData)){
                    break;
                }
                emsPutrecExg.setUid(UUIDGenerator.getUUID());
                //企业执行标志为空则设置默认值为运行
                if (StringUtil.isEmpty(emsPutrecExg.getEtpsExeMarkcd()))
                    emsPutrecExg.setEtpsExeMarkcd("1");
            }
            //检查序号或料号是否重复
            if(StringUtil.isNotEmpty(repeatData)){
                return setErrorJson(repeatData);
            }
            //检查数据
            String checkMsg = emsPutrecExgService.checkBeforeInsert(emsPutrecExgs);
            if (StringUtil.isNotEmpty(checkMsg)) {
                return setErrorJson(checkMsg);
            }
            Boolean flag = emsPutrecExgService.insertBatch(emsPutrecExgs);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("insertBatchData()--error", e);
        }
        return ajaxResult;
    }
}
