package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsCusBscService
 * 类描述：账册备案正式表服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:06
 */
public interface IEmsCusBscService extends IService<EmsCusBsc> {
    /**
     * 查询账册备案申请表/分页
     *
     * @param page 分页
     * @param emsCusBsc
     * @return Page<EmsCusBsc>
     */
    Page<EmsCusBsc> selectEmsCusBscList(Page<EmsCusBsc> page, EmsCusBsc emsCusBsc);
    /**
     * 查询企业下的所有账册号
     */
    List<EmsCusBsc> selectEmsNoList(EmsCusBsc emsCusBsc);

    /**
     * 选择账册正式表一条数据获得账册备案变更的数据
     * @param emsCusBsc 账册正式表
     * @param appId
     * @return EmsCusBsc
     */
    EmsPutrecBsc getEmsPutrecBscChgData(EmsCusBsc emsCusBsc, String appId);
}
