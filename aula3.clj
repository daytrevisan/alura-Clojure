(ns curso.aula3)

(defn valor-descontado
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
    (- valor-bruto desconto))
  valor-bruto))

(println (valor-descontado 1000))
(println (valor-descontado 100))



; PREDICATE -> quando se devolve true ou false, coloca-se '?'
(defn deve-aplicar-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))

(println (deve-aplicar-desconto? 1000))
(println (deve-aplicar-desconto? 100))


(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))



; Aridade é o número de parâmetros -> ex 'aplica-desconto tem aridade 1
(defn valor-descontado
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))
(println (valor-descontado 100))



; Excluindo false (retorno como 'nil')
(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando a versão redefinida")
  (if (> valor-bruto 100)
    true))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))
(println (valor-descontado 1000))
(println (valor-descontado 100))


; WHEN
; Testando com 'when'
; Só existe o caso verdadeiro
(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando a versão when")
  (when (> valor-bruto 100)
    true))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))
(println (valor-descontado 1000))
(println (valor-descontado 100))



; Chamando a versão direta
(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando a versão > (direta)")
  (> valor-bruto 100))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))
(println (valor-descontado 1000))
(println (valor-descontado 100))



; Limpando o código
(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))
(println (valor-descontado 1000))
(println (valor-descontado 100))



(defn mais-caro-que-100?
  [valor-bruto]
  (println "Deixando claro invocação de mais-caro-que-100?")
  (> valor-bruto 100))

; Aplicando uma condição de aplicação
; Alteramos a aridade para 2 -> precisamos passar 2 parâmetros no println'
(defn valor-descontado
  "Retorna o valor bruto de 10% se deve aplicar desconto"
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))


; FUNÇÃO COMO PARÂMETRO

(println "Função como parâmetro")
(println (valor-descontado mais-caro-que-100? 1000))
(println (valor-descontado mais-caro-que-100? 100))



; Características Programação Funcional
; HIGHER ORDER FUNCTIONS
; IMUTABILIDADE



; Possibilidade de sintetizar ainda mais a função -> mas que afeta a legibilidade
(defn mais-caro-que-100? [valor-bruto] (> valor-bruto 100))


; FUNÇÕES ANÔNIMAS

; Mesma lógica, mas em uma função anônima (sem nome) -> pode ser chamado 'lambda'
; Declaração inline -> menor, porém mais complexa
(println "Função anônima")
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 1000))
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 100))

; Com nome do argumento 'valor-bruto' abreviado para 'v'
(println "Com argumento abreviado")
(println (valor-descontado (fn [v] (> v 100)) 1000))
(println (valor-descontado (fn [v] (> v 100)) 100))

; FUNÇÕES LAMBDA

; Sem declaração de função nem de argumentos
; '%1' representa 1 parâmetro
(println "Sem função com apenas 1 parâmetro")
(println (valor-descontado #(> %1 100) 1000))
(println (valor-descontado #(> %1 100) 100))

; Sem parâmetros
(println "Sem função e sem parâmetro")
(println (valor-descontado #(> % 100) 1000))
(println (valor-descontado #(> % 100) 100))




; Redefinindo como símbolo ("variável")
(def mais-caro-que-100? (fn [valor-bruto] (> valor-bruto 100)))
(def mais-caro-que-100? #(> % 100))
(println "Redefinindo como apenas símbolo")
(println (valor-descontado mais-caro-que-100? 1000))
(println (valor-descontado mais-caro-que-100? 100))