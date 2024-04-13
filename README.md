# Projet Dataframe en Java
[![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/AliBay07/dataframe-java) 
[![Build Status](https://github.com/AliBay07/dataframe-java/actions/workflows/maven.yml/badge.svg)](https://github.com/AliBay07/dataframe-java/actions/workflows/maven.yml) 
[![Build Status](https://github.com/AliBay07/dataframe-java/actions/workflows/docker-image.yml/badge.svg)](https://github.com/AliBay07/dataframe-java/actions/workflows/docker-image.yml) 
[![Google Cloud](https://img.shields.io/badge/Google%20Cloud-My%20Resource-blue?logo=google-cloud)](http:35.222.62.82)




Ce projet vise à fournir une implémentation en Java équivalente au DataFrame de la bibliothèque pandas en Python.

## Fonctionnalités implémentées

- **Affichage du DataFrame:** La classe DataFrame permet d'afficher les données du DataFrame.
- **Sélection avancée :** Vous pouvez sélectionner des données à l'aide des indexes (fonction `iloc`) ou des labels (fonction `loc`). De plus, une sélection avancée est possible avec la fonction `filter` pour filtrer une colonne en fonction des valeurs données en paramètre.
- **Description statistique :** La fonction `describe()` affiche les statistiques du DataFrame, y compris la moyenne, la valeur minimale, la valeur maximale, le nombre d'occurrences et l'écart type.
- **Regroupement de données :** La fonction `groupby` permet de regrouper les valeurs en fonction d'un label et de choisir entre les options moyenne ou somme pour l'agrégation.

## Outils utilisés

- **Maven :** Utilisé pour la gestion des dépendances et la construction du projet.
- **Docker :** Utilisé pour la conteneurisation de l'application, facilitant le déploiement et la gestion de l'environnement de développement.
- **Jacoco :** Utilisé pour le suivi de la couverture de code.
- **JUnit :** Utilisé pour les tests unitaires afin d'assurer la qualité du code.
- **Google Cloud Platform (GCP) :** Utilisé pour le déploiement et l'hébergement de l'application.

## Workflow Git

Nous avons mis en place un workflow Git simple mais efficace pour assurer la qualité du code et faciliter le déploiement :

1. À chaque push sur la branche principale (`main`), des actions sont déclenchées :
   - Compilation des tests avec Maven.
   - Construction et publication de l'image Docker avec la dernière version du code.
2. Nous avons configuré Jacoco pour spécifier que le déploiement du code n'est autorisé que si la couverture de code est supérieure à 0,8.
3. La validation des pull/merge requests est gérée par au moins une personne qui doit approuver le merge request pour que la fusion soit autorisée.
4. Nous avons utilisé des branches dédiées pour chaque fonctionnalité afin de travailler de manière isolée sur chaque caractéristique et faciliter la gestion des versions et la collaboration entre les membres de l'équipe.

## Retours d'expérience sur les outils utilisés

- **Maven et Docker :** Ces outils simplifient le travail mais peuvent être un peu compliqués à utiliser au départ.
- **JUnit et JaCoCo :** Très utiles pour assurer la qualité du code et obtenir un bon aperçu de la couverture de code.
- **Google Cloud Platform :** Peut être difficile à utiliser pour les novices.
