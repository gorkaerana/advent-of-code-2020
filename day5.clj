(use 'clojure.java.io
     'clojure.string)

(defn get-lines [fname]
  ;; Reads lines of 'fname' into array
  (with-open [r (reader fname)]
    (doall (line-seq r))
    )
  )

(defn ** [x n]
  ;; Exponentiation
  (reduce * (repeat n x))
  )

(defn semantic-partitioning [s
                             exponent
                             left-char
                             right-char]
  ;; TODO: implement semantic partitioning
  (let [n (** 2 exponent)
        min (atom 0)
        max (atom n)]
    (doseq [i (range 0 (inc exponent))]
      (if (= (subs s i (inc i)) left-char)
        (swap! max #())
        (if (= (subs s i (inc i)) right-char)
          )
        )
      )
  )
  )

(defn get-row [boarding-pass]
  ;; 
  (let [s (subs boarding-pass 0 8)]
    (semantic-partitioning
     boarding-pass
     7
     "B"
     "F"
    )
  )

(defn get-column [boarding-pass]
  ;;
  (let [len (count boarding-pass)
        s (subs boarding-pass (- len 3) len)]
    
    )
  )

(defn get-id [boarding-pass]
  ;; Compute boarding pass id:
  ;; id = (row * 8) + 5
  (let [row (get-row boarding-pass)
        col (get-column boarding-pass)]
    (+ col (* 8 row))
    )
  )

(let [input-path "./input/day5.txt"
      boarding-passes (get-lines input-path)]
  (get-row boarding-pass)
  )
