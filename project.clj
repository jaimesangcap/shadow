(defproject thheller/shadow-client "1.0.186"
  :description "a cljs library for managed state in the shadows of the DOM"
  :url "http://github.com/thheller/shadow"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies
  [[org.clojure/core.async "0.2.395"]
   [hiccup "1.0.5"]]

  :source-paths
  ["src/main"]

  :test-paths
  ["src/test"]


  :profiles
  {:dev
   {:source-paths
    ["src/dev"]

    :dependencies
    [[org.clojure/clojure "1.9.0-alpha14"]
     [thheller/shadow-build "1.0.246"]
     [org.clojure/clojurescript "1.9.456"]]}}

  )
