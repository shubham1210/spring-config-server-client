package com.learning.springbootconfig.controller;

import com.learning.springbootconfig.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope // all the values here need to be refreshed.
public class GreetingController {

  @Value("${app.desc}") // if this prop is not there then it wil fail to up server.
  String greetingValue;

  @Value("${app.list.value}") // if this prop is not there then it wil fail to up server.
    List<Integer> listOfValues;


  @Value("${my.greeting1: default value}") // if this prop is not there then it wil fail to up server.
    String greetingValueDefault;

  @Value("#{${dbValues}}")
  Map db;


  @Autowired
  private DbConfig dbConfig;

  @Autowired
  private Environment environment;

  @GetMapping("/greet")
  public String greeting()
  {
    return listOfValues+greetingValue+greetingValueDefault;
  }

  @GetMapping("/db")
  public Map dbValue()
  {
    return db;
  }

  @GetMapping("/dbConfig")
  public String dbConfig()
  {
    return dbConfig.toString();
  }

  //this is bad idea to look up. dont do things on base of env.
  @GetMapping("/env")
  public String getEnv()
  {
    return environment.getProperty("app.desc");

  }

}
