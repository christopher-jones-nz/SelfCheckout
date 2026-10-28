@echo off
echo Compiling and packaging the project...

call mvn clean package

if %errorlevel% neq 0 (
  echo.
  echo Build failed! Stopping script.
  exit /b %errorlevel%
)

echo.
echo Executing SelfCheckout JAR...
java -cp .\target\SelfCheckout-1.0-SNAPSHOT.jar SelfCheckout
