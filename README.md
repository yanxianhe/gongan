# gongan

# 打包成jar
~~~~~
mvn clean package
~~~~~
# 运行

~~~~~
java -jar  target/gongan-0.0.1-SNAPSHOT.jar
~~~~~
# swagger-ui 访问地址
* http://ip:port/swagger-ui/index.html#/


# 添加Linux 启动脚本目录 init-linux

> work 目录下存放 gongan-0.0.1-SNAPSHOT.jar

> gongan.sh
~~~~~
chmod a+x ./gongan.sh
./gongan.sh start    ## 启动
./gongan.sh restart  ## 重启
./gongan.sh stop     ## 停止
~~~~~


# 添加 dockerfile 启动脚本目录 init-docker

> gongan-0.0.1-SNAPSHOT.jar

> dockerfile
