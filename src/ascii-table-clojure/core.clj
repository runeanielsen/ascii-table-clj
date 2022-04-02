(ns ascii-table-clojure.core
  (:require [clojure.string :as str]))

(def ^:private header
  (->> "Dec  Hex  Oct  C"
       (repeat 4)
       (str/join " | ")))

(def ^:private table-rows
  (for [i (range 32)]
    (map #(+ i (* 32 %)) (range 4))))

(defn- get-display-char [n]
  (if (or (< n 33) (== n 127))
    \space
    (char n)))

(defn- format-block [n]
  (format "%3d %4o %4x %2c" n n n (get-display-char n)))

(defn- format-row [xs]
  (->> xs
       (map format-block)
       (str/join " | ")))

(defn ascii-table []
  (str header \newline
       (->> table-rows
            (map format-row)
            (str/join \newline))))

(println (ascii-table))
