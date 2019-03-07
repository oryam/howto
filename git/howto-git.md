# HOWTO Git

### How to prevent pushing commits to other remote branches?
The propery `push.default` should be set as `simple`. 
Show properties list with the following command:
```
git config --list
```
If it is `matching`, it means that when pushing, all local commits will be pushed to their remote branches.
```
git config push.default simple 
# or git config --global push.default simple
```

### How to revert a commit?
```
git log
git revert <commit> 
# Type ':wq' to save the editor in git bash, or use '--no-edit' parameter such as 'git revert --no-edit <commit>'
# 'git revert' automatically modify your local file and commit the modification with a comment
git push
```

### How to show files in a commit?
```
git show --pretty="format:" --name-only 4d9f430
```

### How to prevent login git when pushing?
Note: it does not work for Windows. Refers to https://github.com/Microsoft/Git-Credential-Manager-for-Windows  
Use git credential cache in memory (or git credential store)
```
git config credential.helper cache
```
To change the timeout, edit `.git/config` file by adding `--timeout=28800` (in seconds)
```
[credential]
	helper = cache --timeout=28800
```

### How to clone behind a proxy?
```
[http]
  proxy = http://username:password@host_or_ip:port
  sslVerify = false
```

### How to syncing a fork
Source : http://stackoverflow.com/questions/7244321/how-to-update-a-github-forked-repository

```
# List the current remotes
git remote -v

# Add the remote, call it "upstream":
git remote add upstream https://github.com/whoever/whatever.git

# Fetch all the branches of that remote into remote-tracking branches,
# such as upstream/master:
git fetch upstream

# List all local and remote-tracking branches
git branch -va

# Make sure that you're on your master branch:
git checkout master

# Rewrite your master branch so that any commits of yours that
# aren't already in upstream/master are replayed on top of that
# other branch:
git rebase upstream/master

# or merge upstream's master into our own
git merge upstream/master

# or git push -f origin master
```

**With eGit (Eclipse)**

Scenario:
- you forked a repository in your own repository as a master branch
- you are keeping your master clean without any commit
- your master is only used to merge updates from the original repository you forked
- you have some branches with your modifications
- you refresh your branches with your freshest master


1. Update your master  
  - git fetch upstream  
  - git checkout master  
  - git rebase upstream/master  
2. with eGit  
  - checkout <your branch>  
  - rebase interactively + preserve merge  
  - show view git staging  
  - skip or continue  
  - on conflict, start merge tool  
  - resolve conflict in editor, save  
  - add to index  
  - commit + message  
  - skip commit until the last one  
  - for the last one, commit and push  
  - or commit by amending the last commit


### How to migrate from svn to git

```
# prepare user mapping
svn log --xml | grep -P "^<author" | sort -u | perl -pe 's/<author>(.*?)<\/author>/$1 = /' > users.txt

# initialize git repo from svn with history and user mapping (use -s if standard layout)
git svn clone _svn_url_branch --authors-file=users.txt --no-metadata my_folder

# when failing, resume with
git svn fetch

# when pasword change, in Git bash, remove file in ~/.subversion/auth/svn.simple/*

# check commit message
git log -n2

# clean branches and tags
git for-each-ref refs/remotes/tags | cut -d / -f 4- | grep -v @ | while read tagname; do git tag "$tagname" "tags/$tagname"; git branch -r -d "tags/$tagname"; done
git for-each-ref refs/remotes | cut -d / -f 3- | grep -v @ | while read branchname; do git branch "$branchname" "refs/remotes/$branchname"; git branch -r -d "$branchname"; done

# set user name and email
git config user.name "user name"
git config user.email "email@mail.com"

# set user name and email
git remote add origin _git_url_
# git config http.postBuffer 524288000
git push -u origin master
```

### How to use ssh key

```
ssh-keygen -t rsa -b 4096 -C "your_email_used_for_github.com"
# optional: customize your path file: ~/.ssh/my_key_rsa

# then add your public key .pub into your Github.com profile settings
# ex. copy/paste the output of the following commande: $ cat /.ssh/my_key_rsa.pub
```

**Example of configuration host**

```
nano ~/.ssh/config

# copy the following content:
Host github
    HostName github.com
    User git
    PreferredAuthentications publickey
    IdentityFile ~/.ssh/my_key_rsa

# then try the command: $ ssh github
```

**Example of script or command to enable your key on your local machine**

```
#!/bin/bash

eval `ssh-agent -s`
ssh-add -D
ssh-add ~/.ssh/my_key_rsa
ssh -T git@github.com
```

## Misc
```
git config --global http.sslVerify false
git config --list
git config --global http.sslVerify false

git status
git reflog
git checkout 6844a91
git status
git push -f origin master

git revert -n HEAD~3..HEAD  # prepare a new commit reverting last 3 commits
git commit -m "sorry - revert last 3 commits because I was not careful"
git push origin master  # regular push

git branch : list branches
git branch v0 : checkout a local branch (-b for create local)
git push origin v0
git push origin -d v0

git init
git remote add origin https://github.com/oryam/howto.git
git pull origin master
git config user.name "oryam"
git config user.email "16582185+oryam@users.noreply.github.com"
git config core.autocrlf input
```

## Markdown

### How to write new line in .md file?
Use double `space` character. In the following example replace `_` by a `space`  
```
a__  
b__  
c__  
```
