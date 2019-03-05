/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author marij
 */

 public class Util {
    private Properties properties;
    private static Util instance;
    
    private Util() throws FileNotFoundException, IOException{
        properties=new Properties();
        properties.load(new FileInputStream("db.properties"));
    }
    
    public static Util getInstance() throws IOException{
        if(instance==null){
            instance=new Util();
        }
        return instance;
    }
    
    public String getDriver(){
        return properties.getProperty(properties.getProperty("current_db")+"_driver");
    }
    
    public String getUrl(){
        return properties.getProperty(properties.getProperty("current_db")+"_url");
    }
    
    public String getUser(){
        return properties.getProperty(properties.getProperty("current_db")+"_user");
    }
    
    public String getPassword(){
        return properties.getProperty(properties.getProperty("current_db")+"_password");
    }
}
