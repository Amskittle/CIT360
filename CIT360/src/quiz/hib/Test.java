package quiz.hib;

/*
 * This is test script created by Amy Norman. It is not complete and needs to be updated to 
 * match the SillyHibernateUseExample file in the test.hib package.
 */

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import quiz.hib.HibernateUtilSingleton;
import quiz.hib.Movies;
import quiz.hib.Quotes;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test tst = new Test(); // Create an instance of the test class.
				
		// These could potentially be replaced with variables that the user enters. For now it's hard coded.
		String quote1 = "Olo";
		
		String movie1 = "Megamind"; // Correct answer
		String movie2 = "Frozen";
		String movie3 = "Despicable Me";
		String movie4 = "Cloudy with a Chance of Meatballs 2";

		// Populate the movies table
		tst.addMovie(movie1);
		/*tst.addMovie(movie2);
		tst.addMovie(movie3);
		tst.addMovie(movie4);*/

		// Populate the quotes table
		//tst.addQuote(quote1, movie1, movie2, movie3, movie4);
	}

	private void addMovie(String title) {
		Transaction trns = null; // Begin transaction
		// Start hibernate session.
		Session session = HibernateUtilSingleton.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			
			List<Movies> movies = session.createQuery("from Movies as m where m.movie = :movieTitle")
					   .setString( "movieTitle", title )
					   .list();		
			
			//This for loop isn't working.
			for (Iterator<Movies> iter = movies.iterator(); iter.hasNext();) {
			    Movies movie = iter.next();
			    System.out.println("Movie: " + movie.getTitle());
			}
			
			/*
			Movies movie = new Movies();
			
			//Add movie title into Movies table
			movie.setMovie(title);

			// Save changes, commit transaction
			session.save(movie);  */
			session.getTransaction().commit();
			
		} catch (RuntimeException e) {
			if (trns != null) {
				// Rollback transaction if error occurs
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

}
