/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Date;

/**
 *
 * @author luciano
 */
public class Post {

    private Long id;
    private String status;
    private Date timestamp;
    private String userid;

    public Post(Long id, String status, Date timestamp, String userid) {
        this.id = id;
        this.status = status;
        this.timestamp = timestamp;
        this.userid = userid;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    

}
