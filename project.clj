(defproject lein-fortune "0.2.0-SNAPSHOT"
  :description "Simple clojure / compojure / ring webapp to show config dependent fortune cookies over web frontend"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring/ring-core "1.4.0"]
     [ring/ring-devel "1.4.0"]
     [ring/ring-jetty-adapter "1.4.0"]
     [compojure "1.4.0"]
     [hiccup "1.0.5"]]
  :resource-paths ["./resources/"]
  :plugins [[lein-ring "0.9.7"][lein-pprint "1.1.1"]]
  :ring {:handler lein_fortune.core/app})
