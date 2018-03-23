package com.liferay.doc.alberto.portlet.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

import com.liferay.doc.alberto.analyzer.ProcessCalculateTaxes;
import com.liferay.doc.alberto.analyzer.config.Config;
import com.liferay.doc.alberto.analyzer.config.ConfigLiferay;
import com.liferay.doc.alberto.analyzer.NumericFunctions;
import com.liferay.doc.alberto.model.BeanBasketLine;
import com.liferay.doc.alberto.portlet.constants.FirstExcerciseLiferayPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=FirstExcerciseLiferay-portlet Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FirstExcerciseLiferayPortletKeys.FirstExcerciseLiferay,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FirstExcerciseLiferayPortlet extends MVCPortlet {

	private static final String WEB_BASKET = "web-basket";
	private static final String WEB_BASKET_TAXES = "web-basket-taxes";
	private static final String BEAN_BASKET = "beanBasket";
	private static final String BEAN_BASKET_TAXES = "beanBasketTaxes";
	private static final String AMOUNT = "amount";
	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String TEXT = "text";
	private static final ProcessCalculateTaxes processCalculateTaxes 
	        = new ProcessCalculateTaxes();
	private static final Config config = new ConfigLiferay();
	private static final NumericFunctions numericFunctions 
    = new NumericFunctions(config);
	
	public void addDetail(ActionRequest request, ActionResponse response) {
		PortletPreferences prefs = request.getPreferences();

		String[] basketPre = prefs.getValues(WEB_BASKET, new String[1]);

		List<String> basket = new ArrayList<>();

		if (basketPre[0] != null) {
			basket = 
			new ArrayList<>(Arrays.asList(basketPre));
		}
		
		double amount = ParamUtil.getDouble(request, AMOUNT);
		String name = ParamUtil.getString(request, NAME);
		String priceString = ParamUtil.getString(request, PRICE);
		//error in price for comma mil million 
		double price = 0;
		try {
			price = Double.parseDouble(priceString.replaceAll(",", ""));
		} catch (NullPointerException | NumberFormatException e) {
			price = 0;
		}
 

		StringBuilder printBasketBuilder = new StringBuilder();
		printBasketBuilder.append(" • ")
        				  .append(numericFunctions.formatInteger(amount))
        				  .append(" ")
        				  .append(name)
						  .append(" at ")
		                  .append(numericFunctions.formatFloat(price));		
		basket.add(printBasketBuilder.toString());
		
		String[] basketPost = basket.toArray(new String[basket.size()]);
		
	    String[] basketTaxesPost = 
	    	processCalculateTaxes.processTaxesBasketList(basketPost);

		try {
			prefs.setValues(WEB_BASKET, basketPost);
			prefs.setValues(WEB_BASKET_TAXES, basketTaxesPost);
			prefs.store();
		} catch (IOException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ValidatorException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ReadOnlyException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
			        .class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void addDetailFree(ActionRequest request, ActionResponse response) {
		PortletPreferences prefs = request.getPreferences();

		String[] basketPre = prefs.getValues(WEB_BASKET, new String[1]);

		List<String> basket = new ArrayList<>();

		if (basketPre[0] != null) {
			basket = 
			new ArrayList<>(Arrays.asList(basketPre));
		}
		
		String text = ParamUtil.getString(request, TEXT);

		StringBuilder printBasketBuilder = new StringBuilder();
		printBasketBuilder.append(" • ")
        				  .append(text);
		basket.add(printBasketBuilder.toString());
		
		String[] basketPost = basket.toArray(new String[basket.size()]);
		
	    String[] basketTaxesPost = 
	    	processCalculateTaxes.processTaxesBasketList(basketPost);

		try {
			prefs.setValues(WEB_BASKET, basketPost);
			prefs.setValues(WEB_BASKET_TAXES, basketTaxesPost);
			prefs.store();
		} catch (IOException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ValidatorException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ReadOnlyException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
			        .class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public void cleanBasket(ActionRequest request, ActionResponse response) {
		PortletPreferences prefs = request.getPreferences();

		try {
			prefs.setValues(WEB_BASKET, new String[0]);
			prefs.setValues(WEB_BASKET_TAXES, new String[0]);
			prefs.store();
		} catch (IOException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ValidatorException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ReadOnlyException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
			        .class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void processTaxes(ActionRequest request, ActionResponse response) {
		PortletPreferences prefs = request.getPreferences();

		String[] basketPrev = prefs.getValues(WEB_BASKET, new String[1]);

		List<String> basket = new ArrayList<>();

		if (basketPrev[0] != null) {
			basket = 
			new ArrayList<>(Arrays.asList(basketPrev));
		}
		
		//ADD process to analyze Basket
		

		String[] basketList = basket.toArray(new String[basket.size()]);
		String[] basketTaxes = 
		   processCalculateTaxes.processTaxesBasketList(basketList);

		try {
			prefs.setValues(WEB_BASKET_TAXES, basketTaxes);
			prefs.store();
		} catch (IOException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ValidatorException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
					        .class.getName()).log(Level.SEVERE, null, ex);
		} catch (ReadOnlyException ex) {
			Logger.getLogger(FirstExcerciseLiferayPortlet
			        .class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
	    throws PortletException, IOException {

	    PortletPreferences prefs = renderRequest.getPreferences();
	    String[] basketDetails = prefs.getValues(WEB_BASKET, new String[1]);
	    String[] basketTaxesDetails = prefs.getValues(WEB_BASKET_TAXES, new String[1]);

	    if (basketDetails[0] != null) {
	        List<BeanBasketLine> basket = parseEntries(basketDetails);
	        renderRequest.setAttribute(BEAN_BASKET, basket);
	    }

	    if (basketTaxesDetails[0] != null) {
	        List<BeanBasketLine> basketTaxes = parseEntries(basketTaxesDetails);
	        renderRequest.setAttribute(BEAN_BASKET_TAXES, basketTaxes);
	    }

	    super.render(renderRequest, renderResponse);
	}

	private List<BeanBasketLine> parseEntries(String[] webBasketDetails) {
		    List<BeanBasketLine> basket = new ArrayList<>();

		    for (String detailBasket : webBasketDetails) {
		        BeanBasketLine webDetail = new BeanBasketLine(detailBasket);
		        basket.add(webDetail);
		    }

		    return basket;
	}	
}