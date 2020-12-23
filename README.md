# Streaming server

## 개요
트레이너 영상과 형상정보를 스트리밍 해주는 서버입니다.

## What is Used
- java Spring Boot
- Spring Data Jpa
- JWT Authorization By JJWT
- Error Logback Notification(Slack)

## Getting Started
### Dependencies
- JDK11
- All dependencies are managed by gradle
## Deployment
this project build docker image automatically so deploy on docker
### login github docker registry
    sudo docker login https://docker.pkg.github.com --username [userName]
### Deploy on server
- deploy test server(develop branch)
```shell script
sudo docker run -d  -p 8080:8080 --name=streaming-server \
-v /etc/localtime:/etc/localtime:ro  -e TZ=Asia/Seoul  --restart=unless-stopped \
docker.pkg.github.com/me-x-us/api-server/test:version
```
## Contributing

If you see this project and have any points to improve or do not understand, please post any issues or Pull Requests.
Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code
of conduct, and the process for submitting pull requests to us.

## Authors
  - [Always0ne](https://github.com/Always0ne) - **SangIl Hwang** - <si8363@soongsil.ac.kr>

See also the list of [contributors](https://github.com/me-x-us/straming-server/contributors)
who participated in this project.
## Used or Referenced Projects
- [SpringBootRestApiTemplate](https://github.com/always0ne/SpringBootRestApiTemplate) - **MIT** 
    - referenced to create Spring rest APi Server.
 - [spring-logback-slack-notification-example](https://github.com/brant-hwang/spring-logback-slack-notification-example) - **LICENSE ?** 
    - referenced to create an error notification function.

## License
```
MIT License

Copyright (c) 2020 always0ne

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
