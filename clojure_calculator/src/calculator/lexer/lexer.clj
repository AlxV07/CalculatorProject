(ns calculator.lexer.lexer
    (:require [calculator.lexer.token :as token]
              [clojure.string :as str]))

(defn lex [input]
    (let [parts (str/split input #"\s+")]  ;; temp, split by space
        (map (fn [part]
            (case part
                "(" token/open-token
                ")" token/close-token
                "+" token/add-token
                "-" token/sub-token
                "*" token/mul-token
                "/" token/div-token
                (token/->NumToken (Integer/parseInt part))))
            parts)))  ;; default, num
