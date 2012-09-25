(ns webtest.init
  ;(:use webtest.core)
  (:require [clojure.java.io :as io]
            [immutant.messaging :as messaging]
            [immutant.utilities :as utilities]
            [immutant.web :as web]
            [noir.server :as server]))

;; This file will be loaded when the application is deployed to Immutant, and
;; can be used to start services your app needs. Examples:


;; Web endpoints need a context-path and ring handler function. The context
;; path given here is a sub-path to the global context-path for the app
;; if any.

; (web/start "/" my-ring-handler)
; (web/start "/foo" a-different-ring-handler)

(web/start "/alpha" (fn [r] {:status 200 :body "hello alpha"}))
(web/start "/beta" (fn [r] {:status 200 :body "hello beta"}))

;; To start a Noir app:
; (server/load-views (str (web/src-dir) "/<(str ns)>/views"))
; (web/start "/" (server/gen-handler {:mode :dev :ns '<(str ns)>}))

(server/load-views (io/file (utilities/app-root) "src/webtest/gamma/views"))
(web/start "/gamma" (server/gen-handler {:ns 'webtest.gamma}))

(server/load-views (io/file (utilities/app-root) "src/webtest/delta/views"))
(web/start "/delta" (server/gen-handler {:ns 'webtest.delta}))

;; Messaging allows for starting (and stopping) destinations (queues & topics)
;; and listening for messages on a destination.

; (messaging/start "/queue/a-queue")
; (messaging/listen "/queue/a-queue" #(println "received: " %))

