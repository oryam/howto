# HOWTO Visual Studio Code

### Settings
Example of settings
```
// Place your settings in this file to overwrite the default settings
{
  "editor.insertSpaces": true,
  "editor.formatOnType": true,
  "editor.renderWhitespace": true,
  "files.exclude": {
    "**/.git": true,
    "**/.github": true,
    "**/.svn": true,
    "**/.DS_Store": true
  },
  "files.autoSave": "afterDelay",
  "files.autoSaveDelay": 5000,
  "files.watcherExclude": {
    "**/.git/objects/**": true,
    "**/node_modules/**": true
  },
  "explorer.workingFiles.maxVisible": 20,
  "search.exclude": {
    "**/node_modules": true,
    "**/bower_components": true,
    "**/dist": true,
    "**/coverage": true
  },
  "typescript.format.insertSpaceAfterOpeningAndBeforeClosingNonemptyBrackets": true
}
```

### How to install a snippet from the market place?
Press `F1` > Type: `install extension` > Search for extension name

### How to use a snippet?
In a file type snippet extension keyword. 
Use `tab` to switch from a tab-stop to another one.

### How to create a snippet?
Refer to the official documentation.  
Use `${}` syntax for tab-stop. 
With `${id:label}` syntax the `$id` can be reused in your template, the `$label` is used by default as the value when using the snippet. 
Use `$1` or `$id` syntax to reuse the variable in the snippet template code.

### How to set up proxy?
Open user settings, customize http.proxy (and http.proxyStrictSSL).
```  
"http.proxy": "http://<username>:<pass>@<proxyhost>:<port>",
"http.proxyStrictSSL": false
```
### How to format css, scss?
Get the extension: Stylesheet Formatter `ext install vscode-stylesheet-formatter`

### How to debug an Angular 2 + Webpack application?

- Create a debug launcher and tasks. `@see` in `./debug` folder of this help
- Run Chrome with arguments `--remote-debugging-port=9222`. Example of Windows shorcut : `"C:\Program Files (x86)\Google\chrome.exe" --remote-debugging-port=9222`
