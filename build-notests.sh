#!/bin/bash
set -xe
repoDir=`pwd`
function buildAll() {
  cd "$repoDir"
  cd gradle
  ./gradlew --parallel checkSourceFormatting clean assemble
  cd "$repoDir"
  cd maven
  mvn -T 1C --fail-at-end clean package
  cd "$repoDir"
  cd liferay-workspace
  ./gradlew --parallel checkSourceFormatting clean assemble
  cd "$repoDir"
  ./gradlew --parallel diff
}
buildAll
