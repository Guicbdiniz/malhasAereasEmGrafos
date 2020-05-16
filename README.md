# malhasAereasEmGrafos
Trabalho final da matéria "Algorítmos Computacionis em Grafos" do semestre 2020/01 do curso Engenharia de Software na PUC Minas.

**Integrantes**: Guilherme Diniz, Leonardo Antunes e Tulio Soares

Enunciado
======

### Roteiro:
A malha aérea de uma região pode ser representada por um grafo, no qual os vértices
são os aeroportos dessa região. Um grafo não-dirigido pode ser utilizado para modelar as
diversas rotas; enquanto um grafo dirigido pode ser usado para representar os diversos
voos. Uma rota possui vários voos. Por exemplo, há voos de Confins para Salvador na
parte da manhã, da tarde e da noite.
Pretende-se, nesse projeto, desenvolver uma aplicação que receba como entrada um
conjunto de dados acerca dessa malha aérea e responda vários tipos de perguntas
detalhadas a seguir.

### Tarefas:
* Criar uma estrutura de dados que seja capaz de suportar os dois grafos: o de
rotas e o de voos, os quais partilham os vértices, que são os aeroportos da região
modelada. O grafo que representa as rotas deve ter apenas uma aresta
conectando cada par (origem, destino) de aeroportos atendidos; enquanto o grafo
de voos deve ter apenas uma aresta conectando cada par ordenado (origem,
destino) de aeroportos. Existem vários pesos associáveis às arestas: distância,
duração do voo, horários dos voos (apenas para o grafo que representa os
diversos voos), etc.
* Dados uma origem e um destino, desenvolver um algoritmo que determine a
viagem com menor custo em termos de: número de conexões, distância
total percorrida, tempo total de voo, duração total da viagem
(considerando-se que pode haver esperas nas conexões. Nesse caso, utilize o
primeiro horário de voo possível).
* Desenvolver um algoritmo que determine se é possível, para todos os
aeroportos da região, a partir de um aeroporto atingir qualquer outro
(com ou sem escalas). Se isso não for possível, indique os conjuntos de
aeroportos que, separadamente, atendem essa condição. Se isso for possível,
indique quais os aeroportos que, se ficassem fora de serviço (apenas um de cada
vez), impediriam essa situação para o conjunto de aeroportos em operação
restante.
* Suponhamos que seja preciso chegar ao aeroporto B para uma reunião
importante à hora H. Desenvolver um algoritmo que determine o último voo
em que se pode sair do aeroporto de origem A sem chegar atrasado ao destino.
* Suponhamos que você pretenda montar uma empresa de carga aérea, com uma
frota na qual cada aeronave fará voos de ida e volta apenas entre dois
aeroportos. Quantas aeronaves, no mínimo, serão necessárias e quais rotas
serão efetivamente usadas, se o objetivo é atender todos os aeroportos, com
um consumo total mínimo (não é importante o tempo total que uma
encomenda demorará para chegar ao destino, mas apenas garantir que há uma
rota até esse destino).

### Formato do Arquivo de Entrada:
Para a realização dos testes e avaliação do código desenvolvido, será fornecido um
arquivo texto de entrada que apresentará, na primeira linha, o número de aeroportos
da região. As linhas seguintes desse arquivo de entrada conterão, cada uma, as seguintes
informações, no seguinte formato: nome do aeroporto 1; nome do aeroporto 2;
direção do voo; distância entre os aeroportos, em quilômetros; duração do voo;
horários de partida dos voos. Se o valor do parâmetro direção do voo for 1, esse
voo é direcionado do aeroporto 1 para o aeroporto 2. Se o valor desse parâmetro for -1, o
voo tem a direção contrária, sendo direcionado, portanto, do aeroporto 2 para o
aeroporto 1.
Segue um exemplo de arquivo de entrada:
```
3
CONFINS; GUARULHOS; 1; 606; 1:16; 7:00; 9:00; 18:00
CONFINS; GUARULHOS; -1; 606; 1:16; 8:00; 12:00; 17:00; 21:00
CONFINS; SANTOS DUMONT; 1; 480; 1:10; 7:30
GUARULHOS; SANTOS DUMONT; 1; 421; 0:30; 8:00; 18:00
```
Assuma que existem voos para todas as rotas já autorizadas.
Seu grupo deve criar seus próprios arquivos de entrada para testes, mas eles devem
seguir o formato especificado acima, pois será executado o código implementado com os
arquivos de teste (nesse formato) durante a correção desse trabalho prático.

### Código:
Todo o código deve ser desenvolvido na linguagem de programação Java.
* Os alunos devem comentar todos os métodos implementados.
* O código deverá ser desenvolvido observando-se o formato de entrada
especificado.
* As estruturas de dados utilizadas devem ser alocadas dinamicamente e o código
deve ser modularizado.
* Legibilidade e boas práticas de programação serão avaliadas.

### Documentação:
Deve também ser entregue uma documentação do projeto, que não deve exceder dez
páginas e deve conter pelo menos os seguintes itens:
* Uma introdução do problema em questão.
* Modelagem e solução proposta para os problemas apresentados. O algoritmo
utilizado para a resolução de cada problema proposto deve ser explicado de forma
clara, possivelmente através de pseudocódigo e esquemas ilustrativos.
* Uma breve conclusão do trabalho implementado.
