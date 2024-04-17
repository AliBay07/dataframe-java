# Projet Dataframe en Java
 [![Build Status](https://github.com/AliBay07/dataframe-java/actions/workflows/maven.yml/badge.svg)](https://github.com/AliBay07/dataframe-java/actions/workflows/maven.yml) [![Build Status](https://github.com/AliBay07/dataframe-java/actions/workflows/docker-and-cloud.yml/badge.svg)](https://github.com/AliBay07/dataframe-java/actions/workflows/docker-and-cloud.yml) [![Build Status](https://github.com/AliBay07/dataframe-java/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/AliBay07/dataframe-java/actions/workflows/maven-publish.yml/) 

Package : [![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/AliBay07/dataframe-java/packages/2123285)


Ce projet vise à fournir une implémentation en Java équivalente au DataFrame de la bibliothèque pandas en Python.

## Exécution des tests et construction du projet

Pour compiler le projet, vous pouvez utiliser la commande suivante :
```bash
mvn compile
```

Pour exécuter les tests unitaires, vous pouvez utiliser la commande suivante :
```bash
mvn test
```

Pour nettoyer le projet, vous pouvez utiliser la commande suivante :
```bash
mvn clean
```

Pour générer le fichier JAR exécutable, vous pouvez utiliser la commande suivante :
```bash
mvn package
```

## Création de l'image Docker et exécution du conteneur

###### Construction de l'image Docker
```bash
docker build -t dataframe-java .
```

###### Exécution du conteneur à partir de l'image
```bash
docker run dataframe-java
```

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

## Options de développement logiciel et intégration continue réalisée

L'état d'avancement du projet vis à vis des suggestions de fonctionnalités à implémenter au niveau du développement logiciel et de l'intégration/la livraison continue est présenté ci-dessous :

- [x] Mise en place de Github
- [x] Intégration continue avec un pipeline Github Actions
- [x] Adoption d'une méthode de travail collaborative : workflow git
- [ ] Livraison continue avec Maven : déploiement des différentes versions de la bibliothèque dans le dépôt Maven
- [x] Livraison continue avec Docker : construction d'une image Docker déroulant un scénario de présentation de la bibliothèque et déploiement sur Docker Hub
- [ ] Infrastucture-as-code et cloud : déploiement automatique des conteneurs Docker sur des machines virtuelles dans Google Cloud
- [x] Insertion de badges : présence de badges associés aux workflows git mis en place
- [x] Valorisation de la bibliothèque : création d'une page web sur Github Pages pour valorisé la bibliothèque

## Lien vers la documentation du projet

Vous retrouverez la documentation java liée au projet dans le dossier `./docs/apidocs` sur le fichier `index.html`.

## Lien vers le rapport de test de Jacoco

Pour accéder au rapport de couverture de code construit par Jacoco, vous pouvez y accéder après avoir lancer la commande `mvn test`. Cette dernière lance les tests JUnit et la couverture de code avec l'outil Jacoco et crée les rapports associés dans le dossier `./docs/report` où le résumé est situé dans le fichier `index.html`.

## Retours d'expérience sur les outils utilisés

- **Maven et Docker :** Ces outils simplifient le travail mais peuvent être un peu compliqués à utiliser au départ.
- **JUnit et JaCoCo :** Très utiles pour assurer la qualité du code et obtenir un bon aperçu de la couverture de code.
- **Google Cloud Platform :** Peut être difficile à utiliser pour les novices.
