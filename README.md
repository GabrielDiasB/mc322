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

O jogo acontece em uma jornada de 4 noites. Em cada ciclo, o herói se prepara durante o dia (explorando, coletando recursos, descansando e craftando cartas) e luta durante a noite contra um inimigo da fase. Na última noite, a batalha é contra o Ender Dragon.

No início de cada combate, o baralho é embaralhado e uma mão com 5 cartas é comprada. Durante o turno, o jogador gasta XP para usar cartas de dano e escudo ou encerrar a rodada.

Quando o turno termina, o inimigo executa uma ação de ataque, defesa, buff ou debuff. Em seguida, a mão vai para o descarte, novas cartas são compradas e, se a pilha acabar, o descarte é reciclado e embaralhado.

O objetivo é vencer as 4 batalhas. Se a vida do herói chegar a 0, a partida termina.

# Herança de Classes (Carta e Entidade)

- A classe `Carta` foi definida como abstrata e concentra os atributos comuns das cartas (nome, descrição e custo).
- As classes `CartaDano` e `CartaEscudo` herdam de `Carta` e implementam os métodos abstratos `usar(...)` e `usarTexto(...)` com comportamentos específicos.
- A classe `Entidade` também foi definida como abstrata para centralizar estado e comportamento compartilhado entre personagens (vida, escudo, atualização de barras e lógica base de dano).
- As classes `Heroi` e `Inimigo` herdam de `Entidade`, reutilizando essa base e especializando regras de combate conforme o papel de cada uma.

# Criação de Novos Inimigos

- A criação dos inimigos foi centralizada em `gerarInimigoDaFase`, no arquivo `App`, permitindo variar os adversários por fase.
- Cada inimigo é instanciado com nome, vida, escudo e um conjunto de ações de combate.
- A luta final foi separada em `criarBossFinal()`, com configuração própria para o boss `Ender Dragon`.
- A classe `Inimigo` recebe as ações via parâmetro (`AcaoInimigo...`).

# Jornada de Batalhas

- A jornada foi modelada em um loop principal de 4 etapas (dias/noites), controlado por `num_batalha`.
- A cada etapa, o herói se prepara durante o dia (exploração, gerenciamento de XP e crafting) e enfrenta inimigos durante a noite.
- A sequência de confrontos usa a estrutura `DequeInimigo`, com inserção no fim e remoção no início, seguindo a lógica de fila.
- O progresso da campanha inclui escalonamento de dificuldade e encerramento com batalha final no quarto ciclo.

# Ações Variadas para Cada Inimigo

- Cada ação de inimigo é representada por `AcaoInimigo`, com os atributos: nome, tipo (Ataque/Defesa/Buff/Debuff), valor, precisão e chance de escolha.
- A escolha da ação em cada turno usa sorteio ponderado pelas chances (`chanceEscolha`), implementado em `escolherAcao` na classe `Inimigo`.
- O sistema diferencia ataque, defesa, buff e debuff por `TipoAcaoInimigo` e aplica efeitos distintos no método `executarTurno`.
- A precisão da ação é tratada por sorteio percentual, permitindo erros de ataque.
- O inimigo também anuncia a próxima jogada com `anunciarProximaAcao`, permitindo que o jogador monte sua estratégia de forma dinâmica.
- A lógica de escudo máximo evita que ações defensivas sejam escolhidas quando o inimigo já está no limite de escudo.

# Sistema de Preparação Diurna (Exploração, Descanso e Craft)

- O ciclo de dia foi implementado com um menu de ações que consomem XP e impactam diretamente o combate da noite.
- Na opção de exploração, o herói gasta 1 XP para coletar recursos aleatórios (`madeira`, `ferro`, `diamante` e `lã`).
- O inventário permite visualizar recursos e cartas craftadas, além de fabricar novos equipamentos para o baralho.
- O crafting adiciona cartas novas ao `DequeHeroi`, com receitas específicas (por exemplo, armas de dano e itens defensivos).
- A opção de descanso consome 1 XP e recupera vida do herói, criando uma decisão estratégica entre fortalecer o baralho ou preservar HP.

# Sistema de Efeitos (Buffs, Debuffs e Eventos de Combate)

- Foi adicionado um sistema de efeitos contínuos com as classes `Efeito`, `EfeitoForca` e `EfeitoVeneno`.
- A classe `Combate` atua como mediadora de eventos e notifica assinantes (`Subscriber`) em momentos-chave do turno.
- A enumeração `EventoCombate` organiza os gatilhos de combate (como fim de turno e ataque), desacoplando regras de efeito da lógica principal.
- Entidades (`Heroi` e `Inimigo`) agora gerenciam uma lista de efeitos ativos, com acúmulo, redução por turno e remoção automática quando os acúmulos acabam.
- O status dos efeitos ativos é exibido em tempo real junto das barras de vida, escudo e XP.

# Cartas de Efeito e Novas Interações no Combate

- Além de cartas de dano e escudo, o baralho inicial passa a incluir cartas de efeito com a classe `CartaEfeito`.
- A carta `Poção de Força` aplica `Força` ao herói, aumentando o dano fixo em ataques subsequentes.
- A carta `Poção de Veneno` aplica `Veneno` ao inimigo, causando dano ao fim do turno e reduzindo gradualmente os acúmulos.
- Inimigos também podem usar ações de `Buff` (ganho de força) e `Debuff` (aplicação de veneno no herói), ampliando a profundidade tática do combate.

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
