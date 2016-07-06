FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallycheck/tallyuser-servo
RUN mkdir -m 0755 -p /tallycheck/tallyuser-servo
VOLUME ["/tallycheck/tallyuser-servo/conf"]

ADD app /tallycheck/tallyuser-servo
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]