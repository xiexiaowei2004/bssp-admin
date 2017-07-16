package com.powerbridge.bssp.redis.controller;

import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdCodes;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.ServletUtils;
import com.powerbridge.bssp.redis.RedisDataTool;
import com.powerbridge.bssp.redis.service.IRedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：RedisController
 * 类描述：用于取redis数据库的数据
 * 创建人：haihuihuang
 * 创建时间：2017/5/22 21:21
 */
@Controller
@RequestMapping("/redis")
@CrossOrigin
public class RedisController extends BaseController {

    @Autowired
    private IRedisClientTemplate redisClientTemplate;

    /**
     * @param tableNames 表名
     * @return AjaxResult
     * @throws
     * @Description: 通过表名查找数据源（list）
     */
    @RequestMapping(value = "/getDataSource", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getDataSource(String tableNames) {
        AjaxResult ajaxResult = null;
        Map datalist = null;
        try {
            datalist = new RedisDataTool().getRedisData(tableNames);
            ajaxResult =json(MessageConstants.BSSP_STATUS_SUCCESS,datalist);
        } catch (Exception e) {
            logger.error("getDataSource()--err", e);
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * @param dictionaryValue 字典值
     * @return AjaxResult
     * @throws
     * @Description: 用于查找数据字典中的值
     */
    @RequestMapping(value = "/getDictionary", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getDictionary(String dictionaryValue) {
        AjaxResult ajaxResult = null;
        Map datalist = null;
        try {
            datalist = new RedisDataTool().getRedisCodes(dictionaryValue);
            ajaxResult =json(MessageConstants.BSSP_STATUS_SUCCESS,datalist);
        } catch (Exception e) {
            logger.error("getDictionary()--err", e);
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * @return AjaxResult
     * @throws
     * @Description: 通过表名查找数据源（list）
     */
    @RequestMapping(value = "/reload", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult Reload() {
        AjaxResult ajaxResult = null;
        String tableName = ServletUtils.getParameter("tableName");
        try {
            RedisDataTool.reload(tableName);
            ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_SUCCESS);
        } catch (Exception e) {
            logger.error("getDataSource()--err", e);
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return ajaxResult;
    }

}
