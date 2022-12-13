# mock
This command will start application with Jolokia JVM agent:
java -javaagent:<path to jolokia.jar>=port=XXXX,host=0.0.0.0 -jar <path to application.jar>

This program implements a little spring REST application with get and post requests that send SELECT and INSERT queries to postgresql DB.
Select query returns a username and its time of log in and Insert query returns username and time of insertion.


