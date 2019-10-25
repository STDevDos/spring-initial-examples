package com.aspectdemo.fd.dao;

import org.springframework.stereotype.Component;

/**
 * @author froy
 */
@Component
public class MembershipDAO {

    public void addAccount() {

        System.out.println(getClass() + " : DB :: ADDING AN ACCOUNT ");

    }

}
