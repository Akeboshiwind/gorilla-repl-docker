# Gorilla REPL

A docker image to run gorilla repl.

## Running

To run, run the following:

```bash
docker run -d
           -v ~/workspaces/:/usr/src/app/ws/
           -p 3000:3000
           -p 7000:7000
           --rm
           --name gorilla-repl
           akeboshiwind/gorilla-repl
```

This will:
- Start the gorilla repl at http://localhost:3000/worksheet.html
- Start an nREPL at localhost:7000
- Create a `ws/` folder in the container that saves worspaces to `~/workspaces/` on your host
  - Remember to save your worksheets in the `ws/` directory.

## Running with boot

Just swap out `akeboshiwind/gorilla-repl` with `akeboshiwind/gorilla-repl:boot`.

## Docker Compose

Here's how I run it using docker-compose:

```yaml
version: '3.5'

services:
  gorilla-repl:
    image: akeboshiwind/gorilla-repl:boot
    container_name: gorilla-repl
    restart: always
    ports:
      - 3000:3000
    volumes:
      - ./ws/:/usr/src/app/ws/
      - ./data/:/usr/src/app/data/
```

## Loading dependancies at runtime

Sometimes you'll want to add a dependancy to a notebook, to allow this without restarting the whole docker container I've included `cemerick.pomegranate` as a dependancy. It can be imported like below:

```clojure
(use '[cemerick.pomegranate :only (add-dependencies)])
```

And new dependancies can be loaded like below:

```clojure
(add-dependencies :coordinates '[[clj-http "3.9.1"]]
                  :repositories (merge cemerick.pomegranate.aether/maven-central
                                       {"clojars" "https://clojars.org/repo"}))
```

The `:repositories` key is to allow loading from clojars, it needs to be included each time.

You can then require the dependancy like normal:

```clojure
(require '[clj-http.client :as client])
```

Here's a quick function that ties it all together:

```clojure
(defn add-deps
  [deps-list]
  (cemerick.pomegranate/add-dependencies
    :coordinates deps-list
    :repositories (merge cemerick.pomegranate.aether/maven-central
                         {"clojars" "https://clojars.org/repo"})))
```

## Building

For the leiningen version:

```bash
docker build -t gorilla-repl:lein -t gorilla-repl:latest .
```

For the boot version:

```bash
docker build -f Dockerfile.boot -t gorilla-repl:boot .
```
