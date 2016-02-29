(ns bkm-watch-web.stats
  (:require [bkm-watch-db-migrate.mysql :as data :refer :all]))

(defn total []
  (data/total-alerts))

(defn by-month-last []
  )

(defn by-month [year month]
  (data/total-alerts-by-month year month))

(defn aggregated []
  (let [t (total)
        tt (:count (nth t 0))]
    [{:heading "Total Alerts" :content tt}]))
