(ns bkm-watch-web.alerts
  (:require [bkm-watch-db-migrate.mysql :as data]))

(defn get-or [x y]
  (if-not x y (Integer/valueOf x)))

(defn latest
  ([count page]
   (let [count (get-or count 10)
         page (get-or page 0)]
     (data/get-latest-alerts count page))))

(defn search [q count page]
  (let [count (get-or count 10)
        page (get-or page 0)]
    (data/like q count page)))
