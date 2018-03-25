package com.liferay.docs.alberto.portlet;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;


@Component(
		property = {
			"javax.portlet.name=" + PortletKeys.FAST_LOGIN,
			"javax.portlet.name=" + PortletKeys.LOGIN,
			"mvc.command.name=/login/login",
		     "service.ranking:Integer=7200"
		},
		service = MVCRenderCommand.class
	)

public class SpecificLoginMVCRenderCommand implements MVCRenderCommand {

	private static Log _log = LogFactoryUtil.getLog(SpecificLoginMVCRenderCommand.class);
	
	@Override
	    public String render
	        (RenderRequest renderRequest, RenderResponse renderResponse) throws
	        PortletException {
		_log.debug("AGG LoginMVCRender");
		

		PortletPreferences prefs = renderRequest.getPreferences();
		
		//parameters for update user
		String emailAddress = prefs.getValue("emailAddressForRC","");
		String organization = prefs.getValue("organizationForRC","");
		_log.debug("emailAddress: "+emailAddress);
		_log.debug("organization: "+organization);
		
		
		if (!"".equals(organization) && !"".equals(emailAddress))
		{
			//Get company
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
					WebKeys.THEME_DISPLAY);
			long companyId = themeDisplay.getCompanyId();
			_log.debug("companyId: "+companyId);
			
			//get user
			_log.debug("get user");
			try {
				User user = UserLocalServiceUtil.getUserByEmailAddress(
						themeDisplay.getCompanyId(), emailAddress);
				long userId = user.getUserId();
				_log.debug("User id: "+userId);
				
				//get organization
				long organizationId = 
					OrganizationLocalServiceUtil.getOrganizationId(companyId, organization);
				_log.debug("Organization id: "+organizationId);
				
				// add to organization
				UserLocalServiceUtil.addOrganizationUser(organizationId, userId);
				
			} catch (PortalException e) {
				_log.warn(e.getMessage());
				SessionErrors.add(renderRequest, "registrationCodeGeneral");
			}
		} 

		 return mvcRenderCommand.render(renderRequest, renderResponse);
	    }
		
	
	@Reference(target = 
		      "(component.name=com.liferay.login.web.internal.portlet.action.LoginMVCRenderCommand)")
		  protected MVCRenderCommand mvcRenderCommand;
}
