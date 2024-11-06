#!/bin/bash
set -euxo pipefail

##############################################################################
##
##  GH actions CI test script
##
##############################################################################

mvn -ntp -Dhttp.keepAlive=false \
    -Dmaven.wagon.http.pool=false \
    -Dmaven.wagon.httpconnectionManager.ttlSeconds=120 \
    -q clean package liberty:create liberty:install-feature liberty:deploy

mvn test

mvn -ntp liberty:start

sleep 20

cat target/liberty/wlp/usr/servers/defaultServer/logs/messages.log || exit 1

status_code=$(curl -o /dev/null -s -w "%{http_code}" http://localhost:9080/index.xhtml)
[ "$status_code" -eq 200 ] || exit 1

mvn -ntp liberty:stop

mvn -ntp failsafe:verify
