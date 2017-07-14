#!/bin/bash
set -xe
repoDir=`pwd`
function buildAll() {
  cd "$repoDir"
  cd gradle
  ./gradlew clean assemble
  cd "$repoDir"
  cd maven
  mvn --fail-at-end clean package
  cd "$repoDir"
  cd liferay-workspace
  ./gradlew clean assemble -x testIntegration
  cd "$repoDir"
  ./gradlew bundlesTest warsTest diff
  cd liferay-workspace
  ./gradlew clean assemble -Pliferay.workspace.modules.jsp.precompile.enabled=true
  ./gradlew check -Pliferay.workspace.modules.dir=modules,tests $@
}
buildAll
