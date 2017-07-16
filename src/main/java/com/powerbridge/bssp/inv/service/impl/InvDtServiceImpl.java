package com.powerbridge.bssp.inv.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.inv.dao.InvDtMapper;
import com.powerbridge.bssp.inv.entity.InvDt;
import com.powerbridge.bssp.inv.service.IInvDtService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 项目名称：bssp Maven Webapp
 * 类名称：BondInvtImgServiceImplX
 * 类描述：InvImg 表业务逻辑层接口 实现类
 * 创建时间：2017年5月17日 下午10:51:17
 */
@Service("invDtService")
public class InvDtServiceImpl extends BaseServiceImpl<InvDtMapper, InvDt> implements IInvDtService {

    @Autowired
    private InvDtMapper invImgMapper;

    /**
     * @param
     * @param
     * @return List<InvExg>
     * @throws
     * @Description:
     */
    public List<InvDt> autoGetInvtImgList(String seqNo) {

        return invImgMapper.selectByBondInvtImgList(seqNo);
    }

    /**
     * @param
     * @param
     * @return List<InvExg>
     * @throws
     * @Description:
     */
    public List<InvDt> handlerGetInvtImgList(String seqNo) {

        return invImgMapper.selectByHandlerInvtImgList(seqNo);
    }

    /**
     * @param
     * @param
     * @return String
     * @throws
     * @Description: 报关数据检查
     */
    public String checkDeclareData(String seqNo){
        String tempStr = "";
        List<InvDt> invImgList = invImgMapper.selectSameEntryGdsSeqNoList(seqNo);//检索存在两条以上的相同报关单商品序号
        for (InvDt invImg:invImgList){
           // List<InvImg> invImgData = invImgMapper.selectSameGdSNoData(invImg.getSeqNo(),invImg.getEntryGdsSeqno());//查询相同报关单商品序号记录
            Map<String,Object> columnMap = new HashMap<String,Object>();
            columnMap.put("SEQ_NO",seqNo);
            columnMap.put("ENTRY_GDS_SEQNO",invImg.getEntryGdsSeqno());
            List<InvDt> invImgData = invImgMapper.selectByMap(columnMap);
            for (int i=0;i<invImgData.size()-1;i++){
               for (int j=i+1;j<invImgData.size();j++){
                   if(StringUtil.isNotEmpty(invImgData.get(i).getGdecd())){
                       if(StringUtil.isEmpty(invImgData.get(i).getDclUnitcd()) || StringUtil.isEmpty(invImgData.get(j).getDclUnitcd())|| StringUtil.isEmpty(invImgData.get(i).getNatcd()) || StringUtil.isEmpty(invImgData.get(j).getNatcd()) || StringUtil.isEmpty(invImgData.get(i).getDclCurrcd())||StringUtil.isEmpty(invImgData.get(j).getDclCurrcd()) || StringUtil.isEmpty(invImgData.get(i).getLvyrlfModecd())||StringUtil.isEmpty(invImgData.get(j).getLvyrlfModecd())){
                            continue;
                       }
                       if(!invImgData.get(i).getGdecd().equals(invImgData.get(j).getGdecd())||!invImgData.get(i).getDclUnitcd().equals(invImgData.get(j).getDclUnitcd())
                               || !invImgData.get(i).getNatcd().equals(invImgData.get(j).getNatcd())|| !invImgData.get(i).getDclCurrcd().equals(invImgData.get(j).getDclCurrcd()) || !invImgData.get(i).getLvyrlfModecd().equals(invImgData.get(j).getLvyrlfModecd()))
                       {
                           tempStr += invImgData.get(i).getGdsSeqno()+":"+invImgData.get(j).getGdsSeqno()+",";
                           tempStr = tempStr.substring(0,tempStr.length()-1);
                       }
                   }

               }
            }
        }
        return tempStr;
    }

    /**
     * 获取最大的序号
     *
     * @return
     */
   public  BigDecimal getMaxSeqno(String putrecNo,String iEFlag,String mtpckEndprdMarkcd){
       BigDecimal maxSeqno = invImgMapper.getMaxSeqno(putrecNo,iEFlag,mtpckEndprdMarkcd);
       if (null == maxSeqno) {
           maxSeqno = BigDecimal.valueOf(0);
       }
       maxSeqno=maxSeqno.add(new BigDecimal(1));//取最大值加1
       return maxSeqno;
    }

  public  Page<InvDt> selectByInvImg(Page<InvDt>page,InvDt invImg){
         page.setRecords(invImgMapper.selectByInvImg(page,invImg));
         return page;
    }

    public  Map checkInvDtList(List<InvDt> invDtList){
       Map<String,String> resultMap = new HashMap<String,String>();
       if (invDtList.size()==0){
           resultMap.put("0","没有清单商品不能申报!");
       }else{
           for(InvDt invDt:invDtList){
                if (StringUtils.isEmpty(invDt.getGdsMtno()) || StringUtils.isEmpty(invDt.getDclCurrcd()) || StringUtils.isEmpty(invDt.getDclQty()+"")||StringUtils.isEmpty(invDt.getDclUprcAmt()+"")||StringUtils.isEmpty(invDt.getDclTotalAmt()+"")||StringUtils.isEmpty(invDt.getLawfQty()+"")||StringUtils.isEmpty(invDt.getNatcd()+"")||StringUtils.isEmpty(invDt.getUseCd())||StringUtils.isEmpty(invDt.getLvyrlfModecd())){
                    resultMap.put("0","存在清单商品必填项为空不能申报!");
                    break;
                }
           }
       }
       return  resultMap;
    }


}
