(ns ascii-table-clojure.core
  (:require [clojure.string :as str]))

(def header
  (str/join " | " (repeat 4 "Dec  Hex  Oct  C")))

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
  (str/join " | " (map format-block xs)))

(defn ascii-table []
  (str/join "\n" (conj (map format-row table-rows) header)))

(comment
  (println (ascii-table)))
