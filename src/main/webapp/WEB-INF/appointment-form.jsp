
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Création de consultation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<main class="container">
    <c:if test="${error != null}">
        <h3 class="mx-auto text-danger">${message}</h3>
    </c:if>
    <form action="appointment" method="POST" class="mt-5">
        <input type="number" value="${patientId}" name="patientId" hidden="true">
        <div class="mb-3">
            <label for="physicianName" class="form-label">Nom du médecin</label>
            <input type="text" class="form-control" id="physicianName" name="physicianName" required/>
        </div>
        <div class="mb-3">
            <label for="date" class="form-label">Date de la consultation</label>
            <input type="date" class="form-control" id="date" name="date" required/>
        </div>
        <button type="submit" class="btn btn-primary d-block mx-auto">Créer</button>
    </form>
</main>
</body>
</html>
