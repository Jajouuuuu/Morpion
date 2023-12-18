# Morpion Solitaire

## Description
Le projet Morpion Solitaire est un jeu développé en Java 17 avec JavaFX  par Jeanne-Emma Lefèvre et Paul Malet. Le jeu implémente les règles du Morpion Solitaire où un seul joueur est nécessaire. Les règles sont simples, et l'objectif est d'obtenir le score maximal en ajoutant des croix sur une grille.

## Installation
Assurez-vous d'avoir Maven installé sur votre système. Pour exécuter le projet, utilisez la commande suivante à la racine du projet (dans le dossier nommé Morpion) :

```bash
mvn clean install javafx:run
```

## Utilisation
Une fois le projet lancé, suivez les règles du Morpion Solitaire pour ajouter des points à la grille (au niveau des intersections) et maximiser votre score.

## Javadoc
L'index de la Javadoc se trouve dans le dossier `javadoc`. Vous pouvez également consulter la documentation générée à l'emplacement suivant : `Morpion\target\site\javadoc\apidocs`.

## Structure du Projet
- Les tests sont exécutés automatiquement lors du lancement du projet.
- Les ressources telles que les fichiers .xml et les images sont situées dans le dossier `ressources`.
- Les fichiers de sauvegarde sont à la racine du projet.

## Arborescence du Projet
```
Morpion
│   README.md
│   pom.xml   
├── src
├── test
├── ressources
│   ├── images
│   ├── styles
│   └── ...
├── javadoc
│   └── ...
└── sauvegardes
    └── ...
```

## Dépendances
Le fichier `pom.xml` importe toutes les bibliothèques nécessaires au projet.

- [bcrypt](https://mvnrepository.com/artifact/at.favre.lib/bcrypt) - 0.10.2
- [jBCrypt](https://mvnrepository.com/artifact/de.svenkubiak/jBCrypt) - 0.4.1
- [maven-javadoc-plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-javadoc-plugin) - 3.6.3
- [gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) - 2.10.1
- [javafx-controls](https://mvnrepository.com/artifact/org.openjfx/javafx-controls) - 18
- [javafx-fxml](https://mvnrepository.com/artifact/org.openjfx/javafx-fxml) - 18
- [opencsv](https://mvnrepository.com/artifact/com.opencsv/opencsv) - 5.9
- [junit](https://mvnrepository.com/artifact/junit/junit) - 4.13.2
- [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) - 2.12.5

## Auteurs
- Jeanne-Emma Lefèvre
- Paul Malet
