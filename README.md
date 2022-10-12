# CryptocurrencyApp-

This app uses MVVM arachitecture and uses interactor classes instead of usecases to retrieve data from the api.
The app will fetch a coin list which will display when the app is launched. Once a coin is selected from the list 
it takes you to the coin details screen which contains a description and 2 lists for tags and team memebers. I used 
Livedata in the viewmodels to observe data coming from api.

# Improvements
If the project was bigger and growing I would have used Dependacy Injection Dagger Hilt. 
On the Coin Details screen Instead of a recyclerview I would use a chip group instead for selecting a tag for a search.
I attempted pagination using paging3 and a manual solution which did not work. More time would get that working.
