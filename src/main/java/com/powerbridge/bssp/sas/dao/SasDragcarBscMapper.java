package com.powerbridge.bssp.sas.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 扡卡备案表 Mapper 接口
 * </p>
 *
 * @author huanliu
 * @since 2017-06-21
 */
@Repository
public interface SasDragcarBscMapper extends BaseMapper<SasDragcarBsc> {


    /**
     *
     * @param page
     * @param sasDragcarBsc
     * @return  List<SasDclBsc>
     */

    List<SasDragcarBsc> selectSasDragcarBscList(Page<SasDragcarBsc> page, SasDragcarBsc sasDragcarBsc);
}