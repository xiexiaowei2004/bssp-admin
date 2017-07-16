package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsPutrecImgService
 * 类描述：账册备案申请料件服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：willChen
 * 修改时间：2017/5/16 22:00
 */
public interface IEmsPutrecImgService extends IService<EmsPutrecImg> {
    /**
     * 查询加工账册备案料件/分页
     *
     * @param page 分页
     * @param emsPutrecImg
     * @return List<EmsPutrecImg>
     */
    Page<EmsPutrecImg> selectEmsPutrecImgList(Page<EmsPutrecImg> page, EmsPutrecImg emsPutrecImg);
    /**
     * 获取最大的商品序号
     *
     * @return 单据编号
     */
    BigDecimal getMaxGdsSeqno(String seqNo);

    /**
     * 复制商品
     * @param emsPutrecImg
     * @return
     */
    EmsPutrecImg copyGds(EmsPutrecImg emsPutrecImg);
    /**
     * 判断料件序号是否存在
     * @param emsPutrecImg
     * @return boolean
     */
    boolean isExistGdsSeqno(EmsPutrecImg emsPutrecImg);
    /**
     * 判断商品料号是否存在
     * @param emsPutrecImg
     * @return boolean
     */
    boolean isExistGdsMtno(EmsPutrecImg emsPutrecImg);

    /**
     * 批量插入时，判断数据是否合法
     * @param emsPutrecImgs
     * @return
     */
    String checkBeforeInsert(List<EmsPutrecImg> emsPutrecImgs);

    /**
     * 检查必填项
     * @param emsPutrecImgs
     * @return
     */
    String checkListEmpty(List<EmsPutrecImg> emsPutrecImgs);
}
