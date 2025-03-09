#!/bin/bash

# Navigate to the backend directory
cd /Users/rasmus/code/seb/exchange/backend

# Build the project
./gradlew build

# Run the built JAR file
java -jar build/libs/exchange-0.0.1-SNAPSHOT.jar