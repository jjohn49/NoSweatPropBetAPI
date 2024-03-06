package com.nosweatbetapi.model

enum class GameBetType{
    Spread,
    OverUnder,
    MoneyLine
}
data class GameBet(val sportsBook: String, val team1:String, val team2:String, val isTeam1Favored: Boolean? = null, val type: GameBetType, val line: Float, val team1Odds: Int? = null, val team2Odds: Int? = null, val overOdds: Int? = null, val underOdds: Int?=null, val time: java.util.Date = java.util.Date())
