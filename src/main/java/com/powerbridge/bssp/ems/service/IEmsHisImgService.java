package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsHisImg;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsHisImgService
 * 类描述：账册备案历史表料件服务
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
public interface IEmsHisImgService extends IService<EmsHisImg> {
    /**
     * 查询加工账册备案料件/分页
     *
     * @param page 分页
     * @param emsHisImg
     * @return List<EmsCusImg>
     */
    Page<EmsHisImg> selectEmsHisImgList(Page<EmsHisImg> page, EmsHisImg emsHisImg);
}
