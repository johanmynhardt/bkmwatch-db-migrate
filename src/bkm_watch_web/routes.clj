(ns bkm-watch-web.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json :refer :all]
            [bkm-watch-web.alerts :as alerts :refer :all]))

(defn json-response [response]
  {:headers {"content-type" "application/json"}
   :body (json/generate-string response)})

(defroutes web-routes
  (GET "/" [] "Hello BKM-Watch")
  (GET "/data/request" request (str request))
  (GET "/data/alerts" [q pageSize page]
       (let [results (if-not q (alerts/latest pageSize page)
                             (alerts/search q pageSize page))]
         (json-response results)))
  (route/not-found "Not Found"))
