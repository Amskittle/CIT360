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
@Table(name = "movies")

public class Movies {
    
    @Id
    @GeneratedValue
    private String movie_id;
    private String title;


    public Movies() {
    	
    }
    
    public String toString() {
//        return "User [id=" + id + ", pword=" + pword + ", uname=" + uname + ", phoneNumbers]";
    	return "Movie [id=" + movie_id + ", movie=" + title;
    }
    
    public String getId() {
        return movie_id;
    }
    public void setId(String movie_id) {
        this.movie_id = movie_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}