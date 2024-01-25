<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<main class="container">
    <c:if test="${error != null}">
        <h3 class="mx-auto text-danger">${message}</h3>
    </c:if>
    <form action="patient-form" method="POST" enctype="multipart/form-data" class="mt-5">
        <div class="mb-3">
            <label for="firstname" class="form-label">Nom</label>
            <input type="text" class="form-control" id="firstname" name="firstname"/>
        </div>
        <div class="mb-3">
            <label for="lastname" class="form-label">Prénom</label>
            <input type="text" class="form-control" id="lastname" name="lastname"/>
        </div>
        <div class="mb-3">
            <label for="birthdate" class="form-label">Date de naissance</label>
            <input type="date" class="form-control" id="birthdate" name="birthdate"/>
        </div>
        <div class="mb-3">
            <label for="picture" class="form-label">Photo</label>
            <input type="file" class="form-control" id="picture" name="picture" accept="image/jpeg, image/png"/>
        </div>
        <button type="submit" class="btn btn-primary d-block mx-auto">Valider</button>
    </form>
</main>
</body>
</html>