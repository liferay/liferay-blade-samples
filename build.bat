set repoDir=%cd%

call cd gradle
call gradlew.bat --parallel checkSourceFormatting clean assemble
call cd %repoDir%
call cd maven
call mvn -T 1C --fail-at-end clean package
call cd %repoDir%
call cd liferay-workspace
call gradlew.bat --parallel checkSourceFormatting clean assemble
call cd %repoDir%
call gradlew.bat --parallel bundlesTest warsTest diff
call cd liferay-workspace
call gradlew.bat --parallel check -Pliferay.workspace.modules.dir=apps,extensions,overrides,tests,themes -Pliferay.workspace.modules.jsp.precompile.enabled=true %*