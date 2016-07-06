FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallymanagement/tallymanagement-servo
RUN mkdir -m 0755 -p /tallycheck/tallymanagement-servo
VOLUME ["/tallycheck/tallymanagement-servo/conf"]

ADD app /tallycheck/tallymanagement-servo
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]