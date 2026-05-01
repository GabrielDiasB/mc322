# Projeto MC322 - Jogo de Cartas Minecraft

Este projeto foi desenvolvido como parte dos laboratórios da disciplina **MC322 - Programação Orientada a Objetos**.

O objetivo é implementar um jogo de cartas por turnos no estilo *Roguelike* com o tema de **Minecraft**, no qual o jogador navega por um mapa ramificado, toma decisões de caminhos, utiliza cartas para batalhar, gerencia sua energia (XP) e cria novos itens a partir de recursos e drops de monstros.

O projeto foi desenvolvido em **Java** e executado via terminal, utilizando formatação ANSI para uma interface rica e colorida.

## Estrutura do Projeto

O projeto está organizado por pacotes em `src/main/java/jogo`:

```text
.
├─ src/
│  └─ main/
│     └─ java/
│        └─ jogo/
│           ├─ app/
│           │  └─ App.java
│           ├─ interfacejogo/
│           │  └─ Interface.java
│           ├─ servicos/
│           │  ├─ Craft.java
│           │  ├─ CriaInimigos.java
│           │  └─ Fluxocombate.java
│           ├─ entidades/
│           │  ├─ Entidade.java
│           │  ├─ Heroi.java
│           │  └─ Inimigo.java
│           ├─ cartas/
│           │  ├─ Carta.java
│           │  ├─ CartaDano.java
│           │  ├─ CartaEscudo.java
│           │  ├─ CartaEfeito.java
│           │  └─ DequeHeroi.java
│           ├─ efeitos/
│           │  ├─ Efeito.java
│           │  ├─ EfeitoForca.java
│           │  ├─ EfeitoVeneno.java
│           │  └─ EfeitoCura.java
│           ├─ mapa/
│           │  ├─ MapaCampanha.java
│           │  └─ NoMapa.java
│           ├─ eventos/
│           │  ├─ Evento.java
│           │  ├─ Batalha.java
│           │  ├─ Acampamento.java
│           │  ├─ ComandoAcampamento.java
│           │  └─ ComercianteVillager.java
│           └─ batalha/
│              ├─ AcaoInimigo.java
│              ├─ Combate.java
│              ├─ DequeInimigo.java
│              ├─ EventoCombate.java
│              ├─ResultadoAcaoInimigo.java
│              ├─ Subscriber.java
│              └─ TipoAcaoInimigo.java
├─ lib/
├─ bin/
└─ README.md


Onde:

- **src/main/java/jogo** — contém todos os arquivos `.java` organizados por domínio
- **lib** — pasta reservada para dependências externas (não utilizada neste projeto)
- **bin** — arquivos `.class` gerados após a compilação

# Como Compilar o Projeto

No diretório raiz do projeto, execute:

```bash
./gradlew build
```

Esse comando compila todos os arquivos.

# Como Executar o Projeto

Após compilar, execute:

```bash
./gradlew run
```

Isso iniciará o programa e o combate será executado no terminal.

# Como Jogar
O jogo segue uma progressão de mapa com múltiplos caminhos.
1.	Navegação: A cada avanço, o jogador deve escolher qual nó do mapa deseja visitar (Ex: Floresta vs. Caverna).
2.	Eventos: Os nós do mapa podem ser Batalhas contra inimigos, Acampamentos seguros ou encontros com um Comerciante Villager.
3.	Economia (XP e Drops): O XP de Turno atua como a energia vital do jogo. Ele é gasto tanto para jogar cartas em batalhas quanto para realizar explorações no acampamento e realizar trocas com o Villager. Derrotar monstros concede Drops exclusivos (Pólvora, Osso, Esmeralda) usados para cartas mais raras.
4.	Combate: No início de cada combate, uma mão de 5 cartas é comprada. O jogador gasta XP para usar habilidades defensivas e ofensivas. A intenção do inimigo é sempre visível, permitindo planejamento estratégico.
5.	Objetivo Final: Sobreviver às escolhas do mapa, melhorar o baralho na Mesa de Crafting e derrotar o Boss Final (Ender Dragon).

# Herança e Polimorfismo
• Entidades: A classe Entidade é abstrata e centraliza estado e comportamento compartilhado (vida, escudo, barra de status). Heroi e Inimigo herdam e especializam essa base.
• Cartas: A classe Carta é abstrata. Suas filhas (CartaDano, CartaEscudo, CartaEfeito) implementam o comportamento de uso de forma polimórfica.
• Eventos: A classe abstrata Evento define um contrato base iniciar(). Classes filhas (Batalha, Acampamento, ComercianteVillager) aplicam polimorfismo para que o motor do jogo (Fluxocombate) execute diferentes tipos de interação sem precisar saber qual evento específico está acontecendo.

# Padrões de Projeto Utilizados
• Command Pattern: Utilizado na interface do Acampamento. As ações (Explorar, Descansar, Craftar) são encapsuladas na interface ComandoAcampamento, permitindo uma estrutura de menu flexível e livre de estruturas condicionais longas.
• Observer/Pub-Sub: O sistema de Combate notifica assinantes (Subscriber) em momentos-chave (como fim de turno), garantindo que efeitos de dano contínuo (como Veneno) sejam aplicados de forma desacoplada.

# Mapa e Progressão Ramificada
• A jornada foi modelada utilizando uma estrutura de Grafos/Árvores nas classes NoMapa e MapaCampanha.
• Cada nó possui um Supplier<Evento>, criando os eventos (Inimigos, Fogueiras, Lojas) apenas no momento em que o jogador visita o local (Lazy Evaluation).
• Múltiplos caminhos forçam escolhas estratégicas: o jogador deve balancear o risco de ir para batalhas difíceis em busca de drops raros ou procurar caminhos com Acampamentos e Villagers para recuperação.

# Sistema de Baralho, Crafting e Drops
• Crafting: A Mesa de Crafting permite converter recursos genéricos (Madeira, Ferro, Lã, Diamante) em equipamentos base.
• Drops Monstruosos: Inimigos específicos concedem materiais únicos (Esqueletos dropam Ossos, Creepers dropam Pólvora, Evokers dropam Esmeraldas). Esses itens desbloqueiam receitas de cartas mais poderosas (como Espada de Osso ou Bomba de Pólvora).
• O baralho é encapsulado em DequeHeroi, possuindo uma lógica de deckbuilding contínua: cartas criadas ou compradas vão para o baralho e rotacionam junto com o descarte.

# Ações Variadas para Cada Inimigo
• Cada inimigo possui uma "IA" ditada por uma lista de AcaoInimigo (Ataque, Defesa, Buff, Debuff).
• A escolha da ação do inimigo usa um sorteio ponderado.
• A intenção de ataque do inimigo é fixada na interface antes do turno do herói, fornecendo previsibilidade e punindo a má gestão do escudo do jogador.

# Tecnologias Utilizadas
• Java (Orientação a Objetos)
• Gradle (Build System)
• Git e GitHub

# Autores
Projeto desenvolvido por:
• Gabriel Dias Bastos - 257756
• Lucas Silva Bueno - 194572
