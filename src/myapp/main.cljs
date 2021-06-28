(ns myapp.main
  (:require ["react-native" :as rn]
            [applied-science.js-interop :as j]
            [hx.react :as hx]))

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
     [rn/Text "Hello World"]]))

(defn start {:dev/after-load true} []
  (if-let [rw @*root-wrapper]
    (j/call rw :forceUpdate)
    (rn/AppRegistry.registerComponent "MyApp" (fn [] RootWrapper))))

(defn init []
  (start))
