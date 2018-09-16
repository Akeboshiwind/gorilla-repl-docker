(defproject gorilla-repl "0.1.0"
  :dependencies [;; Clojure
                 [org.clojure/clojure "1.8.0"]

                 ;; Dependancy loading at runtime
                 [com.cemerick/pomegranate "1.0.0"]]
  :target-path "target/%s"
  :plugins [[lein-gorilla "0.4.0"]]
  :profiles {:uberjar {:aot :all}})
