FROM clojure:boot

EXPOSE 3000
EXPOSE 7000

COPY ./build.boot /usr/src/app/
COPY ./boot.properties /usr/src/app/
WORKDIR /usr/src/app

RUN boot deps

CMD boot gorilla --ip 0.0.0.0 --port 3000 --nrepl-port 7000
