@echo off
pushd "%~dp0"
if not exist build/retangulos.jar (
	javac -d bin src\pucrs\alest2\t1\*.java
	jar cfe build\retangulos.jar pucrs.alest2.t1.Main -C bin pucrs
)

for /r %%a in (data/teste*) do (
	echo %%~nxa
	java -jar build\retangulos.jar "%%~a"
	echo:
)
popd
