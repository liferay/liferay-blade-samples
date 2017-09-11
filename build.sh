#!/bin/bash
set -xe
repoDir=`pwd`
function buildAll() {
  cd liferay-workspace
  ./gradlew clean assemble -Pliferay.workspace.modules.jsp.precompile.enabled=true
  ./gradlew check -Pliferay.workspace.modules.dir=modules,tests $@
}
buildAll
