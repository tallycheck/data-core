FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallycheck/tallyadmin-servo
RUN mkdir -m 0755 -p /tallycheck/tallyadmin-servo
VOLUME ["/tallycheck/tallyadmin-servo/conf"]

ADD app /tallycheck/tallyadmin-servo
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]