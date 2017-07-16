package com.powerbridge.bssp.ems_bws.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisBsc;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsBwsHisBscService
 * 类描述：物流账册历史表服务
 * 创建人：jokylao
 * 创建时间：2017/7/6
 */
public interface IEmsBwsHisBscService extends IService<EmsBwsHisBsc> {
    /**
     * 查询账册备案申请表/分页
     *
     * @param page 分页
     * @param emsBwsHisBsc
     * @return Page<EmsBwsHisBsc>
     */
    Page<EmsBwsHisBsc> selectEmsBwsHisBscList(Page<EmsBwsHisBsc> page, EmsBwsHisBsc emsBwsHisBsc);
}
