#!/usr/bin/env bash
set -euo pipefail

DIR=$(pwd)

hey -z 30s -m POST -T "application/json" -D "$DIR/transactions.json" http://localhost:8080/transactions/report
