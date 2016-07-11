FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallycheck/tallyuser-servo-4-admin
RUN mkdir -m 0755 -p /tallycheck/tallyuser-servo-4-admin
VOLUME ["/tallycheck/tallyuser-servo-4-admin/conf"]

ADD app /tallycheck/tallyuser-servo-4-admin
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]