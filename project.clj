(defproject youtrack "0.1.0-SNAPSHOT"
  :description "Jetbrains Youtrack api example"
  :url "https://you.myjetbrains.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :main ^:skip-aot svg.core
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.7.0"]
                 [cheshire "5.8.0"]])