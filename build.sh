#!/bin/bash
set -xe
repoDir=`pwd`
function buildAll() {
  cd "$repoDir"
  cd gradle
  ./gradlew checkSourceFormatting clean assemble
  cd "$repoDir"
  cd maven
  mvn --fail-at-end clean package
  cd "$repoDir"
  cd liferay-workspace
  ./gradlew clean assemble
  cd "$repoDir"
  ./gradlew diff
  cd liferay-workspace
  ./gradlew check -Pliferay.workspace.modules.dir=apps,extensions,overrides,tests,themes -Pliferay.workspace.modules.jsp.precompile.enabled=true $@
}
buildAll
