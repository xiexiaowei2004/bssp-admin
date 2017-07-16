package com.powerbridge.bssp.edi.dao;

import com.powerbridge.bssp.edi.entity.EdiMessageHead;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 报文头表 Mapper 接口
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-01
 */
public interface EdiMessageHeadMapper extends BaseMapper<EdiMessageHead> {
	/**
	 * 
	 * @Description 获取报文头表数据
	 * @param SeqNo 单据编号
	 * @return
	 */
	public EdiMessageHead getEdiMessageHead(@Param("seqNo")String seqNo);
}