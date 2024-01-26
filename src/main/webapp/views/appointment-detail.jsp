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
<main class="container text-center">
    <h1>Consultation de ${appointment.getPatient().getFirstname()} ${appointment.getPatient().getLastname()} effectuée par le Dr.${appointment.getPhysicianName()}</h1>
    <h3>Feuilles de soins :</h3>
    <ul class="list-group">
        <c:forEach items="${appointment.getCareSheets()}" var="caresheet">
            <li class="list-group-item">${caresheet.getCare()} pendant ${caresheet.getDuration()} jours</li>
        </c:forEach>
    </ul>
    <form action="appointment" method="POST">
        <input type="number" name="appointmentId" value="${appointment.getId()}" hidden="true"/>
        <input type="number" name="mode" value="addCareSheet" hidden="true"/>
        <div class="input-group my-3">
            <input type="text" class="form-control" placeholder="Soin" name="care" required />
            <span class="input-group-text">pendant</span>
            <input type="number" class="form-control" placeholder="Durée (en jours)" name="duration" required />
        </div>
        <button type="submit">Ajouter feuille de soin</button>
    </form>
        <c:if test="${error == true}">
            <div class="text-danger">${message}</div>
        </c:if>
    <h3>Prescriptions :</h3>
    <ul class="form-list">
        <c:forEach items="${appointment.getPrescriptions()}" var="prescription">
            <li class="list-group-item">${prescription.getDrug()} pendant ${prescription.getDuration()}</li>
        </c:forEach>
    </ul>
    <form action="appointment" method="POST">
        <input type="number" name="appointmentId" value="${appointment.getId()}" hidden="true"/>
        <input type="number" name="mode" value="addPrescription" hidden="true"/>
        <div class="input-group my-3">
            <input type="text" class="form-control" placeholder="Médicament" name="drug" required />
            <span class="input-group-text">pendant</span>
            <input type="number" class="form-control" placeholder="Durée (en jours)" name="duration" required />
        </div>
        <button type="submit">Ajouter prescription</button>
    </form>
    <c:if test="${error == true}">
        <div class="text-danger">${message}</div>
    </c:if>
</main>
</body>
</html>