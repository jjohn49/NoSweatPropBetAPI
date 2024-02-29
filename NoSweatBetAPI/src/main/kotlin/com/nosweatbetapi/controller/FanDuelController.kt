package com.nosweatbetapi.controller

import com.nosweatbetapi.model.SportsBookTeamBets
import com.nosweatbetapi.service.FanDuelService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("FanDuel")
class FanDuelController {

    val service: FanDuelService = FanDuelService()
    @GetMapping("league/nba/spread/current")
    fun getCurrentNBASpreads(): SportsBookTeamBets{
        return service.getCurrentNBASpreads()
    }

}