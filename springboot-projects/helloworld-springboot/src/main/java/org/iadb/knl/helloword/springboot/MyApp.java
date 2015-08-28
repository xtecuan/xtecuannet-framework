/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package org.iadb.knl.helloword.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author julianr
 */
@RestController
@EnableAutoConfiguration
public class MyApp {



  @RequestMapping("/hello")
  String hello(
      @RequestParam(value = "user", required = true, defaultValue = "anonymous") String user) {

    return "Hello: " + user;
  }

  /**
   *
   * @param args
   */
  public static void main(String[] args) {

    SpringApplication.run(MyApp.class, args);
  }
}
