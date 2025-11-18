#!/bin/bash
cd /home/kavia/workspace/code-generation/android-tv-streaming-home-ui-215456-214421/android_tv_home_screen_ui
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

