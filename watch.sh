#!/bin/bash

FILE="Calculator.java"
CLASS="Calculator"
LAST_HASH=""
JAVA_PID=""

# Kill the Java process if it's running
kill_java() {
  if [ -n "$JAVA_PID" ] && kill -0 "$JAVA_PID" 2>/dev/null; then
    echo "Stopping previous Java program (PID: $JAVA_PID)..."
    kill "$JAVA_PID"
    wait "$JAVA_PID" 2>/dev/null
  fi
}

# Make sure the file exists first
if [ ! -f "$FILE" ]; then
  echo "Error: $FILE does not exist."
  exit 1
fi

LAST_HASH=$(md5sum "$FILE" | awk '{ print $1 }')

while true; do
  NEW_HASH=$(md5sum "$FILE" | awk '{ print $1 }')
  if [ "$NEW_HASH" != "$LAST_HASH" ]; then
    echo "Detected change. Compiling..."
    javac "$FILE"
    if [ $? -eq 0 ]; then
      echo "Compilation successful."
      kill_java
      echo "Starting new Java program..."
      java "$CLASS" &
      JAVA_PID=$!
    else
      echo "Compilation failed."
    fi
    LAST_HASH=$NEW_HASH
  fi
  sleep 1
done
