package com.powerbridge.bssp.edi.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiCirclationInfoMapper;
import com.powerbridge.bssp.edi.entity.EdiCirclationInfo;
import com.powerbridge.bssp.edi.service.IEdiCirclationInfoService;
@Service("ediCirclationInfoService")
public class EdiCirclationInfoServiceImpl extends BaseServiceImpl<EdiCirclationInfoMapper, EdiCirclationInfo> implements IEdiCirclationInfoService {


}
