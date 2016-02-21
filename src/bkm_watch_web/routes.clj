(ns bkm-watch-web.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [bkm-watch-db-migrate.mysql :as data]
            [cheshire.core :as json :refer :all]))

(defn json-response [response]
  {:headers {"content-type" "application/json"}
   :body (json/generate-string response)})

(defroutes web-routes
  (GET "/" [] "Hello BKM-Watch")
  (GET "/data/request" request (str request))
  (GET "/data/alerts" [page limit]
       (let [limit (if-not limit 10 (Integer/parseInt limit))
             page (if-not page 0 (Integer/parseInt page))]
         (json-response (data/get-latest-alerts limit page))))
  (route/not-found "Not Found"))
