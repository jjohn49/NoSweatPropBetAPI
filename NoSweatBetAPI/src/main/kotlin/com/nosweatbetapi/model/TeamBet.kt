package com.nosweatbetapi.model

enum class TeamBetType{
    Spread,
    OverUnder,
    MoneyLine
}
data class TeamBet(val favoredTeam:String, val underDog:String, val type: TeamBetType, val line: Float, val favoredOdds: Int, val underDogOdds: Int)
