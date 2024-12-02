(ns day_02)

(defn check-diff [nums]
  (let [inc_seq (< (first nums) (second nums))]
    (loop [i 1]
      (if (= i (count nums)) true
          (let [diff (* (if inc_seq 1 -1) (- (nth nums i) (nth nums (dec i))))]
            (if (or (< diff 1) (> diff 3)) false (recur (inc i))))))))

(defn -main []
  (let [ans (atom 0)]
    (with-open [reader (clojure.java.io/reader "data.txt")]
      (doseq [line (line-seq reader)]
        (let [nums (map (fn [part] (Integer/parseInt part))
                        (clojure.string/split line #"\s+"))]
          (when (check-diff nums) (swap! ans inc)))))
    (println @ans)))
