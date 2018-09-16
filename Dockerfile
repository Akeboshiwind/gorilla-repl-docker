FROM clojure

EXPOSE 3000
EXPOSE 7000

COPY ./project.clj /usr/src/app/
WORKDIR /usr/src/app

RUN lein deps

CMD lein gorilla :ip 0.0.0.0 :port 3000 :nrepl-port 7000
