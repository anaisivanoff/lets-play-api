# lets-play-api
API RESTful développée avec Spring Boot, Postman et MongoDB pour gérer des utilisateurs et des produits, avec authentification JWT, rôles (user/admin), mots de passe hashés et gestion robuste des erreurs.

# 🎲 Let's Play API
## 🚀 Objectifs d’apprentissage

Ce projet a été réalisé dans le cadre d’un exercice de mise en pratique de Spring Boot, de la conception d’API REST et de la sécurité applicative. Il m’a permis de :

- Maîtriser la création d’une API RESTful avec Spring Boot (architecture controller / service / repository, bonnes pratiques HTTP).
- Intégrer et gérer les données avec MongoDB via Spring Data.
- Implémenter des opérations CRUD complètes pour plusieurs entités (utilisateurs, produits, etc.).
- Appliquer Spring Security et une authentification basée sur JWT (JSON Web Token).
- Mettre en place un contrôle d’accès basé sur les rôles (utilisateur vs administrateur).
- Implémenter une gestion sécurisée des mots de passe avec hashage BCrypt (hash + salt).
- Construire une gestion robuste des erreurs avec des codes HTTP pertinents et des réponses JSON claires (validation, authentification, autorisation, conflits, etc.).

## 🧱 Stack technique

- Langage : Java 17 (ou ta version)
- Framework : Spring Boot
  - Spring Web (API REST)
  - Spring Data MongoDB
  - Spring Security
- Base de données : MongoDB
- Authentification : JWT (JSON Web Token)
- Build : Maven
- Tests manuels : Postman

#⚙️ Installation & lancement

#Prérequis

- Java installé (JDK 17 recommandé)
- Maven installé
- MongoDB en cours d’exécution (local ou Atlas)
- Git installé

### Récupérer le projet

bash
git clone https://github.com/anaisivanoff/lets-play-api.git
cd lets-play-api
