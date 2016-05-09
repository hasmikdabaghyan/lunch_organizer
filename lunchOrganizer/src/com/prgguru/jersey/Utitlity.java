package com.prgguru.jersey;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utitlity {
  /**
   * Null check Method
   * 
   * @param txt
   * @return
   */
  public static boolean isNotNull(String txt) {
    // System.out.println("Inside isNotNull");
    return txt != null && txt.trim().length() >= 0 ? true : false;
  }

  /**
   * Method to construct JSON
   * 
   * @param tag
   * @param status
   * @return
   */
  public static String constructJSON(String tag, boolean status) {
    JSONObject obj = new JSONObject();
    try {
      obj.put("tag", tag);
      obj.put("status", new Boolean(status));
    } catch (JSONException e) {
      // TODO Auto-generated catch block
    }
    return obj.toString();
  }

  /**
   * Retrieves json value by key 
   * @param obj json object
   * @param key
   * @return
   * @throws Exception 
   */
  public static String getJsonValue(JSONObject obj, String key) throws Exception {
    try {
      return obj.getString(key);
    } catch (JSONException e) {
      throw new Exception(key + " request parameter is required");
    }
  }
  
  public static JSONObject convertStringToJson(String string){
    try {
      return new JSONObject(string);
    } catch (JSONException e) {
      return null;
    }
  }
  
  /**
   * Method to construct JSON with Error Msg
   * 
   * @param tag
   * @param status
   * @param err_msg
   * @return
   */
  public static String constructJSON(String tag, boolean status, String err_msg) {
    JSONObject obj = new JSONObject();
    try {
      obj.put("tag", tag);
      obj.put("status", new Boolean(status));
      obj.put("error_msg", err_msg);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
    }
    return obj.toString();
  }
}
