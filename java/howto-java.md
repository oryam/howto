# HOWTO Java

### How to get a Windows portable version

- Download jdk exe from Oracle (e.g. jdk-8u72-windows-x64.exe)
- Unzip the tools.zip found inside it into the destination folder (use 7zip for example)
- Run in commande line : `for /r %x in (*.pack) do .\bin\unpack200 -r "%x" "%~dx%~px%~nx.jar"`
