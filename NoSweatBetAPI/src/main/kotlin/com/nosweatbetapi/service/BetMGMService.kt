package com.nosweatbetapi.service

import com.nosweatbetapi.model.SportsBookTeamBets
import com.nosweatbetapi.model.TeamBet
import com.nosweatbetapi.model.TeamBetType
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration


class BetMGMService: SportsBookService {

    override val name: String
        get() = "BetMGM"

    val NBA_URL = "https://sports.pa.betmgm.com/en/sports/basketball-7/betting/usa-9/nba-6004"
    val NHL_URL = "https://sports.pa.betmgm.com/en/sports/hockey-12/betting/usa-9/nhl-34"
    val NCAAM_URL = "https://sports.pa.betmgm.com/en/sports/basketball-7/betting/usa-9/college-264"

    private fun getCurrentBets(url:String, type:TeamBetType): SportsBookTeamBets{
        val driver: ChromeDriver = ChromeDriver()
        driver.get(url)

        //Need to wait because MGM sucks and is slooooooowwwwwww
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7))

        //the table we need
        val sportsbookTable = driver.findElement(By.className("six-pack-groups"))

        val games = sportsbookTable.findElements(By.tagName("ms-six-pack-event"))

        val bets = scrapeBets(games, type)

        driver.close()

        return SportsBookTeamBets(name, bets)
    }

    private fun scrapeBets(games: MutableList<WebElement>, type: TeamBetType): MutableList<TeamBet>{
        val bets: MutableList<TeamBet> = mutableListOf()
        val position = type.ordinal
        for(i in games){
            val teams = i.findElements(By.className("participant"))
            val team1 = teams[0].text
            val team2 = teams[1].text


            if(type != TeamBetType.MoneyLine){
                val line = i.findElements(By.tagName("ms-option-group")).get(position)
                val pointlineText = line.findElement(By.className("option-attribute")).text

                //TODO
                //For some reason the Team Odds when looking at the over Under is the same as the Spread
                //Need to Fix this
                val odds = i.findElements(By.className("option-value"))
                val team1OddsText = odds[0].text
                val team2OddsText = odds[1].text

                val team1Odds = if(team1OddsText.get(0)=='-') team1OddsText.removeRange(0,1).toInt() * -1 else team1OddsText.removeRange(0,1).toInt()
                val team2Odds = if(team2OddsText.get(0)=='-') team2OddsText.removeRange(0,1).toInt() * -1 else team2OddsText.removeRange(0,1).toInt()

                if(type == TeamBetType.Spread){
                    bets.add(TeamBet(team1, team2, isTeam1Favored = (pointlineText.get(0)=='-'),type = type, line =pointlineText.removeRange(0,1).toFloat(), team1Odds =  team1Odds, team2Odds = team2Odds, ))
                }else{
                    bets.add(TeamBet(team1,team2,type=type,line = pointlineText.removeRange(0,2).toFloat(), overOdds = team1Odds, underOdds = team2Odds))
                }
            }else{
                val odds = i.findElements(By.tagName("ms-font-resizer"))
                val team1OddsText = odds[5].text
                val team2OddsText = odds[6].text

                val team1Odds = if(team1OddsText.get(0)=='-') team1OddsText.removeRange(0,1).toInt() * -1 else team1OddsText.removeRange(0,1).toInt()
                val team2Odds = if(team2OddsText.get(0)=='-') team2OddsText.removeRange(0,1).toInt() * -1 else team2OddsText.removeRange(0,1).toInt()

                bets.add(TeamBet(team1, team2, (team1Odds< team2Odds), TeamBetType.MoneyLine, 0f, team1Odds = team1Odds, team2Odds = team2Odds))
            }

        }

        return bets
    }
    override fun getCurrentNBASpreads(): SportsBookTeamBets {
        return this.getCurrentBets(NBA_URL, TeamBetType.Spread)
    }

    override fun getCurrentNBAOverUnders(): SportsBookTeamBets {
        return this.getCurrentBets(NBA_URL, TeamBetType.OverUnder)
    }

    override fun getCurrentNBAMoneyLines(): SportsBookTeamBets {
        return this.getCurrentBets(NBA_URL, TeamBetType.MoneyLine)
    }

    override fun getCurrentNHLSpreads(): SportsBookTeamBets {
        return this.getCurrentBets(NHL_URL, TeamBetType.Spread)
    }

    override fun getCurrentNHLOverUnders(): SportsBookTeamBets {
        return this.getCurrentBets(NHL_URL, TeamBetType.OverUnder)
    }

    override fun getCurrentNHLMoneyLines(): SportsBookTeamBets {
        return this.getCurrentBets(NHL_URL, TeamBetType.MoneyLine)
    }

    override fun getCurrentNCAAMSpreads(): SportsBookTeamBets {
        return this.getCurrentBets(NCAAM_URL, TeamBetType.Spread)
    }

    override fun getCurrentNCAAMOverUnders(): SportsBookTeamBets {
        return this.getCurrentBets(NCAAM_URL, TeamBetType.OverUnder)
    }

    override fun getCurrentNCAAMMoneyLines(): SportsBookTeamBets {
        return this.getCurrentBets(NCAAM_URL, TeamBetType.MoneyLine)
    }

}