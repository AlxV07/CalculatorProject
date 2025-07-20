(ns calculator.lexer.lexer
    (:require [calculator.lexer.token :as token]
              [clojure.string :as str]))

;; lex the given input into tokens list
;; `input`: input string
;; returns: a list of tokens
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
