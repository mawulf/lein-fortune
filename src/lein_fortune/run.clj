(ns lein_fortune.run)

(use 'ring.adapter.jetty)
(require 'lein_fortune.core)

(defonce server (run-jetty #'lein_fortune.core/app {:port 8080 :join? false}))

