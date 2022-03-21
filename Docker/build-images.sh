#!/bin/bash
set -e

docker pull registry.gitlab.com/sti-group/sti-app/node:fermium
docker build -f node/Dockerfile -t registry.gitlab.com/sti-group/sti-app/node:fermium .
docker push registry.gitlab.com/sti-group/sti-app/node:fermium

docker pull registry.gitlab.com/sti-group/sti-app/openjdk:8-jdk
docker build -f openjdk8/Dockerfile -t registry.gitlab.com/sti-group/sti-app/openjdk:8-jdk .
docker push registry.gitlab.com/sti-group/sti-app/openjdk:8-jdk