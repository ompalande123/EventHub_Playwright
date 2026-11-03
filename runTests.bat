@echo off
title EventHub Playwright Automation

echo =====================================
echo   EventHub Playwright Test Execution
echo =====================================

echo.
echo Installing Chromium if required...
call mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"

if errorlevel 1 (
    echo Browser installation failed!
    pause
    exit /b 1
)

echo.
echo Running Playwright TestNG tests...
call mvn clean test

echo.
echo =====================================
echo Test execution completed.
echo Check test-output and target/surefire-reports
echo =====================================

pause
