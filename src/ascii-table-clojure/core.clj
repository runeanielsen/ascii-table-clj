(ns ascii-table-clojure.core
  (:require [clojure.string :as str]))

(def header
  (->> "Dec  Hex  Oct  C"
       (repeat 4)
       (str/join " | ")))

(def table-rows
  (for [i (range 32)]
    (map #(+ i (* 32 %)) (range 4))))

(defn get-display-char [n]
  (if (or (< n 33) (== n 127))
    \space
    (char n)))

(defn format-block [n]
  (format "%3d %4o %4x %2c" n n n (get-display-char n)))

(defn format-row [xs]
  (->> xs
       (map format-block)
       (str/join " | ")))

(defn ascii-table []
  (str/join
   \newline
   (conj (map format-row table-rows) header)))

(comment
  (println (ascii-table)))
