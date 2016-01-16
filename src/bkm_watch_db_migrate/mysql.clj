(ns bkm-watch-db-migrate.mysql
  (:require [clojure.java.jdbc :as j]))

;; https://github.com/clojure/java.jdbc

(def db-info {
              :subprotocol "mysql"
              :subname "//localhost:3306/bkmwatch"
              :user "bkmwatch"
              :password "bkmwatch"})

(j/query db-info ["show tables"])

;; todo: (j/create-table-ddl :users )
; http://clojure-doc.org/articles/ecosystem/java_jdbc/using_ddl.html
; http://clojure-doc.org/articles/ecosystem/java_jdbc/home.html
