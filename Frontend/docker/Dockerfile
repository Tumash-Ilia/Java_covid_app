FROM registry.gitlab.com/sti-group/sti-app/node:fermium

RUN mkdir -p /home/sti

WORKDIR /home/sti
COPY . .
COPY docker/entrypoint.sh /usr/bin/entrypoint.sh
RUN rm -rf docker .dockerignore

ENV HOST=0.0.0.0 PORT=80
EXPOSE 80

RUN chmod +x /usr/bin/entrypoint.sh
ENTRYPOINT ["/usr/bin/entrypoint.sh"]