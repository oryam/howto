# HOWTO Linux

### How to zip or tar?
```
tar cvzf <dest_name_tgz> <folder_name_to_zip> # create archive
tar xzf <archive_name> # extract archive
```

### How to watch process?
```
ps aux
watch -n 2 'ps -fe | grep <your_keyword>'
ps -fe | grep <your_keyword>
jmap -heap <pid>
```

### How to read log file?
```
tail -f <your_path_file>
nano <your_path_file>
```

### How to check disk space?
```
df -h
du -sh *
du -h --max-depth=1 .
```

### How to check disk space for inodes?
```
df -i
for i in /var/lib/jenkins/plugins/*; do echo $i; find $i |wc -l; done
```

### How to find a file?
```
find / -type d -name 'httpdocs' # Example to find httpdocs
```

### How merge/copy folder?
```
rsync -av --remove-source-files <SOURCE> <DEST>
# check `source` and `dest`, then remove `source`
rm -rf <SOURCE>
```


### How to convert Windows file to Unix file?
```
# remove carriage return
sed -i 's/\r//' CRLF.txt
# add carriage return
sed -i 's/$/\r/' LF.txt
```
-i edit files in place 



### How to wipe free space?

```
# delete file
shred -zvu myfile

# delete recursively
find . -depth -type f -exec shred -zvu -n 5 {} \;

# dd if=<source> of=<target> [Options]
# here, /dev/sda1 is the USBKEY path
dd if=/dev/zero of=/dev/sda1 bs=512 count=1
dd if=/dev/urandom of=/dev/sda1 bs=4096

# shred [option] <target>
shred -vfz -n 10 /dev/sda1
shred -v --random-source=/dev/urandom -n10 /dev/sda1

# wipe [options] <target>
# only for magnetic disk
wipe /dev/sda1

# scrub [option] <target>
scrub /dev/sda1

# ref. https://superuser.com/questions/19326/how-to-wipe-free-disk-space-in-linux
dd if=/dev/zero of=zero.small.file bs=1024 count=102400
shred -z zero.small.file
cat /dev/zero > zero.file
sync
rm zero.small.file
shred -z zero.file
sync
rm zero.file
```



## Debian
### How to update Debian?
```
apt-get update
apt-get upgrade
apt-get dist-upgrade
```

### How to find cron?
```
ls /etc/cron.d/
```

### How to remove temporary files from /tmp?
```
/sbin/reboot 
# If you can reboot the machine without any risk or impact for your apps
```

### How to send an email when booting?
```
(crontab -l; echo '@reboot echo "Server has restarted "`hostname`" "`date` | mail -s "System Restart" <your_email>') | crontab -
```

### How to checkdisk at boot?
```
touch /forcefsck
reboot 
# Make sure your can reboot without any risk for your running applications
```


### How to output format?

bash

`PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\W\[\033[00m\]\$ '`

----


# Fedora

- https://docs.fedoraproject.org/
- https://fedoraproject.org/wiki/DNF_system_upgrade

- https://www.unixmen.com/things-install-fedora-24/
- http://folkswithhats.org/

- add repository: rpmfusion.org
```
dnf install --nogpgcheck http://download1.rpmfusion.org/free/fedora/rpmfusion-free-release-24.noarch.rpm
Non-Free RPM Fusion
dnf install --nogpgcheck http://download1.rpmfusion.org/nonfree/fedora/rpmfusion-nonfree-release-24.noarch.rpm
```
- search needed drivers, then install them. E.g. akmod-*
```
dnf search akmod
```
Warning: akmode-nvidia can be incompatible. 
If Fedora can not boot the GUI, change boot settings by typing `e` instead of `ENTER`, 
then add the runlevel `3` just after `quiet`.

- install chrome from google repository

- install missing drivers for Wifi : http://codeketchup.blogspot.fr/2015/12/how-to-install-broadcom-drivers-in.html


## Commands

```
dnf history
dnf remove package*
```

## Tools

- rfkill: enable/disable wifi
    - rfkill list
- ifconfig: show mac address, etc.
- iwlist scan: show available wifi
    - if wifi is on `eth1` interface
    ```
    iwconfig
    iwconfig eth1 essid mynet key 16a12bd649ced7ce42ee3f383f
    ifconfig eth1 up
    dhclient eth1
    iwconfig eth1
    ```
- dnf install network-manager-applet

## Misc

```
# list installed package
rpm -qa
# uninstall package
rpm -e <package name>
```