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

- **src** — contem todos os arquivos `.java` do projeto
- **lib** — pasta reservada para dependencias externas (nao utilizada neste projeto)
- **bin** — arquivos `.class` gerados apos a compilacao

# Como Compilar o Projeto

No diretorio raiz do projeto, execute:

```bash
javac -d bin $(find src -name "*.java")
```

Esse comando compila todos os arquivos `.java` dentro da pasta `src` e coloca os arquivos compilados (`.class`) na pasta `bin`.

# Como Executar o Projeto

Apos compilar, execute:

```bash
java -cp bin App
```

Isso iniciará o programa e o combate sera executado no terminal.

# Como Jogar

Durante o combate:

- O jogador controla um **heroi**
- O jogador usa cartas de **dano** e **escudo**
- As cartas afetam os atributos de combate entre heroi e inimigos
- As açõess acontecem em **turnos**

O combate termina quando:

- o **heroi e derrotado**, ou
- o **inimigo é derrotado**

# Tecnologias Utilizadas

- Java
- Visual Studio Code
- Git e GitHub

# Autores

Projeto desenvolvido por:

- Gabriel Dias Bastos - 257756
- Lucas Silva Bueno - 194572
