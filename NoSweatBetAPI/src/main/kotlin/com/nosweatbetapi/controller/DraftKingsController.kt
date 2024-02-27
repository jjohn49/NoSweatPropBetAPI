package com.nosweatbetapi.controller

import com.nosweatbetapi.model.SportsBookSpreadBets
import com.nosweatbetapi.model.SpreadBet
import com.nosweatbetapi.service.DraftKingsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( "DraftKings")
class DraftKingsController {

    val service: DraftKingsService = DraftKingsService()

    @GetMapping("current/nba/spreads")
    fun currentNBASpreads(): SportsBookSpreadBets{
        return service.getCurrentNBASpreads()
    }

}