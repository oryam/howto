# HOWTO Npm

### How to set up proxy

**.npmrc**

Edit or create the file `.npmrc` locate in **user home directory**. E.g. %USERPROFILE%\.npmrc
```
proxy=http://<username>:<pass>@<proxyhost>:<port>
https-proxy=http://<uname>:<pass>@<proxyhost>:<port>
```
- Optionally add `strict-ssl=false` too
- Url encode special characters such as `:` (`%3A`), `/` (`%2F`)


**.typingsrc**

Edit or create the file `.typingsrc` locate in **project directory**.
```
proxy=http://<username>:<pass>@<proxyhost>:<port>
https-proxy=http://<uname>:<pass>@<proxyhost>:<port>
```
- Optionally add `rejectUnauthorized=false` too
