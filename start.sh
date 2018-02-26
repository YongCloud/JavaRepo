#!/bin/bash
if [[ $#<1 ]]; then
	echo Usage:sh start.sh jarPath
	exit 1
fi

cd $1
for i in *.jar
	do path=$path:$1$i
done
echo $path

#mainClass=com.x.y.Z
#java -cp $path $mainClass
