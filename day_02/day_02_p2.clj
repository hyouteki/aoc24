(ns day_02_p2)

(defn check-diff [nums start skip_allowed]
  (let [inc_seq (atom true), dec_seq (atom true),
        inc_skip (atom skip_allowed), dec_skip (atom skip_allowed),
        inc_last (atom (dec start)), dec_last (atom (dec start))]
    (loop [i start]
      (if (= i (count nums)) true
        (do (when @inc_seq
              (let [diff (- (nth nums i) (nth nums @inc_last))]
                (if (or (< diff 1) (> diff 3))
                  (if @inc_skip (reset! inc_skip false)
                      (reset! inc_seq false))
                  (reset! inc_last i))))
            (when @dec_seq
              (let [diff (- (nth nums @dec_last) (nth nums i))]
                (if (or (< diff 1) (> diff 3))
                  (if @dec_skip (reset! dec_skip false)
                      (reset! dec_seq false))
                  (reset! dec_last i))))
          (if (not (or @inc_seq @dec_seq)) false (recur (inc i))))))))

(defn -main []
  (let [ans (atom 0)]
    (with-open [reader (clojure.java.io/reader "data.txt")]
      (doseq [line (line-seq reader)]
        (let [nums (map (fn [part] (Integer/parseInt part))
                        (clojure.string/split line #"\s+"))]
          (when (or (check-diff nums 1 true) (check-diff nums 2 false))
            (swap! ans inc)))))
    (println @ans)))
