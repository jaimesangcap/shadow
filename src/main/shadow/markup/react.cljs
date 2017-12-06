(ns shadow.markup.react
  "EXPERIMENTAL - DO NOT USE"
  (:refer-clojure :exclude [for map meta time])
  (:require-macros [shadow.markup.react :as m])
  ;; macro requires this
  (:require [shadow.markup.react.impl.interop :as interop]
            [shadow.markup.css :as css]
            ["react" :as react]))

(defn for [& args]
  (throw (ex-info "shadow.markup.react/for is a macro" {})))

(defn $ [type & args]
  (interop/create-element type args))

(defn with-inner-html [html props]
  (let [html #js {"__html" html}]
    (js/goog.object.set props "dangerouslySetInnerHTML" html))
  props)

(defn fragment [& body]
  (let [arr (into-array body)]
    (.unshift arr nil)
    (.unshift arr react/Fragment)
    (.apply react/createElement nil arr)))

(m/define-elements)