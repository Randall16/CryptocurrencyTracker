## Cryptocurrency Tracker

An Android app that track the current and historic prices of popular cryptocurrencies such as Bitcoin. 

## Project Status

This project is mostly complete. The majority of the features I was aiming for are implemented and fully functional. Some possible future additions include getting notifications involved for when there is a big price spike. I am also considering adding a "news" tab that works with the Twitter API to display recent tweets about cryptocurrrency.

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

This project was mainly created to to further my experience with Android programming. There are litterly thousands of cryptocurrency price tracking apps on the Play Store so creating the "best" one wasn't a viable goal. With this project I set out to gain experience building an app that pulls data from a remote source. In this case I used the CryptoCompare API as my source of pricing data. I then used Google's Volley Library manage and create the HTTP connections with the API.

After getting this initial pricing data I looked to expand on the functionality of the app. I then implemented the Graph tab by iterating through the historic price data and creating data points on the actual Graph View itself.

One of the main challenges I faced while building this was handling assynchronous opertaions. The solution for this ended being to create an Interface that contained an "OnFetchesComplete" method. I then required this Interface to be passed into each instance of the object. When the object would complete its fetches I called that method to alert the rest of the program that data had been successfully pulled and that the U.I. components needed to be updated.

Overall, I learned a ton while building this app. Working with an API that outputs data in JSON format, managing multiple HTTP requests, and working with U.I. related components like Fragments is just a sample of all the tasks necessary to make an app like this work.
