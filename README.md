# BackgroundAccelerometer
Android application to record accelerometer data in the background

Once opened, you can close the app and the service will run indefinitely, and will restart itself on reboot. 
The application stores the log file `accel_log.txt` in your `Documents` folder.
It writes data every 5 seconds by default.

The file is of the format:
  <time since epoch>, <x value>, <y value>, <z value>

TODO:
- set directory and filename from UI
- split log files if gets too large
- set up server to push data to
