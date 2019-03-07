# HOWTO Javascript


## Ajax request

Easy way to send ajax request with a POST method and json content, in Web browser console.

```
var url = 'http://localhost/authenticate';
var xhr = new XMLHttpRequest();
xhr.open('POST', url);
xhr.setRequestHeader('Content-Type', 'application/json');
xhr.onload = function() {
    if (xhr.status === 200) {
        var userInfo = JSON.parse(xhr.responseText);
		console.log(xhr.responseText);
		console.log(userInfo);
    }
};
xhr.send(JSON.stringify({
  login: 'mylogin',
  password: 'mypassword'
}));
```
