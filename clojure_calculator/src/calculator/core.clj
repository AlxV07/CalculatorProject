(ns calculator.core
    (:require [calculator.lexer.lexer :as lexer])
    (:require [calculator.parser.parser :as parser])
    (:require [calculator.parser.astnode :as astnode]))

(defn -main []
;;       (println "Hello from core!")
;;       (println (lexer/lex "1 + 2 * 3"))
;;       (println (lexer/lex "1 + 2"))
;;       (println (astnode/eval-node (parser/parse (lexer/lex "( 1 + 2 )")))))
      (println (astnode/eval-node (parser/parse (lexer/lex "( 10 * 2 ) - 5 + ( 3 + 3 )")))))
