(ns calculator.calculator
    (:require [calculator.lexer.lexer :as lexer])
    (:require [calculator.parser.parser :as parser])
    (:require [calculator.parser.astnode :as astnode]))

;; main calculate end-point: takes input string and returns numerical result
;; `str-input`: input string to solve
;; returns: numerical calculation of input
(defn calculate [str-input]
    (astnode/eval-node (parser/parse (lexer/lex str-input))))

;; Format input string to be ready to send to calculator
;; `str-input`: input string to format
;; `was-digit` (OPTIONAL): set any value (overloaded constructor) to indicate last char was a digit
;; returns: formatted string ready for calculating
(defn format-input
    ([str-input]
        (if (empty? str-input)
            str-input
            (let [s1 (subs str-input 1)]
                (if (= \space (first str-input))
                    (format-input s1)
                    (if (Character/isDigit (first str-input))
                        (str (first str-input) (format-input s1 true))
                        (str (first str-input) " " (format-input s1)))))))
    ([str-input was-digit]
        (if (empty? str-input)
            str-input
            (let [s1 (subs str-input 1)]
                (if (= \space (first str-input))
                    (str " " (format-input s1))
                    (if (Character/isDigit (first str-input))
                        (str (first str-input) (format-input s1 true))
                        (str " " (first str-input) " " (format-input s1))))))))

;; Get a random operator to help generate random input
;; returns: string of random arithmetic operator
(def op ["+" "-" "*" "/"])
(defn random-op []
    (rand-nth op))

;; Generate random input for evaluation
;; returns: string of random input
(defn random-input
    ([]
    (let [len (rand-int 7)]
        (random-input len)))
    ([nof]
        (println nof)
        (if (= nof 0)
            (rand-int 100)
            (str (rand-int 100) (random-op) (random-input (- nof 1))))))
