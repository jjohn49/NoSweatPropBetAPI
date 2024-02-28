package com.nosweatbetapi.service


import com.nosweatbetapi.model.SportsBookTeamBets
import com.nosweatbetapi.model.TeamBet
import com.nosweatbetapi.model.TeamBetType
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.stereotype.Service


@Service
class DraftKingsService : SportsBookService{

    override val name: String
        get() = "DraftKings"

    val nbaUrl: String = "https://sportsbook.draftkings.com/leagues/basketball/nba"

    private fun getCurrentBets(url: String, type: TeamBetType): SportsBookTeamBets{
        val driver: ChromeDriver = ChromeDriver()
        driver.get(url)

        val SpreadBets: MutableList<String> = mutableListOf()
        val sportsbookTable = driver.findElement(By.className("sportsbook-table__body"))

        val teams = sportsbookTable.findElements(By.tagName("tr"))

        val bets: MutableList<TeamBet> = scrapeBets(type, teams)

        driver.close()
        return SportsBookTeamBets(name, bets)
    }

    private fun scrapeBets(type: TeamBetType,teams: MutableList<WebElement>): MutableList<TeamBet> {
        val position: Int = type.ordinal
        val bets: MutableList<TeamBet> = mutableListOf()
        for (i in teams.indices step 2) {
            val team1: String = teams[i].findElement(By.className("event-cell__name-text")).text
            val team1OddsText: String = teams[i].findElements(By.className("sportsbook-odds")).get(position).text
            val team1Odds: Int = if (team1OddsText.get(0).compareTo('-') == 1) team1OddsText.removeRange(0, 1).toInt() * -1 else team1OddsText.removeRange(0, 1).toInt()

            val pointSpreadText: String = if(type != TeamBetType.MoneyLine) teams[i].findElements(By.className("sportsbook-outcome-cell__line")).get(position).text else "pk"

            val pointSpread: Float
            if(type != TeamBetType.OverUnder)
                pointSpread = if (pointSpreadText != "pk") pointSpreadText.removeRange(0, 1).toFloat() else 0f
            else{
                pointSpread = pointSpreadText.toFloat()
            }

            val isTeam1Favorite: Boolean = pointSpreadText.get(0) == '-'
            val team2: String = teams[i + 1].findElement(By.className("event-cell__name-text")).text
            var team2OddsText: String = teams[i + 1].findElements(By.className("sportsbook-odds")).get(position).text
            val team2Odds: Int = if (team2OddsText.get(0).compareTo('-') == 1) team2OddsText.removeRange(0, 1).toInt() * -1 else team2OddsText.removeRange(0, 1).toInt()

            try {
                if(type!=TeamBetType.OverUnder){
                    bets.add(TeamBet(if (isTeam1Favorite) team1 else team2, if (isTeam1Favorite) team2 else team1, type, pointSpread, if (isTeam1Favorite) team1Odds else team2Odds, if (isTeam1Favorite) team2Odds else team1Odds))
                }else{
                    bets.add(TeamBet(team1, team2, type, pointSpread, overOdds = team1Odds, underOdds = team2Odds))
                }


            } catch (e: Exception) {
                println(team1)
                println(team2)
                println(team1Odds)
                println(team2Odds)
                println(pointSpread)
                println(e.localizedMessage)
            }

        }
        return bets
    }

    override fun getCurrentNBASpreads(): SportsBookTeamBets{
        return this.getCurrentBets(nbaUrl, TeamBetType.Spread)
    }
    override fun getCurrentNBAOverUnders(): SportsBookTeamBets {
        return this.getCurrentBets(nbaUrl, TeamBetType.OverUnder)
    }

    override fun getCurrentNBAMoneyLines(): SportsBookTeamBets {
        return this.getCurrentBets(nbaUrl, TeamBetType.MoneyLine)
    }
    override fun getCurrentNHLSpreads(): SportsBookTeamBets{
        return this.getCurrentBets("https://sportsbook.draftkings.com/leagues/hockey/nhl",TeamBetType.Spread)
    }
    override fun getCurrentNCAAMSpreads():SportsBookTeamBets{
        return this.getCurrentBets("https://sportsbook.draftkings.com/leagues/basketball/ncaab",TeamBetType.Spread)
    }
}