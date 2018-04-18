@echo off
pushd "%~dp0"
if exist build/retangulos.jar goto _Run
set javac=
for %%a in (javac.exe) do set javac=%%~$PATH:a
if "%javac%"=="" (
	echo javac nao encontrado. Instale o JDK.
	goto _End
)

javac -d bin src\pucrs\alest2\t1\*.java
if not exist build mkdir build
jar cfe build\retangulos.jar pucrs.alest2.t1.Main -C bin pucrs

:_Run
for /r %%a in (data/teste*) do (
	echo %%~nxa
	java -jar build\retangulos.jar "%%~a"
	echo:
)

:_End
popd

