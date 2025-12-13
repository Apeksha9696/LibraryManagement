@echo off
echo Starting Library Management System...
echo.
mvn spring-boot:run
echo.
echo Application is running at: http://localhost:9000
echo Press any key to open in browser...
pause >nul
start http://localhost:9000