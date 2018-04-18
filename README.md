Trabalho 1 - ALEST2 2018/1

Para compilar e executar os casos de teste, execute o script run.sh (Linux) ou run.bat (Windows). É necessário ter o Oracle JDK instalado e incluído no PATH.

Enunciado:

Você está prestando assessoria a pintores cubistas que estão enfrentando um problema inesperado
depois de terem programado um sistema de pintura usando IA. O sistema até que pinta
bem, mas surgiu um problema devido à sobreposição de retângulos nas pinturas (os pintores
são péssimos programadores) e alguns retângulos ficam parcialmente (ou até totalmente) encobertos.
Isso conduz a um excesso de gastos com tinta e a situação econômica não permite
esses luxos. Sobrou pra você escrever um algoritmo que faça a previsão correta dos gastos com
tinta, recebendo os seguintes dados:

• Um inteiro 1 ≤ n ≤ 50000.

• Uma lista contendo coordenadas inteiras x1, y1, x2, y2 determinando a posição de n retângulos
R1, R2, . . . , Rn.

• Cada retângulo também tem uma cor associada a ele e assume-se que os retângulos são
pintados na ordem dada, sobrepondo-se livremente.

Seu algoritmo deve determinar quanta área será pintada de cada cor se os retângulos Ri
forem pintados. Os artistas precisam da área exata para poder calcular a quantidade de tinta e
baratear custos.

