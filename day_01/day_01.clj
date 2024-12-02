(ns day_01
  (:require [clojure.java.io :as io]))

(defn -main []
  (let [list1 (atom [])
        list2 (atom [])
        ans (atom 0)]
    (with-open [reader (io/reader "data.txt")]
      (doseq [line (line-seq reader)]
        (let [[num1 num2] (map (fn [part] (Integer/parseInt part))
                               (clojure.string/split line #"\s+"))]
          (swap! list1 conj num1)
          (swap! list2 conj num2))))
    (reset! list1 (sort @list1))
    (reset! list2 (sort @list2))
    (doseq [[num1 num2] (map vector @list1 @list2)]
      (swap! ans + (Math/abs (- num1 num2))))
    (println @ans)))
