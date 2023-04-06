#!/usr/bin/env bash
set -euo pipefail

JAVA_OPTS=(-Xms2G -Xmx2G)

java "${JAVA_OPTS[@]}" -jar target/zielony-1.0-SNAPSHOT-jar-with-dependencies.jar
