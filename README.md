# BackgroundAccelerometer
Android application to record accelerometer data in the background

Once opened, you can close the app and the service will run indefinitely, and will restart itself on reboot. 
The application stores the log file `accel_log.csv` in a folder you specify from the UI.
It writes data every 5 seconds by default.

The file has on each line:
    (time in seconds since epoch), (x value), (y value), (z value)

TODO:
- split log files if gets too large
- set up server to push data to
