package com.powerbridge.bssp.ems_bws.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsBwsBscMapper")
public interface EmsBwsBscMapper extends BaseMapper<EmsBwsBsc> {
    /**
     * 查询加工账册备案申请表/分页
     *
     * @param page 分页
     * @param emsBwsBsc
     * @return List<EmsBwsBsc>
     */
    List<EmsBwsBsc> selectEmsBwsBscList(Page<EmsBwsBsc> page, EmsBwsBsc emsBwsBsc);
    
    /**
   	 * 
   	 * @Description 拼接业务单据sql
   	 * @param str
   	 * @return
   	 */
     EmsBwsBsc getBusinessData(@Param(value="str") String str);
}