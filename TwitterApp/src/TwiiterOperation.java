import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;

/**
 * Created by anubhav on 23/4/16.
 */
public class TwiiterOperation {

    static int i = 0;

    public static void main(String args[]) {

        // confriguation builder for twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("I96zJvIJ4ATvciKy48159Vilc")
                .setOAuthConsumerSecret("a4h801XhVQBgWNh1jpLpPsKOabZXyk4sMMtOBXTJPLTsDy5y2Z")
                .setOAuthAccessToken("1897308554-EyXKswFYyK9QmU7En2XKvHb8Z2TLnqN3JmdWebc")
                .setOAuthAccessTokenSecret("03HNq0kYAa0bpmAxmUOGrR5r9Kx6gEMGsq10QWbO7rB5g");
        // twitter facory object
        TwitterFactory tf = new TwitterFactory(cb.build());
        List<Post> userstatus = new ArrayList<>();
        twitter4j.Twitter tw = tf.getInstance();
        // ypu can update new status using commented code
  /*      try {
            Status st = tw.updateStatus("i am held back for a long time");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        System.out.println("Status Updated");
*/
        try {
            List<Status> listOfStatus = tw.getHomeTimeline();
            for (Status status : listOfStatus) {
                    String stat = status.getText();
                    Date date = status.getCreatedAt();
                    int likes = status.getFavoriteCount();
                    String user = status.getUser().toString();
                    int retweet = status.getRetweetCount();
                    userstatus.add(i, new Post(stat, date, retweet, likes, user));
                    i++;

            }
            //  compare by first date in higher to lower order then for retweet and no of likes
            System.out.println("****** sorted by date and then retweets and then likes*******--------");
            Collections.sort(userstatus, Comparator.comparing(Post::getpostinDate).reversed()
                    .thenComparing(Post::getNoOfRetweets).reversed()
                    .thenComparing(Post::getNoOfLikes).reversed());
            userstatus.forEach(System.out::println);
            System.out.println("******* sorted by Latest Post **********---------------");
            //compare by Latest Post (Newer to Older)
            Collections.sort(userstatus, Comparator.comparing(Post::getpostinDate).reversed());
            userstatus.forEach(System.out::println);
            System.out.println("********* sorted by retweets ************---------------");
            //Compare by Number of Retweets (Higher to Lower)
            Collections.sort(userstatus, Comparator.comparing(Post::getNoOfRetweets).reversed());
            userstatus.forEach(System.out::println);
            //Compare by Number of Likes (Higher to Lower)
            System.out.println("**********sorted by likes ****************---------------");
            Collections.sort(userstatus, Comparator.comparing(Post::getNoOfLikes).reversed());
            userstatus.forEach(System.out::println);
            //Collections.sort(userstatus,(user1,user2)->user1.getCreatedAt().compareTo(user2.getCreatedAt()));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

/*
 static post class contain the tweet of user
 */
    public static class Post {
        String Status;
        Date postingDate;
        int retweet;
        int noOfLikes;
        String Name;
/*
constructor of post class which takes status ,postingdate,retweet,nooflikes,name as arguments
 */
        Post(String Status, Date postingDate, int retweet, int noOfLikes, String Name) {

            this.Status = Status;
            this.postingDate = postingDate;
            this.retweet = retweet;
            this.noOfLikes = noOfLikes;
            this.Name = Name;
        }
/*
ACCESSOR FOR STATUS
 */
        public String getStatus() {
            return Status;
        }
    /*
    ACCESSOR FOR POST DATE
     */
        public Date getpostinDate() {
            return postingDate;
        }
    /*
    ACCESSOR FOR NO OF LIKES
     */
        public int getNoOfLikes() {
            return noOfLikes;
        }
    /*
    ACCESSOR FOR NAME
     */
        public String getName() {
            return Name;
        }
    /*
    ACCESSOR FOR RETWEET
     */
        public int getNoOfRetweets() {
            return retweet;
        }

/*
overrided to string  method for preety print
 */
        @Override
        public String toString() {



            return "tweets{" +
                    "*************status*****************='" + Status + '\'' +
                    ",Name='" + Name.substring(30, 60) + '\'' +
                    ",retweet='" + retweet + '\'' +
                    ",likes='" + noOfLikes + '\'' +
                    '}';
        }

    }
}