# HOWTO Codenvy

## How to build a Angular 2 application + Angular CLI in codenvy

Try https://codenvy.io/

- Create a nodejs workspace, at least 2Go RAM (otherwise agent rsync issue met)
- Start with an existing Angular project initialized with Angular CLI from a git repository or create it from scratch
- Upgrade node version if needed
- Install @angular/cli globally
- Install package dependencies of the application
- Start the application
- Edit .gitignore if you want to keep node_modules during snapshoting the workspace

```
curl -sL https://deb.nodesource.com/setup_9.x | sudo -E bash -
sudo apt-get install -y nodejs
node -v && npm -v

sudo npm install --unsafe-perm -g @angular/cli@latest
sudo npm install --unsafe-perm -g npm-check-updates
```

Get focus on your project, so ${current.project.path} will be valid
Create a command `install` such as following (Commands tabsheet on the left side panel menu), then run it:
```
cd ${current.project.path} && npm install
```

Get focus on your project, so ${current.project.path} will be valid
Create a command `serve` such as following with preview URL `http://${server.port.4200}` or `${server.4200/tcp}`, then run it:
```
cd ${current.project.path} && ng serve --host 0.0.0.0 --disable-host-check
```

Another example: https://eclipse.org/che/docs/tutorials/angular2/index.html


## How to build an Angular 2 application + Webpack in codenvy

Try the beta version: http://beta.codenvy.com/

Get a repository. E.g. https://github.com/AngularClass/angular2-webpack-starter/
```
sudo apt-get update
sudo apt-get install libfontconfig
sudo apt-get install -y nodejs
sudo ln -s /usr/bin/nodejs /usr/bin/node
sudo apt-get install -y npm
sudo npm install -g typings webpack-dev-server rimraf webpack
```

Move into project folder
```
cd _project_name_
# revert "awesome-typescript-loader": "0.17.0": edit package.json
sudo npm i
sudo typings install
```

Run tests (failed: it required > `sudo apt-get install libfontconfig`)
```
sudo npm test
```

Run the application (failed)
```
sudo npm start
```

Create a command (failed)
```
cd ${current.project.path} && sudo npm start
http://${server.port.3000}
```

## How to build a java web service with spring boot

- Create workspace from default java stack (the spring-boot one does not seem to work)
- Get a project from https://github.com/oryam/osumi.git
- Change port `8080` in application.yml configuration file
- Pre-install dependencies
- Build maven modules
- Start the application

```
# pre-install command
cd ${current.project.path} && mvn install:install-file -Dfile=lib/ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1.0 -Dpackaging=jar -DgeneratePom=true

# install command
mvn install -f ${current.project.path}

# start command
java -jar ${current.project.path}/application/target/*-application.jar

# with preview url : `http://${server.port.8080}/service/rest/item` (or something like `http://${server.port.8080}/${current.project.relpath}`)
```
