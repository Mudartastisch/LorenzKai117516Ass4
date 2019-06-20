#!/bin/sh

cd src

javac *.java

if [ $? -eq 0 ]
then
	java DivideAndConquer 10 101 
fi

