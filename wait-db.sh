#!/bin/bash

set -e

host="$1"
shift
port="$1"
shift
cmd="$@"

while ! mysqladmin ping -h"$host" -P"$port" --silent; do
  >&2 echo "Mysql is unavailable - sleeping"
  sleep 1
done

>&2 echo "Mysql is up - executing command"
exec $cmd