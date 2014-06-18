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
    private Movies correct;
    
    /*
     * one User can have many phone numbers.  CascadeType.ALL causes associated
     * phone numbers to be deleted when a User is deleted.
     */
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
               name="movies",
               joinColumns = { @JoinColumn( name="quote_id") },
               inverseJoinColumns = @JoinColumn( name="movie_id")
               )
    private Set<Movies> misquotes;
    private Set<Movies> movie_quotes;
    public Quotes() {
    	
    }
    
    public String toString() {
//        return "User [id=" + id + ", pword=" + pword + ", uname=" + uname + ", phoneNumbers]";
    	return "Quote [quote=" + quote + ", movie=" + correct;
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
    public Set<Movies> getMisquotes() {
        return misquotes;
    }
    public Set<Movies> getMovieQuotes() {
        return movie_quotes;
    }
}