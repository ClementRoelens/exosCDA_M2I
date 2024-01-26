<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire d'inscription</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<main class="container">
    <c:if test="${error != null}">
        <h3 class="mx-auto mt-5 text-danger text-center">${message}</h3>
    </c:if>
    <h3 class="mx-auto my-4 text-center">${title}</h3>
    <form action="sign" method="POST">
        <div class="mb-3">
            <label for="name" class="form-label">Nom d'utilisateur</label>
            <input type="text" class="form-control" id="name" name="name" required/>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe</label>
            <input type="password" class="form-control" id="password" name="password" required/>
        </div>
        <c:if test="${title.equals('Inscription')}">
            <div class="mb-3">
                <label for="passwordConfirmation" class="form-label">Confirmer le mot de passe</label>
                <input type="password" class="form-control" id="passwordConfirmation" name="passwordConfirmation" required/>
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary d-block mx-auto">Valider</button>
    </form>
</main>
</body>
</html>
