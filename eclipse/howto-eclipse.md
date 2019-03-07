# HOWTO Eclipse

## Subclipse

### How to force update recursively?

Sometimes, the eclipse plugin `Subclipse` missed some files on checkout. 
To fix that, manually update recursively the project in a command line.

`svn up --set-depth=infinity`

### How to ignore sonar rules in code?

- To ignore a Sonar rule on a method: `@SuppressWarnings("squid:UnusedProtectedMethod")`
- To get Eclipse to not flag the `@SuppressWarnings("xxx")` annotation, 
look under the menu headings `Java -> Compiler -> Errors/Warnings -> Annotations -> Unhandled Token in '@SuppressWarnings'` 
and set it to `ignore`.


## Clean

### How to clean eclipse history and other temp files (java, ts, node)

```
@echo off

rem example to delete some folders on windows: clean npm-* folders
rem rmdir /S /Q %TEMP%\npm-*
rem for /f "delims=" %i in ('dir /a:d /s /b npm-*') do echo rd /s /q "%i"
rem for /f %%i in ('dir /a:d /s /b npm-*') do rd /s /q %%i

set JAVA_TEMP=C:\dev\temp\java
set ECLIPSE_WK=C:\dev\eclipse\eclipse-jee-neon-2\wk
set ECLIPSE_WK_HISTORY=%ECLIPSE_WK%\.metadata\.plugins\org.eclipse.core.resources\.history

echo "eclipse history"
cd %ECLIPSE_WK_HISTORY%
for /f %%i in ('dir /a:d /s /b *') do rd /s /q %%i
echo "-"

echo "clean ts-node*"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b ts-node*') do rd /s /q %%i
echo "-"

echo "clean npm-*"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b npm-*') do rd /s /q %%i
echo "-"

echo "clean *-node"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b *-node') do rd /s /q %%i
echo "-"

echo "clean karma-*"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b karma-*') do rd /s /q %%i
echo "-"

echo "clean jetty-*"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b jetty-*') do rd /s /q %%i
echo "-"

echo "tomcat*"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b tomcat*') do rd /s /q %%i
echo "-"

echo "ms*"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b ms*') do rd /s /q %%i
echo "-"

echo "%TEMP%\*.log"
cd %TEMP%
del *.log
echo "-"

echo "%JAVA_TEMP%\tomcat*"
cd %JAVA_TEMP%
for /f %%i in ('dir /a:d /s /b tomcat*') do rd /s /q %%i
echo "-"

echo "%JAVA_TEMP%\*.log"
cd %JAVA_TEMP%
del *.log
echo "-"
```
