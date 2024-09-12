(ns curso.aula4)

(def precos [30 700 1000])

; Imprimindo o primeiro elemento do vetor
(println "Posição 0: " (precos 0))

; Passando uma posição que não existe
; Error: IndexOutOfBoundsException -> mesmo erro do Java
;(println (precos 17))

; Outra opção para acessar dados da lista, utilizando 'get'
; GET -> p/ evitar exceptions
(println "Posição 0 com get: " (get precos 0))
(println "Posição 2 com get: " (get precos 2))

; Posição inexistente com 'get'
; Aqui daria o mesmo erro, mas com 'get' existe um tratamento de erro
; Retorno como 'nul'
(println "valor padrão nulo: " (get precos 17))

; Aqui indicamos o valor de saída para posição inexistente
(println "valor padrão zero p/ posição inexistente: " (get precos 17 0))

; Indicando zero como padrão, mas existe a posição
(println "valor padrão zero, mas existe a posição: " (get precos 2 0))

; Para adicionar um elemento ao final do vetor (não altera vetor original)
(println (conj precos 5))
(println precos)

; Porém, desta forma não persiste, é necessário redefinir o vetor
; Isso ocorre devido à imutabilidade (persistência) do Clojure

; Para somar 1 a um número -> c/ sinal de operação '+'
(println (+ 5 1))

; Soma 1, utilizando a função 'increase' (inc)
(println (inc 5))

; Somando 1 ao primeiro elemento do vetor
; Atualizo a posição 0 do vetor 'precos' incrementando (em 1)
(println (update precos 0 inc))

; Mesma lógica para 'decrement' (dec)
; Atualizo a posição 1 do vetor 'precos' decrementando (em 1)
(println (update precos 1 dec))

; Vetor original permanece imutável
(println precos)

(defn soma-1
  [valor]
  (println "Estou somando 1 em " valor)
  (+ valor 1))

(println (update precos 0 soma-1))

(defn soma-3
  [valor]
  (println "Estou somando 3 em " valor)
  (+ valor 3))

(println (update precos 0 soma-3))


; Código da Aula Anterior

(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
    (- valor-bruto desconto))
  valor-bruto))

(println valor-descontado 1000)

; Função MAP
; Para aplicar desconto a todos os elementos do vetor
; "Mapeie o valor descontado para todos os preços"
(println "Map desconto?: " (map valor-descontado precos))

; Função Range
(println "Lista 1 até 10: " (range 10))

; Função FILTER
; Filtra os números pares do range
(println "Filter even: " (filter even? (range 10)))

(println "Vetor inteiro: " precos)
(println "Filtra os descontados" (filter aplica-desconto? precos)) ; filtra somente true

(println "Map após o filter: " (map valor-descontado (filter aplica-desconto? precos)))

; Função REDUCE
; Retorna apenas um valor (conforme indicação do sinal operador)
(println "Total:" (reduce + precos))
(println "Subtração:" (reduce - precos))

(defn minha-soma
  [valor-1 valor-2]
  (println "Somando" valor-1 "e" valor-2)
  (+ valor-1 valor-2))

; Reduce retorna a soma dos dois primeiros valores do vetor
(println "Resultado:" (reduce minha-soma precos))

; Somando os números de 1 a 10
; Pega o resultado da função com o próximo elemento
(println (reduce minha-soma (range 10)))

; Se o vetor só tem um elemento, retorna o elemento, não chama a função (não tem com quem somar)
(println "Retorna elemento:" (reduce minha-soma [15]))

; Passando um valor inicial na função
; Soma o valor inicial com posição 0 do vetor, resultado soma com posição 1 e assim sucessivamente
(println "---------------------------")
(println "Somando elementos do vetor a partir do zero: ")
(println "Total vetor:" (reduce minha-soma 0 precos))

; Passando um valor inicial no Range
(println "---------------------------")
(println "Somando entre 5 e 15:")
(println "Total range:" (reduce minha-soma 5 (range 15)))

; Chamando a função 'minha-soma' -> soma 1 ao vetor com um único elemento
(println "---------------------------")
(println "Total c/ único elemento:" (reduce minha-soma 1 [15]))

; Testando com vetor vazio -> retorna apenas o elemento
(println "---------------------------")
(println "Vetor vazio:" (reduce minha-soma 1 []))

; Vetor vazio sem passar nenhum elemento para somar
; Retorna erro
;(println (reduce minha-soma []))

; (def vazio [])
; (println (reduce minha-soma vazio))