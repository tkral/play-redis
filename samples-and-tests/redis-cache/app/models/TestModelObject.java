package models;


import java.io.Serializable;

/**
 * User: jonhelgi
 * Date: 4.10.2011
 * Time: 11:26
 */


public class TestModelObject implements Serializable{

    public TestModelObject() {
    }

    public TestModelObject(Long id, String testProperty) {
        this.id = id;
        this.testProperty = testProperty;
    }

    public Long id;

    public String testProperty;
}
