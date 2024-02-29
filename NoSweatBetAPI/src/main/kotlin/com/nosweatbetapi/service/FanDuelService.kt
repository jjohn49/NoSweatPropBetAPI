package com.nosweatbetapi.service

import com.nosweatbetapi.model.SportsBookTeamBets
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

class FanDuelService: SportsBookService {

    override val name: String
        get() = "FanDuel"

    val NBA_URL = "https://pa.sportsbook.fanduel.com/navigation/nba"


    override fun getCurrentNBASpreads(): SportsBookTeamBets {
        val driver: ChromeDriver = ChromeDriver()
        driver.get(NBA_URL)

        val sportsbookTable = driver.findElement(By.tagName("ul")).text





        //driver.close()

        return SportsBookTeamBets("", mutableListOf())
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