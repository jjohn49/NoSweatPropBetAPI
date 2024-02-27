package com.nosweatbetapi.service


import com.nosweatbetapi.model.SportsBookSpreadBets
import com.nosweatbetapi.model.SpreadBet
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Service
import java.time.Duration
import org.openqa.selenium.support.FindBy


@Service
class DraftKingsService {

    fun main() {
        val driverLocation: String = "NoSweatBetAPI/src/main/resources/chromedriver"
        System.setProperty("webdriver.chrome.driver", driverLocation)
    }

    fun getCurrentNBASpreads(): SportsBookSpreadBets{
        val url = "https://sportsbook.draftkings.com/leagues/basketball/nba"
        val driver = ChromeDriver()
        driver.get(url)

        val SpreadBets: MutableList<String> = mutableListOf()
        val sportsbookTable = driver.findElement(By.className("sportsbook-table__body"))

        val teams = sportsbookTable.findElements(By.tagName("tr"))

        val spreadBets: MutableList<SpreadBet> = mutableListOf()
        for(i in teams.indices step 2){
            val team1 = teams[i].findElement(By.className("event-cell__name-text")).text
            val team1OddsText = teams[i].findElement(By.className("sportsbook-odds")).text
            val team1Odds: Int = if (team1OddsText.get(0).compareTo('-')==1) team1OddsText.removeRange(0,1).toInt() * -1 else team1OddsText.removeRange(0,1).toInt()

            val pointSpread = teams[i].findElement(By.className("sportsbook-outcome-cell__line")).text.removeRange(0,1).toFloat()


            val team2 = teams[i+1].findElement(By.className("event-cell__name-text")).text
            var team2OddsText = teams[i+1].findElement(By.className("sportsbook-odds")).text
            val team2Odds: Int = if (team2OddsText.get(0).compareTo('-')==1) team2OddsText.removeRange(0,1).toInt() * -1 else team2OddsText.removeRange(0,1).toInt()


            try{
                spreadBets.add(SpreadBet(if (team1Odds < team2Odds) team1 else team2, if (team1Odds < team2Odds) team2 else team1, pointSpread, if (team1Odds < team2Odds) team1Odds else team2Odds, if (team1Odds < team2Odds) team2Odds else team1Odds))

            }catch (e: Exception){
                println(team1)
                println(team2)
                println(team1Odds)
                println(team2Odds)
                println(pointSpread)
            }

        }

        driver.close()
        return SportsBookSpreadBets("DraftKings", spreadBets)
    }
}