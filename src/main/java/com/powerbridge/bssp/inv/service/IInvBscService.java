package com.powerbridge.bssp.inv.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvBsc;

import java.util.List;

/**
 * <p>
 * 保税核注清单表  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface IInvBscService extends IService<InvBsc> {
	
	/**
     * @param id
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    boolean txDeleteInvBsc(String id, String seqNo);

    /**
     * @param idList
     * @param seqNoByList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
    boolean txDeleteInvBscByList(List<String> idList, List<String> seqNoByList);

    /**
     * 查询核注清单信息
     *
     * @param page 分页
     * @param InvBsc
     * @return Page<InvBsc>
     */
    Page<InvBsc> selectByInveBsc(Page<InvBsc> page, InvBsc invBsc);
}
