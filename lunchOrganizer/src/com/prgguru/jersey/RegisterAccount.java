package com.prgguru.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/registerAccount")
public class RegisterAccount {
  // HTTP Get Method
  @GET
  // Path: http://localhost/<appln-folder-name>/registerAccount/doregister
  @Path("/doregister")  
  // Produces JSON as response
  @Produces(MediaType.APPLICATION_JSON) 
  // Query parameters are parameters: http://localhost/<appln-folder-name>/registerAccount/doregister?name=pqrs&username=abc&password=xyz
  public String doRegister(@QueryParam("name") String name, @QueryParam("username") String uname, @QueryParam("password") String pwd){
      String response = "";
      System.out.println("Inside doRegister "+uname+"  "+pwd);
      int retCode = registerAccount(name, uname, pwd);
      if(retCode == 0){
          response = Utitlity.constructJSON("register",true);
      }else if(retCode == 1){
          response = Utitlity.constructJSON("register",false, "You are already registered");
      }else if(retCode == 2){
          response = Utitlity.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
      }else if(retCode == 3){
          response = Utitlity.constructJSON("register",false, "Error occured");
      }
      return response;

  }

  private int registerAccount(String name, String uname, String pwd){
      System.out.println("Inside checkCredentials");
      int result = 3;
      if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)){
          try {
              if(DBConnection.insertAccount(name, uname, pwd)){
                  System.out.println("RegisterUser if");
                  result = 0;
              }
          } catch(SQLException sqle){
              System.out.println("RegisterUSer catch sqle");
              //When Primary key violation occurs that means user is already registered
              if(sqle.getErrorCode() == 1062){
                  result = 1;
              } 
              //When special characters are used in name,username or password
              else if(sqle.getErrorCode() == 1064){
                  System.out.println(sqle.getErrorCode());
                  result = 2;
              }
          }
          catch (Exception e) {
              // TODO Auto-generated catch block
              System.out.println("Inside checkCredentials catch e ");
              result = 3;
          }
      }else{
          System.out.println("Inside checkCredentials else");
          result = 3;
      }

      return result;
  }
}