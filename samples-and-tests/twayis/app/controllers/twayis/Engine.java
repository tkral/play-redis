/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.twayis;

import play.mvc.With;
import javax.inject.Inject;
import play.mvc.Controller;
import java.util.List;
import models.Post;
import services.RedisImpl;
import services.Twayis;
import controllers.*;
/**
 *
 * @author luciano
 */
@With({Secure.class, RedisImpl.class})
public class Engine extends Controller {
    @Inject
    static Twayis twayis;

    public static void home() {
        final String loggedIn = session.get("username");
        List<Post> posts = twayis.getUserPosts(loggedIn, 10);
        long followers = twayis.getFollowersCount(loggedIn);
        long following = twayis.getFollowingCount(loggedIn);
        render(posts, following, followers);
    }

    public static void post(String status) {
        twayis.post(session.get("username"), status);
        home();
    }

    public static void follow(String username) {
        twayis.follow(session.get("username"), username);
        renderJSON("{status:\"ok\"}");
    }

    public static void unfollow(String username) {
        twayis.unfollow(session.get("username"), username);
        renderJSON("{status:\"ok\"}");
    }

}
