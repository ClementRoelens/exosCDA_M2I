<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des patients</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<main class="container">
    <form action="patient" method="GET" class="mx-auto mt-4 w-25">
        <input type="text" class="form-control" name="name" required/>
        <button type="submit" class="btn btn-outline-dark d-block mx-auto mt-2">Rechercher</button>
    </form>
    <ul class="list-group mt-5">
        <c:forEach items="${patients}" var="patient">
            <li class="list-group-item rounded w-50 d-flex justify-content-between align-items-center my-2 mx-auto">
                <img src="${pageContext.request.contextPath}/image?id=${patient.getId()}" alt="Photo du patient" class="img-thumbnail" style="max-height:100px">
                <span class="fs-4">${patient.getFirstname()} ${patient.getLastname()}</span>
                <a class="btn btn-outline-dark" href="patient?id=${patient.getId()}">Voir détails</a>
            </li>
        </c:forEach>
    </ul>
</main>
</body>
</html>