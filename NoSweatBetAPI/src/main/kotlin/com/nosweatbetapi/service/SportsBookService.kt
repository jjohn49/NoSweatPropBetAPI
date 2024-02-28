package com.nosweatbetapi.service

import com.nosweatbetapi.model.SportsBookTeamBets

interface SportsBookService {
    val name: String

    fun getCurrentNBASpreads():SportsBookTeamBets
    fun getCurrentNBAOverUnders():SportsBookTeamBets

    fun getCurrentNBAMoneyLines():SportsBookTeamBets

    fun getCurrentNHLSpreads():SportsBookTeamBets
    fun getCurrentNHLOverUnders():SportsBookTeamBets
    fun getCurrentNHLMoneyLines():SportsBookTeamBets


    fun getCurrentNCAAMSpreads():SportsBookTeamBets
    fun getCurrentNCAAMOverUnders():SportsBookTeamBets
    fun getCurrentNCAAMMoneyLines():SportsBookTeamBets
}