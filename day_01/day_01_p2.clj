(ns day_01_p2
  (:require [clojure.java.io :as io]))

(defn -main []
  (let [list1 (atom [])
        list2 (atom [])]
    (with-open [reader (io/reader "data.txt")]
      (doseq [line (line-seq reader)]
        (let [[num1 num2] (map (fn [part] (Integer/parseInt part))
                               (clojure.string/split line #"\s+"))]
          (swap! list1 conj num1)
          (swap! list2 conj num2))))
    
    (let [freq1 (frequencies @list1)
          freq2 (frequencies @list2)
          ans (atom 0)]
      (doseq [num @list1]
        (swap! ans + (* (get freq1 num 0) num (get freq2 num 0))))
      (println @ans))))
