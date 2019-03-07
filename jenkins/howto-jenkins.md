# HOWTO Jenkins

## Links

- https://jenkins.io/
- https://jenkins.io/2.0/
- https://wiki.jenkins-ci.org/display/JENKINS/Starting+and+Accessing+Jenkins
- https://wiki.jenkins-ci.org/display/JENKINS/JenkinsLinuxStartupScript
- https://wiki.jenkins-ci.org/display/JENKINS/Plugins
- http://updates.jenkins-ci.org/download/plugins/
- https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins+as+a+Unix+daemon
- https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins+on+Red+Hat+distributions
- http://pkg.jenkins-ci.org/redhat/
- https://wiki.jenkins-ci.org/display/JENKINS/Distributed+builds

## Getting started

Download jenkins war and run jenkins as standalone 
or install jenkins with a package manager and use it as a service.

When using Jenkins, to install plugins, it requires internet connection, 
otherwise you need to download and install plugins manually.


### Run as standalone

```
java -jar jenkins.war
```
Redirect output log. Set or replace `%LOGFILE%` (`$LOGFILE` on Linux) by filename
```
java -jar jenkins.war > %LOGFILE% 2>&1
```
On windows, run jenkins in background and redirect output log, with start
```
start /B java -jar jenkins.war > %LOGFILE% 2>&1
```
On Linux, run jenkins in background and redirect output log
```
nohup java -jar jenkins.war > $LOGFILE 2>&1
```
When running standalone, jenkins creates data files in `JENKINS_HOME`. Set the variable to define another location.  
For example, on Windows, create a `jenkins-start.bat` in the same folder of jenkins.war:
```bat
@echo off

rem comment or define java home
set JAVA_HOME=

rem define new location where jenkins will extract all working files
set JENKINS_HOME=

rem define log file
set LOGFILE=jenkins.log

start /B java -jar jenkins.war > %LOGFILE% 2>&1
```

### First steps

1. Install jenkins or download war
1. Run jenkins with custom JENKINS_HOME, JAVA_HOME, jenkins.log
1. When running jenkins 2, first screens invite to install plugins and define an admin user
1. For automatic plugins installation, internet connection is required, otherwise install plugins manually
1. When first steps are done, manage Jenkins settings to define tools (java, maven, etc.)
1. Define security strategy mode. Manage roles, create users and assign roles
1. Customize workspace and jobs location (or use symbolic links to avoid anoying errors)

#### System configuration
Change workspace and builds location to manage separately the file system (useful of space usage)

#### Role strategy
Define security based on role and job name pattern
- https://wiki.jenkins-ci.org/display/JENKINS/Role+Strategy+Plugin


## FAQ

### Failed to scout
Java exception is logged into jenkins log at startup
```
Failed to scout ... java.lang.NoClassDefFoundError: hudson/plugins/copyartifact/BuildSelector
```
Install Copy Artifact Plugin. 
Ref. https://issues.jenkins-ci.org/browse/JENKINS-30327
