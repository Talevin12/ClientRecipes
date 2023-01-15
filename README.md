# Hi there


### Update Android Studio
* In android studio Click Help -> Check for updates...
* Install new version and restart visual studio

### Handle zip file
* Open zip file 'FoodizClient'
* Extract folder to desire directory

### Open project from Android studio
* On homepage click 'Open'
* Find desired directory
* Click on 'FoodizClint'folder and OK
* Click 'Trush Project'
* Wait for indexing\installing\Gradle: downloading....

### Change Server path
* Open CMD
* Type ipconfig
* Copy IPv4 Address 
* Return to Android Studio
* Go to in directories app -> java -> com.example.foodizclient (the 1st) -> athentication -> apiAssets -> APIClient
* In retrofit variable in baseurl input change to 'https://[ip copied]:8082

### Add emulator device
* if device (pixel 5) selected Click play
* if not:
  * Click No Devices (left to play button)
  * Click Device Manager
  * Create device
  * Coose Pixel 5
  * Choose R as SDK (download if needed)
  * Click next
  * Click Show Advenced Settings
  * In the stroage/RAM choose 8 GB RAM
  * Click Finish
  * Click play

#### That's it! you succeed to run our app
