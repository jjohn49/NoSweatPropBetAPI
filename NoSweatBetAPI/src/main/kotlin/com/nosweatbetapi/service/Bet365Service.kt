package com.nosweatbetapi.service

import com.nosweatbetapi.model.GameBet
import com.nosweatbetapi.model.GameBetType
import com.nosweatbetapi.model.SportsBookTeamBets
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

class Bet365Service: SportsBookService {
    override val name: String
        get() = "Bet365"

    fun getCurrentBets(url: String, type: GameBetType): MutableList<GameBet>{
        val bets: MutableList<GameBet> = mutableListOf()
        val driver: ChromeDriver = ChromeDriver()
        driver.get(url)

        val betTable = driver.findElement(By.className("gl-MarketGroupContainer"))

        val teamsCol = betTable.findElement(By.className("sgl-MarketFixtureDetailsLabel"))
        val propBetCols = betTable.findElements(By.className("sgl-MarketOddsExpand"))


        val spreadCol = propBetCols.get(0)
        val overUnderCol = propBetCols.get(1)
        val moneylineCol = propBetCols.get(2)

        val teams = teamsCol.findElements(By.className("rcl-MarketCouponAdvancedBase_Divider"))
        val spreads = spreadCol.findElements(By.className("sac-ParticipantCenteredStacked50OTB"))

        for(i in 0..<teams.count()){
            val teamsInGame = teams.get(i).findElements(By.className("scb-ParticipantFixtureDetailsHigherBasketball_Team"))
            val spreadsInGame = spreads.get(i).findElements(By.className(""))

        }



        driver.close()

        return bets
    }

    override fun getCurrentNBASpreads(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNBAOverUnders(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNBAMoneyLines(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNHLSpreads(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNHLOverUnders(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNHLMoneyLines(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNCAAMSpreads(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNCAAMOverUnders(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }

    override fun getCurrentNCAAMMoneyLines(): SportsBookTeamBets {
        TODO("Not yet implemented")
    }
}