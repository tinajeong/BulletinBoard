# BulletinBoard

## 환경세팅 
### Docker로 mysql 실행

```shell
$ docker run --name bulletin-board -e MYSQL_PORT_HOST=% -e MYSQL_ROOT_PASSWORD=board9999! -p3306:3306 -d mysql:latest
```

### Docker terminal 에서 DB 스키마 생성
```shell
# mysql -u root -p
mysql> create database bulletin_local;
mysql> show databases;
```