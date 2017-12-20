#!/usr/bin/env bash

rm tests/speedfines.csv
rm tests/avgspeedfines.csv
rm tests/accidents.csv

jar=flinkProgram/target/flinkProgram-1.0-SNAPSHOT.jar

flink run -p $1 -c master2017.flink.VehicleTelematics $jar $2 tests/

