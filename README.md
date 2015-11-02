# BackgroundAccelerometer
Android application to record accelerometer data in the background

Once opened, you can close the app and the service will run indefinitely, and will restart itself on reboot. 
The application stores the log file `accel_log.csv` in a folder you specify from the UI.
It writes data every 5 seconds by default.

The file has on each line:
    (time in seconds since epoch), (x value), (y value), (z value)

TODO:
- while running, the user can set a marker event or interval to signify a certain activity they'd like to collect data for and compare to unmarked data
- split log files if gets too large (by day/week?)
- set up server to push data to
- add visual graph in main page
- allow user to manually specify time interval for recordings
- allow user to specify date/time format
- ability to email logs directly from the app
- better UI
