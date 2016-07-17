FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallycheck/tallyuser-authentication-servo
RUN mkdir -m 0755 -p /tallycheck/tallyuser-authentication-servo
VOLUME ["/tallycheck/tallyuser-authentication-servo/conf"]

ADD app /tallycheck/tallyuser-authentication-servo
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]