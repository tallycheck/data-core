FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallycheck/tallyadmin-authority-servo
RUN mkdir -m 0755 -p /tallycheck/tallyadmin-authority-servo
VOLUME ["/tallycheck/tallyadmin-authority-servo/conf"]

ADD app /tallycheck/tallyadmin-authority-servo
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]