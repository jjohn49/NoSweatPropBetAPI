package com.nosweatbetapi.service

import com.nosweatbetapi.model.GameBet
import com.nosweatbetapi.model.GameBetType
import com.nosweatbetapi.model.SportsBookTeamBets
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

class ESPNBetService: SportsBookService {
    override val name: String
        get() = "ESPNBet"

    val NBA_URL: String = "https://espnbet.com/sport/basketball/organization/united-states/competition/ncaab/featured-page"

    fun getCurrentBets(url:String, type:GameBetType): MutableList<GameBet>{
        val bets: MutableList<GameBet> = mutableListOf()
         val driver: ChromeDriver = ChromeDriver()

        driver.get(url)

        val table = driver.findElement(By.className("space-y-4"))



        driver.close()

        return bets
    }

    fun scrapeBets(table: WebElement, type:GameBetType): MutableList<GameBet>{
        return mutableListOf()
    }

    override fun getCurrentNBASpreads(): SportsBookTeamBets {
        return SportsBookTeamBets(name,this.getCurrentBets(NBA_URL, GameBetType.Spread) )
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