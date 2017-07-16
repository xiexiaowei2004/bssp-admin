package com.powerbridge.bssp.ems_bws.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusBsc;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsBwsCusBscService
 * 类描述：账册备案申请表服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:06
 */
public interface IEmsBwsCusBscService extends IService<EmsBwsCusBsc> {
    /**
     * 查询账册备案申请表/分页
     *
     * @param page 分页
     * @param emsBwsCusBsc
     * @return Page<EmsBwsCusBsc>
     */
    Page<EmsBwsCusBsc> selectEmsBwsCusBscList(Page<EmsBwsCusBsc> page, EmsBwsCusBsc emsBwsCusBsc);
    /**
     * 查询账册备案正式账册编号
     *
     * @return List<EmsBwsCusBsc>
     */
    List<EmsBwsCusBsc> selectBwsNoList(EmsBwsCusBsc emsBwsCusBsc);

    /**
     * 选择账册正式表一条数据生成备案变更的数据
     *
     * @param emsBwsCusBsc 账册正式表
     * @param appId
     * @return
     */
    EmsBwsBsc generateEmsBwsChgData(EmsBwsCusBsc emsBwsCusBsc, String appId);
}
