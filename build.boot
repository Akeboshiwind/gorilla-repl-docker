(def project 'gorilla-repl)
(def version "0.1.0-SNAPSHOT")

(set-env! :dependencies '[;; Clojure
                          [org.clojure/clojure "1.9.0"]

                          ;; Boot
                          [nrepl "0.3.1"]
                          [sooheon/boot-gorilla "0.1.1-SNAPSHOT"]

                          ;; Dependancy loading at runtime
                          [com.cemerick/pomegranate "1.0.0"]])

(require '[sooheon.boot-gorilla :refer [gorilla]])

(deftask deps [])
