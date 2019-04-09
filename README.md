# Celestial Bodies Client

## Star gazing isn't just a hobby, It's a way of life. 
Celestial Bodies helps the a curious star gazers to simply point their device in the direction of a star and recieve information about the star at a click of a button.

This app allows the user to point his phone at a celestial object or constellation in the night time sky, touch the screen at the location of the object, and that celestial app will be identified immediately in context of other celesial objects. The name, location, current knowledge of the object, etc. will be made available. The presentation of the celestial sky will be aesthetically pleasing as possible. The app will require acquiring access to an api database containing the description and locations of those objects. Also, to make the app work, gps location capability tied to angle tilt of the phone will be required. The coordinate information needed for the app to locate and display a given celestial object will be constantly updated via the api. I believe a server will be needed for obtaining gps coordinates of the user. This app will allow users to log in and provide to other users useful information such as particularly good locations for observing the stars. Only those who log in to the service and have filled out a form will be able to communicate with others via the app: A third person user authentication service will be used for this part of the service.

## Required functional components

* A client application&mdash;an Android app, a Java SE application, web pages constructed dynamically on the server side using a templating or component approach, etc.

* An application server, usually in the form of a REST service provider.

* A database server&mdash;typically&mdash;but not necessarily&mdash;a MySQL or Derby database server. (Note that the application server and database server may reside on different hosts or the same host; they may even be embedded in a single program.)

    The inclusion of a back-end database (which is required) doesn't preclude the use of a database on the client side&mdash;e.g. a SQLite database on Android. In fact, your client implementation will still be expected to include some kind of persistent data store.

* Communication between the client application and server application. Typically, this will consist of one or more REST (or REST-like) services published by the server application, and consumed by the client application. These services should allow the client application to create, read, and update (and optionally delete) objects stored on the server side, in accordance with the business logic of the server side.

* Support for multiple simultaneous client application users, connected as relevant to the server application.

* Automated pre-loading of any relevant base data sets (client- and server-side).

* User authentication (this may&mdash;and often does&mdash;leverage the services of an OAuth2 provider, such as Google).

## Current Development of Celestial Bodies

Celestial Bodies is close to being complete, the user is able to point their device towards an a star or planet in the sky and click the capture button and instantly get the top 10 stars, ordered by magnitude. The user is able to check their previous sky-searches in the history screen or retrieve more info about the current stars that the user captured. Capturing stars displays an image of the grouping of stars and also the current ascension and declination of the position of the users device.

### Unimplemented/Incomplete Elements of Celestial Bodies

+ The dashboard displays categories to search, but each categories do not diplay anything once clicked.
+ The search is currently no connected to backend server, therefore cannot make queries.
+ Info doesn't search when item is clicked
+ Need to make the app stay in profile orientation by disabling landscape view

### Known Issues/Bugs

+ Currently having issues with connecting to backend server, search and info hard coded.

## Android API Version and Hardware

for this application the following were used to test UI and function.
+ Andrid API 27
+ Nexus 5x emulator
+ Samsung Galaxy Note 9
+ HTC One M9
+ Galaxy J7 Sky Pro

## Third Party Libraries

