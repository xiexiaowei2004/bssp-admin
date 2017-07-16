package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsHisBsc;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsHisBscService
 * 类描述：账册备案正式表服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:06
 */
public interface IEmsHisBscService extends IService<EmsHisBsc> {
    /**
     * 查询账册备案申请表/分页
     *
     * @param page 分页
     * @param emsHisBsc
     * @return Page<EmsHisBsc>
     */
    Page<EmsHisBsc> selectEmsHisBscList(Page<EmsHisBsc> page, EmsHisBsc emsHisBsc);
}
