#!/bin/bash

docker container prune --force
docker image prune -a --force
