# Note d’intention projet 12:
## Aidez la communauté en tant que développeur d'application Java:
## Site communautaire de sorties moto

En tant que développeur JEE/ Spring, j’ai été sollicité par des amis organisant régulièrement des sorties moto en groupe pour concevoir une application web utile dans leur pratique de la moto. Jusqu’à présent, l’organisation de ce genre de sorties se faisaient sur des forums spécialisés et était chronophage pour les organisateurs avec un réel problème de communication.

Cette application permettra =

* d’organiser  des sorties avec un nombre définis de participants.
* d’indiquer les étapes et les arrêts prévus : pompe à essence, pause café/ resto, hôtel / camping ....
* filtrer les sorties selon divers critères: niveau / durée/ lieu / hors piste
* s’inscrire et/ou se désister d’une sortie pour les participants.
* publier un mini compte rendu de la sortie avec photos + commentaires d’utilisateurs ayant participé
* partager des roadbooks= fichier d'itinéraire KML (google earth/ google map).
* consulter automatiquement les prévisions météo sur l'itinéraire de la sortie (utilisation d’une API météo) une fois le fichier d'itinéraire uploadé.
* mailing automatique en cas d’annulation par l’organisateur.
* espace d'échanges entre utilisateurs: fil de publication “chat”

Cette application web devra être responsive pour une utilisation sur support mobile.

## Pour la démo:
Vous pouvez vous connecter en utilisant l'identifiant : rider1 et le mot de passe= 12345

## Tehnologies utilisées:
* JDK 8
* Spring Boot 2.2.2
* Spring Boot data jpa
* Spring Boot mail
* Google Maps Javascript API
* OpenWeather Rest API
* Maven
* mySQL 5.6.41 (login: root, pwd=root)
* IntelliJ 2018.2
* Bootstrap 3.3.7
* JSTL 1.2
* JSP  2.3
* Apache Tomcat (embeded & 9.0.29)
* dbUnit 2.5.4 et H2 pour les unitaires et d'intégration


## Lancer l'application à l'aide du plugin Maven

Il est possible de compiler et d'executer rapidement l'application avec la commande suivante maven

```
mvn spring-boot:run
```

## Compiler en JAR/WAR

La compilation des livrables se fait avec la commande suivante à l'aide de maven
```
mvn clean package
```
