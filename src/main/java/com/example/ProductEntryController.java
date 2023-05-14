package com.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class ProductEntryController implements Initializable{
    private static ConnectionDatabase data;
    @FXML
    TextField TFPname1;
    @FXML
    TextField TFParea1;
    @FXML
    TextField TFPquantity1;
    @FXML
    TextField TFPaddress1;
    @FXML
    TextField TFPphoneNumber1;
    @FXML
    CheckBox CheckUse1;
    @FXML
    CheckBox Checktreatment1;
    @FXML
    ImageView BTNAddProduct1;
    @FXML
    ImageView BTN1logout;
    @FXML
    Button BTNEntry1;
    @FXML
    private ComboBox<String> ComboBox_Clothes;

    @FXML
    static ObservableList<Invoice> LIST = FXCollections.observableArrayList();
    static String location;
    static double LocalPrice=0;
    static int DevDate=0;
    private static int StatusCounter=0;
    static List<String> MailNames=new ArrayList<>();

    public static double getLocalPrice() {
        return LocalPrice;
    }
    public static void addLocalPrice(double local) {
        LocalPrice += local;
    }
    public static void setZeroLocalPrice() {
        LocalPrice = 0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list= FXCollections.observableArrayList("Pants","Shirt","Jacket","Others");
        ComboBox_Clothes.setItems(list);
        StatusHelper();
    }



    public static void StatusHelper(){
        try {
            int WaitToTreatment=0;
            String tempDate;
            data = ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();
            String s="SELECT SYSDATE from USER_TABLE";
            Statement ss=con.createStatement();
            ResultSet sss=ss.executeQuery(s);
            sss.next();
            tempDate=sss.getString(1);
            String stmt="SELECT productID,STATUS,STRINGDATE,CUSTOMER_EMAIL from product";
            Statement stmtt = con.createStatement();
            ResultSet rs = stmtt.executeQuery(stmt);
            while(rs.next())
            {
                int flag=0;
                if(rs.getString(2).equals("IN_TREATMENT"))
                {
                    String[] SpaceSplit=rs.getString(3).split(" ");
                    String[] SpaceSplitTemp=tempDate.split(" ");
                    String[] Split = SpaceSplit[0].split("-");
                    String[] tempDateSplit = SpaceSplitTemp[0].split("-");
                    if (Split[1].equals(tempDateSplit[1]))
                    {
                        if ( Integer.parseInt(tempDateSplit[2])-Integer.parseInt(Split[2]) > 2 ) {flag=1;}}
                    else {if(Integer.parseInt(tempDateSplit[2])+(30-Integer.parseInt(Split[2])) >2 )
                            flag=1;}
                    if(flag!=0)
                    {
                        String update="update product set status='" + "COMPLETE'" + "where productID="+rs.getInt(1);
                        Statement stmtUpdate=con.createStatement();
                        stmtUpdate.executeUpdate(update);
                        if(StatusCounter>=1)StatusCounter--;
                        WaitToTreatment++;
                        int f=1;
                        WorkerRest(f);
                        String updateEmail="update product set EMAIL_FLAG='" + "true'" + "where productID="+rs.getInt(1);
                        stmtUpdate.executeUpdate(updateEmail);
                        MailNames.add(rs.getString(4));
                    }
                    else  StatusCounter++;
                }
/*                if (!MailNames.isEmpty()) {
                    Mail m = new Mail();
                    m.RasheedEmail(MailNames);
                    MailNames.clear();
                }*/
            }
            ResultSet RS=stmtt.executeQuery(stmt);
            while(RS.next())
            {
                if(WaitToTreatment==0)break;
                if(RS.getString(2).equals("WAITING"))
                {
                    String updatee = "update product set status='" + "IN_TREATMENT'" + "where productID="+RS.getInt(1);
                    Statement stmtUpdate = con.createStatement();
                    stmtUpdate.executeUpdate(updatee);
                    WaitToTreatment--;
                    int f=2;
                    WorkerRest(f);
                }
            }
        }
        catch (Exception e) {System.out.println("iam here in Status Counter idiot");}}

    private static void WorkerRest(int f) {
        String W_flag="",IDString="";
        if(f==1)
        {
            W_flag="false";
        }
        else if(f==2)
        {
            W_flag="true";
        }
        try {
            data = ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String s = "SELECT ID,WORKFLAG from WORKERS";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(s);
            while(rs.next())
            {
                if(f==1) {
                    if (rs.getString(2).equals("false"))
                    {
                        IDString=rs.getString(1);
                        break;
                    }

                }
                else if(f==2)
                {
                    if (rs.getString(2).equals("true"))
                    {
                        IDString=rs.getString(1);
                        break;
                    }
                }
            }
            String update = "UPDATE WORKERS set WORKFLAG='"+W_flag+"' WHERE ID='"+IDString+"'";
            Statement stmtt = con.createStatement();
            stmtt.executeUpdate(update);


        }
        catch (Exception e)
        {
            System.out.println("Exception in worker flag method");
        }
    }

    public void LogoutBtnClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)mouseEvent.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Exception in Logout Clicked");
        }
    }

    public void BTN_EnterClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Invoice-view/invoice.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) TFPphoneNumber1.getScene().getWindow();
            stage.setScene(scene);
                TFParea1.setText("");
                TFPname1.setText("");
                TFPquantity1.setText("");
                TFPaddress1.setText("");
                TFPphoneNumber1.setText("");
                CheckUse1.setSelected(false);
                Checktreatment1.setSelected(false);
                ComboBox_Clothes.setVisible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddProductClicked(MouseEvent mouseEvent) {
        StatusHelper();
        try {
            String   name=TFPname1.getText()
                    ,area=TFParea1.getText()
                    ,quantity=TFPquantity1.getText()
                    ,address=TFPaddress1.getText()
                    ,phone=TFPphoneNumber1.getText();
            location=address;

            boolean flag = true;
            for(int i=0;i<area.length();i++)
            {
                if(!Character.isDigit(area.charAt(i)))
                {
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Area must contain Digits only");
                    flag=false;
                    break;
                }
            }
            for(int i=0;i<quantity.length();i++)
            {
                if(!Character.isDigit(quantity.charAt(i)))
                {
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Quantity must contain Digits only");
                    flag=false;
                    break;
                }
            }
            if(quantity.equals(null)){
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Quantity can't be empty");
                flag=false;
            }
            if(area.equals(null)) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Area can't be empty");
                flag=false;
            }
            if(address.equals(null)) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Address can't be empty");
                flag=false;
            }
            if(phone.length()!=12 || (phone.charAt(0)!='9' && phone.charAt(1)!='7')) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Please enter a valid Phone Number");
                flag=false;
            }
            for(int i=0;i<name.length();i++) {
                if(Character.isDigit(name.charAt(i)))
                {
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Name can't contain digits");
                    flag=false;
                    break;
                }
            }


            if(flag) {
                data = ConnectionDatabase.getInstance();
                Connection con = data.getConnectData();
                String    useFlag = null
                        , clothType = null
                        , WellCleaned = null
                        , Customer_email = HelloController.getGmailCounter();

                if (Checktreatment1.isSelected())
                    WellCleaned = "true";
                else WellCleaned = "false";
                if (CheckUse1.isSelected()) {
                    useFlag = "true";
                    clothType = ComboBox_Clothes.getSelectionModel().getSelectedItem().toString();
                }
                else {
                    useFlag = "false";
                    clothType = null;
                }


                String WhatIsStatus=null,CurrentDate;
                String str="SELECT SYSDATE from USER_TABLE";
                Statement stmtt = con.createStatement();
                ResultSet rss = stmtt.executeQuery(str);
                rss.next();
                CurrentDate=rss.getString(1);

                if(StatusCounter<10)
                {
                    WhatIsStatus="IN_TREATMENT";
                    StatusCounter++;
                }
                else WhatIsStatus="WAITING";



                double price = 0;
                if (CheckUse1.isSelected()) {
                    if (ComboBox_Clothes.getValue().equals("Pants")) price = Double.parseDouble(quantity) * 5;
                    else if (ComboBox_Clothes.getValue().equals("Shirt")) price = Double.parseDouble(quantity) * 5;
                    else if (ComboBox_Clothes.getValue().equals("Jacket")) price = Double.parseDouble(quantity) * 10;
                    else price = Double.parseDouble(quantity) * 1;
                } else
                    price = Double.parseDouble(quantity) * Double.parseDouble(area) * 0.45;

                if(Checktreatment1.isSelected())
                {
                    price+=price*0.05;
                }

                addLocalPrice(price);

                LIST.add(
                        new Invoice(name, Double.parseDouble(area), Double.parseDouble(quantity), price)
                );
                String all = "INSERT INTO Product values(Prouct_ID_sequence.NEXTVAL," + "'" + name + "'," + "'" + area + "'," + "'" + quantity + "',"
                        + "'" + address + "'," + "'" + phone + "'," + "'" + useFlag + "'," + "'" + clothType + "'," + "'" + WellCleaned + "'," + "'" + Customer_email + "'," + "'"+WhatIsStatus+"',"+price+",'"+"false"+"',"+"'"+CurrentDate+"')";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(all);
                TFParea1.setText("");
                TFPname1.setText("");
                TFPquantity1.setText("");
                CheckUse1.setSelected(false);
                Checktreatment1.setSelected(false);
                ComboBox_Clothes.setVisible(false);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public void CheckUseClicked1(ActionEvent actionEvent) {
        if(CheckUse1.isSelected())
        {
            ComboBox_Clothes.setVisible(true);
        }
        else
        {
            ComboBox_Clothes.setVisible(false);
        }
    }

}
