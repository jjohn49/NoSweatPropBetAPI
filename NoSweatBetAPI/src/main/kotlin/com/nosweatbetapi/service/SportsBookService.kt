package com.nosweatbetapi.service

import com.nosweatbetapi.model.SportsBookTeamBets

interface SportsBookService {
    val name: String

    fun getCurrentNBASpreads():SportsBookTeamBets
    fun getCurrentNBAOverUnders():SportsBookTeamBets

    fun getCurrentNHLSpreads():SportsBookTeamBets

    fun getCurrentNCAAMSpreads():SportsBookTeamBets
}