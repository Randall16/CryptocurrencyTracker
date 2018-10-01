## Cryptocurrency Tracker

An Android app that tracks the current and historic prices of popular cryptocurrencies such as Bitcoin. 

## Project Status

This project is mostly complete. The majority of the features I was aiming for are implemented and fully functional. Some possible future additions include getting notifications involved for when there is a big price spike. I am also considering adding a "news" tab that works with the Twitter API to display recent tweets about cryptocurrrency.

Also, as of right now the supported cryptocurrencies are Bitcoin, Bitcoin Cash, Ethereum, Litecoin, Ripple, and Stellar. In the future if I ever want to add more this will be easy because I just need to extend the base Cryptocurrency which already contains the methods for retrieving the pricing data. 

## Project Screen Shots

#### Home Fragment:   

<img src="https://github.com/Randall16/CryptocurrencyTracker/blob/master/screenshots/homeFragment.jpg" width="250" >

### Graph Fragment

Year Change                | 7 Day Change
:-------------------------:|:-------------------------:
<img src="https://github.com/Randall16/CryptocurrencyTracker/blob/master/screenshots/yearGraph.jpg" width="250" > |  <img src="https://github.com/Randall16/CryptocurrencyTracker/blob/master/screenshots/sevenDayGraph.jpg" width="250" >

### Converter Fragment

<img src="https://github.com/Randall16/CryptocurrencyTracker/blob/master/screenshots/converterFragment.jpg" width="250" > |  <img src="https://github.com/Randall16/CryptocurrencyTracker/blob/master/screenshots/converterWithKeypad.jpg" width="250" >

### Domestic Currency Support

<img src="https://github.com/Randall16/CryptocurrencyTracker/blob/master/screenshots/domesticCurrency.jpg" width="250" >


## Reflection

This project was created mainly to to further my experience with Android programming. With this project I set out to gain experience building an app that pulls data from a remote source. In this case I used the CryptoCompare API (https://min-api.cryptocompare.com/) as my source of pricing data. I then used Google's Volley Library manage and create the HTTP connections with the API.

After getting this initial pricing data I looked to expand on the functionality of the app. I then implemented the Graph tab by iterating through the historic price data and creating data points based on the time frame that the user has selected E.g. "7 Days" or "30 Days." To make this Graph View I used the open source MPAndroidChart Library (https://github.com/PhilJay/MPAndroidChart).

Overall, I learned a ton while building this app. Working with an API that outputs data in JSON format, managing multiple HTTP requests, and working with U.I. related components like Fragments is just a fraction of all the tasks necessary to make an app like this work.
