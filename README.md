# The_Series_App
Aplicación para ver las series que hay y poder gestionarlas usando la api de [TheMoviedb](https://www.themoviedb.org/) nativa para Android

Proyecto de las asignuaturas:
* Desarrollo de Interfaces
* Programación Multimedia y Dispositivos Móviles

Proyecto para Android


***


#### Instalar la aplicación
* O bien instalarla mediante el ide
* O usar el apk de la aplicación localizado en `/app/release` el archivo `app-release.apk`

***


#### Tecnologías usadas:
* Java 8
* [Android Studio](https://developer.android.com/studio)
* [Android Jetpack](https://developer.android.com/jetpack?hl=es-419)
* [Retrofit](https://square.github.io/retrofit/)
* [Firebase](https://firebase.google.com/)

***


#### Firebase:
* La aplicación cuenta con el login de Google con Firebase
* Los usuarios que hacen login se almacenan en una colección de firebase `users` 
* Las series favoritas marcadas por el usuario se guardan en una subcolección del usuario llamada `favorites`
* Se pueden visualizar las series favoritas y modificarlas

***