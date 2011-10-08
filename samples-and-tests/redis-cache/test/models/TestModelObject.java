package models;

import java.io.Serializable;

/**
 * Object used for object caching tests.
 * 
 * @author Jon Helgi
 */
public class TestModelObject implements Serializable {

    public Long id;
    public String testProperty;
    public TestModelObject() {  }

    public TestModelObject(Long id, String testProperty) {
        this.id = id;
        this.testProperty = testProperty;
    }

}
