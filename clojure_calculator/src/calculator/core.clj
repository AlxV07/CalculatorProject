(ns calculator.core
    (:require [calculator.calculator :as calculator]))

(defn -main []
;;       (println "Hello from core!")
;;       (println (lexer/lex "1 + 2 * 3"))
;;       (println (lexer/lex "1 + 2"))
;;       (println (astnode/eval-node (parser/parse (lexer/lex "( 1 + 2 )")))))
;;       (println (astnode/eval-node (parser/parse (lexer/lex "( 10 * 2 ) - 5 + ( 3 + 3 )")))))
;;     (println (calculator/calculate "1 + 2 + ( 6 / 2 )")))
    (println (calculator/calculate "( 1 + 2 + ( 6 / 2 ) )"))
    (def x (calculator/calculate "1 + 2"))
    (def y (calculator/calculate "3 + 4"))
    (println "x =" x)
    (println "y =" y)
    (println "x + y =" (+ x y)))
