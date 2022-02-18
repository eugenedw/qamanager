#!/bin/sh
#echo "Will load log4j file using region: ${region}"
/usr/lib/postgresql/9.3/bin/postgres -D /var/lib/postgresql/9.3/main -c config_file=/etc/postgresql/9.3/main/postgresql.conf
psql -U docker -P docker
export JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1043"
exec java $JAVA_OPTS -jar qamanager.jar