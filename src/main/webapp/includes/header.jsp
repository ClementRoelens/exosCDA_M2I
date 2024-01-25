<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <nav class="navbar bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand text-light" href="home"><i class="bi bi-globe"></i></a>
            <h4 class="text-light">Bonjour ${(cookie["isLogged"].getValue() != null) ? cookie["isLogged"].getValue() : "visiteur"} !</h4>
            <ul class="navbar nav">
                <c:choose>

                    <c:when test="${cookie['isLogged'].getValue() != null}">
                        <li class="nav-item"><a class="nav-link text-light" href="logout">Se déconnecter</a></li>
                        <li class="nav-item"><a href="patient-form" class="nav-link text-light">Créer un patient</a>
                        </li>
                        <li class="nav-item"><a href="appointment-form" class="nav-link text-light">Créer une
                            consultation</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link text-light" href="sign?mode=signin">Se connecter</a>
                        </li>
                        <li class="nav-item"><a class="nav-link text-light" href="sign?mode=signup">S'inscrire</a></li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item"><a href="patients-list" class="nav-link text-light">Liste des patients</a></li>
            </ul>
        </div>
    </nav>
</header>