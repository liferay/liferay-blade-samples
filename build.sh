#!/bin/bash

#for dir in $( ls -d bndtools/*/ ); do
#	pushd $dir
#	./gradlew build
#	popd
#done

for dir in $( ls -d gradle/*/ ); do
	echo "Building [$dir]"
	pushd $dir
	./gradlew build
	popd
done

for dir in $( ls -d maven/*/ ); do
	echo "Building [$dir]"
	pushd $dir
	mvn package
	popd
done