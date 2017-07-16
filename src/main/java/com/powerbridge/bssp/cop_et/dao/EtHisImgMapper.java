package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtHisImg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库正式表料件 Mapper 接口
 *
 * @author willChen
 * @since 2017-06-01
 */
@Repository("etHisImgMapper")
public interface EtHisImgMapper extends BaseMapper<EtHisImg> {

    List<EtHisImg> selectEtHisImgList(Pagination page, EtHisImg etHisImg);

}