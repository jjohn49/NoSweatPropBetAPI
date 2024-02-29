package com.nosweatbetapi.model

enum class TeamBetType{
    Spread,
    OverUnder,
    MoneyLine
}
data class TeamBet(val team1:String, val team2:String,val isTeam1Favored: Boolean? = null, val type: TeamBetType, val line: Float, val team1Odds: Int? = null, val team2Odds: Int? = null, val overOdds: Int? = null, val underOdds: Int?=null)
