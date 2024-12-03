(ns day_03)

(defn -main []
  (let [ans (atom 0)]
    (doseq [[_ num1 num2] (re-seq #"mul\((\d+),(\d+)\)" (slurp "data.txt"))]
      (swap! ans + (* (Integer/parseInt num1) (Integer/parseInt num2))))
    (println @ans)))
