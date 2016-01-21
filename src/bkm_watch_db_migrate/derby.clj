(ns bkm-watch-db-migrate.derby
  (:require [clojure.java.jdbc :as j]))

(def db-info {:classname "org.apache.derby.jdbc.EmbeddedDriver"
              :subprotocol "derby"
              :subname "/home/johan/bkmwatchdb"})

;(j/query db-info ["describe ALERT_RECORD"])

(def table "ALERT_RECORD")

(defn all-results [db table]
  (j/query db [(str "select * from " table " ")]))

(defn get-old-alerts []
  (all-results db-info table))
