(ns bkm-watch-web.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json :refer :all]
            [bkm-watch-web.alerts :as alerts :refer :all]
            [bkm-watch-web.stats :as stats :refer :all]))

(defn json-response [response]
  {:headers {"content-type" "application/json"}
   :body (json/generate-string response)})

(defn wrap-cors [response]
  (assoc-in response [:headers] {"Access-Control-Allow-Origin" "*"
                                 "Accept" "GET"
                                 "Access-Control-Allow-Methods" "GET"
                                 "Access-Control-Allow-Headers" "accept, content-type"}))

(defroutes web-routes
  (GET "/" [] "Hello BKM-Watch")

  (GET "/data/request" request (str request))
  (GET "/data/alerts" [q pageSize page]
       (let [results (if-not q (alerts/latest pageSize page)
                             (alerts/search q pageSize page))]
         (json-response results)))

  (OPTIONS "/data/stats" [] (wrap-cors {}))
  (GET "/data/stats" []
       (wrap-cors 
        (json-response (stats/aggregated))))

;; others/defaults
  (route/resources "/")
  (route/not-found "Not Found"))
