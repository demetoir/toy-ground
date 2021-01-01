# todo

docker compose 이용 
nginx 설정 적용
더 좋은 디렉토리 구조
eb config 분리

.ebignore 사용하듀

환경변수 넘겨주는것


# AWS EB

AWS EB CLI를 이용하여 배포까지하기

platform branch: Docker running on 64bit Amazon Linux 2
platform version: 3.2.3


1. install aws eb cli at local

```shell
# on mac 
brew update

brew install awsebcli

eb --version
```

eb cli 설치 참
https://docs.aws.amazon.com/ko_kr/elasticbeanstalk/latest/dg/eb-cli3-install-osx.html

2. 소스가 잇는 리포 디렉토리로 이동

3. eb 초기화

```shell
eb init --profile eb-only

```

이때 기본 profile 지정으로 권한 분리 하는것을 권장

~/.aws/config 위치에 이런식으로 추가하면된다

```

[profile eb-only]
aws_access_key_id = key_id
aws_secret_access_key = access_key
```

이때 aws iam 계정의 권한은 AWSElasticBeanstalkFullAccess 정도이면 된다

4. 배포전 로컬에서 테스트

```shell
eb local run
```

다른터미널에서 status도 체크해볼

```shell
eb local status
```


5. 배포

```shell
eb deploy
```
이렇게 하면 수동으로 로컬에서 배포는 가능하다 

eb deploy 시 도커 이미지를 빌드하므로 도커 이미지를 빌드하는데 최적화가 필요하다
Dockerfile에서 문법인 

```dockerfile
FROM node:14-alpine AS BUILD_IMAGE
```
이거 쓰면 local에서는 되지만 deploy시에는 터진다


디폴트값으로 아무것도 설정하지 않으면 프로젝트 폴더 전체를 번들링해서 업로드다

# [reference]
https://jeonghwan-kim.github.io/eb-cli-%ED%88%B4-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%A0%95%EB%A6%AC/

