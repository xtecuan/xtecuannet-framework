/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.web.samples.sampleapp;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public class Person implements Serializable {

    private String firstname;
    private String lastname;

    public Person() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompleteName() {

        return firstname + " " + lastname;
    }

}
