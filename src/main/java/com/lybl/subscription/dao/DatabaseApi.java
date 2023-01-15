package com.lybl.subscription.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;

public class DatabaseApi implements CommandLineRunner {
    // Spring Boot will automatically wire this object using application.properties:

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deleteRecord(String name) {

        final String deleteSql = "DELETE FROM employee WHERE name = ?";

// define query arguments

        Object[] params = { name };

// define SQL types of the arguments

        int[] types = { Types.VARCHAR };

        int rows = this.jdbcTemplate.update(deleteSql, params, types);

        System.out.print("Deleted employee."+name);

    }

//Update Query sample

// this.jdbcTemplate.update(

// “UPDATE employee SET lastname = ?,age = ? where name = ?”,

// name, lastname,age);

    @EventListener(ApplicationReadyEvent.class)

    public void doSomethingAfterStartup() {

        System.out.println("I have just started up");

    }

    @Override
    public void run(String... args) throws Exception {

    }
}
