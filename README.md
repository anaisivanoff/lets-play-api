# lets-play-api

API RESTful développée avec Spring Boot et MongoDB pour gérer des utilisateurs et des produits, avec authentification JWT, rôles (USER / ADMIN), mots de passe hashés et gestion robuste des erreurs. [web:94][web:92]

---

## 1. Contexte et objectifs

Ce projet a été réalisé dans le cadre de ma formation pour travailler sur :

- La conception d’une API REST propre (contrôleurs, services, repositories).
- L’authentification et l’autorisation avec JWT.
- La persistance des données dans une base NoSQL (MongoDB).
- Une gestion claire des erreurs et des statuts HTTP.

Cas d’usage principal : permettre à des utilisateurs de s’inscrire, se connecter et gérer une liste de produits, avec des droits différents selon les rôles.

---

## 2. Stack technique

- Java et Spring Boot (API REST, injection de dépendances, validation).
- Spring Security pour l’authentification et l’autorisation. [web:94]
- MongoDB pour la persistance des utilisateurs et des produits. [web:92]
- JWT (JSON Web Token) pour les tokens d’accès. [web:94]
- Maven pour la gestion des dépendances.
- IntelliJ IDEA comme IDE, Postman/Insomnia pour les tests d’API.

---

## 3. Installation et lancement

### 3.1. Prérequis

- Java 17 ou plus.
- Maven (ou le wrapper `mvnw` fourni).
- MongoDB en local (par défaut `mongodb://localhost:27017`) ou via Docker. [web:92][web:96]

Exemple avec Docker :

```bash
docker run -d --name lets-play-mongo -p 27017:27017 mongo
```

### 3.2. Cloner le projet

```bash
git clone https://github.com/anaisivanoff/lets-play-api.git
cd lets-play-api/lets-play-api
```

### 3.3. Configuration

Dans `src/main/resources/application.properties` (ou `application.yml`), définir au minimum :

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/letsplay
jwt.secret=change_me_par_un_secret_long
jwt.expiration=3600000
```

Adapter ces propriétés aux noms réellement utilisés dans ton projet.

### 3.4. Lancer l’application

Depuis le dossier où se trouve `pom.xml` :

```bash
./mvnw spring-boot:run
# ou
mvn spring-boot:run
```

L’API est accessible sur :

```text
http://localhost:8080
```

---

## 4. Endpoints principaux

Les chemins suivants sont à adapter aux URLs exactes de ton projet.

### Authentification

- `POST /api/auth/register`  
  Corps : `{"username": "...", "password": "...", "role": "USER"}`  
  Accès : public.  
  Effet : crée un utilisateur avec mot de passe hashé.

- `POST /api/auth/login`  
  Corps : `{"username": "...", "password": "..."}`  
  Accès : public.  
  Effet : renvoie un JWT si les identifiants sont valides. [web:94][web:93]

### Utilisateurs

- `GET /api/users/me`  
  Accès : utilisateur connecté (JWT requis dans `Authorization: Bearer <token>`).  
- `GET /api/users`  
  Accès : ADMIN uniquement.

### Produits

- `GET /api/products`  
- `POST /api/products`  
- `PUT /api/products/{id}`  
- `DELETE /api/products/{id}`  

Accès : à adapter en fonction de ta logique (lecture pour USER, écriture pour ADMIN, etc.).

---

## 5. Sécurité et JWT

- Authentification basée sur JWT :  
  - Connexion via `/api/auth/login`.  
  - L’API renvoie un token JWT dans la réponse. [web:94][web:93]
  - Les requêtes protégées doivent inclure l’en-tête :

    ```http
    Authorization: Bearer <JWT>
    ```

- Rôles : `USER` et `ADMIN`, gérés par Spring Security via la configuration HTTP ou des annotations sur les endpoints. [web:92][web:96]

---

## 6. Gestion des erreurs

L’API renvoie des codes HTTP explicites :

- 400 : requête invalide (validation, format des données, etc.).
- 401 : authentification manquante ou invalide.
- 403 : accès interdit (rôle insuffisant).
- 404 : ressource non trouvée (produit, utilisateur, etc.).
- 500 : erreur interne inattendue.

Les erreurs peuvent être centralisées dans des handlers globaux (par exemple avec `@ControllerAdvice`) ou des exceptions personnalisées. [web:93][web:94]

---

## 7. Mon rôle dans le projet

- Conception et implémentation de l’API REST (controllers, services, repositories).
- Mise en place de l’authentification JWT et de la gestion des rôles.
- Intégration de MongoDB pour la persistance des données.
- Gestion des erreurs et organisation générale du projet.
- Tests des endpoints avec Postman.

---

## 8. Pistes d’amélioration

- Ajouter des tests unitaires et d’intégration (Spring Boot Test).
- Générer une documentation OpenAPI/Swagger.
- Mettre en place un système de refresh token.
- Dockeriser l’API et la base MongoDB pour un déploiement plus simple.