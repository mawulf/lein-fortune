(defproject lein-fortune "0.1.0-SNAPSHOT"
  :description "Simple clojure / compojure / ring webapp to show config dependent fortune cookies over web frontend"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring/ring-core "0.2.5"]
     [ring/ring-devel "0.2.5"]
     [ring/ring-jetty-adapter "0.2.5"]
     [compojure "0.4.0"]
     [hiccup "0.2.6"]]
  :main lein_fortune.core)
