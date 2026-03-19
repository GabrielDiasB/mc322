# Projeto MC322 - Jogo de Cartas Minecraft

Este projeto foi desenvolvido como parte dos laboratórios da disciplina **MC322 - Programação Orientada a Objetos**.

O objetivo é implementar o início de um jogo de cartas por turnos com tema de **Minecraft**, no qual o jogador utiliza cartas para atacar, se defender e derrotar inimigos.

O projeto foi desenvolvido em **Java** e executado via terminal.

# Estrutura do Projeto

O projeto segue a estrutura padrão criada pelo VS Code para projetos Java:

```text
.
├─ src/
│  ├─ App.java
│  ├─ Heroi.java
│  ├─ Inimigo.java
│  ├─ CartaDano.java
│  ├─ CartaEscudo.java
│  └─ ...
├─ lib/
├─ bin/
└─ README.md
```

Onde:

- **src** — contém todos os arquivos `.java` do projeto
- **lib** — pasta reservada para dependências externas (não utilizada neste projeto)
- **bin** — arquivos `.class` gerados após a compilação

# Como Compilar o Projeto

No diretório raiz do projeto, execute:

```bash
javac -d bin $(find src -name "*.java")
```

Esse comando compila todos os arquivos `.java` dentro da pasta `src` e coloca os arquivos compilados (`.class`) na pasta `bin`.

# Como Executar o Projeto

Após compilar, execute:

```bash
java -cp bin App
```

Isso iniciará o programa e o combate será executado no terminal.

# Como Jogar

O jogo acontece em uma jornada de 4 noites. Em cada ciclo, o herói se prepara durante o dia - a preparação do herói ainda está em desenvolvimento e deve ser terminada para a próxima tarefa - e luta durante a noite contra um inimigo da fase. Na última noite, a batalha é contra o Ender Dragon.

No início de cada combate, o baralho é embaralhado e uma mão com 5 cartas é comprada. Durante o turno, o jogador gasta XP para usar cartas de dano e escudo ou encerrar a rodada.

Quando o turno termina, o inimigo executa uma ação de ataque ou defesa. Em seguida, a mão vai para o descarte, novas cartas são compradas e, se a pilha acabar, o descarte é reciclado e embaralhado.

O objetivo é vencer as 4 batalhas. Se a vida do herói chegar a 0, a partida termina.

# Herança de Classes (Carta e Entidade)

- A classe `Carta` foi definida como abstrata e concentra os atributos comuns das cartas (nome, descrição e custo).
- As classes `CartaDano` e `CartaEscudo` herdam de `Carta` e implementam os métodos abstratos `usar(...)` e `usarTexto(...)` com comportamentos específicos.
- A classe `Entidade` também foi definida como abstrata para centralizar estado e comportamento compartilhado entre personagens (vida, escudo, atualização de barras e lógica base de dano).
- As classes `Heroi` e `Inimigo` herdam de `Entidade`, reutilizando essa base e especializando regras de combate conforme o papel de cada uma.

# Criação de Novos Inimigos

- A criação dos inimigos foi centralizada em `gerarInimigoDaFase`, no arquivo `App`, permitindo variar os adversários por fase.
- Cada inimigo é instanciado com nome, vida, escudo e três ações de combate.
- A luta final foi separada em `criarBossFinal()`, com configuração própria para o boss `Ender Dragon`.
- A classe `Inimigo` recebe as ações via parâmetro (`AcaoInimigo...`).

# Jornada de Batalhas

- A jornada foi modelada em um loop principal de 4 etapas (dias/noites), controlado por `num_batalha`.
- A cada etapa, o herói se prepara durante o dia (exploração e gerenciamento de XP) - essa etapa ainda está em desenvolvimento e deve ser concluída na próxima tarefa - e enfrenta inimigos durante a noite.
- A sequência de confrontos usa a estrutura `DequeInimigo`, com inserção no fim e remoção no início, seguindo a lógica de fila.
- O progresso da campanha inclui escalonamento de dificuldade e encerramento com batalha final no quarto ciclo.

# Ações Variadas para Cada Inimigo

- Cada ação de inimigo é representada por `AcaoInimigo`, com os atributos: nome, tipo (Ataque/Defesa), valor, precisão e chance de escolha.
- A escolha da ação em cada turno usa sorteio ponderado pelas chances (`chanceEscolha`), implementado em `escolherAcao` na classe `Inimigo`.
- O sistema diferencia ataque e defesa por `TipoAcaoInimigo` e aplica efeitos distintos no método `executarTurno`.
- A precisão da ação é tratada por sorteio percentual, permitindo erros de ataque.
- O inimigo também anuncia a próxima jogada com `anunciarProximaAcao`, permitindo que o jogador monte sua estratégia de forma dinâmica.

# Sistema de Baralho do Herói (Compra, Descarte e Uso)

- O baralho foi encapsulado em `DequeHeroi`, com quatro listas: coleção total (`cartas`), pilha de compra (`deque`), mão atual (`atual`) e descarte (`descarte`).
- No início da batalha, `inicializacao` copia as cartas para a pilha de compra e embaralha (`Collections.shuffle`).
- A compra inicial de mão ocorre em `comprarAtual`, que compra 5 cartas chamando `comprarCarta`.
- Ao usar uma carta (`usar`), a carta sai da mão e vai para o descarte, evitando reutilização no mesmo ciclo.
- Ao encerrar turno, `descartarAtual` move as cartas restantes da mão para o descarte e uma nova mão é comprada.
- Quando a pilha de compra esvazia, o descarte é reciclado para a pilha de compra e embaralhado novamente.

# Tecnologias Utilizadas

- Java
- Visual Studio Code
- Git e GitHub

# Autores

Projeto desenvolvido por:

- Gabriel Dias Bastos - 257756
- Lucas Silva Bueno - 194572
