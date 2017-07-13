#!/bin/bash
set -xe
repoDir=`pwd`
function buildAll() {
  cd liferay-workspace
  ./gradlew check -x testIntegration -Pliferay.workspace.modules.dir=modules,tests -Pliferay.workspace.modules.jsp.precompile.enabled=true $@
}
buildAll
