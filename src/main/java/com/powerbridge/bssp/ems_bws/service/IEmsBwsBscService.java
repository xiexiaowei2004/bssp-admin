package com.powerbridge.bssp.ems_bws.service;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsBwsBscService
 * 类描述：账册备案申请表服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:06
 */
public interface IEmsBwsBscService extends IService<EmsBwsBsc> {
    /**
     * 查询账册备案申请表/分页
     *
     * @param page 分页
     * @param emsBwsBsc
     * @return Page<EmsBwsBsc>
     */
    Page<EmsBwsBsc> selectEmsBwsBscList(Page<EmsBwsBsc> page, EmsBwsBsc emsBwsBsc);
    
    /**
   	 * 
   	 * @Description 拼接业务单据sql
   	 * @param str
   	 * @return
   	 */
	EmsBwsBsc getBusinessData(@Param(value="str") String str);

	/**
	 * 批量删除
	 * @param seqNoByList 主键集合
	 * @return
	 */

	Boolean deleteBySeqNoList(List<String> seqNoByList);
}
