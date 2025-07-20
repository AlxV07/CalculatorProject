(ns calculator.parser.parser
    (:require [calculator.lexer.token :as token])
    (:require [calculator.parser.astnode :as astnode]))

(defn op-node-from-tokens [type left right]
    (case type
        :add (astnode/->AddNode left right)
        :sub (astnode/->SubNode left right)
        :mul (astnode/->MulNode left right)
        :div (astnode/->DivNode left right)
        (throw (ex-info (str "Unknown operator: " type) {}))))

(defn peek-token [tokens pos]
    (nth tokens pos nil))

(defn consume-token [tokens pos expected-type]
  (let [token (peek-token tokens pos)]
    (if (not= (token/get-type token) expected-type)
      (throw (ex-info (str "Expected " expected-type ", got " (token/get-type token)) {}))
      [token (inc pos)])))

(declare parse-level1)

;; numbers and parentheses
(defn parse-level3 [tokens pos]
  (let [token (peek-token tokens pos)
        type (some-> token token/get-type)]  ;; assert token != null
    (cond
      (= type (:num token/token-types))
      (let [[_ nxt-pos] (consume-token tokens pos type)
            node (astnode/->NumNode (:val token))]
        [node nxt-pos])

      (= type (:open token/token-types))
      (let [[_ nxt1-pos] (consume-token tokens pos type)
            [node nxt2-pos] (parse-level1 tokens nxt1-pos)
            [_ nxt3-pos] (consume-token tokens nxt2-pos (:close token/token-types))]
        [node nxt3-pos])

      :else
      (throw (ex-info "Unexpected token in parse-level3" {:token token})))))

;; * and /
(defn parse-level2 [tokens pos]
  (loop [[node nxt-pos] (parse-level3 tokens pos)]
    (let [token (peek-token tokens nxt-pos)
          type (some-> token token/get-type)]
      (if (#{(:mul token/token-types) (:div token/token-types)} type)
        (let [[_ nxt1-pos] (consume-token tokens nxt-pos type)
              [right-node nxt2-pos] (parse-level3 tokens nxt1-pos)]
          (recur [(op-node-from-tokens type node right-node) nxt2-pos]))
        [node nxt-pos]))))

;; + and -
(defn parse-level1 [tokens pos]
  (loop [[node nxt-pos] (parse-level2 tokens pos)]
    (let [token (peek-token tokens nxt-pos)
          type (some-> token token/get-type)]
      (if (#{(:add token/token-types) (:sub token/token-types)} type)
        (let [[_ nxt1-pos] (consume-token tokens nxt-pos type)
              [right-node nxt2-pos] (parse-level2 tokens nxt1-pos)]
          (recur [(op-node-from-tokens type node right-node) nxt2-pos]))
        [node nxt-pos]))))

(defn parse [tokens]
    (let [[node pos] (parse-level1 tokens 0)]
        node))
