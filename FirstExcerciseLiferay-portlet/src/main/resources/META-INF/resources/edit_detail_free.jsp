<%@ include file="init.jsp"%>

<p>
	<b><liferay-ui:message key="Add a element to basket, text free" /></b>
	<liferay-ui:message key="Rules: fist number amount, last number price, rest name of product" /><

</p>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="addDetailFree" var="addDetailFreeURL"></portlet:actionURL>

<aui:form action="<%=addDetailFreeURL%>" name="<portlet:namespace />fm">
	<aui:fieldset>
		<aui:input name="text" type="text">
			<aui:validator name="required" />
		</aui:input>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%=viewURL.toString()%>"></aui:button>
	</aui:button-row>
</aui:form>