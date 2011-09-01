/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.twayis;

import java.util.List;

import javax.inject.Inject;

import models.Post;
import play.mvc.Controller;
import play.mvc.With;
import services.Twayis;
import controllers.Secure;
/**
 *
 * @author luciano
 */
@With(Secure.class)
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
