# HOWTO Angular 2 (ng2)

## Quick reference

- https://angular.io/
- https://cli.angular.io/
- https://material.angular.io/
- https://github.com/angular/flex-layout

- https://ng2-ui.github.io/dist/
- http://wijmo.com/products/wijmo-5/flexgrid/
- https://www.ag-grid.com/


## Install with Angular CLI

### How to start a new project from scratch?

Create a new project with Angular CLI. Refers to the official documentation.

### How to run unit test with PhantomJS?

Ref. https://blog.dmbcllc.com/unit-testing-an-angular-2-cli-project/

```
npm install --save-dev phantomjs-prebuilt
npm install --save-dev karma-phantomjs-launcher
npm install --save-dev karma-spec-reporter
```


**karma.conf.js**

// require('karma-chrome-launcher'),
require('karma-phantomjs-launcher'),
require('karma-spec-reporter'),

browsers: ['PhantomJS'],

reporters: config.angularCli && config.angularCli.codeCoverage
          ? ['spec', 'karma-remap-istanbul']
          : ['spec'],



Edit `polyfills.ts`, uncomment some lines and install `intl`.

**polyfills.ts**

import 'core-js/es6/object';
import 'core-js/es6/array';
import 'intl';  // Run `npm install --save intl`.

```
npm install --save intl
```


### How to run e2e test with Chrome headless?

Protractor Starts Selenium Server. 
Ref. http://stackoverflow.com/questions/43354714/how-run-headless-angular2-e2e-tests-using-protractor-in-vagrant-ubuntu-16-04

[not working in codenvy]

sudo apt-get install default-jdk -y

sudo npm install -g protractor
sudo webdriver-manager update

sudo ln /usr/lib/node_modules/protractor/node_modules/webdriver-manager/selenium/chromedriver_2.29 /usr/bin/chromedriver

wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
sudo apt-get update
sudo apt-get install google-chrome-stable -y

sudo apt-get update -qqy
sudo apt-get -qqy install xvfb

xvfb-run -a -e /dev/stdout -s "-screen 0 2920x2580x24" ng e2e


### How to run e2e test with PhantomJS?

Warning: it is not recommanded to run end2end tests with PhantomJS. Use Chrome or Firefox for example for more real e2e tests.

npm install -g protractor
webdriver-manager update
ng e2e --no-webdriver-update


## Install with Webpack

### How to start a new project from scratch?

Visit https://github.com/oryam/angular2-quickstart

**Usual dependencies**

```
npm i -g npm
npm install typings webpack-dev-server rimraf webpack -g
npm install karma protractor typescript -g
npm install apimocker -g
```

## Quickstart

### How to get a first blank application?

Get a project from https://github.com/AngularClass/angular2-webpack-starter

### How to add sass support?

Install webpack plugins
```
npm install node-sass --save-dev
npm install sass-loader --save-dev
```

Add webpack loaders in configuration
```
module: {
  loaders: [
    // Support for SASS
    { test: /\.scss$/, loader: 'raw-loader!sass-loader?sourceMap', exclude: /node_modules/ },
```

### How to add material css?

Use `@angular2-material` (in beta) and `ng2-material` (addons data table)
```
npm i -S @angular2-material/core
npm i -S @angular2-material/button
npm i -S @angular2-material/card
npm i -S @angular2-material/checkbox
npm i -S @angular2-material/grid-list
npm i -S @angular2-material/icon
npm i -S @angular2-material/input
npm i -S @angular2-material/list
npm i -S @angular2-material/progress-bar
npm i -S @angular2-material/progress-circle
npm i -S @angular2-material/radio
npm i -S @angular2-material/sidenav
npm i -S @angular2-material/slide-toggle
npm i -S @angular2-material/tabs
npm i -S @angular2-material/toolbar

npm i -S ng2-material
```
- Use icons. Refers to http://google.github.io/material-design-icons/#icon-font-for-the-web
```
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
```

- Import ng2-material providers
```
import { MATERIAL_BROWSER_PROVIDERS } from 'ng2-material';

bootstrap(App, [
  ...MATERIAL_BROWSER_PROVIDERS,
]);
```
- Import styles into application styles such as `app.component.scss`
```
@import "node_modules/ng2-material/all.scss";
```