+ [Retrofit](https://square.github.io/retrofit/) (includes OkHttp and Retrofit)

  Retrofit is designed to establish and perform requests to WikiSky API web service.
  
+ [Stetho](https://github.com/facebook/stetho/blob/master/README.md)

  Stetho is a developmental tool that examines the local display resources and database contents.

+ [Deep Dive Android Utilities](https://github.com/deep-dive-coding-java/android-utilities/blob/master/README.md)
    This repository contains utility classes (currently 2 of them) that simplify the use of some important Android features in          application code.

+ [Room](https://developer.android.com/jetpack/androidx/releases/room)
    
    Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

+ [Sensor-fusion](https://bitbucket.org/apacha/sensor-fusion-demo)

  Sensor-fusion is a publicly-available demo that allows the user to access their phone's gyroscope and/or accelerometer in order to map out its spatial orientation.

## External Services
This app accesses has the HYG star database pre-loaded as well accesses the Wikisky XML API.

### WikiSky

WikiSky provided the ability information and the ability to search for stars.
+ [WikiSky.org](http://www.wikisky.org/)

+ [WikiSky API](http://server7.wikisky.org/api?locale=EN)

+ [WikiSky XML Documentation](http://server7.wikisky.org/XML_API_V1.0.html)

+ [WikiSky Image Genenerator Documentation](http://server7.wikisky.org/ImageGenerator_1.0.html)

### HYG Database

HYG Star Database provided and managed 100,000+ star database that was used for our backend server.

+ [GitHub](https://github.com/astronexus/HYG-Database/blob/master/README.md)
+ [Background Information](http://www.astronexus.com/hyg)
+ [Version Control](http://www.astronexus.com/endeavour)

## Potential Future of Celestial Bodies


### Goals

### User Interface Improvements

## Design Documentation

+ [Wireframe](docs/Celestial_Bodies_WireFrame_1_1.pdf)

+ [User Stories](docs/User_Stories.md)

## Javadoc Page

## Licenses/Copyright
[Copyright & Licenses](/docs/Copyright_&_Licenses.md)

## Instructions for Building Celestial Bodies

+ 1. [From the celestial-bodies github repository](https://github.com/celestial-bodies/celestial-bodies-client)
        copy the SSH copy key
        
        ![screenshot1](https://user-images.githubusercontent.com/46542189/55755427-2fd36000-5a0c-11e9-964b-404f935277a0.png)
        
+ 2. Open up your preferred IDE (Intellij in this example) and from the homepage, select "Check out from version Control."
        Select git and paste the SSH key into the url field, save it to a a directory of your choosing, and click "Clone."
        
        ![screenshot2](https://user-images.githubusercontent.com/46542189/55755775-37473900-5a0d-11e9-95f6-4783b2670fa6.png)
        
        ![screenshot3](https://user-images.githubusercontent.com/46542189/55755791-40d0a100-5a0d-11e9-8a40-c4a6e18d2a14.png)

+ 3. Once the process finishes, you'll be prompted whether or not you want to open the project. Do NOT open, and instead click "No."
        From the homepage, select "Import Project", select the directory you saved the project to, and click "Ok." 
        
        ![screenshot4](https://user-images.githubusercontent.com/46542189/55756434-c56fef00-5a0e-11e9-8244-c8ba9d0b641e.png)
        
        ![screenshot5](https://user-images.githubusercontent.com/46542189/55756459-d6b8fb80-5a0e-11e9-8b58-d6195612a9fa.png)
        
+ 4. Once you click "Ok," a window will be brought up with importing options. Select "Import project from external model"
        and select the Gradle option and click "Next."
        
        ![screenshot6](https://user-images.githubusercontent.com/46542189/55756717-678fd700-5a0f-11e9-9599-ec37ac04139c.png)
        
     Upon clicking "Next," Intellij will bring up a window in which you may further configure your import settings,
        but most of these can be left on their defaults (as shown in the below screenshot).
        
        ![screenshot7](https://user-images.githubusercontent.com/46542189/55756955-03214780-5a10-11e9-9fc5-7d808efcc13c.png)
        
     You'll be asked if you'd like to overwrite the existing file. Click "Yes."

+ 5. The program will open and the Gradle will start building.          


# Instructions for Using Celestial Bodies

# On Startup

## 1. Sign into Celestial Bodies with Google account.

   ![sign_in](https://user-images.githubusercontent.com/44448717/55816966-b3df2380-5ab0-11e9-9a4d-ecff48e10172.png)


## 2. Upon signing into Celestial Bodies will navigate the user to the home screen.

   ![home_screen](https://user-images.githubusercontent.com/44448717/55816979-ba6d9b00-5ab0-11e9-9595-3a0d1c369899.png)
    
### The Celestial Bodies use bottom navigation which provides options to navigate to the functions the app.
### The bottom navigation has three main options, capture, dashboard, and search.
    
   ![main_nav](https://user-images.githubusercontent.com/44448717/55818521-066e0f00-5ab4-11e9-935b-2c5de06a9ac4.png)
    
### The home screen also has an option to allow the user to see previous searches.
    
   ![historynav](https://user-images.githubusercontent.com/44448717/55818071-00c3f980-5ab3-11e9-9894-3a6fbcbf324b.png)    

 # Capturing Stars

## 1. The Capture navigation is the main function of Celestial Bodies. Capture allows the user to point their device in the direction of an object in the sky and recieve information about the object at a click of a button.
    
   ![capturenav](https://user-images.githubusercontent.com/44448717/55818068-002b6300-5ab3-11e9-91c4-5cb2dc9475b4.png)


## 2. After navigating to the capture screen, the user can point their device in the direction of a star.
    
   ![capturefragment](https://user-images.githubusercontent.com/44448717/55816987-bd688b80-5ab0-11e9-8fb4-2863c4d1b4b1.png)
   
 ### With the help of the phones internal motion sensors, the user will see up to date position, tilt, roll, and direction of their     device.
    
   ![motionsensor](https://user-images.githubusercontent.com/44448717/55819424-f820f280-5ab5-11e9-9403-7907f56f87db.png)


## 3. When the user determines which star they desire to recieve information about; they will use the capture button.
    
   ![captureshotnav](https://user-images.githubusercontent.com/44448717/55818064-002b6300-5ab3-11e9-99dc-bf346579c1fa.png)


## 4. The capture button will prompt a query to the WikiSky API, which will display a star map of  portion of space in which the device was directed.

   ![capture_screen](https://user-images.githubusercontent.com/44448717/55817005-c8bbb700-5ab0-11e9-8364-03037dbc642b.png)


## 5. WikiSky then returns a list of stars, which can be viewed within the info icon located to the left of the capture button.

   ![infonav](https://user-images.githubusercontent.com/44448717/55818065-002b6300-5ab3-11e9-8083-f8fba3321ae3.png)


## 6. Upon clicking the info icon, the user will see a display of the ten brightest stars in the region captured.

   ![infofragment](https://user-images.githubusercontent.com/44448717/55817020-ce190180-5ab0-11e9-84a4-60a4352b7cfe.png)
     

## 7. Once the list of stars are displayed, the user has the ability to click on the star of their choosing to recieve more information.

   ![star_info](https://user-images.githubusercontent.com/44448717/55817031-d709d300-5ab0-11e9-9984-c86ae50e3655.png)
    


# Search for Stars

## The search is located in bottom navigaion and allows the user to search for a single star.
 
 
 ## 1. Search navigation is located to the right of the capture option in the bottom navigation,
 
 ![signoutnav](https://user-images.githubusercontent.com/44448717/55818069-002b6300-5ab3-11e9-8401-8e6f12a824c4.png)
 
 ## 2. Upon clicking search, this will display a search box that allows the user to enter a name of a star they would like more information.
 
 ![search_box](https://user-images.githubusercontent.com/44448717/55821998-f22e1000-5abb-11e9-9cd2-388b73b6cdc5.png)
 
 ## 3. Upon clicking the search button will prompt a display of the stars information.
 
 ![search_star_nav](https://user-images.githubusercontent.com/44448717/55818063-002b6300-5ab3-11e9-8d56-b078d9e85c29.png)
 
 ### Star Information
 
 ![search_info](https://user-images.githubusercontent.com/44448717/55817084-f0ab1a80-5ab0-11e9-9dc1-99ded04038c2.png)
 
 ## Search History
 
## The history menu option provides the user with list of previous stars that they searched for.

## 1. User history is located at the top right corner of the screen.

  ![historynav](https://user-images.githubusercontent.com/44448717/55818071-00c3f980-5ab3-11e9-9894-3a6fbcbf324b.png)
  
## 2. Upon clicking the history icon, the user will see a display of their past queries.

  ![historyfragment](https://user-images.githubusercontent.com/44448717/55817111-ff91cd00-5ab0-11e9-97f3-4d6afdcc819c.png)
  
## Sign out

## When the user is finished using the Celestial Bodies, the user has the option to sign out of the app or simply close the app and remain signed in.

## 1. Sign out is located in the top right corner of the screen to left of history icon within the action bar menu.

![signout](https://user-images.githubusercontent.com/44448717/55817118-03bdea80-5ab1-11e9-857d-fcc02f24d437.png)










## Celestial Bodies, All Rights Reserved
