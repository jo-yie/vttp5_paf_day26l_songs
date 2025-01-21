package vttp5_paf_day26l_songs.repo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp5_paf_day26l_songs.repo.Constants.*;

@Repository
public class SongRepo {

    @Autowired
    private MongoTemplate template; 

    // get all years in db
    /*
        db.songs.distinct("released_year")
     */
    public List<Integer> getYears() { 

        return template.findDistinct(
            new Query(), 
            F_YEAR, C_SONGS, Integer.class
        );

    }

    // get all songs in one year 
    /*
        db.songs.find({
            released_year:<YEAR>
        })
     */
    public List<Document> getAllSongsByYear(Integer year) { 

        Criteria criteria = Criteria.where(F_YEAR)
                                    .is(year);

        Query query = Query.query(criteria);

        query.fields()
            .include(F_TRACK_NAME, F_ARTIST_NAME)
            .exclude("_id");

        return template.find(query, Document.class, C_SONGS);

    }

}
