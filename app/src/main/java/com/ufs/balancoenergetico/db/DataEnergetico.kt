package com.ufs.balancoenergetico.db


data class dataPreparacaosolo(
    val maodeobra: Double? = null,
    val oleodissel: Double? = null,
    val lubrificante: Double? = null,
    val trator: Double? = null,
    val graxa: Double? = null,
    val semeadora: Double? = null,
    val pulverizador: Double? = null,
    val gradagem: Double? = null,
    val colheitadera: Double? = null,
    val ensiladeira: Double? = null,
    val transporteforagem: Double? = null,
    val fungicida: Double? = null,
    val herbicida: Double? = null,
    val inseticida: Double? = null,
    val tipodesemente: Double? = null,
    val fertilizanteazotado: Double? = null,
    val fertilizantepotassico: Double? = null,
    val fertilizantefosfatado: Double? = null
    )

data class datasemeioadubacao(
    val tipodesemente: Double? = null,
    val fertilizanteazotado: Double? = null,
    val fertilizantepotassico: Double? = null,
    val fertilizantefosfatado: Double? = null
)

data class datacrescimentomilho(
    val fungicida: Double? = null,
    val herbicida: Double? = null,
    val inseticida: Double? = null
)

data class datacolheita(
    val colheitadera: Double? = null,
    val maodeobra: Double? = null,
    val ensiladeira: Double? = null,
    val transporteforagem: Double? = null
)

data class Datamilho(
    val produçaodomilho:Double? = null,
    val grao:Double? = null,
    val silagem:Double? = null,
)

data class DataUsuario(
    val usuario: String? = null,
)

data class Datasavesoma(
    val soma:String? = null,
)

data class DataBE(
    val Be:Double?= null,
    val Re:Double?= null,
    val Ee:Double?= null
    )