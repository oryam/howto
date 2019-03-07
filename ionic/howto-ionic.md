# HOWTO IONIC

- Build an Ionic App in 45 minutes : https://www.youtube.com/watch?v=mjjLq43miYY
- https://apps.ionic.io
- http://lab.ionic.io
- https://creator.ionic.io
- http://market.ionic.io
- http://view.ionic.io


## 2018-01-31

```
# create a blank application
ionic start myapp blank

# log in ionicframework.com and create an application with a Free plan, then link your application
cd myapp
ionic link --pro-id 123456

# set up authentication, then you can try to deploy it throuh Ionic View App website
ionic ssh setup
git push ionic master

# set up an external git repository
git remote add origin https://github.com/abc/myapp.git
git push --set-upstream origin master

# run the application locally
ionic serve
```

Tip: on Windows system, use git-gui to generate a ssh key, then add it in your Ionic View Account
