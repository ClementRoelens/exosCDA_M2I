# Gestion de bibliothèque

# Introduction

- Dans ce TP, vous allez créer une application de gestion de bibliothèque. L'application doit permettre aux utilisateurs de rechercher des livres dans la bibliothèque, de voir les détails d'un livre particulier, de les emprunter et de les retourner. L'application doit également permettre aux bibliothécaires de gérer les stocks de livres et les emprunts.
1. Étape 1 : Création des classes de base

Créez les classes suivantes :
- Livre
    - Un livre a un titre, un auteur et un identifiant unique.
    - Implémentez une méthode toString() pour afficher les détails du livre.

- Personne
    - Une personne a un nom et un prénom.
    - Implémentez une méthode toString() pour afficher les détails de la personne.

- Emprunt
    - Un emprunt relie une personne à un livre et contient une date de début et une date de fin.
    - Implémentez une méthode toString() pour afficher les détails de l'emprunt.

2. Étape 2 : Héritage

Créez les classes suivantes qui héritent de la classe Livre :
- LivrePapier
    - Un livre papier a un nombre de pages et une maison d'édition.
- LivreNumerique
    - Un livre numérique a un format (PDF, EPUB, etc.) et une taille en octets.

3. Étape 3 : Collections

Créez la classe suivante :
- Bibliothèque
    - Une bibliothèque contient une liste de livres et une liste d'emprunts en cours.
    - Implémentez les méthodes suivantes :
        - ajouterLivre(Livre livre) : ajoute un livre à la bibliothèque.
        - supprimerLivre(String idLivre) : supprime un livre de la bibliothèque en utilisant son identifiant unique.
        - trouverLivresParTitre(String titre) : retourne une liste de livres correspondant au titre donné.
        - trouverLivresParAuteur(String auteur) : retourne une liste de livres correspondant à l'auteur donné.
        - emprunterLivre(String idLivre, Personne personne) : emprunte un livre à une personne et enregistre l'emprunt.
        - retournerLivre(String idLivre) : retourne un livre emprunté et met à jour l'emprunt.