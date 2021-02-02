#!/usr/bin env bash
export PATH=$PATH:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/software/usr/bin:/software/usr/sbin:/software/usr/local/bin:/software/usr/local/sbin

filePath=`dirname $0`
WORK_DIR=$filePath/work
JAVA_OPTS="-XX:+UseG2GC -Xmx512m -Xms256m"

PID=${WORK_DIR}/gongan-api-server.pid

funcStart() {
    nohup java -jar ${JAVA_OPTS} ${WORK_DIR}/gongan-0.0.1-SNAPSHOT.jar 2>&1 >/dev/null &
    if [ ! -z "${PID}" ]; then
      echo $! > ${PID}
    fi
}
funcStop() {
    kill `cat ${PID}` && rm ${PID}
}

if [ "$1" = "start" ] ; then
    funcStart
elif [ "$1" = "stop" ] ; then
    funcStop
elif [ "$1" = "restart" ] ; then
    funcStop
    funcStart
else
  echo "Usage: gongan.sh ( commands ... )"
  echo "commands:"
  echo "  start             Start gongan API Server"
  echo "  stop              Stop gongan API Server"
  echo "  restart           Restart gongan API Server"
  exit 1

fi