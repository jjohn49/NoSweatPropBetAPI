package com.nosweatbetapi.model

data class SportsBookTeamBets(val sportsBookName: String = "None", val spreadBets: MutableList<TeamBet>)
