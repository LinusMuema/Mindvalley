# ![Logo](https://github.com/LinusMuema/Mindvalley/blob/master/app/src/main/ic_launcher-playstore.png =20x20)Mindvalley Android Challenge
* Android application to display the channels section.

## Contributors
* [Linus Muema](https://github.com/LinusMuema)

## Techologies used
* [Retrofit](https://square.github.io/retrofit/) : This library is used to make the network calls to retrieve data through the network.
* [RxJava](https://github.com/ReactiveX/RxJava) : This is used to bring the functional-reactive paradigm in my application to handle asynchronous operations and make code clean.
* [Koin](https://insert-koin.io/) : This is used to add Dependency injection in the application to simplify the code and make the application more efficient.
* [Room](https://developer.android.com/topic/libraries/architecture/room) : This is a persistence library to introduce offline capabilities  into the app by saving data in the local device database.
## Challenges faced
### 1. Resposne from the api
* The response type from the api comes in the form of text/html which I had not interacted with before, but with the help of [Jsoup](https://jsoup.org/) I was able to extract the required data and convert it to Gson format. 

### 2. Adding RxJava to Room
* Using LiveData to observe data in the database proved not be as efficient therefore I opted to add RxJava to Room functions within the DAO and this was new to me.


## Improvement suggestions
### 1. Images from the api
* The images through the api were in a square format but according to the [Figma](https://www.figma.com/file/EjpKhZjcmTwAkrVRUKIK2p/Channels) the images were to be in rectangular shapes which leads to pixelations and bad qulaity images. I would suggest the option to change the style to a more square option or changing the api Images to the required size.

### 2. The api response type
* This is not much of an issue but if the reponse would be "application/json" then the amount of code written would be less and it would be easier to work with the response without any need of external libraries.
