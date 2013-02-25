(ns shadow.ui.popup
  (:require [shadow.object :as so]
            [shadow.keyboard :as kb]
            [shadow.dom :as dom]))

(so/define-event :popup-closing "" [])
(so/define-event :popup-closed "" [])
(so/define-event :popup-open "" [])

(defn close [this]
  (let [parent (so/get-parent this)]
    (so/notify! parent :popup-closing)
    (so/destroy! this)
    (so/notify! parent :popup-closed)))

(so/define ::popup-backdrop
  :on []
  :dom (fn [this] [:div#backdrop])
  :dom-events [:click #(close (:parent %))]

  :keyboard ["escape" #(close (:parent %))])

(defn create [parent popup-type obj]
  (when-not (map? obj)
    (throw (ex-info "popup/create requires map as third arg" {:obj obj})))
  (so/create popup-type (merge obj {:parent parent})))

(defn show [popup]
  (let [;; make backdrop a child of the popup, so it gets destroyed when the popup is destroyed, neat eh?
        backdrop (so/create ::popup-backdrop {:parent popup})]

    (kb/push-focus backdrop)
    (kb/push-focus popup)

    (dom/append backdrop)
    (dom/append popup)
    (so/notify! popup :popup-open)
    popup
    ))

(defn open [parent popup-type obj]
  (show (create parent popup-type obj)))

