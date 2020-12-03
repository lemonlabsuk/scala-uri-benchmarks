#!/usr/bin/env bash

SCALA_URI_VERS=$1
BENCHMARK_CLASS=$2

if [ -z SCALA_URI_VERS ]; then
  echo "Which versions of scala-uri would you like benchmark? (space separated)"
  read -r SCALA_URI_VERS
fi

for SCALA_URI_VER in $SCALA_URI_VERS; do
  sbt -DscalaUri.ver=$SCALA_URI_VER "jmh:run -rf json -rff jmh-$SCALA_URI_VER.json $BENCHMARK_CLASS"

  VER_FILE=jmh-scala-uri-versions.json

  if ! grep -q $SCALA_URI_VER $VER_FILE; then
      sed -i '' -e '$ d' $VER_FILE
      sed -i '' -e '$s/$/,/' $VER_FILE
      printf "  \"$SCALA_URI_VER\"\n" >> $VER_FILE
      printf "]\n" >> $VER_FILE
  fi
done
