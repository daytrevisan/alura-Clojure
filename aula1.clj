(ns curso.aula1)

; FUNÇÕES E VETORES DEF

; Invocação de função -> tudo entre parênteses

(println "Bem vindo ao sistema de estoque")

; Definindo uma variável (nome da variável seguida do valor)
(def total-de-produtos 15)

; Imprimindo variável total de produto com o rótulo (string) à frente)
(println "Total" total-de-produtos)

; Permite redefinição da variável
(def total-de-produtos 17)

(println "Total" total-de-produtos)

; Para uma operação, coloca-se o sinal matemático no início
; Operadores são tratados como funções
; Ex: somar 17 com 3
(println (+ 17 3))

(println(- 17 5))

(println (* 2 total-de-produtos))

(def total-de-produtos (+ total-de-produtos 3))

(println "Total" total-de-produtos)

(def estoque ["Mochila", "Camiseta"])
(println estoque)

; Buscando elemento por posição
(println (estoque 0))
(println (estoque 1))

; Podemos contar o número de elementos na lista
(println(count estoque))

; Adicionando um elemento na lista
(println (conj estoque "Cadeira"))

; Porém, ao imprimir, o último elemento não incorporou à lista efetivamente
; Imutabilidade do Clojure -> só é permitido redefinição (pois o apontamento continua para o mesmo local )
(println estoque)

; Para inclusão efetiva de novo elemento -> def seguido de conj
(def estoque (conj estoque "Cadeira"))

(println estoque)

; A imutabilidade em Clojure oferece mais segurança aos dados
; As alterações realizadas ficam mais visíveis para controle

; FUNÇÕES E PARÂMETROS

; Definindo uma função -> necessário passar parâmetros
(defn imprime-mensagem []
  (println "------------------------")
  (println "Bem vindo(a) ao estoque!"))

(println(imprime-mensagem))

; Definindo um desconto de 10% para um produto
; Nome da função como imperativo

(defn aplica-desconto [valor-bruto]
  (* valor-bruto 0.9))

(println(aplica-desconto 100))

; Nome da função como substantivo -> "função pura"/simples
(defn valor-descontado
  "Retorna o valor descontado que é 90% do valor bruto"
  [valor-bruto]
  (* valor-bruto 0.9))

; Para melhorar a legilibilidade
(defn valor-descontado
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (* valor-bruto (- 1 0.10)))

(println(valor-descontado 200))

; SÍMBOLOS LOCAIS VERSUS GLOBAIS

; Do exemplo anterior, vamos definir um símbolo ("variável") para indicar o desconto
; Obs: mesmo definido dentro da função, o símbolo comporta-se de forma global

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (def desconto 0.10)
  (* valor-bruto (- 1 desconto))
  )

(println (valor-descontado 1000))

; Para alterarmos este escopo do símbolo ("variável"), alteramos de 'def' para 'let
; 'Let' atua apenas dentro dos seus próprios parênteses e é necessário passar um vetor

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [desconto-let (/ 15 100)]
    (println "Calculando desconto de" desconto-let)
    (* valor-bruto (- 1 desconto))))

; Em Clojure, é comum fechar todos os parênteses na mesma linha

(valor-descontado 5000)

; A divisão (/ 10 100) retorna 90N

; Clojure dá suporte para as classes BigInt (ex: 90N) e BigDecimal (ex: 90M) -> precisão infinita de ponto flutuante

; LET MÚLTIPLO E CONDICIONAIS

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor for estritamente maior que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (println "Calculando desconto de" desconto)
      (- valor-bruto desconto))
    (println "Valor total sem desconto" valor-bruto)))

(valor-descontado 100)

(if (> 500 100)
  (println "maior")
  (println "menor ou igual"))

(if (> 50 100)
  (println "maior")
  (println "menor ou igual"))