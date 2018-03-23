<%@ include file="init.jsp"%>


<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
</portlet:renderURL>
<portlet:actionURL name="addDetail" var="addDetailURL"></portlet:actionURL>

<aui:form action="<%=addDetailURL%>" name="<portlet:namespace />fm">
	<aui:fieldset>
		<aui:input name="amount" type="number">
			<aui:validator name="required" />
		</aui:input>
		<aui:input name="name" type="text">
			<aui:validator name="required" />
		</aui:input>
		<aui:input name="price" type="text">
			<aui:validator name="required" />
			<aui:validator name="number" />
		</aui:input>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%=viewURL.toString()%>"></aui:button>
	</aui:button-row>
</aui:form>