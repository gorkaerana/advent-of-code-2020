(use 'clojure.java.io
     'clojure.string)

(defn get-lines [fname]
  ;; Reads lines of 'fname' into array
  (with-open [r (reader fname)]
    (doall (line-seq r))
    )
  )

(defn slice [coll i j]
  ;; Takes slice from coll, i.e., items between indexes i (included) and j (not included)
  (->> coll (take j) (drop i))
  )

(defn get-blank-line-indexes [coll]
  ;;
  (filter
    #(= (coll %1) "")
    (range 0 (count coll)))
  )

(defn consolidate-batch [coll]
  ;; TODO: return map
  (let [passport (->> coll
                      (map #(split %1 #"[ ]+"))
                      (vec)
                      (apply concat)
                      (vec)
                      (map #(split %1 #":"))
                      )]
    (zipmap
     (map #(keyword (%1 0)) passport)
     (map #(%1 1) passport))
    )
  )

(defn validate-passport [passport]
  ;;
  (let [fields ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" "cid"]]
    (=
     (count (filter
     #(contains? passport %1)
     (map keyword fields)))
     (count fields))
    )
  )


;; NOTE: remember to add empty lines to beginning and end of input file
(let [input-path "./input/day4.txt"
      batch-file (vec (get-lines input-path))
      blank-line-indexes (vec (get-blank-line-indexes batch-file))]

  (println
   (->> (-> batch-file
            (slice
             (inc (blank-line-indexes 0))
             (blank-line-indexes 1)))
       (consolidate-batch)
       (validate-passport)))

  )
