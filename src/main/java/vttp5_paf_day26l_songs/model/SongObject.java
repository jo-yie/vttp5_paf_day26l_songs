package vttp5_paf_day26l_songs.model;

public class SongObject {

    private String trackName; 
    private String artistName;
    public SongObject() {
    }
    public SongObject(String trackName, String artistName) {
        this.trackName = trackName;
        this.artistName = artistName;
    }
    public String getTrackName() {
        return trackName;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    } 
    
}
