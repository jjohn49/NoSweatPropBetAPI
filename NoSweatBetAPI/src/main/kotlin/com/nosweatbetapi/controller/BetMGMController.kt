package com.nosweatbetapi.controller

import com.nosweatbetapi.model.SportsBookTeamBets
import com.nosweatbetapi.service.BetMGMService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("BetMGM")
class BetMGMController {

    val service: BetMGMService = BetMGMService()
    @GetMapping("league/nba/spread/current")
    fun getCurrentNBASpreads(): SportsBookTeamBets{
        return service.getCurrentNBASpreads()
    }

    @GetMapping("league/nba/overunder/current")
    fun getCurrentNBAOverUnder(): SportsBookTeamBets{
        return service.getCurrentNBAOverUnders()
    }

    @GetMapping("league/nba/moneyline/current")
    fun getCurrentNBAMoneyLine(): SportsBookTeamBets{
        return service.getCurrentNBAMoneyLines()
    }

    @GetMapping("league/nhl/spread/current")
    fun getCurrentNHLSpreads(): SportsBookTeamBets{
        return service.getCurrentNHLSpreads()
    }

    @GetMapping("league/nhl/overunder/current")
    fun getCurrentNHLOverUnder(): SportsBookTeamBets{
        return service.getCurrentNHLOverUnders()
    }

    @GetMapping("league/nhl/moneyline/current")
    fun getCurrentNHLMoneyLine(): SportsBookTeamBets{
        return service.getCurrentNHLMoneyLines()
    }

    @GetMapping("league/ncaam/spread/current")
    fun getCurrentNCAAMSpreads(): SportsBookTeamBets{
        return service.getCurrentNCAAMSpreads()
    }

    @GetMapping("league/ncaam/overunder/current")
    fun getCurrentNCAAMOverUnder(): SportsBookTeamBets{
        return service.getCurrentNCAAMOverUnders()
    }

    @GetMapping("league/ncaa/moneyline/current")
    fun getCurrentNCAAMMoneyLine(): SportsBookTeamBets{
        return service.getCurrentNCAAMMoneyLines()
    }

}