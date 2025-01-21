package vttp5_paf_day26l_songs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp5_paf_day26l_songs.model.SongObject;
import vttp5_paf_day26l_songs.service.SongService;

@Controller
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("")
    public String homePage(Model model) { 

        List<Integer> years = songService.getAllYears();
        model.addAttribute("years", years);

        return "home";

    }

    @GetMapping("/songs")
    public String getAllSongsFromYear(@RequestParam("year") Integer year, Model model) { 

        List<SongObject> songObjects = songService.getSongsFromYear(year);

        model.addAttribute("year", year);
        model.addAttribute("songObjects", songObjects);

        return "songs";

    }
    
}
