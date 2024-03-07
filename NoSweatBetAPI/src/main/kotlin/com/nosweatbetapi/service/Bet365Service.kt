package com.nosweatbetapi.service

import com.nosweatbetapi.model.GameBet
import com.nosweatbetapi.model.GameBetType
import com.nosweatbetapi.model.SportsBookTeamBets
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

class Bet365Service: SportsBookService {
    override val name: String
        get() = "Bet365"

    fun getCurrentBets(url: String, type: GameBetType): MutableList<GameBet>{
        val bets: MutableList<GameBet> = mutableListOf()
        val driver: ChromeDriver = ChromeDriver()
        driver.get(url)

        val betTable = driver.findElement(By.className("gl-MarketGroupContainer"))

        scrapeBets(betTable, type)



        driver.close()

        return bets
    }

    private fun scrapeBets(betTable: WebElement, type: GameBetType) {
        val bets: MutableList<GameBet> = mutableListOf()
        val position:Int = type.ordinal
        val teamsCol = betTable.findElement(By.className("sgl-MarketFixtureDetailsLabel"))
        val propBetCols = betTable.findElements(By.className("sgl-MarketOddsExpand"))


        val chosenPropBet = propBetCols.get(position)
        val spreadCol = propBetCols.get(0)
        val overUnderCol = propBetCols.get(1)
        val moneylineCol = propBetCols.get(2)

        val teams = teamsCol.findElements(By.className("rcl-MarketCouponAdvancedBase_Divider"))
        val spreads = spreadCol.findElements(By.className("sac-ParticipantCenteredStacked50OTB"))

        for (i in 0..<teams.count()) {
            val teamsInGame =
                teams.get(i).findElements(By.className("scb-ParticipantFixtureDetailsHigherBasketball_Team"))

            //If moneyline this should be empty
            val lineInGame =  spreads.get(i).findElements(By.className("sac-ParticipantCenteredStacked50OTB_Handicap"))
            val odds = spreads.get(i).findElements(By.className("sac-ParticipantCenteredStacked50OTB_Odds"))

            if(type == GameBetType.Spread){
                bets.add(GameBet(name, teams.get(0).text, teams.get(1).text, true, type, lineInGame.get(0).text.removeRange(0,1).toFloat()))
            }

        }
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