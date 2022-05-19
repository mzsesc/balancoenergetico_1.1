package com.ufs.balancoenergetico.db

data class dataPreparacaosolo(
    val implementos: String? = null,
    val maobra: String? = null,
    val oleodissel: String? = null,
    val lubrificante: String? = null,
    val trator: String? = null
)

data class dataSemeioeadubacao(
    val tipodesemente: String? = null,
    val fertilizantesintetico: String? = null,
    val materiaorganica: String? = null,
)

data class dataDesenvolvimentomilho(
    val fertilizante: String? = null,
    val inseticidas: String? = null,
    val pesticidas: String? = null,
    val nitrogenio: String? = null
)

data class datacolheita(
    val colheitadera: String? = null,
    val maobra: String? = null,
    val ensiladeira: String? = null
)
