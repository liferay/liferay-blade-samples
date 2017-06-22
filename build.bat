set repoDir=%cd%

call cd gradle
call gradlew.bat clean build
call cd %repoDir%
call cd maven
call mvn --fail-at-end clean package
call cd %repoDir%
call cd liferay-workspace
call gradlew.bat clean build
call cd %repoDir%
call gradlew.bat bundlesTest warsTest diff
call cd liferay-workspace
call gradlew.bat check -Pliferay.workspace.modules.dir=modules,tests -Pliferay.workspace.modules.jsp.precompile.enabled=true %*