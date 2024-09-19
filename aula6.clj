(ns curso.aula6)

; DESTRUCT DE SEQUENCIA, MAP, REDUCE EM MAPAS E THREAD LAST

(def pedido {
             :mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

; Função para imprimir e retornar 15
(defn imprime-e-15 [valor]
  (println "valor" (class valor) valor)                     ; Passando entrada do mapa, e não um vetor
  15)

(println (map imprime-e-15 pedido))

; Redefinindo a função como chave-valor
; Forma incorreta -> retorna erro
; Aqui temos 1 parâmetro, mas a aridade é 2
;(defn imprime-e-15 [chave valor]
;  (println "chave e valor")
;  15)
;
;(println (map imprime-e-15 pedido))

; Passa um parâmetro que é um vetor
; Mas vamos desestruturar o vetor em dois parâmetros (chave e valor)
(defn imprime-e-15 [[chave valor]]
  (println chave "<e>" valor)
  15)

(println (map imprime-e-15 pedido))

; Mapeando só para o valor
(defn imprime-e-15 [[chave valor]]
  (println chave "<e>" valor)
  valor)

(println (map imprime-e-15 pedido))

(defn preco-dos-produtos [[chave valor]]                    ; 'chave' fica apagada pois não é utilizada
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produtos pedido))
(println "Total" (reduce + (map preco-dos-produtos pedido)))

; Outra forma
(defn preco-dos-produtos [[_ valor]]                    ; como 'chave' não é utilizada, podemos substituir por _
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produtos pedido))
(println "Total" (reduce + (map preco-dos-produtos pedido)))

; Extraindo o encadeamento de 'map' e 'reduce' para uma função
(defn total-do-pedido
  [pedido]
  (reduce + (map preco-dos-produtos pedido)))

(println "Total pela função:" (total-do-pedido pedido))

; THREAD LAST
; Primeiro Função, depois Coleção
; Todas as funções que trabalham com coleções (como map, reduce e filter) usam este padrão

(defn total-do-pedido
  [pedido]
  (->> pedido
      (map preco-dos-produtos ,,,)                          ; função 'preco-dos-produtos' + coleção ',,,'
      (reduce + ,,,)))

(println "Total pela função c/ thread last:" (total-do-pedido pedido))



(defn preco-total-do-produto [produto]
  (* (:quantidade produto) (:preco produto)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       vals                                                 ; usa-se 'vals' p/ retornar as chaves
       (map preco-total-do-produto ,,,)
       (reduce + ,,,)))

(println "Total com vals:" (total-do-pedido pedido))



; Utilizando FILTER
(def pedido {
             :mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}
             :chaveiro {:quantidade 1}})

; Filtrando apenas itens gratuitos (sem preço)
(defn gratuito?
  [item]
  ;(println item)
  (<= (get item :preco 0) 0))

(println "Filtrando gratuitos:")
(println (filter gratuito? (vals pedido)))

; Variação que recebe os dois argumentos
; Como a 'chave' não é utilizada, usamos destructuring -> substitui 'chave' por '_'
(defn gratuito?
  [[_ item]]
  (<= (get item :preco 0) 0))

(println "Filtrando gratuitos dois argumentos:")
(println (filter gratuito? pedido))

; Variação com função anônima
(defn gratuito?
  [[_ item]]
  (<= (get item :preco 0) 0))

; Função anônima que recebe parâmetro e chama função 'gratuito'
(println "Filtrando gratuitos destructuring:")
(println (filter (fn [[_ item]] (gratuito? item)) pedido))

; Lambda
; Função 'second' que devolve o segundo elemento
(println "Filtrando utilizando lambda")
(println (filter #(gratuito? (second %)) pedido))

; Utilizando o NOT
(defn pago?
  [item]
  (not (gratuito? item)))

(println (pago? {:preco 50}))
(println (pago? {:preco 0}))

; Fazendo uma composição (função COMP) -> 'not' + 'gratuito'
(println ((comp not gratuito?) {:preco 50}))

; A função COMP retorna uma função
(def pago? (comp not gratuito?))
(println (pago? {:preco 50}))
(println (pago? {:preco 0}))