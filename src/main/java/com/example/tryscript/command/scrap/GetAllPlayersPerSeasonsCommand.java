package com.example.tryscript.command.scrap;

import com.example.tryscript.model.Player;
import com.example.tryscript.repository.PlayerRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllPlayersPerSeasonsCommand {
    @Autowired
    private PlayerRepository playerRepository;

    private String mainDomain = "https://www.basketball-reference.com";

    private List<Player> playerList;

    public void run(String season) {
        try {
            playerList = new ArrayList<>();

            Document document = Jsoup.connect(
                    mainDomain + "/leagues/NBA_"+ season +"_totals.html"
            )
                    .maxBodySize(0)
                    .get();

            Elements rows = document.getElementById("all_totals_stats").getElementsByTag("tr");

            for (Element row : rows) {
                if(!(row.getElementsByTag("th").first().text().equals("Rk"))) {
                    Elements columns = row.getElementsByTag("td");

                    Player player = Player.builder().season(season).build();

                    for (Element column : columns) {
                        player = setPlayerValue(player, column);
                    }

                    player.setId(player.getSeason() + player.getTeam() + player.getId());
                    playerList.add(player);
                }
            }

            playerRepository.saveAll(playerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Player setPlayerValue(Player player, Element element){
        switch (element.attr("data-stat")){
            case "player" :
                player.setId(element.getElementsByTag("a").first().attr("href"));
                player.setName(element.text());
                break;
            case "pos" :
                player.setPos(element.text());
                break;
            case "team_id" :
                player.setTeam(element.text());
                break;
            case "g" :
                player.setGp(Integer.parseInt(element.text()));
                break;
            case "mp" :
                player.setMp(Integer.parseInt(element.text()));
                break;
            case "fg" :
                player.setFg(Integer.parseInt(element.text()));
                break;
            case "fga" :
                player.setFga(Integer.parseInt(element.text()));
                break;
            case "fg3" :
                player.setFg3(Integer.parseInt(element.text()));
                break;
            case "fg3a" :
                player.setFg3a(Integer.parseInt(element.text()));
                break;
            case "fg2" :
                player.setFg2(Integer.parseInt(element.text()));
                break;
            case "fg2a" :
                player.setFg2a(Integer.parseInt(element.text()));
                break;
            case "ft" :
                player.setFt(Integer.parseInt(element.text()));
                break;
            case "fta" :
                player.setFta(Integer.parseInt(element.text()));
                break;
            case "orb" :
                player.setOrb(Integer.parseInt(element.text()));
                break;
            case "drb" :
                player.setDrb(Integer.parseInt(element.text()));
                break;
            case "trb" :
                player.setTrb(Integer.parseInt(element.text()));
                break;
            case "ast" :
                player.setAst(Integer.parseInt(element.text()));
                break;
            case "stl" :
                player.setStl(Integer.parseInt(element.text()));
                break;
            case "blk" :
                player.setBlk(Integer.parseInt(element.text()));
                break;
            case "tov" :
                player.setTov(Integer.parseInt(element.text()));
                break;
            case "pf" :
                player.setPf(Integer.parseInt(element.text()));
                break;
            case "pts" :
                player.setPts(Integer.parseInt(element.text()));
                break;
            default:
        }

        return player;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
