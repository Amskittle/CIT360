package quiz.hib;

import java.util.UUID;

public class GenerateUUID {
  
  public static final void main(String... aArgs){
    //generate random UUIDs
	  // Do we need UUIDs for the other two tables? misquote_movie and quote_movie
    UUID quote_id = UUID.randomUUID();
    UUID movie_id = UUID.randomUUID();
  }

} 