package jogo.batalha;


/**
 * Eventos disparados durante o fluxo do combate para acionar efeitos e reações.
 */
public enum EventoCombate {
    /** Evento disparado no fim do turno do jogador. */
    FIM_TURNO_JOGADOR,
    /** Evento disparado no início do turno do inimigo. */
    INICIO_TURNO_INIMIGO,
    /** Evento disparado no fim do turno do inimigo. */
    FIM_TURNO_INIMIGO,
    /** Evento disparado quando o jogador executa um ataque. */
    ATAQUE_JOGADOR,
    /** Evento disparado quando o inimigo executa um ataque. */
    ATAQUE_INIMIGO,
    /** Evento disparado quando uma entidade recebe dano. */
    RECEBER_DANO
}
