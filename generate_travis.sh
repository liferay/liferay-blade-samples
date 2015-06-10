#!/bin/bash

#for dir in $( ls -d bndtools/*/ ); do
#	pushd $dir
#	./gradlew build
#	popd
#done

echo "language: java"
echo ""
echo "script:"

for dir in $( ls -d gradle/*/ ); do
	echo "    - cd ${dir}; ./gradlew build"
done

echo "    - cd maven; mvn --fail-at-end package"