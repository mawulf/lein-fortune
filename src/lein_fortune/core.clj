(ns lein_fortune.core
  (:require [lein_fortune.configurator :as cf])
  (:require [clojure.contrib.string])
  (:use compojure.core)
  (:use hiccup.core)
  (:use hiccup.page)
  (:import (java.util Random)))

(defn read-fortune-data-file 
  "open file, split them into a vector and bind them to a local var"
  [f i]
  (let [spl (clojure.contrib.string/split #"%{1}\n" (slurp f))]
  (try
    (nth spl i)
    (catch Exception e
      "There is no slogan available!"
      ))))

(defn start-fortune [i]
  (read-fortune-data-file (cf/get-config-value :fortune-file-path) i))

(defn view-layout 
  "constructs the html header and adds the bodies content"
  [& content]
  ;; build the html sceleton
  (html
    (doctype :xhtml-strict)
    (xhtml-tag "en"
      [:head
        [:meta {:http-equiv "Content-type"
                :content "text/html; charset=utf-8"}]
        [:title "fortune"]]
      [:body content])))

(defn view-output 
  "a is the number of the requested slogan"
  [a]
  (view-layout
     [:i [:font {:color "blue"} [:h1 "clojure-fortune"]]]
    (if-not (= a 0)
      [:h4 "slogan no. " a]) 
    ;; TODO 
    [:i (start-fortune a)]
    [:br]
    [:a.action {:href "/fortune"} "take another slogan"]))

(defn parse-input [a]
  [(try
     (Long/parseLong a)
     (catch NumberFormatException e 0))])

(defn view-output-random []
  "shows a new slogan random generated"
  (def rnd (Random.))
  (let [rnr (. rnd (nextInt (Integer/parseInt (cf/get-config-value :max-slogan-count))))]
    (view-layout
         [:i [:font {:color "blue"} [:h1  "clojure-fortune"]]]
         (if-not (= rnr 0)
           [:h4 "slogan no. " rnr])
         [:i (start-fortune rnr)]
         [:br]
         [:a.action {:href "/fortune"} "take another slogan"]))) 

(defn view-default-input []
  "response if random is false/deactivated"
   (view-layout
    [:i [:font {:color "blue"} [:h1  "clojure-fortune"]]]
    [:h4 "insert number to show slogan"]
    [:form {:method "post" :action "/fortune"}
      [:input.text {:type "text" :name "a"}]
      [:input.action {:type "submit" :value "show slogan"}]]))

(defn view-input []
  (if (= (cf/get-config-value :load-random-fortune) "true")
    (view-output-random)
  (view-default-input)))

(defroutes app
  ;; shows the default view
  (GET "/fortune" []
    (view-input))
  ;; shows the view with action
  (POST "/fortune" [a]
    (let [[a] (parse-input a)]
      (view-output a))))



