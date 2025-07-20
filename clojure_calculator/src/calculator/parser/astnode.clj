(ns calculator.parser.astnode)

(defprotocol ASTNode
    (eval-node [this]))  ;; `eval-node` bc `eval` is Lisp keyword

(defrecord NumNode [val]
    ASTNode
    (eval-node [_] val))

(defrecord AddNode [left right]
    ASTNode
    (eval-node [_]
        (let [left-val (eval-node left)
              right-val (eval-node right)]
            (+ left-val right-val))))

(defrecord SubNode [left right]
    ASTNode
    (eval-node [_]
        (let [left-val (eval-node left)
              right-val (eval-node right)]
            (- left-val right-val))))

(defrecord MulNode [left right]
    ASTNode
    (eval-node [_]
        (let [left-val (eval-node left)
              right-val (eval-node right)]
            (* left-val right-val))))

(defrecord DivNode [left right]
    ASTNode
    (eval-node [_]
        (let [left-val (eval-node left)
              right-val (eval-node right)]
            (quot left-val right-val))))  ;; `quot` for integer division
