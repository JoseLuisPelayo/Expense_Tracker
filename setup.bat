@echo off
REM Mensaje de inicio
echo === Iniciando la construccion del proyecto ===

cd /d %~dp0
echo === Running 'mvn clean install' ===
call mvn clean install

if errorlevel 1 (
    echo Error durante la construcción del proyecto. Verifica los logs.
    pause
    exit
)

set APP_DIR=%~dp0target

echo === Añadiendo el directorio actual al PATH ===
setx PATH "%PATH%;%APP_DIR%"

copy .\expense.bat %APP_DIR%\
cmd
