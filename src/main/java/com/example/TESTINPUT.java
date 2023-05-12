package com.example;
import javafx.css.Size;

public class TESTINPUT {
    public static boolean idTest(String id) {
        if (id.length() == 9) {
            boolean flag = true;
            for (int i = 0; i < id.length(); i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) return true;
            else return false;
        }
        return false;
    }

    public static boolean phoneNumberTest(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            boolean flag = true;
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) return true;
            else return false;
        }
        return false;
    }

    public static boolean gmailTest(String gmail) {
        if (Character.isDigit(gmail.charAt(0)) || gmail.length() < 17) return false;
        else {
            boolean flag = false;
            for (int i = 1; i < gmail.length(); i++) {
                if (gmail.charAt(i) == '@') flag = true;
            }
            if (flag == false) return false;
            else return true;
        }
    }

    public static boolean passwordTest(String password) {
        if( password.length()<8) return false;
        else {
            boolean flags=false,flagc=false,flagn=false;
            for (int i = 0; i < password.length(); i++){
                if(Character.isLowerCase(password.charAt(i))) flags=true;
                else if(Character.isUpperCase(password.charAt(i))) flagc=true;
                else if (Character.isDigit(password.charAt(i))) flagn=true;
            }
            if(flags==true && flagc==true && flagn==true) return true;
            else return false;
        }
    }
    public static int  ordernameTest(String name) {
        int flag=0;
        name=(name.toUpperCase());
        if(name.equals("CARPET")) flag=1;
        else if( name.equals("COVER"))  flag=2;
        else if(name.equals("BLANKET")) flag=3;
        return flag;
    }
    public static boolean orderQuantityTest(String Quantity) {
        if ( Quantity.length() >0) {
            boolean flag = true;
            for(int i=0;i<Quantity.length();i++){
                if(!Character.isDigit(Quantity.charAt(i))){
                    flag = false ;
                    break;
                }
            }
            if(flag == true)return true;
            else return false;
        }
        return false;
    }
    public static boolean orderSizeTest(String Size) {
        return TESTINPUT.orderQuantityTest(Size);

    }
    public static boolean orderColorTest(String Color) {
        boolean flag = true;
        for (int i = 0; i < Color.length(); i++) {
            if (Character.isDigit(Color.charAt(i))) {
                flag = false;
                break;
            }
        }
        if (flag == true) return true;
        else return false;
    }

    public static boolean orderPictureTest(String Picture) {
        if(Picture.indexOf(".png")!=-1 || Picture.indexOf(".jpg")!=-1) return true;
        else return false;
    }
}