#!/bin/bash
set -e
if [ ! -f build/retangulos.jar ]
then
	javac -d bin src/pucrs/alest2/t1/*.java
	jar cfe build/retangulos.jar pucrs.alest2.t1.Main -C bin pucrs
fi

for file in $(find data/teste*)
do
	printf "\033[1;33m$(basename $file)\033[0m\n"
	java -jar build/retangulos.jar $file
	echo
done
