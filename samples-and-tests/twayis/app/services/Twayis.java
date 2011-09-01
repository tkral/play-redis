package services;

import java.util.List;
import models.Post;

/**
 * Service exposing basic Twitter-like mehods.
 * @author luciano
 */
public interface Twayis {
    void follow(String currentUser, String userToFollow);
    void unfollow(String currentUser, String userToUnFollow);
    long post(String username, String status);
    List<Post> timeline(int maxTweets);
    
    List<Post> getUserPosts(String username,int maxPosts);
    String getUserId(String username);
    boolean isFollowing(String username, String followingWho);
    long getFollowersCount(String username);
    long getFollowingCount(String username);
    
    
    void checkUsername(String username);
    void checkPassword(String password, String password2);
    void register(String username, String pazz);
    
}
