(ns curso.aula5)

; COLEÇÕES

; Coleção associativa
; Implementação de mapa -> hashmap

(def estoque {"Mochila" 10 "Camiseta" 5})                   ; vírgula é opcional
(def estoque {"Mochila" 10, "Camiseta" 5})                  ; mas é uma boa prática
(println estoque)

(def estoque {"Mochila"  10                                 ; outra opção, itens por linha
              "Camiseta" 5})
(println estoque)                                           ; a saída é a mesma do modo anterior

; Podemos contar o número de elementos através do 'count'
(println "Temos" (count estoque) "elementos")

; Como as listas são em formato chave-valor, podemos solicitar cada um deles separadamente
; As chaves do vetor -> com 'keys'
(println "Chaves são:" (keys estoque))

; Os valores do vetor -> com 'vals'
(println "Os valores são:" (vals estoque))

; O mais comum é usar keyword (e não chaves)
; :mochila

(def estoque {:mochila  10
              :camiseta 5})

; Podemos incluir novos elementos à lista por meio de keywords
; Retorno de um novo mapa, mas o original permanece imutável
(println (assoc estoque :cadeira 3))
(println (assoc estoque :mochila 1))
(println estoque)

; Atualizando o mapa -> update recebe uma função
(println (update estoque :mochila inc))
(println estoque)                                           ; mapa original permanece imutável

(defn tira-um
  [valor]
  (println "Subtraindo um de" valor)
  (- valor 1))

(println estoque)
(println (update estoque :camiseta tira-um))
;(println estoque)

; Substrai 3 do valor que for passado
(println "Subtraindo 3 do valor" (update estoque :mochila #(- % 3)))

; Dissociando um elemento do mapa
(println (dissoc estoque :mochila))

; Criando um pedido

(def pedido {
             :mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})
(println "\n\n\n\n")
(println pedido)

; Incluir um item através da redefinição de 'pedido -> utilizando 'assoc'
(def pedido (assoc pedido :chaveiro {:quantidade 1, :preco 10}))

; mapa como função
(println (pedido :mochila))                                 ; se o mapa for nulo, retorna 'NullPointerException'

(println (get pedido :mochila))                             ; outra opção com 'get'
(println (get pedido :cadeira))                             ; retorna 'nil'
(println (get pedido :cadeira {}))                          ; 'get' com valor default (mapa nulo)

(println (:mochila pedido))                                 ; keyword pode ser invocada como função
(println (:cadeira pedido))                                 ; keyword implementa interface ifn
(println (:cadeira pedido {}))

(println (:quantidade (:mochila pedido)))

; Incrementando um subitem dentro do item
(println (update-in pedido [:mochila :quantidade] inc))     ; deve retornar 3

; PREFIX
; Chamada de dentro para fora (menos utilizado)
(println (:quantidade (:mochila pedido)))

; THREADING FIRST
; "Passar fio", "encadear"
; A ideia é "cascatear" como se fosse em orientação a objetos (inovacação de funções)
; Maneira mais utilizada -> melhor legibilidade
(println "Threading:" (-> pedido
                          :mochila
                          :quantidade))

; Outra possibilidade -> encadeando 'println'
(-> pedido
    :mochila ,,,
    :quantidade ,,,
    println ,,,)