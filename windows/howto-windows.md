# HOWTO Windows

### How to remove `hiberfil.sys`?
The `hiberfil.sys` is used when turning off the computer in hibernation. 
Open a command prompt as Administrator and type 
```
powercfg /h off
```
Use the following command to turn in on: `powercfg.exe /h on`. 
Notice that the huge file will appear again when turning on, so you need enought space disk.


## Multi boot

### How to multi boot

- use Easy2Boot. Tutorial http://www.lecadelo.fr/easy2boot-une-trousse-a-outils-de-poche/

## Scripts

### Clean temp folder

Using wildcard:
```
@echo off
rem for /f "delims=" %i in ('dir /a:d /s /b npm-*') do echo rd /s /q "%i"
rem for /f %%i in ('dir /a:d /s /b npm-*') do rd /s /q %%i
echo "clean npm-*"
cd %TEMP%
for /f %%i in ('dir /a:d /s /b npm-*') do rd /s /q %%i
echo "-"
```
