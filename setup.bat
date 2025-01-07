@echo off
REM Mensaje de inicio
echo === Iniciando la construccion del proyecto ===

REM Navegar al directorio del proyecto (opcional)
cd /d %~dp0

REM Compilar el proyecto con Maven

echo === Ejecutando 'mvn clean install' ===
call mvn clean install
pause

if errorlevel 1 (
    echo Error durante la construcción del proyecto. Verifica los logs.
    pause
    exit /b
)

REM Ruta donde está el archivo jar y el bat
pause
set "APP_DIR=%CD%"
pause
REM Añadir el directorio al PATH del sistema de forma permanente
echo === Añadiendo el directorio actual al PATH ===
setx PATH "%PATH%;%APP_DIR%" /M
if errorlevel 1 (
    echo No se pudo añadir el directorio al PATH. Puede que no tengas permisos de administrador.
    pause
    exit /b
)

echo El directorio %APP_DIR% se ha añadido al PATH del sistema.
echo Ahora puedes usar 'xpense' desde cualquier ubicación.

pause
