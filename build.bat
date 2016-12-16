set repoDir=%cd%

call cd bndtools
call gradlew.bat clean build
call cd %repoDir%
call cd gradle
call gradlew.bat clean build
call cd %repoDir%
call cd liferay-gradle
call gradlew.bat clean build
call cd %repoDir%
call cd maven
call mvn --fail-at-end clean package
call cd %repoDir%
call cd liferay-workspace
call gradlew.bat clean build -x :tests:test
call cd %repoDir%
call gradlew.bat bundlesTest warsTest diff
call gradlew.bat build %*
