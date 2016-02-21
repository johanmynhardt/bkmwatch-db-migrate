(defproject bkm-watch-db-migrate "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.4.2"]
                 ; db libraries
                 [org.mariadb.jdbc/mariadb-java-client "1.2.0"]
                 [org.apache.derby/derby "10.10.1.1"]
                 [org.apache.derby/derbyclient "10.10.1.1"]
                 [com.zaxxer/HikariCP "2.4.3"]
                 ; web libraries
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [cheshire "5.5.0"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler bkm-watch-web.handler/app}
  :main ^:skip-aot bkm-watch-db-migrate.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
