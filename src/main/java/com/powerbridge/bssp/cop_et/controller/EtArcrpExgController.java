package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.cod_cus.service.ICodCusComplexService;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpExg;
import com.powerbridge.bssp.cop_et.entity.EtCusExg;
import com.powerbridge.bssp.cop_et.service.IEtArcrpExgService;
import com.powerbridge.bssp.cop_et.service.IEtCusExgService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EtArcrpExgController
 * 类描述：企业联网档案库成品接口实现
 * 创建人：willChen
 * 创建时间：2017/5/22 16:18
 * 修改人：willChen
 * 修改时间：2017-07-03 11:13:08
 */
@Controller
@RequestMapping("/cop_et/etArcrpExg")
@CrossOrigin
public class EtArcrpExgController extends BaseController {
    @Autowired
    private IEtArcrpExgService etArcrpExgService;
    @Autowired
    private IEtCusExgService etCusExgService;
    @Autowired
    private ICodCusComplexService codCusComplexService;

    /**
     * 新增数据
     *
     * @param etArcrpExg 新增成品信息
     * @return 0 失败 1 成功
     */
    @RequiresPermissions("etArcrpExg:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEtArcrpExg(EtArcrpExg etArcrpExg) {
        AjaxResult ajaxResult;
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            etArcrpExg.setUid(uId);

            if (etArcrpExgService.insert(etArcrpExg)) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpExg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addEtArcrpExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id删除
     *
     * @param id 成品表主键 UID
     * @return 0 失败 1 成功
     */
    @RequiresPermissions("etArcrpExg:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (etArcrpExgService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id批量删除
     *
     * @param idList id字符串 “,”分开
     * @return 0 失败 1 成功
     */
    @RequiresPermissions("etArcrpExg:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<EtArcrpExg>();
        entityWrapper.in("UID", ids);
        if (etArcrpExgService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     *
     * @param etArcrpExg 成品修改信息
     * @return 0 失败 1 成功
     */
    @RequiresPermissions("etArcrpExg:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEtArcrpExg(EtArcrpExg etArcrpExg) {
        if (null == etArcrpExg.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (etArcrpExgService.updateById(etArcrpExg)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     *
     * @param id 成品表主键 UID
     * @return 成品信息
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EtArcrpExg etArcrpExg = etArcrpExgService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpExg);
    }

    /**
     * 根据条件查询
     *
     * @param etArcrpExg 查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 成品列表
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtArcrpExgList(EtArcrpExg etArcrpExg, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtArcrpExg> etArcrpExgPage;
        try {
            // 分页
            etArcrpExgPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etArcrpExgPage.setAsc(super.getOrderSort(sortOrder));
                etArcrpExgPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etArcrpExgPage.setOrderByField("A.GDS_SEQNO");
            }
            etArcrpExgPage = etArcrpExgService.selectEtArcrpExgList(etArcrpExgPage, etArcrpExg);
        } catch (Exception e) {
            logger.error("selectEtArcrpExgList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpExgPage.getRecords(), etArcrpExgPage.getTotal());
    }

    /**
     * 选取新增数据
     *
     * @param etArcrpExg 成品信息
     * @param idList     商品表主键字符串 PK_SEQ “,”分开
     * @return 0 失败 1 成功
     */
    @RequiresPermissions("etArcrpExg:list:add")
    @RequestMapping(value = "/list/chooseAdd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult chooseAddEtArcrpExg(EtArcrpExg etArcrpExg, String idList) {
        AjaxResult ajaxResult;
        try {
            //获取id数组
            List ids = Arrays.asList(idList.split(","));
            List<CodCusComplex> codCusComplexList = codCusComplexService.selectBatchIds(ids);
            Integer count = etArcrpExgService.chooseAddEtArcrpExg(etArcrpExg, codCusComplexList);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, count);

        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("chooseAddEtArcrpExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 变更选取新增数据
     *
     * @param idList id字符串 “,”分开
     * @return 0 失败 1 成功
     */
    @RequiresPermissions("etArcrpExg:list:add")
    @RequestMapping(value = "/list/changeChooseAdd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult changeChooseAddEtArcrpExg(String idList) {
        AjaxResult ajaxResult;
        try {
            //获取id数组
            List ids = Arrays.asList(idList.split(","));

            List<EtCusExg> etCusExgList = etCusExgService.selectBatchIds(ids);
            List<EtArcrpExg> etArcrpExgList = new ArrayList<>();
            for (EtCusExg etCusExg : etCusExgList) {
                EtArcrpExg etArcrpExg = new EtArcrpExg();
                BeanUtils.copyProperties(etArcrpExg, etCusExg);
                //生成id
                etArcrpExg.setUid(UUIDGenerator.getUUID());
                //修改标识改为修改
                etArcrpExg.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_1);
                etArcrpExg.setChgTmsCnt(etArcrpExg.getChgTmsCnt() + 1);
                etArcrpExgList.add(etArcrpExg);

            }
            if (etArcrpExgService.insertBatch(etArcrpExgList)) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpExgList);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("changeChooseAddEtArcrpExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 获取商品列表，且列表中不包含当前档案库已存在的商品
     *
     * @param codCusComplex 筛选条件
     * @param seqNo         单据编号
     * @param pageSize      页面容量
     * @param pageNumber    第几页
     * @param sort          排序列名
     * @param sortOrder     排序规则
     * @return 商品列表
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list/complexList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getComplexList(CodCusComplex codCusComplex, @RequestParam("seqNo") String seqNo, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<CodCusComplex> comparablePage;
        try {
            //默认不返回数据
            if (StringUtil.isEmpty(codCusComplex.getCodeT()) && StringUtil.isEmpty(codCusComplex.getgName())) {
                return json(MessageConstants.BSSP_STATUS_SUCCESS, null);
            }

            // 分页
            comparablePage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                comparablePage.setAsc(super.getOrderSort(sortOrder));
                comparablePage.setOrderByField(sort);
            } else {
                //默认按序号升序
                comparablePage.setOrderByField("PK_SEQ");
            }

            //查询出申请表和正式表中已有的商品集合
            Set<String> gdecdSet = getComplexIterator(seqNo);
            Iterator<String> iterator = gdecdSet.iterator();

            //查询商品集合
            EntityWrapper complexEntityWrapper = new EntityWrapper();
            if (codCusComplex.getgName() != null && !"".equals(codCusComplex.getgName())) {
                complexEntityWrapper.like("G_NAME", codCusComplex.getgName());
            }
            if (codCusComplex.getCodeT() != null && !"".equals(codCusComplex.getCodeT())) {
                complexEntityWrapper.like("CODE_T", codCusComplex.getCodeT(), SqlLike.RIGHT);
            }
            //遍历set
            while (iterator.hasNext()) {
                complexEntityWrapper.notLike("CODE_T", iterator.next(), SqlLike.RIGHT);
            }
            comparablePage = codCusComplexService.selectPage(comparablePage, complexEntityWrapper);
        } catch (Exception e) {
            logger.error("getComplexList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, comparablePage.getRecords(), comparablePage.getTotal());
    }

    /**
     * 根据seq查询最大商品序号
     *
     * @param seqNo     单据编号
     * @param chgTmsCnt 变更次数
     * @return 最大商品序号
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list/{seqNo}/getMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Integer getMaxGdsSeqno(@PathVariable String seqNo, Integer chgTmsCnt) {
        return etArcrpExgService.getMaxGdsSeqno(seqNo, chgTmsCnt);
    }

    /**
     * 根据商品编号拿取商品名称
     *
     * @param gdecd 商品编号
     * @param seqNo 单据编号
     * @return 失败返回原因，成功返回商品名称
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list/getGdsNm", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getGdsNm(String gdecd, String seqNo) {
        //根据统一编号查找已有的商品编号集合
        Set<String> gdecdSet = getComplexIterator(seqNo);
        if (gdecdSet.contains(gdecd)) {
            return json(MessageConstants.BSSP_STATUS_FAIL, "商品编码已存在，不能新增");
        }

        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("CODE_T", gdecd, SqlLike.RIGHT);
        List<CodCusComplex> codCusComplexList = codCusComplexService.selectList(entityWrapper);
        if (codCusComplexList.size() > 0) {
            String gdsNm = codCusComplexList.get(0).getgName();
            return json(MessageConstants.BSSP_STATUS_SUCCESS, gdsNm);
        } else {
            return json(MessageConstants.BSSP_STATUS_FAIL, "商品编码无效");
        }
    }

    /**
     * 删除新增的成品并对其他新增商品重新排序
     *
     * @param uid       删除商品主键UID
     * @param seqNo     单据编号
     * @param chgTmsCnt 变更次数
     * @param gdsSeqno  商品序号
     * @return 0 失败 1 成功
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list/deleteAdd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteAdd(@RequestParam String uid, @RequestParam String seqNo, @RequestParam Integer chgTmsCnt, @RequestParam String gdsSeqno) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", seqNo);
        entityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
        entityWrapper.gt("GDS_SEQNO", gdsSeqno);
        List<EtArcrpExg> etArcrpExgList = etArcrpExgService.selectList(entityWrapper);
        if (etArcrpExgService.deleteAdd(uid, etArcrpExgList)) {
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据统一编号查找已有的商品编号集合
     *
     * @param seqNo 单据编号
     * @return 商品编号集合
     */
    private Set getComplexIterator(String seqNo) {
        //查询出申请表和正式表中已有的商品
        EntityWrapper exgEntityWrapper = new EntityWrapper();
        exgEntityWrapper.eq("SEQ_NO", seqNo);
        List<EtArcrpExg> etArcrpExgList = etArcrpExgService.selectList(exgEntityWrapper);
        //修改标记不为删除
        exgEntityWrapper.notLike("MODF_MARKCD", ChkStatusConstant.MODF_MARKCD_2);
        List<EtCusExg> etCusExgList = etCusExgService.selectList(exgEntityWrapper);
        //商品编码集合
        Set<String> gdecdSet = new HashSet();
        for (EtArcrpExg etArcrpExg : etArcrpExgList) {
            gdecdSet.add(etArcrpExg.getGdecd());
        }
        for (EtCusExg etCusExg : etCusExgList) {
            gdecdSet.add(etCusExg.getGdecd());
        }
        return gdecdSet;
    }

}
