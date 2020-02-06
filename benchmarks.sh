#!/usr/bin/env bash

SCALA_URI_VER=$(cat build.sbt | grep -E "io\.lemonlabs.+scala-uri" | cut -d'"' -f6)

# sbt "jmh:run -rf json -rff jmh-$SCALA_URI_VER.json"

VER_FILE=jmh-scala-uri-versions.json

if ! grep -q $SCALA_URI_VER $VER_FILE; then
    sed -i '' -e '$ d' $VER_FILE
    sed -i '' -e '$s/$/,/' $VER_FILE
    printf "  \"$SCALA_URI_VER\"\n" >> $VER_FILE
    printf "]\n" >> $VER_FILE
fi
