#!/bin/bash
repoDir=`pwd`
function buildAll() {
  cd "$repoDir"
  cd bndtools
  ./gradlew clean build
  cd "$repoDir"
  cd gradle
  ./gradlew clean build
  cd "$repoDir"
  cd liferay-gradle
  ./gradlew clean build
  cd "$repoDir"
  cd maven
  mvn --fail-at-end clean package
  cd "$repoDir"
  cd liferay-workspace
  ./gradlew clean build -x :tests:test
  cd "$repoDir"
  ./gradlew bundlesTest warsTest diff
  cd liferay-workspace
  ./gradlew build $@
}
buildAll
