# WiM - Warehouse Inventory Monitor

My solution to Reaktor's Junior Developer, summer
2021 [pre-assignment](https://web.archive.org/web/20201231124339/https://www.reaktor.com/junior-dev-assignment/)

Live Demo: ~~https://peaceful-earth-81572.herokuapp.com/~~ No longer available.  
Initial startup of the application can take a couple of minutes as it initially pulls data from the legacy APIs to the
cache.

WiM consists of two distinct parts, server and app.

App is located in the [app/](https://github.com/ChargedByte/WiM/tree/stable/app) directory, it is
a [Nuxt.js](https://nuxtjs.org/) SPA written mostly in TypeScript and Vue.

Server is located at the base of the repository, it is written in Java and
uses [Sring Boot](https://spring.io/projects/spring-boot). It servers a dual purpose of both serving the app to users
and caching the data from the legacy APIs.

## Requirements

- Java Development Kit 11+ (I use [AdoptOpenJDK](https://adoptopenjdk.net/))
- [Node 14+](https://nodejs.org/en/)

## Building

The application is built using [Gradle](https://gradle.org/)

```
$ ./gradlew build # or 'gradlew.bat build' if on Windows
```

After the build has finished the output can be found at `build/libs/WiM-${VERSION}.jar`
