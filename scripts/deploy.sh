#!/bin/bash

REPOSITORY=/home/ec2-user/app/backend2
ZIP_DIR=$REPOSITORY/zip
JAR_NAME="centerthon-0.0.1-SNAPSHOT.jar"

echo "> Build 파일 복사"

# zip 디렉터리의 JAR 파일을 REPOSITORY 디렉터리로 복사
cp $ZIP_DIR/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

# `pgrep` 대신 `ps`와 `grep` 사용
CURRENT_PID=$(ps -ef | grep 'CENTERTHON_BE' | grep 'java' | awk '{print $2}')

echo "현재 구동중인 어플리케이션 pid: $CURRENT_PID"


if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $REPOSITORY/$JAR_NAME

echo "> $JAR_NAME 실행"
nohup java -jar \
   -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-real-db.properties \
   -Dspring.profiles.active=real,local \
   -DDB_URL=$DB_URL \
   -DDB_USERNAME=$DB_USERNAME \
   -DDB_PASSWORD=$DB_PASSWORD \
   -DCHATGPT_KEY=$CHATGPT_KEY \
   -DSTT_URL=$STT_URL \
   -DSTT_ID=$STT_ID \
   -DSTT_SECRET=$STT_SECRET \
   $REPOSITORY/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
