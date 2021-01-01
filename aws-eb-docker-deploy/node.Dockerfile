# build context는 리포 루트 디렉토리
# 루트 디렉토리의 .dockerignore를 사용한다
# 작성일 기준으로 .dockerignore를 cli상에서 파일 입력으로 주입하는 방법을 못찾았다

# to apply reduce docker image size
# reference https://medium.com/trendyol-tech/how-we-reduce-node-docker-image-size-in-3-steps-ff2762b51d5a

#   docker build . -f node.Dockerfile -t aws-eb-docker-deploy-node
#   docker container run -p 3000:3000 aws-eb-docker-deploy-node
#
# stage 1
#
FROM node:14-alpine AS BUILD_IMAGE

# couchbase sdk requirements
RUN apk update && apk add yarn curl bash python g++ make && rm -rf /var/cache/apk/*

# install node-prune (https://github.com/tj/node-prune)
RUN curl -sfL https://install.goreleaser.com/github.com/tj/node-prune.sh | bash -s -- -b /usr/local/bin

# 앱 디렉터리 생성
WORKDIR /usr

# package 및 설정 파일 복사
COPY ./package.json ./package.json
COPY ./app.js ./app.js

# install and build
RUN npm install
#RUN npm run build

# remove development dependencies
RUN npm prune --production

# run node prune
RUN /usr/local/bin/node-prune

# todo: 사용자 지정으로 불필요한 패키지 제거하기
# https://tsh.io/blog/reduce-node-modules-for-better-performance/

#
# stage 2
#
FROM node:14-alpine

WORKDIR /usr

# install pm2 as global
RUN npm install pm2 -g

# copy from build image
COPY --from=BUILD_IMAGE /usr/app.js ./app.js
COPY --from=BUILD_IMAGE /usr/node_modules ./node_modules

# copy package and etc config
COPY ./package*.json ./
COPY ./ecosystem.config.js .

EXPOSE 3000

CMD pm2-runtime ./ecosystem.config.js
