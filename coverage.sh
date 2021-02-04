#!/bin/bash
# Turn off animations
adb shell settings put global window_animation_scale 0 &
adb shell settings put global transition_animation_scale 0 &
adb shell settings put global animator_duration_scale 0 &

clear
./gradlew clean jacocoTestReport
./gradlew jacocoTestReport

REPORT_PATH="file://$(pwd)/app/build/jacoco/jacocoHtml/index.html"

echo ${REPORT_PATH} | pbcopy

echo "Report available at:"
echo ${REPORT_PATH}

echo "Report file path copied to clipboard. You can paste it in your favorite browser. :)"


# Before use it, in the first time, you must guarantee some running permissions:
# chmod +x coverage.sh
#
# After that, you just need to run:
# ./coverage.sh
#
# It will generate coverage report and copy the html report path to your clipboard.