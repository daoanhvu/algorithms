<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
	<title>This is an example from VU</title>
	<spring:url value="/resources/core/css/hello.css" var="coreCss" />
	<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
	
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
</head>