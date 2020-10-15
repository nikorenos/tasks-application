call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:rename
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomact
echo Cannot rename file
goto fail

:stoptomact
call %CATALINA_HOME%\bin\shutdown.bat

:copyfile
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runtomact
echo Cannot copy file
goto fail

:runtomact
call %CATALINA_HOME%\bin\startup.bat
goto end


:fail
echo.
echo There were errors.

:end
echo.
echo Runcrud job is finished.