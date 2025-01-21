package vttp5_paf_day26l_songs.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import vttp5_paf_day26l_songs.model.SongObject;
import vttp5_paf_day26l_songs.repo.SongRepo;

@Controller
public class SongController {

    @Autowired
    private SongRepo songRepo;

    @GetMapping("")
    public String homePage(Model model) { 

        List<Integer> years = songRepo.getYears();
        years.sort(Comparator.reverseOrder());

        model.addAttribute("years", years);

        return "home";

    }

    @GetMapping("/test")
    public String test() {

        List<Document> results = songRepo.getAllSongsByYear(2000);

        for (Document d: results) {
            System.out.println(d.get("track_name")); 
            System.out.println(d.get("artist(s)_name")); 

            // System.out.println(d);
        }

        return "test";

    }

    // @GetMapping("/year/{year}")
    // public String getAllSongsFromYear(@PathVariable Integer year, Model model) { 

    //     List<Document> results = songRepo.getAllSongsByYear(year);
    //     List<SongObject> songObjects = new ArrayList<>(); 

    //     for (Document d: results) {

    //         SongObject so = new SongObject(); 

    //         String trackName = d.get("track_name").toString();
    //         String artistName = d.get("artist(s)_name").toString();

    //         so.setTrackName(trackName);
    //         so.setArtistName(artistName);

    //         songObjects.add(so);

    //     }

    //     model.addAttribute("year", year);
    //     model.addAttribute("songObjects", songObjects);

    //     return "songs";

    // }

    @GetMapping("/songs")
    public String getAllSongsFromYear(@RequestParam("year") Integer year, Model model) { 

        List<Document> results = songRepo.getAllSongsByYear(year);
        List<SongObject> songObjects = new ArrayList<>(); 

        for (Document d: results) {

            SongObject so = new SongObject(); 

            String trackName = d.get("track_name").toString();
            String artistName = d.get("artist(s)_name").toString();

            so.setTrackName(trackName);
            so.setArtistName(artistName);

            songObjects.add(so);

        }

        model.addAttribute("year", year);
        model.addAttribute("songObjects", songObjects);

        return "songs";

    }
    
}
