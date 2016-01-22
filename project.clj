(defproject bkm-watch-db-migrate "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [org.mariadb.jdbc/mariadb-java-client "1.2.0"]
                 [org.apache.derby/derby "10.10.1.1"]
                 [org.apache.derby/derbyclient "10.10.1.1"]
                 [com.zaxxer/HikariCP "2.4.3"]]
  :main ^:skip-aot bkm-watch-db-migrate.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
