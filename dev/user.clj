(ns user
  (:require [mentat.clerk-utils.build :as b]
            [mentat.clerk-utils.css :as css]
            [nextjournal.clerk :as clerk]))

(css/set-css!
 "https://unpkg.com/mathbox/build/mathbox.css")

(def index
  "notebooks/maacl/feigenbaum.clj")

(def defaults
  {;; NOTE by default, your project's first notebook is registered as its index;
   ;; this means that static builds will populate `index.html` with this
   ;; notebook.
   ;;
   ;; Comment out the following line to tell Clerk to generate its own index
   ;; with a list of all built pages.
   :index index
   :cljs-namespaces
   '[maacl.sci-extensions]})

(def serve-defaults
  (assoc defaults
         :port 7777
         :watch-paths ["notebooks"]
         :browse? true))

(def static-defaults
  (assoc defaults
         :browse? false
         :paths ["notebooks/**.clj"]
         ;; Uncomment this if you have a custom cname.
         :cname ""
         :git/url "https://github.com/maacl/feigenbaum"))

(defn serve!
  "Alias of [[mentat.clerk-utils.build/serve!]] with [[defaults]] supplied as
  default arguments.

  Any supplied `opts` overrides the defaults."
  ([] (serve! {}))
  ([opts]
   (b/serve!
    (merge serve-defaults opts))))

(def ^{:doc "Alias for [[mentat.clerk-utils.build/halt!]]."}
  halt!
  b/halt!)

(defn build!
  "Alias of [[mentat.clerk-utils.build/build!]] with [[static-defaults]] supplied
  as default arguments.

  Any supplied `opts` overrides the defaults."
  [opts]
  (b/build!
   (merge static-defaults opts)))
