#!/bin/sh
# chkconfig: 2345 80 20

EXTAPI_DEPLOYMENT_DIR="/usr/local/apms-api"

start() {
	
	PID=$(findPID)
	if [ -n "$PID" ]; then                                                
	    echo "It is already running."                                 
	    RETVAL=1                                                           
	    return                                                             
	fi                                                                     

	OLD_PWD=`pwd`
	# Start daemons.                                                       
	echo -n "Starting... "                                          
	cd $EXTAPI_DEPLOYMENT_DIR
	sh ./start.sh
	RETVAL=$?
	echo
	cd $OLD_PWD
	PID=$(findPID)
    if [ -n "$PID" ]; then
        echo -n "Started."
    fi
}

stop() {
	# Stop daemons.
	echo -n "Shutting down... "

	PID=$(findPID)
	if [ -n $PID ]; then
		kill $PID
	else
		echo "It is not running."
	fi
	
	RETVAL=$?
	echo
}

restart() {
	stop
	sleep 10 # give it a few moments to shut down
	start
}

status() {
	PID=$(findPID)
	if [ -n "$PID" ]; then
		echo "It is running"
		RETVAL=0
	else 
		echo "It is not running"
		RETVAL=1
	fi
}

findPID() {
	echo `ps ax --width=1000 | grep java | grep extapi | awk '{print $1}'`
}

# Handle how we were called.
case "$1" in
	start)
		start
		;;
	stop)
		stop
		;;
	restart)
		restart
		;;
	status) 
		status
		;;
	*)
		echo "Usage $0 {start|stop|restart|status}"
		RETVAL=1
esac

exit $RETVAL