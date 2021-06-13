# MyAlbum
This is a small android project that make use of a public API for fetching a list of Albums and then displays the Albums.

## This application includes:
  1. Displays albums list sorted by title.
  2. Albums are persisted for offline viewing.
  3. Thumbnail images for each album for easy recognition(Currently, only dummy placeholder images).
  4. Error handling in case of API call failure. Displays a user friendly error.

### The application has the following features:
  1. Uses the latest recommendations from Android Community for architecture approach and libraries.
  2. Used Kotlin language.
  3. Uses mvvm with repository architecture.
  4. Uses Architecture components like livedata, databinding, room data base for offline viewing.
  5. Min SDK version is 23
  6. Unit test using jUnit included.
  
  ### List of Recommendations for future:
  1. Can have a splash screen for better UX.
  2. Album list can have a valid thumbnail image to recognize the albums easyly. This needs the API to send the thumnail image url as part of json response.
  3. Tap on Albums list for viewing the details. Album detail image url needs to be sent as part of json response for the album-details API. 
  4. Albums can be liked and rated.
  5. Have a login feature for the app to uniquely track the user.
  6. User can add comments and share opinions about the albums under each Album details page.
  7. Can have an Artist version also which can be used for collaborating Artist with public and vice versa.
  8. Social media sharing directly from the app so that Alums can be shared to friends and relatives.
  9. Can have Albums purchased with payment options.
  10. Usrs can view/play albums and also download albums for offline hearing.



