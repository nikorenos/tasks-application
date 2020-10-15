call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runwebpage
echo.
echo There were errors with runcrud - breaking work.
goto fail

:runwebpage
@start "C:\Program Files\Mozilla Firefox\firefox.exe" http://localhost:8080/crud/v1/task/getTasks
@echo Firefox started.
goto end

:fail
echo.
echo Showtasks failed.

:end
echo.
echo Showtasks job is finished.