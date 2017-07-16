package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsPutrecBscService
 * 类描述：账册备案申请表服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：jokylao
 * 修改时间：2017/6/1 22:06
 */
public interface IEmsPutrecBscService extends IService<EmsPutrecBsc> {
    /**
     * 查询账册备案申请表/分页
     *
     * @param page 分页
     * @param emsPutrecBsc
     * @return Page<EmsPutrecBsc>
     */
    Page<EmsPutrecBsc> selectEmsPutrecBscList(Page<EmsPutrecBsc> page, EmsPutrecBsc emsPutrecBsc);
    
    /**
   	 * 
   	 * @Description 拼接业务单据sql
   	 * @param str
   	 * @return
   	 */
	EmsPutrecBsc getBusinessData(@Param(value="str") String str);

	/**
	 * 批量删除
	 * @param seqNoByList 主键集合
	 * @return
	 */

	boolean deleteBySeqNoList(List<String> seqNoByList);
	/**
	 * 根据单据编号 判断单据是否存在
	 * @param seqNo
	 * @return
	 */
	boolean isExistBsc(String seqNo);
}
