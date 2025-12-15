# 🏠 AZURIMMO API REST : Système de Gestion Immobilière

## 🌟 Introduction

Ce projet est l'implémentation d'une API REST pour le système de gestion immobilière **AzurImmo**. Il a été construit en utilisant l'écosystème Spring Boot pour une gestion robuste des entités relationnelles **Bâtiment** et **Appartement**.

L'architecture est strictement séparée en couches (Controller, Service, Repository) et utilise le patron de conception **Data Transfer Object (DTO)** pour assurer la sécurité et l'indépendance des données de l'API vis-à-vis du modèle JPA.

## ⚙️ Stack Technique

| Catégorie | Technologie | Rôle |
| :--- | :--- | :--- |
| **Backend Core** | Spring Boot 3.x | Conteneur IoC et gestion du serveur embarqué (Tomcat). |
| **Persistance** | Spring Data JPA / Hibernate | Mapping Objet-Relationnel (ORM) et gestion des transactions. |
| **Dépendances** | Lombok | Réduction du code répétitif (Getters, Setters, Constructors). |
| **Base de Données** | MariaDB / PostgreSQL | Stockage des données relationnelles. |
| **Build Tool** | Maven | Gestion des dépendances et du cycle de vie du projet. |

## 🚀 Mise en Place du Projet

### 1. Prérequis

* **Java Development Kit (JDK) 17+**
* **Maven**
* Un outil de test d'API (Postman ou VS Code REST Client).

### 2. Configuration de la Base de Données

Modifiez le fichier `src/main/resources/application.properties` pour établir la connexion et définir le comportement d'Hibernate :

```properties
# Configuration de la connexion à la BDD
spring.datasource.url=jdbc:mariadb://localhost:3307/azurimmo
spring.datasource.username=root
spring.datasource.password=

# Configuration Hibernate (DDL)
# 'update' est recommandé pour les changements incrémentiels de schéma.
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=9005
