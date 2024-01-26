<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détail du patient</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<main class="container">
    <img src="${pageContext.request.contextPath}/image?id=${patient.getId()}" alt="Photo de ${patient.getFirstname()} ${patient.getLastname()}"
         class="rounded d-block my-5 mx-auto my-4" style="max-height:250px">
    <div class="d-flex justify-content-around w-50 mx-auto fs-2">
        <span>${patient.getFirstname()} ${patient.getLastname()}</span>
        <span>Né le ${patient.getBirthdate().toLocalDate().getDayOfMonth()}/${patient.getBirthdate().toLocalDate().getMonthValue()}/${patient.getBirthdate().toLocalDate().getYear()}</span>
    </div>

    <a href="appointment?patientId=${patient.getId()}" class="btn btn-outline-dark d-block mx-auto my-5 w-25">Créer consultation</a>
</main>
</body>
</html>