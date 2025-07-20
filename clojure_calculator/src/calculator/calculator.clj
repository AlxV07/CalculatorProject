(ns calculator.calculator
    (:require [calculator.lexer.lexer :as lexer])
    (:require [calculator.parser.parser :as parser])
    (:require [calculator.parser.astnode :as astnode]))

;; main calculate end-point: takes input string and returns numerical result
;; `str-input`: input string to solve
;; returns: numerical calculation of input
(defn calculate [str-input]
    (astnode/eval-node (parser/parse (lexer/lex str-input))))
