# lets-play-api

API RESTful développée avec Spring Boot et MongoDB pour gérer des utilisateurs et des produits, avec authentification JWT, rôles (USER / ADMIN), mots de passe hashés et gestion robuste des erreurs. [web:94][web:92]

---

## 1. Contexte et objectifs

Ce projet a été réalisé dans le cadre de ma formation pour :

- Concevoir une API REST structurée (controllers, services, repositories).
- Mettre en place une authentification et une autorisation basées sur JWT.
- Persister les données dans une base NoSQL MongoDB.
- Gérer proprement les erreurs et les statuts HTTP.

Cas d’usage principal : permettre à des utilisateurs de s’inscrire, se connecter et gérer une liste de produits, avec des droits différents selon les rôles. [web:94][web:106]

---

## 2. Stack technique

- Java et Spring Boot (API REST, injection de dépendances, validation).
- Spring Security pour l’authentification et l’autorisation. [web:94]
- MongoDB pour le stockage des utilisateurs et des produits. [web:92][web:107]
- JWT (JSON Web Token) pour les tokens d’accès. [web:94][web:106]
- Maven pour la gestion des dépendances.
- IntelliJ IDEA pour le développement, Postman ou Insomnia pour tester l’API.

---

## 3. Structure du projet

Les classes principales se trouvent dans `lets-play-api/src/main/java/com/letsplay/api` :

- `LetsPlayApiApplication.java` : classe principale Spring Boot, point d’entrée de l’application.
- `User.java` : modèle représentant un utilisateur stocké dans MongoDB (identifiants, rôle, etc.).
- `GlobalExceptionHandler.java` : gestionnaire global d’exceptions pour renvoyer des réponses d’erreur cohérentes avec des statuts HTTP adaptés. [web:94][web:112]

D’autres packages peuvent contenir les controllers, services, repositories et composants liés à la sécurité (JWT, configuration Spring Security).

---

## 4. Installation et lancement

### 4.1. Prérequis

- Java 17 ou plus.
- Maven installé (ou utilisation du wrapper `mvnw` présent dans le projet).
- MongoDB en local (par défaut sur `mongodb://localhost:27017`). [web:107]

Optionnel : lancer MongoDB via Docker.

```bash
docker run -d --name lets-play-mongo -p 27017:27017 mongo
```

### 4.2. Cloner le projet

```bash
git clone https://github.com/anaisivanoff/lets-play-api.git
cd lets-play-api/lets-play-api
```

Le fichier `pom.xml` se trouve dans ce dossier interne `lets-play-api`.

### 4.3. Configuration

Le projet utilise un fichier `application.yml` dans `src/main/resources` :

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/letsplay
  application:
    name: lets-play-api

server:
  port: 8080
```

Cette configuration démarre l’API sur `http://localhost:8080` et utilise une base MongoDB `letsplay` sur `localhost:27017`. [web:107]

Si tu as des propriétés spécifiques pour JWT (`jwt.secret`, `jwt.expiration`, etc.), ajoute-les également dans ce fichier.

### 4.4. Lancer l’application

Depuis le dossier où se trouve `pom.xml` :

```bash
./mvnw spring-boot:run
# ou
mvn spring-boot:run
```

L’API est alors accessible à l’adresse :

```text
http://localhost:8080
```

---

## 5. Endpoints (à adapter à ton code)

Les chemins suivants sont des exemples classiques ; adapte-les aux URLs exactes de tes controllers.

### Authentification

- `POST /api/auth/register`  
  Corps : `{"username": "...", "password": "...", "role": "USER"}`  
  Accès : public  
  Effet : crée un utilisateur avec mot de passe hashé.

- `POST /api/auth/login`  
  Corps : `{"username": "...", "password": "..."}`  
  Accès : public  
  Effet : renvoie un JWT si les identifiants sont valides. [web:94][web:106]

### Utilisateurs

- `GET /api/users/me`  
  Accès : utilisateur connecté (JWT dans l’en-tête `Authorization: Bearer <token>`).  
- `GET /api/users`  
  Accès : réservé au rôle ADMIN.

### Produits

- `GET /api/products`  
- `POST /api/products`  
- `PUT /api/products/{id}`  
- `DELETE /api/products/{id}`  

Accès à préciser : par exemple lecture pour USER, création/modification/suppression pour ADMIN. [web:94][web:106]

---

## 6. Sécurité et JWT

- Authentification basée sur JWT :  
  - Connexion via un endpoint de login (ex. `/api/auth/login`).  
  - L’API renvoie un token JWT. [web:94][web:106]
  - Les requêtes sécurisées doivent inclure l’en-tête :

    ```http
    Authorization: Bearer <JWT>
    ```

- Rôles : `USER` et `ADMIN`, gérés via Spring Security (configuration HTTP et/ou annotations). [web:92][web:106]

---

## 7. Gestion des erreurs

Le fichier `GlobalExceptionHandler.java` centralise le traitement des exceptions de l’API afin de renvoyer des réponses JSON cohérentes, avec des statuts HTTP adaptés. [web:112][web:114]

Par exemple :

- 400 : requête invalide.
- 401 : absence de token ou token invalide.
- 403 : rôle insuffisant pour accéder à la ressource.
- 404 : ressource non trouvée.
- 500 : erreur interne.

---

## 8. Mon rôle dans le projet

- Conception de l’architecture de l’API (organisation des classes, modèle `User`).
- Implémentation de la sécurité via JWT et gestion des rôles.
- Intégration de MongoDB pour la persistance.
- Mise en place de la gestion centralisée des erreurs (GlobalExceptionHandler).
- Tests des endpoints avec Postman ou Insomnia.

---

## 9. Pistes d’amélioration

- Ajouter des tests unitaires et d’intégration (Spring Boot Test). [web:94]
- Générer une documentation OpenAPI/Swagger.
- Mettre en place des refresh tokens et une rotation de tokens. [web:111]
- Dockeriser l’API et la base MongoDB pour faciliter le déploiement. [web:96]