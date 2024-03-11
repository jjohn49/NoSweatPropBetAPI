package com.nosweatbetapi.service

import com.nosweatbetapi.model.GameBet
import com.nosweatbetapi.model.GameBetType
import com.nosweatbetapi.model.SportsBookTeamBets

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
//import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
//import org.openqa.selenium.firefox.FirefoxDriver
import java.time.Duration


class Bet365Service: SportsBookService {
    override val name: String
        get() = "Bet365"

    val NBA_URL = "https://www.nj.bet365.com/?_h=kTPmHjL5DwBru7SKcU_eAA%3D%3D#/AC/B18/C20604387/D48/E1453/F10/"

    fun getCurrentBets(url: String, type: GameBetType): MutableList<GameBet>{


        val chromeOptions: ChromeOptions = ChromeOptions()
        chromeOptions.addArguments("--window-size=1920,1080")
        chromeOptions.addArguments("--headless=new")

        val driver = ChromeDriver()
        driver.get(url)

        //Need to wait because MGM sucks and is slooooooowwwwwww
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12))

        val betTable = driver.findElement(By.className("gl-MarketGroupContainer"))

        val bets = scrapeBets(betTable, type)

        driver.close()

        return bets
    }

    private fun scrapeBets(betTable: WebElement, type: GameBetType): MutableList<GameBet> {
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

            try{
                val teamsInGame =
                    teams.get(i).findElements(By.className("scb-ParticipantFixtureDetailsHigherBasketball_Team"))

                //If moneyline this should be empty
                val lineInGame =  spreads.get(i).findElements(By.className("sac-ParticipantCenteredStacked50OTB_Handicap"))
                val odds = spreads.get(i).findElements(By.className("sac-ParticipantCenteredStacked50OTB_Odds"))

                if(type == GameBetType.Spread){
                    bets.add(GameBet(name, teams.get(0).text, teams.get(1).text, true, type, lineInGame.get(0).text.removeRange(0,1).toFloat()))
                }else if (type == GameBetType.OverUnder){
                    val overOdds = if(odds.get(0).text.get(0)=='-') odds.get(0).text.removeRange(0,1).toInt() * -1 else odds.get(0).text.removeRange(0,1).toInt()
                    val underOdds = if(odds.get(1).text.get(1)=='-') odds.get(1).text.removeRange(0,1).toInt() * -1 else odds.get(1).text.removeRange(0,1).toInt()
                    bets.add(GameBet(name, teams.get(0).text, teams.get(1).text,false,type, lineInGame.get(0).text.removeRange(0,1).toFloat(), overOdds = overOdds, underOdds = underOdds))
                }else if(type == GameBetType.MoneyLine){
                    println("To Busy to do this rn")
                }
            }catch (e: Exception){
                println("Failed to scrape a bet.  Continuing to the Next.")
            }


        }

        return bets
    }


    override fun getCurrentNBASpreads(): SportsBookTeamBets {
        return SportsBookTeamBets(name, this.getCurrentBets(NBA_URL, GameBetType.Spread))
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