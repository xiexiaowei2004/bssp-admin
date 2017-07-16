package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclBsc;

/**
 * <p>
 * 业务申报表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
public interface ISasDclBscService extends IService<SasDclBsc> {

    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDclBsc 数据
     * @return Page<SasDclBsc>
     */
    Page<SasDclBsc> selectByList(Page<SasDclBsc> page, SasDclBsc sasDclBsc);

    /**
     * 查询/分页
     *
     * @param page         分页
     * @param sasDclBsc 数据
     * @return Page<SasDclBsc>
     */
    Page<SasDclBsc> selectBySasDclBscList(Page<SasDclBsc> page, SasDclBsc sasDclBsc);

    /**
     * @param seqNo
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(单条删除)
     */
    boolean txDeleteSasDclBsc(String seqNo);

    /**
     * @param seqNoByList
     * @param chgTmsCntList
     * @return boolean
     * @throws
     * @Description: 用于删除主表时，同时删除字表(批量删除)
     */
//    boolean txDeleteSasDclBscByList(List<String> seqNoByList,List<String> chgTmsCntList);

    /**
     * @param sasDclBsc
     * @return boolean
     * @throws
     * @Description: 用于业务变更和业务结案暂存时，同时修改字表
     */
    boolean txUpdateById(SasDclBsc sasDclBsc);
}
