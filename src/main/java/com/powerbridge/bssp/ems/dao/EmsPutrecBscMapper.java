package com.powerbridge.bssp.ems.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emsPutrecBscMapper")
public interface EmsPutrecBscMapper extends BaseMapper<EmsPutrecBsc> {
    /**
     * 查询加工账册备案申请表/分页
     *
     * @param page 分页
     * @param emsPutrecBsc
     * @return List<EmsPutrecBsc>
     */
    List<EmsPutrecBsc> selectEmsPutrecBscList(Page<EmsPutrecBsc> page, EmsPutrecBsc emsPutrecBsc);
    
    /**
   	 * 
   	 * @Description 拼接业务单据sql
   	 * @param str
   	 * @return
   	 */
     EmsPutrecBsc getBusinessData(@Param(value="str") String str);
}