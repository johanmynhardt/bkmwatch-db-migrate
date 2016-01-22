(ns bkm-watch-db-migrate.mysql
  (:require [clojure.java.jdbc :as j]))

;; https://github.com/clojure/java.jdbc


;;  CONFIGURATION

(def db-spec {
         :subprotocol "mysql"
         :subname "//localhost:3306/bkmwatch"
         :user "bkmwatch"
         :password "bkmwatch"
         :debug true})

(defn pool
  [spec]
  (let [cpds (doto (com.zaxxer.hikari.HikariDataSource.)
               (.setJdbcUrl (str "jdbc:" (:subprotocol spec) ":" (:subname spec)))
               (.setUsername (:user spec))
               (.setPassword (:password spec)))]
    {:datasource cpds :debug (:debug spec)}))

(def db (pool db-spec))

;; DDL

(def create-alert-table
  (j/create-table-ddl :alert
                    [:id "bigint" :primary :key :auto_increment]
                    [:date "DATETIME"]
                    [:message "varchar(4096)"]))

(def drop-alert-table
  (j/drop-table-ddl :alert))

;; TABLE MAINTENANCE

(defn show-tables [db]
  (j/query db ["show tables"]))

(defn drop-if-debug []
  (if (:debug db)
    (j/query db [drop-alert-table])))

(defn drop-alert-table! []
  (drop-if-debug))

(defn create-alert-table! []
  (j/query db [create-alert-table]))

(defn show-tables! []
  (show-tables db))

(defn drop-and-create! []
  (try
    (drop-alert-table!)
    (catch Exception e (str "error dropping " :alert ": " e)))
  (try
    (create-alert-table!)
    (catch Exception e (str "error creating " :alert ": " e))))

;; CRUD

(defn create-alert [alert]
  (let [date (:date alert)
        message (:message alert)]
    (println (str "creating alert: date: '" date ", message: " message))
    (j/insert! db :alert {:date date :message message})))

(defn get-alerts []
  (j/query db ["select * from alert"]))

;; SOME TEST STUFF

(defn test-db []
  (if (:debug db)
    (drop-and-create!))

  (do
    (print "Testing db...\n")
    (show-tables!)
    ;(create-alert nil "hello world")
    (let [adate (. (java.text.SimpleDateFormat. "yyyyMMdd") parse "20151111 GMT")]
      (create-alert adate "nifty"))
    (create-alert "2016-01-12" "hi there")
    (create-alert "2016-01-11 01:00:00" "Better?")
    ;(create-alert nil "hello again")
    ))

;; todo: (j/create-table-ddl :users )
; http://clojure-doc.org/articles/ecosystem/java_jdbc/using_ddl.html
; http://clojure-doc.org/articles/ecosystem/java_jdbc/home.html
