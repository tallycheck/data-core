FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallybiz/tallybiz-servo
RUN mkdir -m 0755 -p /tallycheck/tallybiz-servo
VOLUME ["/tallycheck/tallybiz-servo/conf"]

ADD app /tallycheck/tallybiz-servo
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]