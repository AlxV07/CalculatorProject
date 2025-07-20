(ns calculator.cli
    (:require [calculator.calculator :as calculator]))

;; Start calculator CLI repl
(defn start-calculator-repl []
    (println "Welcome to the calculator repl!\nType 'exit' to exit.")
    (loop []
        (print ">>>")
        (flush)
        (let [input (read-line)]
        (if (= input "exit")
            (println "Exiting...")
            (if (= \space input)
                (do
                    (println "Blank input. Continuing...")
                    (recur))
                (do
                    (println "Given input:" input)
                    (let [formatted (calculator/format-input input)]
                        (println "Formatted input:" formatted)
                        (println "Calculating...")
                        (try
                            (println (calculator/calculate formatted))
                            (catch Exception e
                                (println "Exception:" e "\nContinuing...")))
                        (recur))))))))
