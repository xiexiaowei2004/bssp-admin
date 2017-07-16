package com.powerbridge.bssp.erp.service;

import com.baomidou.mybatisplus.service.IService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by powerbridge on 2017/6/14.
 */
public interface IErpBaseService<Serializable> extends IService<Serializable> {
    void importExcel(String path, String errorPath);

    void insertBatchErp(List<T> entityList) throws Exception;
}
