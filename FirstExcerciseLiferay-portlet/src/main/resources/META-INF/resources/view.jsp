<%@ include file="/init.jsp"%>

<jsp:useBean id="beanBasket" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="beanBasketTaxes" class="java.util.ArrayList" scope="request" />

<liferay-ui:search-container>
	<liferay-ui:search-container-results results="<%=beanBasket%>" />

	<liferay-ui:search-container-row
		className="com.liferay.doc.alberto.model.BeanBasketLine" modelVar="beanBasketLine">
		<liferay-ui:search-container-column-text property="line" name="Basket"/>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<portlet:renderURL var="addDetailURL">
	<portlet:param name="mvcPath" value="/edit_detail.jsp"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="addDetailFreeURL">
	<portlet:param name="mvcPath" value="/edit_detail_free.jsp"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="cleanBasket" var="cleanBasketURL"></portlet:actionURL>
<portlet:actionURL name="processTaxes" var="processTaxesURL"></portlet:actionURL>

<liferay-ui:search-container>
	<liferay-ui:search-container-results results="<%=beanBasketTaxes%>" />

	<liferay-ui:search-container-row
		className="com.liferay.doc.alberto.model.BeanBasketLine" modelVar="beanbasketLine">
		<liferay-ui:search-container-column-text property="line" name="Basket with Taxes"/>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>


<aui:button-row>
	<aui:button onClick="<%=processTaxesURL.toString()%>"
		value="Process Basket"></aui:button>
	<aui:button onClick="<%=addDetailURL.toString()%>"
		value="Add Line to Basket"></aui:button>
	<aui:button onClick="<%=addDetailFreeURL.toString()%>"
		value="Add Line Free to Basket"></aui:button>
	<aui:button onClick="<%=cleanBasketURL.toString()%>"
		value="Clear Basket"></aui:button>
</aui:button-row>

