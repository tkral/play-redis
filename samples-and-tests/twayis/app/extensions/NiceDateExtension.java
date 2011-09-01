/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package extensions;

import com.ocpsoft.pretty.time.PrettyTime;
import java.util.Date;
import play.templates.JavaExtensions;

/**
 *
 * @author luciano
 */
public class NiceDateExtension extends JavaExtensions {

    public static String pretty(Date aDate) {

        PrettyTime p = new PrettyTime();
        return p.format(aDate);
    }


}
