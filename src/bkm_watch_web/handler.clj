(ns bkm-watch-web.handler
  (:require [bkm-watch-web.routes :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  )

(def app (wrap-defaults web-routes site-defaults))


