(ns bkm-watch-web.alerts
  (:require [bkm-watch-db-migrate.mysql :as data]))

(defn latest
  []
  (latest 10 0)
  [count]
  (latest count 0)
  [count page]
  (data/get-latest-alerts count page))
