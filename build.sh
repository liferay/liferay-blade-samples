#!/bin/bash
set -xe
repoDir=`pwd`
function buildAll() {
  cd "$repoDir"
  cd gradle
  ./gradlew checkSourceFormatting clean build
  cd "$repoDir"
  cd maven
  mvn --fail-at-end clean package
  cd "$repoDir"
  cd liferay-workspace
  ./gradlew clean build
  cd "$repoDir"
  ./gradlew bundlesTest warsTest diff
  cd liferay-workspace
  ./gradlew check -Pliferay.workspace.modules.dir=modules,tests -Pliferay.workspace.modules.jsp.precompile.enabled=true $@
}
buildAll
