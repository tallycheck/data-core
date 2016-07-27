FROM java:8-jdk-alpine
MAINTAINER Gao <altiplanogao@hotmail.com>
RUN apk add --no-cache wget bash supervisor

ENV APP_PATH=/tallybiz/tallybiz-servo-4-admin
RUN mkdir -m 0755 -p /tallycheck/tallybiz-servo-4-admin
VOLUME ["/tallycheck/tallybiz-servo-4-admin/conf"]

ADD app /tallycheck/tallybiz-servo-4-admin
COPY supervisord.conf /etc/supervisord.conf

CMD ["/usr/bin/supervisord", "-n"]