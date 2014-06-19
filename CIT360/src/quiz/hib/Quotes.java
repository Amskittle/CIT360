package quiz.hib;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "quotes")

public class Quotes {
    
    @Id
    @GeneratedValue
    private String quote_id;
    private String quote;
//    private Movies correct;
    
    /*
     * one User can have many phone numbers.  CascadeType.ALL causes associated
     * phone numbers to be deleted when a User is deleted.
     */
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
               name="quote_mismovie",
               joinColumns = { @JoinColumn( name="quote_id") },
               inverseJoinColumns = @JoinColumn( name="movie_id")
               )
    private Set<Movies> misMovies;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="quote_movie",
            joinColumns = { @JoinColumn( name="quote_id") },
            inverseJoinColumns = @JoinColumn( name="movie_id")
            ) 
    private Set<Movies> matchingMovies;
    public Quotes() {
    	
    }
    
    public String toString() {
//        return "User [id=" + id + ", pword=" + pword + ", uname=" + uname + ", phoneNumbers]";
    	return "Quote [id="+ quote_id + " quote=" + quote + "]";
    }
    
    public String getId() {
        return quote_id;
    }
    public void setId(String quote_id) {
        this.quote_id = quote_id;
    }
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }

	public Set<Movies> getMisMovies() {
		return misMovies;
	}
//  These setters aren't actually used since we're pulling from the database and not adding to it.
//	public void setMisMovies(Set<Movies> misMovies) {
//		this.misMovies = misMovies;
//	}

	public Set<Movies> getMatchingMovies() {
		return matchingMovies;
	}

//	public void setMatchingMovies(Set<Movies> matchingMovies) {
//		this.matchingMovies = matchingMovies;
//	}

}