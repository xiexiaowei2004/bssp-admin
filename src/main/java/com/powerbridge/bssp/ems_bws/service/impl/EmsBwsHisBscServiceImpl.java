package com.powerbridge.bssp.ems_bws.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsBscMapper;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsHisBscMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsHisBscService;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsHisDtService;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsDtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


/**
 * 项目名称：bssp-admin
 * 类名称：EmsBwsHisBscServiceImpl
 * 类描述：账册备案申请表服务实现
 * @author jokylao
 * @since 2017-07-06
 */
@Service("emsBwsHisBscService")
public class EmsBwsHisBscServiceImpl extends BaseServiceImpl<EmsBwsHisBscMapper, EmsBwsHisBsc> implements IEmsBwsHisBscService {

    @Autowired
    private EmsBwsHisBscMapper emsBwsHisBscMapper;
    /**
     * 查询物流账册历史表/分页
     *
     * @param page 分页
     * @param emsBwsHisBsc
     * @return Page<EmsBwsHisBsc>
     */
    public Page<EmsBwsHisBsc> selectEmsBwsHisBscList(Page<EmsBwsHisBsc> page, EmsBwsHisBsc emsBwsHisBsc){
        page.setRecords(emsBwsHisBscMapper.selectEmsBwsHisBscList(page, emsBwsHisBsc));
        return page;
    }
}
