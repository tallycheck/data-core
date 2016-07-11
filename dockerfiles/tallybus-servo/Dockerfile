FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallybus/tallybus-servo
RUN mkdir -m 0755 -p /tallycheck/tallybus-servo
VOLUME ["/tallycheck/tallybus-servo/conf"]

ADD app /tallycheck/tallybus-servo
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]