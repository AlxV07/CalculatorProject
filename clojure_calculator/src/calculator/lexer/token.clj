(ns calculator.lexer.token)

(defprotocol Token
    (get-type [this]))

(def token-types
    {:close :close
     :open  :open
     :add   :add
     :sub   :sub
     :mul   :mul
     :div   :div
     :num   :num})

(defrecord CloseParenthesesToken []
    Token
    (get-type [_] (:close token-types)))

(defrecord OpenParenthesesToken []
    Token
    (get-type [_] (:open token-types)))

(defrecord AddToken []
    Token
    (get-type [_] (:add token-types)))

(defrecord SubToken []
    Token
    (get-type [_] (:sub token-types)))

(defrecord MulToken []
    Token
    (get-type [_] (:mul token-types)))

(defrecord DivToken []
    Token
    (get-type [_] (:div token-types)))

(def close-token
    (->CloseParenthesesToken))

(def open-token
    (->OpenParenthesesToken))

(def add-token
    (->AddToken))

(def sub-token
    (->SubToken))

(def mul-token
    (->MulToken))

(def div-token
    (->DivToken))

(defrecord NumToken [val]
    Token
    (get-type [_] (:num token-types)))
