package simplemask;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Client {
 private static Map users = new HashMap();
 
 public static void main(String[] args){
  String userName = "littlehoi";
  String password = "!ww123123";
  registerUser(userName,password);
  
  userName = "xxx";
  password = "xxx";
  registerUser(userName,password);
  
  String loginUserId = "test";
  String pwd = "123123";
  try {
   if(loginValid(loginUserId,pwd)){
    System.out.println("valid");
   }else{
    System.out.println("fail");
   }
  } catch (NoSuchAlgorithmException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (UnsupportedEncodingException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } 
 }
 
 /**
  * ????
  * 
  * @param userName
  * @param password
  */
 public static void registerUser(String userName,String password){
  String encryptedPwd = null;
  try {
   encryptedPwd = MyMD5Util.getEncryptedPwd(password);
   System.out.println(encryptedPwd);
   users.put(userName, encryptedPwd);
   
  } catch (NoSuchAlgorithmException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (UnsupportedEncodingException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 
 /**
  * ????
  * 
  * @param userName
  * @param password
  * @return
  * @throws UnsupportedEncodingException 
  * @throws NoSuchAlgorithmException 
  */
 public static boolean loginValid(String userName,String password) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException{
  String pwdInDb = (String)users.get(userName);
  if(null!=pwdInDb){ // ?????
    return MyMD5Util.validPassword(password, pwdInDb);
  }else{
   System.out.println("loginfail");
   return false;
  }
 }
}