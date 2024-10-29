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
    -ntp -q clean package liberty:create liberty:install-feature liberty:deploy

mvn -ntp liberty:start

# check the messages.log
# check the url

mvn -ntp liberty:stop

mvn -ntp failsafe:verify
