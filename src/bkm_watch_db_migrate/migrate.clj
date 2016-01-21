(ns bkm-watch-db-migrate.migrat
  (:require [bkm-watch-db-migrate.derby :as d])
  (:require [bkm-watch-db-migrate.mysql :as n]))

(count (d/get-old-alerts))

(n/drop-and-create!)

(defn migrate [old-alerts]
  (doseq [item old-alerts] (n/create-alert item)))

(migrate (d/get-old-alerts))


;(count (n/get-alerts))
