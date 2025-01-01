<%@ include file="common/header.jspf" %>
<%@ include file = "common/navigation.jspf"%>
<div class="container">
	<h1>Enter Todo Details</h1>
	<form:form method="post" modelAttribute="todo">

		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required" />
			<form:errors path="description" cssClass="text-warning"></form:errors>
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="targetDate">TargetDate</form:label>
			<form:input type="text" path="targetDate" required="required" />
			<form:errors path="targetDate" cssClass="text-warning"></form:errors>
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="done">Is Done?</form:label>
			<form:radiobutton path="done" value="true" /> Yes
			<form:radiobutton path="done" value="false" /> No
			<form:errors path="done" cssClass="text-warning"></form:errors>
		</fieldset>

		<form:input type="hidden" path="id" required="required" />
		<input type="submit" class="btn btn-success">
	</form:form>
</div>

<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>