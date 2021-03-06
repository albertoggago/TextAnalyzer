package com.liferay.docs.alberto.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

@Component(
		property = {
				"javax.portlet.name=" + PortletKeys.FAST_LOGIN,
				"javax.portlet.name=" + PortletKeys.LOGIN,
				"mvc.command.name=/login/create_account",
			     "service.ranking:Integer=7200"
		},
		service = MVCActionCommand.class
	) 

public class SpecificCreateAccountMVCActionCommand extends BaseMVCActionCommand {

	private static Log log = LogFactoryUtil.getLog(SpecificCreateAccountMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		log.debug("AGG CreateAccountMVCAction");

		// table of registrationCode Organization
		List<Organization> organizations = OrganizationLocalServiceUtil
				.getOrganizations(Integer.MIN_VALUE,Integer.MAX_VALUE);
		
		Map<String, String> registrationCodeOrganization = new HashMap<>();
		for (Organization organization : organizations) {
			String registrationCode = organization.getExpandoBridge()
					                  .getAttribute("registrationcode").toString();
			if (registrationCode != null) {
				registrationCodeOrganization.put(registrationCode, organization.getName());
			}
		}

		//switch for error in new input folder
		PortletPreferences prefs = actionRequest.getPreferences();
		prefs.setValue("errorForRC","No");
		prefs.store();

		//get information for review Registration Code
		String registrationCode = ParamUtil.getString(actionRequest, "registrationcode");
		String emailAddress = ParamUtil.getString(actionRequest, "emailAddress");
		log.debug("registrationCode:" +registrationCode);
		
		String organization = "";
		
		if (registrationCode.equals("")) {
			SessionErrors.add(actionRequest, "registrationCodeVoid");
		} else if (registrationCodeOrganization.containsKey(registrationCode)) {
			log.debug("oganization:" +registrationCodeOrganization.get(registrationCode));
			organization = registrationCodeOrganization.get(registrationCode);
			
		} else {
			log.warn("registration code not fix ");
			SessionErrors.add(actionRequest, "registrationCodeError");
			prefs.setValue("errorForRC","Yes");
			prefs.store();
			throw new PortalException();
		}

		mvcActionCommand.processAction(actionRequest, actionResponse);
		
		log.debug("AGG LoginMVCRender");
		
		//parameters for update user
		log.debug("emailAddress: "+emailAddress);
		log.debug("organization: "+organization);
		
		
		if (!"".equals(organization) && !"".equals(emailAddress))
		{
			//Get company
			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
					WebKeys.THEME_DISPLAY);
			long companyId = themeDisplay.getCompanyId();
			log.debug("companyId: "+companyId);
			
			//get user
			log.debug("get user");
			try {
				User user = UserLocalServiceUtil.getUserByEmailAddress(
						themeDisplay.getCompanyId(), emailAddress);
				long userId = user.getUserId();
				log.debug("User id: "+userId);
				
				//get organization
				long organizationId = 
					OrganizationLocalServiceUtil.getOrganizationId(companyId, organization);
				log.debug("Organization id: "+organizationId);
				
				// add to organization
				UserLocalServiceUtil.addOrganizationUser(organizationId, userId);
				
			} catch (PortalException e) {
				log.warn(e.getMessage());
			}
		} 

		
	}

    @Reference(
            target = "(component.name=com.liferay.login.web.internal.portlet.action.CreateAccountMVCActionCommand)")

    protected MVCActionCommand mvcActionCommand;

}
