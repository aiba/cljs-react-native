(ns myapp.main
  (:require ["react-native" :as rn]
            [applied-science.js-interop :as j]
            [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [hx.react :as hx]))

(defnc GreetingView []
  ($ rn/Text "Hello World"))

(defonce *root-wrapper (atom nil))

(hx/defcomponent RootWrapper
  (constructor [this]
    this)
  (componentDidMount [this]
    (reset! *root-wrapper this))
  (componentWillUnmount [this]
    (reset! *root-wrapper nil))
  (render [this]
    [rn/View {:style {:flex 1
                      :align-items "center"
                      :justify-content "center"}}
     [GreetingView]]))

(defn start {:dev/after-load true} []
  (if-let [rw @*root-wrapper]
    (j/call rw :forceUpdate)
    (rn/AppRegistry.registerComponent "MyApp" (fn [] RootWrapper))))

(defn init []
  (start))
