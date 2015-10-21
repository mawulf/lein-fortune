(ns lein_fortune.configurator
   (:require [clojure.contrib.duck-streams :as ds])
  (:require [clojure.contrib.lazy-xml :as lxml])
  (:require [clojure.xml :as xml])
  (:require [clojure.zip :as zip])
  (:require [clojure.contrib.zip-filter.xml :as zf])
  (:import (java.io File)))

(def config (ref (zip/xml-zip (lxml/parse-trim (java.io.File. "./config/config-tst.xml")))))

(defn load-config 
  "loads an xml file (e.g. a config file and returns a structured map)"
  []
  (xml/parse (File. "./config/config-tst.xml"))
  )

(defn get-config-value 
  "returns the value from an xml element defined by the given parameter 'tags'"
  [& tags]
	  (apply zf/xml1-> (zip/xml-zip(load-config)) (conj (vec tags) zf/text)))

(defn set-config-value
  "sets a new value programatically to a config key"
  [value & tags]
(ds/spit "./config/config-tst.xml"
  (with-out-str
    (lxml/emit
      (zip/root
       (zip/edit tags "./config/config-tst.xml" value)))))
)



