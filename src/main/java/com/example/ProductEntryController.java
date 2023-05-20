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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductEntryController implements Initializable{
    private static ConnectionDatabase data;
    private static final Logger loggER = Logger.getLogger(ProductEntryController.class.getName());
    @FXML
    TextField tfpname1;
    @FXML
    TextField tfparea1;
    @FXML
    TextField tfpquantity1;
    @FXML
    TextField tfpaddress1;
    @FXML
    TextField tfpphonenumber1;
    @FXML
    CheckBox checkuse1;
    @FXML
    CheckBox checktreatment1;
    @FXML
    ImageView btnaddproduct1;
    @FXML
    ImageView btnlogout1;
    @FXML
    Button btnentry1;
    @FXML
    private ComboBox<String> comboboxClothes;

    @FXML
    static ObservableList<Invoice> lLIST = FXCollections.observableArrayList();
    static String location;
    static double localprice=0;
    static int devDate=0;
    private static int statusCounter=0;

    public static double getLocalPrice() {
        return localprice;
    }
    public static void addLocalPrice(double local) {
        localprice += local;
    }
    public static void setZeroLocalPrice() {
        localprice = 0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list= FXCollections.observableArrayList("Pants","Shirt","Jacket","Others");
        comboboxClothes.setItems(list);
        sStatusHelper();
    }



    public static void sStatusHelper(){
        try {
            int waitToTreatment=0;
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
                    String[] spaceSplit=rs.getString(3).split(" ");
                    String[] spaceSplitTemp=tempDate.split(" ");
                    String[] ssplit = spaceSplit[0].split("-");
                    String[] tempDateSplit = spaceSplitTemp[0].split("-");
                    if (ssplit[1].equals(tempDateSplit[1]))
                    {
                        if ( Integer.parseInt(tempDateSplit[2])-Integer.parseInt(ssplit[2]) > 2 ) {flag=1;}}
                    else {if(Integer.parseInt(tempDateSplit[2])+(30-Integer.parseInt(ssplit[2])) >2 )
                            flag=1;}
                    if(flag!=0)
                    {
                        String update="update product set status='" + "COMPLETE'" + "where productID="+rs.getInt(1);
                        Statement stmtUpdate=con.createStatement();
                        stmtUpdate.executeUpdate(update);
                        if(statusCounter>=1)statusCounter--;
                        waitToTreatment++;
                        int f=1;
                        workerRest(f);
                        String updateEmail="update product set EMAIL_FLAG='" + "true'" + "where productID="+rs.getInt(1);
                        stmtUpdate.executeUpdate(updateEmail);
                        Mail.rasheedEmail(rs.getString(4));
                    }
                    else  statusCounter++;
                }
            }
            ResultSet rssss=stmtt.executeQuery(stmt);
            while(rssss.next())
            {
                if(waitToTreatment==0)break;
                if(rssss.getString(2).equals("WAITING"))
                {
                    String updatee = "update product set status='" + "IN_TREATMENT'" + "where productID="+rssss.getInt(1);
                    Statement stmtUpdate = con.createStatement();
                    stmtUpdate.executeUpdate(updatee);
                    waitToTreatment--;
                    int f=2;
                    workerRest(f);
                }
            }
        }
        catch (Exception e) { loggER.log(Level.WARNING, "iam here in Status Counter idiot");}}

    private static void workerRest(int f) {
        String wwflag="",iddString="";
        if(f==1)
        {
            wwflag="false";
        }
        else if(f==2)
        {
            wwflag="true";
        }
        try {
            data = ConnectionDatabase.getInstance();
            Connection con = data.getConnectData();

            String s = "SELECT id,WORKFLAG from WORKERS";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(s);
            while(rs.next())
            {
                if(f==1) {
                    if (rs.getString(2).equals("false"))
                    {
                        iddString=rs.getString(1);
                        break;
                    }

                }
                else if(f==2)
                {
                    if (rs.getString(2).equals("true"))
                    {
                        iddString=rs.getString(1);
                        break;
                    }
                }
            }
            String update = "UPDATE WORKERS set WORKFLAG='"+wwflag+"' WHERE id='"+iddString+"'";
            Statement stmtt = con.createStatement();
            stmtt.executeUpdate(update);


        }
        catch (Exception e)
        {
            loggER.log(Level.WARNING, "Exception in worker flag method");
        }
    }

    public void logoutBtnClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign-up-view/hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage =(Stage) (((Node)mouseEvent.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            loggER.log(Level.WARNING, "Exception in Logout Clicked");
        }
    }

    public void bTNnEnterClicked(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Invoice-view/invoice.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) tfpphonenumber1.getScene().getWindow();
            stage.setScene(scene);
                tfparea1.setText("");
                tfpname1.setText("");
                tfpquantity1.setText("");
                tfpaddress1.setText("");
                tfpphonenumber1.setText("");
                checkuse1.setSelected(false);
                checktreatment1.setSelected(false);
                comboboxClothes.setVisible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aAddProductClicked(MouseEvent mouseEvent) {
        sStatusHelper();
        try {
            String   name=tfpname1.getText()
                    ,area=tfparea1.getText()
                    ,quantity=tfpquantity1.getText()
                    ,address=tfpaddress1.getText()
                    ,phone=tfpphonenumber1.getText();
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
                JOptionPane.showMessageDialog(f,"address can't be empty");
                flag=false;
            }
            if(phone.length()!=12 || (phone.charAt(0)!='9' && phone.charAt(1)!='7')) {
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Please enter a valid phone Number");
                flag=false;
            }
            for(int i=0;i<name.length();i++) {
                if(Character.isDigit(name.charAt(i)))
                {
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"name can't contain digits");
                    flag=false;
                    break;
                }
            }


            if(flag) {
                data = ConnectionDatabase.getInstance();
                Connection con = data.getConnectData();
                String    useFlag = null
                        , clothType = null
                        , wWellCleaned = null
                        , Customer_email = HelloController.getGmailCounter();

                if (checktreatment1.isSelected())
                    wWellCleaned = "true";
                else wWellCleaned = "false";
                if (checkuse1.isSelected()) {
                    useFlag = "true";
                    clothType = comboboxClothes.getSelectionModel().getSelectedItem();
                }
                else {
                    useFlag = "false";
                    clothType = null;
                }


                String wWhatIsStatus=null,CurrentDate;
                String str="SELECT SYSDATE from USER_TABLE";
                Statement stmtt = con.createStatement();
                ResultSet rss = stmtt.executeQuery(str);
                rss.next();
                CurrentDate=rss.getString(1);

                if(statusCounter<10)
                {
                    wWhatIsStatus="IN_TREATMENT";
                    statusCounter++;
                }
                else wWhatIsStatus="WAITING";



                double price = 0;
                if (checkuse1.isSelected()) {
                    if (comboboxClothes.getValue().equals("Pants")) price = Double.parseDouble(quantity) * 5;
                    else if (comboboxClothes.getValue().equals("Shirt")) price = Double.parseDouble(quantity) * 5;
                    else if (comboboxClothes.getValue().equals("Jacket")) price = Double.parseDouble(quantity) * 10;
                    else price = Double.parseDouble(quantity) * 1;
                } else
                    price = Double.parseDouble(quantity) * Double.parseDouble(area) * 0.45;

                if(checktreatment1.isSelected())
                {
                    price+=price*0.05;
                }

                addLocalPrice(price);

                lLIST.add(
                        new Invoice(name, Double.parseDouble(area), Double.parseDouble(quantity), price)
                );
                String all = "INSERT INTO Product values(Prouct_ID_sequence.NEXTVAL," + "'" + name + "'," + "'" + area + "'," + "'" + quantity + "',"
                        + "'" + address + "'," + "'" + phone + "'," + "'" + useFlag + "'," + "'" + clothType + "'," + "'" + wWellCleaned + "'," + "'" + Customer_email + "'," + "'"+wWhatIsStatus+"',"+price+",'"+"false"+"',"+"'"+CurrentDate+"')";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(all);
                tfparea1.setText("");
                tfpname1.setText("");
                tfpquantity1.setText("");
                checkuse1.setSelected(false);
                checktreatment1.setSelected(false);
                comboboxClothes.setVisible(false);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public void cCheckUseClicked1(ActionEvent actionEvent) {
        comboboxClothes.setVisible(checkuse1.isSelected());
    }

}
