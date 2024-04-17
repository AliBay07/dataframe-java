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

## Déploiement automatique d'une VM sur Google Cloud Platform depuis Git

### Prérequis

Avant de commencer, il faut s'assurer d'avoir les éléments suivants :

- Un compte Google Cloud Platform avec les autorisations nécessaires pour créer des ressources.
- `Terraform` et `Ansible` installés sur sa VM.
Dans le cas contraire, on peut installer ces derniers en exécutant les commandes suivantes :
```bash
sudo apt install terraform 
sudo apt install ansible
```

### Étapes

#### 1. Créer un service account sur GCP

```bash
SERVICE_NAME=[SERVICE_NAME]
PROJECT_NAME=$(gcloud config get-value project)
gcloud iam service-accounts create [SERVICE_NAME]

# Ajouter les autorisations au projet
gcloud projects add-iam-policy-binding "$PROJECT_NAME" --member serviceAccount:"$SERVICE_NAME"@"$PROJECT_NAME".iam.gserviceaccount.com --role roles/editor
#Générer une clé pour le service account
gcloud iam service-accounts keys create ./[SERVICE_NAME].json --iam-account [SERVICE_NAME]@[PROJECT_NAME].iam.gserviceaccount.com
```

#### 2. Configurer `Terraform`

Ensuite, il faut télécharger et extraire le fichier `terraform_basics.tar.gz` depuis ce [lien](https://roparst.gricad-pages.univ-grenoble-alpes.fr/cloud-tutorials/archives/terraform_basics.tar.gz) et modifier les fichiers `simple_deployment.tf` et `variables.tf` pour spécifier les paramètres du déploiement.

#### 3. Créer et ajouter la paire de clés
Pour établir une connexion avec la machine distante, une clé SSH est nécessaire. Une paire de clé se crée avec cette commande : 
```bash
ssh-keygen -t rsa -b 4096 -f ~/.ssh/cloud_rsa
```

Le chemin vers le fichier contenant la clé publique est : `~/.ssh/cloud_rsa.pub`. Il faut l'ajouter au fichier `simple_deployement.tf`.

#### 4. Configurer `Ansible`
C'est l'outil `ansible` qui s'occupe d'installer le conteneur docker sur la nouvelle VM qui sera créée. Pour cela, il est nécessaire de préciser le nom du conteneur tel qu'il est enregistré dans le `DockerHub`.
\
Le fichier de configuration de `Terraform` nécessite une spécification de l'emplacement à partir duquel `Ansible` sera lancé.

#### 5. Lancer `Terraform`

```bash
# Lancer l'outil
terraform init

# Vérifier le plan de déploiement
terraform plan

# Déployer l'infrastructure
terraform apply
```

#### 6. Liaison avec Git

Si tout s'est bien passé, à la fin de l'exécution de `terraform`, il y a une sortie de forme `ip = ...`, c'est avec cette adresse-ci que Git pourra communiquer avec la nouvelle VM, afin de s'assurer de l'installation et l'exécution de chaque nouvell image docker. Cette procédure s'appelle *CD*, Continuous Delivery.\
En pratique, nous ne demandons pas à notre VM d'exécuter l'image, car c'est juste un programme qui fait un affichage et se termine, il n'y a donc pas d'intérêt à le lancer.


##### 6.1 Création de secret 

Afin de pouvoir connecter à la machine, il faut avoir la clé privée crée à l'étape `3`, qui se trouve dans le fichier `~/.ssh/cloud_rsa`, afin de créer un secret git.


##### 6.2 Création de secret
Ayant récupéré l'adresse IP, elle devra être ajoutée dans le fichier du workflow avec la clé et les commandes à exécuter sur la machine virtuelle.

### Fin
Ainsi, chaque *release* sera associée à une procédure automatique de déploiement.

## Options de développement logiciel et intégration continue réalisée

L'état d'avancement du projet vis à vis des suggestions de fonctionnalités à implémenter au niveau du développement logiciel et de l'intégration/la livraison continue est présenté ci-dessous :

- [x] Mise en place de Github
- [x] Intégration continue avec un pipeline Github Actions
- [x] Adoption d'une méthode de travail collaborative : workflow git
- [x] Livraison continue avec Maven : déploiement des différentes versions de la bibliothèque dans le dépôt Maven
- [x] Livraison continue avec Docker : construction d'une image Docker déroulant un scénario de présentation de la bibliothèque et déploiement sur Docker Hub
- [x] Infrastucture-as-code et cloud : déploiement automatique des conteneurs Docker sur des machines virtuelles dans Google Cloud
- [x] Insertion de badges : présence de badges associés aux workflows git mis en place
- [x] Valorisation de la bibliothèque : création d'une page web sur Github Pages pour valorisé la bibliothèque

## Lien vers la documentation du projet

Vous retrouverez la documentation java liée au projet dans le dossier `./docs/apidocs` sur le fichier `index.html`.

## Lien vers le rapport de test de Jacoco

Pour accéder au rapport de couverture de code construit par Jacoco, vous pouvez y accéder après avoir lancer la commande `mvn test`. Cette dernière lance les tests JUnit et la couverture de code avec l'outil Jacoco et crée les rapports associés dans le dossier `./docs/report` où le résumé est situé dans le fichier `index.html`.

## Pages GitHub

Vous pouvez consulter les pages GitHub de ce projet à l'adresse suivante : [https://alibay07.github.io/dataframe-java/](https://alibay07.github.io/dataframe-java/).

## Retours d'expérience sur les outils utilisés

- **Maven et Docker :** Ces outils simplifient le travail mais peuvent être un peu compliqués à utiliser au départ.
- **JUnit et JaCoCo :** Très utiles pour assurer la qualité du code et obtenir un bon aperçu de la couverture de code.
- **Google Cloud Platform :** Peut être difficile à utiliser pour les novices.
