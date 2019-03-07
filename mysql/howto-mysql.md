# HOWTO Mysql

## Installation
- Install mysql 
- Start server
```
cmd> mysqld
```
- Stop server
```
cmd> mysqladmin -u root -p shutdown
```

## Create a database
- Connect as root without password at first time
```
cmd> mysql -u root
```
- Set a password for root
```
mysql> SET PASSWORD FOR 'root'@'localhost' = PASSWORD('admin');
mysql> SET PASSWORD FOR 'root'@'127.0.0.1' = PASSWORD('admin');
mysql> SET PASSWORD FOR 'root'@'::1' = PASSWORD('admin');
```
- Create a database 'mydatabase'
```
mysql> CREATE DATABASE mydatabase;
```
- Use this database
```
mysql> USE mysql;
mysql> CREATE USER 'my_user'@'localhost' IDENTIFIED BY 'my_password';
mysql> GRANT ALL PRIVILEGES ON *.* TO 'my_user'@'localhost' WITH GRANT OPTION;
mysql> CREATE USER 'my_user'@'%' IDENTIFIED BY 'my_password';
mysql> GRANT ALL PRIVILEGES ON *.* TO 'my_user'@'%' WITH GRANT OPTION;
mysql> FLUSH PRIVILEGES;
mysql> quit
cmd> mysql -h localhost -u my_user -p my_password
```
- Disconnect
```
mysql> quit
```

## Connect
- Connect as root with a password
```
cmd> mysql -u root -p
# type admin
```
- List the users
```
mysql> select host, user, password from mysql.user;
```
- or connect as user myuser on mydatabase db
```
cmd> mysql -h localhost -u myuser -p
# type myuser
```
- Change the current user password
```
mysql> set password = password('pass123');
```

## Show tables
```
cmd> mysql -h localhost -u myuser -p
mysql> use mydatabase
mysql> show tables;
```

## Clean tables
```
drop table <table1>, <table2>;
delete from <table>; commit;
```
