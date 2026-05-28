# SecurIT-Memory

SecurIT-Memory est un jeu de memory développé en C# / WinForms sur le thème de la cybersécurité.  
Le jeu propose plusieurs modes de difficulté et de jeu, une interface personnalisée et un fond animé façon terminal.

---

## 1. Objectifs du projet

Ce projet a été réalisé dans le cadre d’un exercice de développement logiciel en C# et WinForms.  
Il m’a permis de :

- Concevoir une application graphique complète avec WinForms.
- Structurer le code en programmation orientée objet (classes, modèles, logique métier).
- Gérer des événements, des timers et des animations graphiques.
- Implémenter plusieurs modes de jeu (chronomètre, hardcore, mémoire inversée).
- Créer une interface personnalisée avec un thème cybersécurité et un fond animé type “terminal”.

---

## 2. Fonctionnalités

- Modes de difficulté :  
  - Facile (4×4)  
  - Moyen (6×6)  
  - Difficile (8×8)

- Modes de jeu :  
  - Mémoire inversée : les cartes se retournent automatiquement.  
  - Mode chronomètre : le temps est compté.  
  - Mode hardcore : nombre d’erreurs limité.

- Interface graphique :
  - Fond animé façon terminal via l’événement `Paint`.
  - Grille de cartes générée dynamiquement selon la difficulté.
  - Menus et navigation pour lancer/rejouer une partie.

---

## 3. Technologies utilisées

- Langage : C# (.NET)
- Framework : WinForms
- Concepts :
  - Programmation orientée objet
  - Timers (`System.Windows.Forms.Timer`)
  - Gestion d’événements (clics, Paint, etc.)
  - Génération dynamique d’UI
- IDE : Visual Studio

---

## 4. Installation et exécution

### Prérequis

- Windows
- Visual Studio (avec charge de travail “Développement .NET de bureau”)

### Étapes

1. Cloner le dépôt :

   ```bash
   git clone https://github.com/anaisivanoff/SecurIT-Memory.git
   cd SecurIT-Memory
   ```

2. Ouvrir la solution `SecurIT-Memory.sln` dans Visual Studio.
3. Choisir le projet de démarrage si nécessaire.
4. Lancer l’application avec le bouton **Start** (ou F5).

---

## 5. Organisation du code

- Formulaires WinForms : interface utilisateur, menus, écran de jeu.
- Classes métier : représentation des cartes, gestion du plateau, logique des modes de jeu.
- Ressources : images, icônes et éléments graphiques pour le thème cybersécurité.

(À adapter avec les vrais noms de formulaires / classes si besoin.)

---

## 6. Mon rôle dans le projet

- Conception de l’interface et du thème cybersécurité.
- Implémentation d’une partie de la logique de jeu (modes, difficulté…).
- Gestion des animations et du fond “terminal”.
- Organisation du code et des ressources.

---

## 7. Pistes d’amélioration

- Ajouter un système de score persistant.
- Enregistrer les meilleures performances (leaderboard).
- Ajouter des effets sonores.
- Internationalisation (FR/EN).