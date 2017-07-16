package com.powerbridge.bssp.ems.controller;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.service.ICodCusComplexService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;
import com.powerbridge.bssp.ems.service.IEmsCusImgService;
import com.powerbridge.bssp.ems.service.IEmsPutrecBscService;
import com.powerbridge.bssp.ems.service.IEmsPutrecImgService;
import org.bouncycastle.asn1.cmp.ErrorMsgContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecImgController
 * 类描述：账册备案申请料件接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
@Controller
@RequestMapping("/ems/emsPutrecImg")
@CrossOrigin
public class EmsPutrecImgController extends BaseController {
    private static final Gson gson = new Gson();
    @Autowired
    private IEmsPutrecImgService emsPutrecImgService;
    @Autowired
    private IEmsCusImgService emsCusImgService;
    @Autowired
    private ICodCusComplexService codCusComplexService;


    /**
     * 新增账册备案申请料件
     *
     * @param emsPutrecImg 详细信息
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsPutrecImg(EmsPutrecImg emsPutrecImg) {
        try {
            //判断商品编码是否存在
            String gdscd = emsPutrecImg.getGdecd();
            String gdscdRows = codCusComplexService.selectCountByGdecd(gdscd);
            if("0".equals(gdscdRows)){
                return  setErrorJson("商品编码不存在");
            }
            //判断序号是否存在
            if (emsPutrecImgService.isExistGdsSeqno(emsPutrecImg)) {
                return setErrorJson("序号已存在,请检查");
            }
            //判断料号是否存在
            if(emsPutrecImgService.isExistGdsMtno(emsPutrecImg)){
                return setErrorJson("料号已存在,请检查");
            }
            //使用工具类生成id
            String uid = UUIDGenerator.getUUID();
            if ("".equals(uid) && null == uid) {
                //失败
                return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
            }
            //料件成品标识，设置为料件
            emsPutrecImg.setMtpckEndprdTypecd("I");
            emsPutrecImg.setUid(uid);
            //默认设置记账清单序号等于商品序号，记账清单编号等于商品编码
            emsPutrecImg.setInvtGNo(emsPutrecImg.getGdsSeqno());
            emsPutrecImg.setInvtNo(emsPutrecImg.getGdecd());
            //执行新增
            if (emsPutrecImgService.insert(emsPutrecImg)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }

        } catch (Exception e) {
            logger.error("addEmsPutrecImg()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除账册备案申请料件
     *
     * @param id uid主键
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecImgById(@PathVariable String id) {
        try {
            EmsPutrecImg emsPutrecImg = emsPutrecImgService.selectById(id);
            if (emsPutrecImg == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }

            //执行删除
            if (emsPutrecImgService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEmsPutrecImgById()--err", e);
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
    //@RequiresPermissions("emsPutrecImg:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecImgByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            //List<EmsPutrecImg> emsPutrecImgList = emsPutrecImgService.selectBatchIds(Arrays.asList(idList.split(",")));
            Boolean flag = emsPutrecImgService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                //删除成功后重新排序
                /*for (EmsPutrecImg emsPutrecImg : emsPutrecImgList) {
                    String modyMark = emsPutrecImg.getModfMarkcd();
                    //删除新增的数据时，重新排序
                    if (modyMark.equals("3")) {
                        resetOrder(emsPutrecImg);
                    }
                }*/
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            logger.error("deleteEmsPutrecImgByList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 删除时对新增的数据重新排序
     *
     * @param emsPutrecImg
     */
    private void resetOrder(EmsPutrecImg emsPutrecImg) {
        String seqNo = emsPutrecImg.getSeqNo();
        BigDecimal gdsSeqno = emsPutrecImg.getGdsSeqno();
        if (gdsSeqno.compareTo(BigDecimal.valueOf(1)) == 1) {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.eq("MODF_MARKCD", "3");
            entityWrapper.gt("GDS_SEQNO", gdsSeqno);//查找大于当前序号的记录
            List<EmsPutrecImg> emsPutrecImgList = emsPutrecImgService.selectList(entityWrapper);
            for (EmsPutrecImg emsPutrecImg1 : emsPutrecImgList) {
                BigDecimal gdsSeqno1 = emsPutrecImg1.getGdsSeqno();
                emsPutrecImg1.setGdsSeqno(gdsSeqno1.subtract(BigDecimal.valueOf(1)));//序号减1
                emsPutrecImgService.updateById(emsPutrecImg1);
            }
        }
    }

    /**
     * 修改账册备案申请料件
     *
     * @param emsPutrecImg 详细信息
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:update")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsPutrecImg(EmsPutrecImg emsPutrecImg) {
        try {
            if (StringUtil.isEmpty(emsPutrecImg.getUid())) {
                return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
            }
            String gdscdRows = codCusComplexService.selectCountByGdecd(emsPutrecImg.getGdecd());
            if("0".equals(gdscdRows)){
                return  setErrorJson("商品编码不存在");
            }
            String emsNo = emsPutrecImg.getEmsNo();
            BigDecimal gdsSeqno = emsPutrecImg.getGdsSeqno();
            String modyMark = emsPutrecImg.getModfMarkcd();
            modyMark = modyMark == null ? "" : modyMark;
            String uid = emsPutrecImg.getUid();
            //判断序号是否存在
            if (emsPutrecImgService.isExistGdsSeqno(emsPutrecImg)) {
                return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
            }
            if (modyMark == "3" && emsCusImgService.isExistGdsSeqno(emsNo, gdsSeqno)) {
                return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
            }
            //判断料号是否存在
            if(emsPutrecImgService.isExistGdsMtno(emsPutrecImg)){
                return setErrorJson("料号已存在,请检查");
            }
            //默认设置记账清单序号等于商品序号，记账清单编号等于商品编码
            emsPutrecImg.setInvtGNo(emsPutrecImg.getGdsSeqno());
            emsPutrecImg.setInvtNo(emsPutrecImg.getGdecd());
            emsPutrecImg.setMtpckEndprdTypecd("I");
            if (emsPutrecImgService.updateById(emsPutrecImg)) {
                return json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
            } else {
                return result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            logger.error("updateEmsPutrecImg()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
    }

    /**
     * 根据id查询账册备案申请料件信息
     *
     * @param id uid主键
     * @return 账册备案申请料件信息
     */
    //@RequiresPermissions("emsPutrecImg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecImgsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsPutrecImg emsPutrecImg = emsPutrecImgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecImgsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请料件列表
     *
     * @return 账册备案申请料件列表
     */
    //@RequiresPermissions("emsPutrecImg:list")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecImgs(EmsPutrecImg emsPutrecImg) {
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
            Page<EmsPutrecImg> emsPutrecImgPage = emsPutrecImgService.selectEmsPutrecImgList(page, emsPutrecImg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecImgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 获取最大的商品序号
     *
     * @param emsPutrecImg
     * @return 商品序号
     */
    @RequestMapping(value = "/list/selectMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectMaxGdsSeqno(EmsPutrecImg emsPutrecImg) {
        AjaxResult ajaxResult = null;
        try {
            BigDecimal maxGdsSeqno = emsPutrecImgService.getMaxGdsSeqno(emsPutrecImg.getSeqNo());
            BigDecimal cusMaxGdsSeqno = emsCusImgService.getMaxGdsSeqno(emsPutrecImg.getSeqNo());
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
            EmsPutrecImg emsPutrecImg = emsPutrecImgService.selectById(id);
            EmsPutrecImg newEmsPutrecImg = emsPutrecImgService.copyGds(emsPutrecImg);
            Boolean flag = emsPutrecImgService.insert(newEmsPutrecImg);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("copyEmsPutrecImg()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 根据商品序号查询料件商品
     *
     * @param seqNo    单据编号
     * @param gdsSeqno 商品序号
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:copyGds")
    @RequestMapping(value = "/list/selectGdsByGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectGdsByGdsSeqno(String seqNo, String gdsSeqno, String chgTmsCnt) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.eq("GDS_SEQNO", gdsSeqno);
            EmsCusImg emsCusImg = emsCusImgService.selectOne(entityWrapper);
            if (emsCusImg == null) {
                entityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
                EmsPutrecImg emsPutrecImg = emsPutrecImgService.selectOne(entityWrapper);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
            } else
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectGdsByGdsSeqno()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 批量插入数据
     *
     * @param jasonEmsPutrecImgs 数据集合
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:insertBatchData")
    @RequestMapping(value = "/list/insertBatchData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertBatchData(String jasonEmsPutrecImgs) {
        Gson gson = new Gson();
        List<EmsPutrecImg> emsPutrecImgs = gson.fromJson(jasonEmsPutrecImgs, new TypeToken<List<EmsPutrecImg>>() {
        }.getType());
        AjaxResult ajaxResult = null;
        try {
            String repeatData = "";
            for (int i = 0; i < emsPutrecImgs.size(); i++) {
                EmsPutrecImg emsPutrecImg = emsPutrecImgs.get(i);
                //判断序号或料号是否重复
                BigDecimal gdsSeqno = emsPutrecImg.getGdsSeqno();
                String gdsMtno = emsPutrecImg.getGdsMtno();
                for (int j = 0; j < emsPutrecImgs.size(); j++) {
                    EmsPutrecImg emsPutrecImg2 = emsPutrecImgs.get(j);
                    BigDecimal gdsSeqno2 = emsPutrecImg2.getGdsSeqno();
                    String gdsMtno2 = emsPutrecImg2.getGdsMtno();
                    if(i!=j && gdsSeqno.equals(gdsSeqno2)){
                        repeatData = String.format("料件序号{0}重复，请检查数据");
                        break;
                    }
                    if(i!=j && gdsMtno.equals(gdsMtno2)){
                        repeatData = String.format("料件料号{0}重复，请检查数据");
                        break;
                    }
                }
                if(StringUtil.isNotEmpty(repeatData)){
                    break;
                }
                emsPutrecImg.setUid(UUIDGenerator.getUUID());
                //企业执行标志为空则设置默认值为运行
                if (StringUtil.isEmpty(emsPutrecImg.getEtpsExeMarkcd()))
                    emsPutrecImg.setEtpsExeMarkcd("1");
            }
            //检查序号或料号是否重复
            if(StringUtil.isNotEmpty(repeatData)){
                return setErrorJson(repeatData);
            }
            //检查数据
            String checkMsg = emsPutrecImgService.checkBeforeInsert(emsPutrecImgs);
            if (StringUtil.isNotEmpty(checkMsg)) {
                return setErrorJson(checkMsg);
            }
            Boolean flag = emsPutrecImgService.insertBatch(emsPutrecImgs);
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
