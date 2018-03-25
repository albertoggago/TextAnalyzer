package com.liferay.docs.alberto.portlet;

import com.liferay.docs.alberto.constants.DynamicIncludePortletKeys;

import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author alberto
 */
@Component(
	    immediate = true,
	    service = DynamicInclude.class
	)
public class DynamicIncludePortlet implements DynamicInclude {


	@Override
    public void include(
         HttpServletRequest request, HttpServletResponse response,
         String key)
       throws IOException {

       PrintWriter printWriter = response.getWriter();

       //really don't work but its a point to review
       printWriter.println(
         "<liferay-ui:error key=\"registrationCodeError\" message=\"registration-code-error\" />\n"
       + "<liferay-ui:error key=\"registrationCodeVoid\" message=\"registration-code-void\" />\n"
       + "<liferay-ui:error key=\"registrationCodeGeneral\" message=\"registration-code-general\" />\n");
    }

    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
       dynamicIncludeRegistry.register(
         DynamicIncludePortletKeys.DYNAMIC_MESSAGE);
    }

}