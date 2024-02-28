package com.nosweatbetapi.controller

import com.nosweatbetapi.model.SportsBookTeamBets
import com.nosweatbetapi.service.DraftKingsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( "DraftKings")
class DraftKingsController {

    val service: DraftKingsService = DraftKingsService()

    @GetMapping("league/nba/spread/current")
    fun getCurrentNBASpreads(): SportsBookTeamBets{
        return service.getCurrentNBASpreads()
    }

    @GetMapping("league/nba/overunder/current")
    fun getCurrentNBAOverUnder(): SportsBookTeamBets{
        return service.getCurrentNBAOverUnders()
    }

    @GetMapping("league/nba/moneyline/current")
    fun getCurrentNBAMoneyLine():SportsBookTeamBets{
        return service.getCurrentNBAMoneyLines()
    }

    @GetMapping("league/nhl/spread/current")
    fun getCurrentNHLSpreads(): SportsBookTeamBets{
        return service.getCurrentNHLSpreads()
    }

    @GetMapping("league/ncaam/spread/current")
    fun getCurrentNCAAMSpreads():SportsBookTeamBets{
        return service.getCurrentNCAAMSpreads()
    }

}