(ns day_03_p2)

(defn -main []
  (let [ans (atom 0), flag (atom true)]
    (doseq [[match num1 num2] (re-seq #"mul\((\d+),(\d+)\)|do\(\)|don't\(\)"
                                      (slurp "data.txt"))]
      (cond
        (and num1 num2) (when @flag (swap! ans + (* (Integer/parseInt num1)
                                                    (Integer/parseInt num2))))
        (= match "do()") (reset! flag true)
        (= match "don't()") (reset! flag false)))
    (println @ans)))
