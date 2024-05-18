# DreamCaseApp - RESTful API

## Description

L'objectif de ce projet est de créer une application RESTful Web Service à l'aide de Spring Boot, qui implémente les opérations CRUD (Create, Read, Update, Delete) pour gérer des cas (dossiers).

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur

## Technologies utilisées

- Spring Boot 2.6.3
- Spring Data JPA
- H2 Database (base de données en mémoire pour les tests)
- Maven

## Configuration

### Cloner le dépôt

git clone https://github.com/votreutilisateur/dream-case-app.git
cd dream-case-app

### Construire le projet
mvn clean install

### Exécuter l'application
mvn spring-boot:run

L'application sera accessible à http://localhost:8080.

Endpoints de l'API :

Créer un cas (POST)
URL: /cases
Méthode: POST

Body: 
{
    "title": "Initial Title",
    "description": "Initial Description"
}

Response:
{
    "caseId": 1,
    "title": "Initial Title",
    "description": "Initial Description",
    "creationDate": "2024-05-15T14:54:27.123",
    "lastUpdateDate": "2024-05-15T14:54:27.123"
}

Lire un cas (GET)

URL: /cases/{caseId}
Méthode: GET
Response:
{
    "caseId": 1,
    "title": "Initial Title",
    "description": "Initial Description",
    "creationDate": "2024-05-15T14:54:27.123",
    "lastUpdateDate": "2024-05-15T14:54:27.123"
}

Mettre à jour un cas (PUT)

URL: /cases/{caseId}
Méthode: PUT
Body:
{
    "title": "Updated Title",
    "description": "Updated Description"
}
Response:
{
    "caseId": 1,
    "title": "Updated Title",
    "description": "Updated Description",
    "creationDate": "2024-05-15T14:54:27.123",
    "lastUpdateDate": "2024-05-15T15:00:00.456"
}

Supprimer un cas (DELETE)

URL: /cases/{caseId}
Méthode: DELETE
Response: 204 No Content
Tester avec Postman

Étape 1: Créer un cas

Méthode: POST
URL: http://localhost:8080/cases
Headers: Content-Type: application/json
Body:
{
    "title": "Initial Title",
    "description": "Initial Description"
}

Étape 2: Mettre à jour un cas

Méthode: PUT
URL: http://localhost:8080/cases/{caseId}
Headers: Content-Type: application/json
Body:
{
    "title": "Updated Title",
    "description": "Updated Description"
}

Étape 3: Supprimer un cas
Méthode: DELETE
URL: http://localhost:8080/cases/{caseId}
Étape 4: Vérifier la suppression
Méthode: GET
URL: http://localhost:8080/cases/{caseId}
Expected Response: 404 Not Found
Exécution des tests unitaires
Pour exécuter les tests unitaires, utilisez la commande suivante :
mvn test

Auteur: Mohamed Said

Licence
Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.

Ce fichier `README.md` couvre les principales informations nécessaires pour configurer, exécuter et tester votre application Spring Boot CRUD API. Assurez-vous de remplacer les liens, les noms et les détails spécifiques par ceux de votre projet.
