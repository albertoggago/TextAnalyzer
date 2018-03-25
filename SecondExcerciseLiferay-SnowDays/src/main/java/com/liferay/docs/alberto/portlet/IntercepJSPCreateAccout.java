package com.liferay.docs.alberto.portlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.RenderResponseWrapper;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;

@Component(
    immediate = true,
    property = {
         "javax.portlet.name=" + PortletKeys.LOGIN
    },
    service = PortletFilter.class
)
public class IntercepJSPCreateAccout implements RenderFilter {

	
    @Override
    public void init(FilterConfig config) throws PortletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
         throws IOException, PortletException {

       // input filed Registration Code
       RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);
       chain.doFilter(request, renderResponseWrapper);
       String text = renderResponseWrapper.toString();
       
       PortletPreferences prefs = request.getPreferences();

       //this variable determine if ther is error for mark
       String registrationCode = ParamUtil.getString(request, "registrationcode");
       String errorRC = prefs.getValue("errorForRC", "");
       if (registrationCode == null) {registrationCode = "";} 
      
       if (text != null) {
         
    	 String textFixHtml = "id=\"_com_liferay_login_web_portlet_LoginPortlet_suffixValue";
         int indexModifyHtml = text.lastIndexOf(textFixHtml);
         int indexModifyHtml1stDiv = text.indexOf("</div>", indexModifyHtml);
         
         int indexHtml = text.indexOf("</div>", indexModifyHtml1stDiv+1);
         
         if (indexHtml >= 0 && indexModifyHtml >= 0) {
          String textInit = text.substring(0, indexHtml);
          String textEnd = text.substring(indexHtml);
          String newDivInit = "<div class=\"form-group input-String-wrapper\">"; 
          String newDivErrorInit = "<div class=\"form-group input-String-wrapper has-error\">"; 
          String newDivEnd = "</div>"; 
          
          String newlabel = "<label class=\"control-label\" for=\"_com_liferay_login_web_portlet_LoginPortlet_registrationcode\"> "
          		+ "Registation Code " 
          		+ " <span class=\"text-warning\">"
          		+ "  <svg class=\"lexicon-icon lexicon-icon-asterisk\" focusable=\"false\" role=\"img\" title=\"\" viewBox=\"0 0 512 512\">\n" 
          		+ "  <path class=\"lexicon-icon-outline\" d=\"M323.6,190l146.7-48.8L512,263.9l-149.2,47.6l93.6,125.2l-104.9,76.3l-96.1-126.4l-93.6,126.4L56.9,435.3l92.3-123.9  "
          		+ "L0,263.8l40.4-122.6L188.4,190v-159h135.3L323.6,190L323.6,190z\"></path>\n" 
          		+ " </svg></span> "
          		+ "  <span class=\"hide-accessible\">Required</span> "
          		+ "</label>";
          String newInput = "<input class=\"field form-control lfr-input-text error-field\" "
          		+ "id=\"_com_liferay_login_web_portlet_LoginPortlet_registrationcode\" "
          		+ "name=\"_com_liferay_login_web_portlet_LoginPortlet_registrationcode\" "
          		+ "style=\"\" type=\"text\" value=\""+registrationCode+"\" maxlength=\"75\" aria-required=\"true\" "
          		+ "aria-invalid=\"true\" aria-describedby=\"_com_liferay_login_web_portlet_LoginPortlet_registrationcodeHelper\" required"
          		+ ">";
          String badCode = "<div class=\"form-validator-stack help-block\" id=\"_com_liferay_login_web_portlet_LoginPortlet_registrationcodeHelper\">"
          		+ "<div role=\"alert\" class=\"required\">Registration Code not Valid, must link with organizations.</div></div>";
          if ("Yes".equals(errorRC)) { 
        	  text = textInit + newDivErrorInit + newlabel + newInput + badCode + newDivEnd + textEnd;}
          else {
        	  text = textInit + newDivInit + newlabel + newInput + newDivEnd + textEnd;}
         } 
         
        response.getWriter().write(text);
       }
    }

}