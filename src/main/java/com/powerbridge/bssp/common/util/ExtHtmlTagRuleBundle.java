package com.powerbridge.bssp.common.util;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * 
*    
* 项目名称：bssp Maven Webapp
* 类名称：ExtHtmlTagRuleBundle   
* 类描述： Sitemesh3中增加自定义tag（网页标签）  
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* 修改备注：   
* @version    
*
 */
public class ExtHtmlTagRuleBundle implements TagRuleBundle{

	@Override
	public void cleanUp(State arg0, ContentProperty arg1, SiteMeshContext arg2) {
		
	}

    @Override  
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {  
        defaultState.addRule("tiziFooter", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("tiziFooter"), false));  
    }  

}
