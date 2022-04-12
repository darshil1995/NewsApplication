# NewsApp
This application uses MVVM architecture. Also it uses Retrofit library to fetch data from the news API.
Components used in the APP: <br>
1.	Kotlin
2.	LiveData
3.	ViewModel
4.	Retrofit
5.	Pagination
6.	Git version Control
7.	Room Database
<br>
## Description:<br>
News App is an app which fetches the data from the CBC News API. This app is built using Clean + MVVM architecture, uses Retrofit2 for making API calls and uses Room database to cache the data.
:<br>
## FLOW<br>
Entering the app, first it will launch our MainActivity and it will display the newsFragment. NewsFragment contains all the list of the news. I have also provide the facility to filter the news by type by clicking on the above buttons.
On click of the list item, it will navigate to the detailed fragment where user can see the whole news.
Also If there is no internet connection, it will notify the user and display the data stored in the database to make our app persistent.
:<br>
## Architecture:<br>
 Followed MVVM approach::<br>
-	ViewModel as NewsViewModel , where the logic is return
-	NewsRepository class which communicates with the data
-	Our Views are located in views package


