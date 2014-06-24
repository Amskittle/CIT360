/* 

 * Test2.java source code
 * The goal is to pull a list of quotes and their associated movies from the database.
 * 
 * 
 */
package quiz.hib;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class Test2 {
    private List<Quotes> quotes;
    
    public Test2() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	Test2 test = new Test2();
    	test.showAllQuotes();
    }
    /*
     * show how to add records to the database
     */
//    private void addNewQuotes() {
//        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
//        /*
//         * all database interactions in Hibernate are required to be inside a transaction.
//         */
//        Transaction transaction = session.beginTransaction();
//        /*
//         * create some User instances.
//         */
//        Movies aMovie = new Movies();
//        aMovie.setId(UUID.randomUUID().toString()); //Go get UUID from GenerateUUID class.
//        aMovie.setTitle("Megamind");
//        
//        Quotes aQuote = new Quotes();
//        aQuote.setId(UUID.randomUUID().toString()); //Go get UUID from GenerateUUID class.
//        aQuote.setQuote("\"Can't wait. LOL. *smileyface* \"");
//        
//        /*
//         * save each instance as a record in the database
//         */
//        session.save(aMovie);
//        session.save(aQuote);
//        transaction.commit();
//        /*
//         * prove that the User instances were added to the database and that
//         * the instances were each updated with a database generated id.
//         */
//        System.out.println("aMovie generated ID is: " + aMovie.getId());
//        System.out.println("aQuote generated ID is: " + aQuote.getId());
//    }
//    
    /*
     * show how to get a collection of type List containing all of the records in the app_user table
     */
    private void showAllQuotes() {
        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        /*
         * execute a HQL query against the database.  HQL is NOT SQL.  It is object based.
         */
        Query allQuotesQuery = session.createQuery("select q from Quotes as q order by q.quote_id");
        /*
         * get a list of User instances based on what was found in the database tables.
         */
        quotes = allQuotesQuery.list();
        System.out.println("\n\nNumber of quotes: "+ quotes.size());
        /*
         * iterate over each Quotes instance returned by the query and found in the list.
         */
        Iterator<Quotes> iter = quotes.iterator();
        while(iter.hasNext()) {
            Quotes element = iter.next();
            System.out.println(element.getQuote() + "\n\n"); // Outputs the quote
            System.out.println("\n\nNumber of correct movies with this quote: "+element.getMatchingMovies().size()); // Outputs the count of correct movies (1)
            System.out.println("Correct movie: "+element.getMatchingMovies() + "\n\n"); // Outputs the .toString of the movie.
            System.out.println("\n\nNumber of incorrect movies with this quote: "+element.getMisMovies().size()); //Outputs the count of incorrect movies (3)
            System.out.println("Incorrect movies: "+element.getMisMovies() + "\n\n"); // Outputs the .toString of each mismatched movie.
            
        }
        transaction.commit();
    }
    
    /*
     * show how to modify a database record
     */
//    private void modifyQuotes() {
//        
//        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//        /*
//         * get a single Quotes instance from the database.
//         */
//        Query singleQuoteQuery = session.createQuery("select q from Quote as q where q.quote LIKE '%LOL%'");
//        Quotes aQuote = (Quotes)singleQuoteQuery.uniqueResult();
//        /*
//         * change the quote for the Java instance
//         */
//        aQuote.setQuote("\"Can't wait. LOL. :)\"");
//        /*
//         * call the session merge method for the Quotes instance in question.  This tells the database that the instance is ready to be permanently stored.
//         */
//        session.merge(aQuote);
//        
//        /*
//         * call the transaction commit method.  This tells the database that the changes are ready to be permanently stored.
//         */
//        transaction.commit();
//        /*
//         * permanently store the changes into the database tables.
//         */
//        showAllQuotes();
//    }
//    
//    private void addSharedPhoneNumber() {
//        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//        /*
//         * get two User instances from the database using HQL.  This is NOT SQL.  It is object based.
//         */
//        Query joshuaQuery = session.createQuery("select u from User as u where u.uname='Joshua'");
//        User joshuaUser = (User)joshuaQuery.uniqueResult();
//        
//        Query aNameQuery = session.createQuery("select u from User as u where u.uname='aName'");
//        User aNameUser = (User)aNameQuery.uniqueResult();
//        
//        /*
//         * create a PhoneNumber instance
//         */
//        PhoneNumber sharedPhoneNumber = new PhoneNumber();
//        sharedPhoneNumber.setPhone("(546)222-9898");
//                                   
//       /*
//        * add the shared phone number to the joshuaUser 
//        */
//       
//       Set<PhoneNumber> joshuaPhoneNumbers = joshuaUser.getPhoneNumbers(); 
//       joshuaPhoneNumbers.add(sharedPhoneNumber);
//       /*
//        * set the single phone number to be used by more than one User
//        */
//       Set<PhoneNumber> aNamePhoneNumbers = aNameUser.getPhoneNumbers();
//       aNamePhoneNumbers.add(sharedPhoneNumber);
//       /*
//        * inform the database that the phone number should be ready for permanent storage.
//        */
//       session.save(sharedPhoneNumber);
//       /*
//        * inform the database that the modified User instances should be ready for permanent storage.
//        */
//       session.merge(joshuaUser);
//       session.merge(aNameUser);
//       /*
//        * permanently store the changes into the database tables.
//        */
//       transaction.commit();
//       /*
//        * show that the database was updated by printing out all of the User instances created by a HQL query
//        */
//       showAllQuotes();
//    }
//    private void deleteAddedQuotes() {
//            // TODO Auto-generated method stub
//            Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
//            Transaction transaction = session.beginTransaction();
//            
//            int numUsers = quotes.size();
//            System.out.println("Quote count: "+numUsers);
//            for(int i = 0; i < numUsers; i++){
//                System.out.println("deleting quote "+quotes.get(i).getQuote());
//                session.delete(quotes.get(i));
//            }
//            transaction.commit();
//            /*
//             * at this point the records have been removed from the database but still exist in our class list attribute.
//             * Do not store lists retrieved from the database since they will be out of synch with the database table from which they come.
//             * This example shows that you should not store retrieved lists.
//             */
//            System.out.println(quotes);
//            quotes.clear();
//            /*
//             * now the Java instances are also gone and the database is back to its original state so the example application can be run again.
//             */
//            System.out.println(quotes);
//        }
                                   
}