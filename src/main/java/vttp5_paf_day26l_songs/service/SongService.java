package vttp5_paf_day26l_songs.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp5_paf_day26l_songs.model.SongObject;
import vttp5_paf_day26l_songs.repo.SongRepo;

@Service
public class SongService {

    @Autowired
    private SongRepo songRepo; 

    public List<Integer> getAllYears() { 

        List<Integer> years = songRepo.getYears();
        years.sort(Comparator.reverseOrder());

        return years;

    }
    
    public List<SongObject> getSongsFromYear(Integer year) {

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

        return songObjects; 

    }
    
}
