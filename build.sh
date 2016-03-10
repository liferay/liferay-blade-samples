#!/bin/bash
function buildAll() {
  pwd=$(repoDir)
  cd "$repoDir"
  cd bndtools
  ./gradlew build
  cd "$repoDir"
  cd gradle
  ./gradlew build
  cd "$repoDir"
  cd liferay-gradle
  ./gradlew build
  cd "$repoDir"
  cd maven
  mvn --fail-at-end package
  cd "$repoDir"
  ./gradlew outputFilesTest
}
buildAll