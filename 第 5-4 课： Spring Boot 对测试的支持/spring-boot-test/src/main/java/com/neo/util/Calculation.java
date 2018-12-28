package com.neo.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Calculation {
  
   public int add(int a, int b) {

      return a + b;
   }

   public int sub(int a, int b) {

      return a - b;
   }
   public double div(double a, double b) {

      return a / b;
   }

   public String getName(String name) {

      return name;
   }

   public List<String> getList(String item) {

      List<String> l = new ArrayList<String>();
      l.add(item);
      return l;
   }

   public Map<String, String> getMap(String key, String value) {

      Map<String, String> m = new HashMap<String, String>();
      m.put(key, value);
      return m;
   }
}  