/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseGUI;

/**
 *
 * @author EL-Torky
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javax.swing.JOptionPane;


public class CollagePortal extends Application{
    
    private Statement stmt;
    private PreparedStatement preparedStatement;
    
    Stage StaffLoginStage = new Stage();
    Stage StudentLoginStage = new Stage();
    Stage StaffPortalStage = new Stage();
    Stage StudentPortalStage = new Stage();
    Stage WithdrawStage = new Stage();
    Stage Rigester = new Stage();
    Stage EnrollOfRegister = new Stage();
    Stage EditCourse = new Stage();
    
    TextField StudentUserName = new TextField();
    PasswordField StudentPassword = new PasswordField();
    TextField StaffUserName = new TextField();
    PasswordField StaffPassword = new PasswordField();
    Label validAccountStudent = new Label();
    Label validAccountStaff = new Label();
    
    String StudentID;
    String StaffID;
    public void start(Stage primaryStage){
        //Call initializeDB method to connect to the DB
        initializeDB();
        
        BorderPane pane = new BorderPane();
        pane.setCenter(new StackPane(new Label("Welcome to the Collage Portal")));
        
        Button btStudentLogin = new Button("Student Portal");
        Button btStaffLogin = new Button("Staff Portal");
        
        btStudentLogin.setOnAction( e -> studentLogin() );
        btStaffLogin.setOnAction( e -> StaffLogin() );
        
        HBox hBox = new HBox(btStudentLogin, btStaffLogin);
        hBox.setSpacing(543);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        
        Scene scene = new Scene(pane, 700, 500);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Collage Portal"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage   
    }
    
    public void studentLogin(){
    // Create a pane and set its properties
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    // Place nodes in the pane
    pane.add(new Label("UserName"), 0, 0);
    pane.add(StudentUserName, 1, 0);
    pane.add(new Label("Password"), 0, 1); 
    pane.add(StudentPassword, 1, 1);
    Button btLogin = new Button("Login");
    pane.add(btLogin, 1, 2);
    GridPane.setHalignment(btLogin, HPos.RIGHT);
    pane.add(validAccountStudent, 1, 3);
    GridPane.setHalignment(validAccountStudent, HPos.RIGHT);
    btLogin.setOnAction(e -> CheckStudentAccount());
    validAccountStudent.setText("");
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    StudentLoginStage.setTitle("Student Login Stage"); // Set the stage title
    StudentLoginStage.setScene(scene); // Place the scene in the stage
    StudentLoginStage.show(); // Display the stage
    }
    
    public void CheckStudentAccount(){
    String userName = StudentUserName.getText();
    String Password = StudentPassword.getText();
    try {
      String queryString = "SELECT * FROM STUDENTS WHERE "
              + "USERNAME = '"+userName+"' AND "
              + "PASSWORD = '"+Password+"'";

      ResultSet rset = stmt.executeQuery(queryString);

      if (rset.next()) {
        JOptionPane.showMessageDialog(null, "Welcome "+rset.getString(2)+" !");
        StudentID = rset.getString(1);
        StudentLoginStage.close();

        studentPortal();
      }
      else {
        validAccountStudent.setText("Invalid UserName/Password");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    
    }
    
    public void studentPortal(){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab tabProfile = new Tab("Student Profile");
        Tab tabScheduel = new Tab("Scheduel"); 
        Tab tabResults = new Tab("Results");
        Tab tabRegister = new Tab("Register");
        Tab tabTextBoxes = new Tab("TextBoxes");
        
        
        //Student Profile Tab
        TextField txtName = new TextField();
        TextField txtDateOfBirth = new TextField();
        TextField txtGPA = new TextField();
        TextField txtSince = new TextField();
        TextField txtEmail = new TextField();
        TextField txtAddress = new TextField();
        TextField txtDepartment = new TextField();
        
        txtName.setEditable(false);
        txtDateOfBirth.setEditable(false);
        txtGPA.setEditable(false);
        txtSince.setEditable(false);
        txtEmail.setEditable(false);
        txtAddress.setEditable(false);
        txtDepartment.setEditable(false);
        
        pane.add(new Label("Name"), 0, 0);
        pane.add(new Label("Date Of Birth"), 0, 1); 
        pane.add(new Label("GPA"), 0, 2);
        pane.add(new Label("Student Since"), 0, 3);
        pane.add(new Label("Email"), 0, 4);
        pane.add(new Label("Address"), 0, 5);
        pane.add(new Label("Department"), 0, 6);
        
        
        pane.add(txtName, 1, 0);
        pane.add(txtDateOfBirth, 1, 1); 
        pane.add(txtGPA, 1, 2);
        pane.add(txtSince, 1, 3);
        pane.add(txtEmail, 1, 4);
        pane.add(txtAddress, 1, 5);
        pane.add(txtDepartment, 1, 6);
        
        Button btApply = new Button("Apply");
        pane.add(btApply, 1, 7);
        GridPane.setHalignment(btApply, HPos.LEFT);
        btApply.setDisable(true);
        
        Button btEdit = new Button("Edit Information");
        pane.add(btEdit, 1, 7);
        GridPane.setHalignment(btEdit, HPos.RIGHT);
        
        btEdit.setOnAction( e -> {
        btEdit.setDisable(true);
        txtDateOfBirth.setEditable(true);
        txtAddress.setEditable(true);
        txtEmail.setEditable(true);
        btApply.setDisable(false);
        JOptionPane.showMessageDialog(null, "You can Now Edit Address, Email"
                + " And Date of Birth");
        });
        
        
        btApply.setOnAction( e -> {
        try{
        
        String queryString = "SELECT * FROM STUDENTS WHERE STUDENT_ID = '"+StudentID+"'";
        ResultSet rset = stmt.executeQuery(queryString);
        if (rset.next()) {
        if( txtDateOfBirth.getText() != rset.getString(4) ){
            
            String updateString = "UPDATE STUDENTS "
                + "SET DATE_OF_BIRTH = '"+txtDateOfBirth.getText()+"' "
                + "WHERE STUDENT_ID = '"+StudentID+"'";
            stmt.executeUpdate(updateString);
        }
        }
        ResultSet rset1 = stmt.executeQuery(queryString);
        if (rset1.next()) {
        if( txtAddress.getText() != rset1.getString(5) ){
            
            String updateString = "UPDATE STUDENTS "
                + "SET ADDRESS = '"+txtAddress.getText()+"' "
                + "WHERE STUDENT_ID = '"+StudentID+"'";
            stmt.executeUpdate(updateString);
        }
        }
        ResultSet rset2 = stmt.executeQuery(queryString);
        if (rset2.next()) {
        if( txtEmail.getText() != rset2.getString(9) ){
            
            String updateString = "UPDATE STUDENTS "
                + "SET EMAIL = '"+txtEmail.getText()+"' "
                + "WHERE STUDENT_ID = '"+StudentID+"'";
            stmt.executeUpdate(updateString);
        }
        }
        ResultSet rset3 = stmt.executeQuery(queryString);
        if (rset3.next()) {
        if(txtDateOfBirth.getText() == rset3.getString(4) 
           && txtAddress.getText() == rset3.getString(5) 
           && txtEmail.getText() == rset3.getString(9)){
           JOptionPane.showMessageDialog(null, "You didn't make any changes !");
        }
        else{
            JOptionPane.showMessageDialog(null, "Changes has been Commited to "
            + "the DataBase!");
        }
        }
        
        btApply.setDisable(true);
        
        
        }
        catch (Exception e1){
         e1.printStackTrace();
        
        }
        btEdit.setDisable(false);
        });
        
        
        
        try {
        String queryString = "SELECT * FROM STUDENTS WHERE STUDENT_ID = '"+StudentID+"'";
        String queryDepartment = "SELECT DEPARTMENT_NAME FROM DEPARTMENT WHERE "
                + "DEPARTMENT_ID = ( SELECT DEPARTMENT FROM STUDENTS "
                + "WHERE STUDENT_ID = '"+StudentID+"' )";
        ResultSet rset = stmt.executeQuery(queryString);
        if (rset.next()) {
        
            txtName.setText(rset.getString(2)+" "+rset.getString(3));
            txtDateOfBirth.setText(rset.getString(4));
            txtGPA.setText(rset.getString(10));
            txtSince.setText(rset.getString(6));
            txtEmail.setText(rset.getString(9));
            txtAddress.setText(rset.getString(5));
            
        }
        ResultSet rsetDept = stmt.executeQuery(queryDepartment);
        if (rsetDept.next()) {
            txtDepartment.setText(rsetDept.getString(1));
        }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Query unsuccessful");
        }
        //Scheduel Tab
        GridPane paneScheduel = new GridPane();
        paneScheduel.setAlignment(Pos.CENTER);
        paneScheduel.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneScheduel.setHgap(40);
        paneScheduel.setVgap(10);
        
        Label lbSATURDAY2 = new Label();
        Label lbSATURDAY4 = new Label();
        Label lbSATURDAY6 = new Label();
        Label lbSATURDAY8 = new Label();
        Label lbSATURDAY10 = new Label();
        Label lbSATURDAY12 = new Label();
        paneScheduel.add(lbSATURDAY2, 1, 1, 2, 1);
        paneScheduel.add(lbSATURDAY4, 3, 1, 4, 1);
        paneScheduel.add(lbSATURDAY6, 5, 1, 6, 1);
        paneScheduel.add(lbSATURDAY8, 7, 1, 8, 1);
        paneScheduel.add(lbSATURDAY10, 9, 1, 10, 1);
        paneScheduel.add(lbSATURDAY12, 11, 1, 12, 1);
        
        Label lbSUNDAY2 = new Label();
        Label lbSUNDAY4 = new Label();
        Label lbSUNDAY6 = new Label();
        Label lbSUNDAY8 = new Label();
        Label lbSUNDAY10 = new Label();
        Label lbSUNDAY12 = new Label();
        paneScheduel.add(lbSUNDAY2, 1, 2, 2, 2);
        paneScheduel.add(lbSUNDAY4, 3, 2, 4, 2);
        paneScheduel.add(lbSUNDAY6, 5, 2, 6, 2);
        paneScheduel.add(lbSUNDAY8, 7, 2, 8, 2);
        paneScheduel.add(lbSUNDAY10, 9, 2, 10, 2);
        paneScheduel.add(lbSUNDAY12, 11, 2, 12, 2);
        
        Label lbMONDAY2 = new Label();
        Label lbMONDAY4 = new Label();
        Label lbMONDAY6 = new Label();
        Label lbMONDAY8 = new Label();
        Label lbMONDAY10 = new Label();
        Label lbMONDAY12 = new Label();
        paneScheduel.add(lbMONDAY2, 1, 3, 2, 3);
        paneScheduel.add(lbMONDAY4, 3, 3, 4, 3);
        paneScheduel.add(lbMONDAY6, 5, 3, 6, 3);
        paneScheduel.add(lbMONDAY8, 7, 3, 8, 3);
        paneScheduel.add(lbMONDAY10, 9, 3, 10, 3);
        paneScheduel.add(lbMONDAY12, 11, 3, 12, 3);
        
        Label lbTUESDAY2 = new Label();
        Label lbTUESDAY4 = new Label();
        Label lbTUESDAY6 = new Label();
        Label lbTUESDAY8 = new Label();
        Label lbTUESDAY10 = new Label();
        Label lbTUESDAY12 = new Label();
        paneScheduel.add(lbTUESDAY2, 1, 4, 2, 4);
        paneScheduel.add(lbTUESDAY4, 3, 4, 4, 4);
        paneScheduel.add(lbTUESDAY6, 5, 4, 6, 4);
        paneScheduel.add(lbTUESDAY8, 7, 4, 8, 4);
        paneScheduel.add(lbTUESDAY10, 9, 4, 10, 4);
        paneScheduel.add(lbTUESDAY12, 11, 4, 12, 4);
        
        Label lbWEDNSDAY2 = new Label();
        Label lbWEDNSDAY4 = new Label();
        Label lbWEDNSDAY6 = new Label();
        Label lbWEDNSDAY8 = new Label();
        Label lbWEDNSDAY10 = new Label();
        Label lbWEDNSDAY12 = new Label();
        paneScheduel.add(lbWEDNSDAY2, 1, 5, 2, 5);
        paneScheduel.add(lbWEDNSDAY4, 3, 5, 4, 5);
        paneScheduel.add(lbWEDNSDAY6, 5, 5, 6, 5);
        paneScheduel.add(lbWEDNSDAY8, 7, 5, 8, 5);
        paneScheduel.add(lbWEDNSDAY10, 9, 5, 10, 5);
        paneScheduel.add(lbWEDNSDAY12, 11, 5, 12, 5);
        
        Label lbTHURSDAY2 = new Label();
        Label lbTHURSDAY4 = new Label();
        Label lbTHURSDAY6 = new Label();
        Label lbTHURSDAY8 = new Label();
        Label lbTHURSDAY10 = new Label();
        Label lbTHURSDAY12 = new Label();
        paneScheduel.add(lbTHURSDAY2, 1, 6, 2, 6);
        paneScheduel.add(lbTHURSDAY4, 3, 6, 4, 6);
        paneScheduel.add(lbTHURSDAY6, 5, 6, 6, 6);
        paneScheduel.add(lbTHURSDAY8, 7, 6, 8, 6);
        paneScheduel.add(lbTHURSDAY10, 9, 6, 10, 6);
        paneScheduel.add(lbTHURSDAY12, 11, 6, 12, 6);
        
        paneScheduel.add(new Label("Day / Period"), 0, 0);
        paneScheduel.add(new Label("SATURDAY"), 0, 1); 
        paneScheduel.add(new Label("SUNDAY"), 0, 2);
        paneScheduel.add(new Label("MONDAY"), 0, 3);
        paneScheduel.add(new Label("TUESDAY"), 0, 4);
        paneScheduel.add(new Label("WEDNSDAY"), 0, 5);
        paneScheduel.add(new Label("THURSDAY"), 0, 6);
        paneScheduel.add(new Label("FRIDAY"), 0, 7);
        
        paneScheduel.add(new Label("2"),  2, 0);
        paneScheduel.add(new Label("4"),  4, 0); 
        paneScheduel.add(new Label("6"),  6, 0);
        paneScheduel.add(new Label("8"), 8, 0);
        paneScheduel.add(new Label("10"), 10, 0);
        paneScheduel.add(new Label("12"), 12, 0);
        
        Button btWithdraw = new Button("WITHDRAW");
        paneScheduel.add(btWithdraw, 0, 8);
        paneScheduel.setHalignment(btWithdraw, HPos.RIGHT);
        btWithdraw.setOnAction( e-> btWithdrawFun() );
        
        String[] EnrollToken = new String[3];
        String[] CourseID = new String[3];
        int i = 0;
        try {
        String queryString1 = "SELECT EnrollToken, COURSE_ID FROM ENROLLMENT WHERE "
            + "STUDENT_ID = '"+StudentID+"' AND "
            + "GREADE = 'U'";
        ResultSet rset = stmt.executeQuery(queryString1);
        
        while(rset.next()) {
          EnrollToken[i] = rset.getString(1);
          CourseID[i] = rset.getString(2);
          i++;
        }
        String[] token = new String[20];
        String[] day = new String[20];
        // for loop
        for(int x = 0; x < i; x++){
            
        String queryString2 = "SELECT DAY_ID, PERIOD_ID FROM COURSE_PERIOD_DAY WHERE "
            + "EnrollToken = '"+EnrollToken[x]+"'";
        
        String queryString3 = "SELECT COURSE_NAME FROM COURSES WHERE "
            + "COURSE_ID = '"+CourseID[x]+"'";
        
        ResultSet enroll = stmt.executeQuery(queryString2);
        
        if(enroll.next()){
        day[x] = enroll.getString(1);
        token[x] = enroll.getString(2);
        //SUNDAY
        if( "SUNDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY2.setText(courseName.getString(1));
                }
            }
            else if( "3".equals(token[x])  || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY4.setText(courseName.getString(1));
                }
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x]) || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x])  || "12".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY12.setText(courseName.getString(1));
                }
            }
            
            }
        //Monday
        if( "MONDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY2.setText(courseName.getString(1));
                }
            }
            else if( "3".equals(token[x])  || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY4.setText(courseName.getString(1));
                }
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x])  || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x])  || "12".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY12.setText(courseName.getString(1));
                }
            }
            
            }
        //TUESDAY
        if( "TUESDAY".equals(day[x]) ){
            if( "1".equals(token[x])|| "2".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY2.setText(courseName.getString(1));
                }
                
            }
            else if( "3".equals(token[x]) || token[x] == "4"){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY4.setText(courseName.getString(1));
                }
                
            }
            else if( "5".equals(token[x]) || "6".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY6.setText(courseName.getString(1));
                }
                
            }
            else if( "7".equals(token[x]) || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY8.setText(courseName.getString(1));
                }
                
            }
            else if( "9".equals(token[x]) || "10".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY10.setText(courseName.getString(1));
                }
                
            }
            else if( "11".equals(token[x]) || "12".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY12.setText(courseName.getString(1));
                }
                
            }
            
            }
        //WEDNSDAY
        if( "WEDNSDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY2.setText(courseName.getString(1));
                }
            }
            else if( "3".equals(token[x])  || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY4.setText(courseName.getString(1));
                }
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x])  || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x])  || "12".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY12.setText(courseName.getString(1));
                }
            }
            
            }
        // THURSDAY
        if( "TUESDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY2.setText(courseName.getString(1));
                }
            }
            else if( "3".equals(token[x]) || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY4.setText(courseName.getString(1));
                }
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x])  || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x]) || "12".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY12.setText(courseName.getString(1));
                }
            }
            
            }
        
        }
        }
        //for loop
        }
        catch (SQLException ex) {
            
        Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Results
        
        GridPane paneResult = new GridPane();
        paneResult.setAlignment(Pos.CENTER);
        paneResult.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneResult.setHgap(40);
        paneResult.setVgap(10);
        
        Label[] lbGrades = new Label[10];
        Label[] lbCourses = new Label[10];
        
        paneResult.add(new Label("CourseName"), 0, 0);
        paneResult.add(new Label("Grade"), 1, 0);
        
        String[] CourseIDGrades = new String[10];
        String[] CourseGrades = new String[10];
        int n = 0;
        try {
        String queryString1 = "SELECT  COURSE_ID,GREADE"
            + " FROM ENROLLMENT WHERE "
            + "STUDENT_ID = '"+StudentID+"'";
        
        
        ResultSet rset = stmt.executeQuery(queryString1);
        
        
        while(rset.next()) {
          CourseIDGrades[n] = rset.getString(1);
          CourseGrades[n] = rset.getString(2);
          n++;
        }
        // for loop
        for(int x = 0; x < n; x++){
        String queryString3 = "SELECT COURSE_NAME FROM COURSES WHERE "
            + "COURSE_ID = '"+CourseIDGrades[x]+"'";
        ResultSet courseName = stmt.executeQuery(queryString3);
        
        if(courseName.next()){
        lbGrades[x] = new Label(""+CourseGrades[x]);
        lbCourses[x] = new Label(""+courseName.getString(1));
        paneResult.add(lbCourses[x], 0, x+1);
        paneResult.add(lbGrades[x], 1, x+1);
        }

        }
        //for loop
        }
        catch (SQLException ex) {
            
        Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Register tab
        
        
        GridPane paneRegister = new GridPane();
        paneRegister.setAlignment(Pos.CENTER);
        paneRegister.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneRegister.setHgap(40);
        paneRegister.setVgap(10);
        
        Label[] lbCoursesToRegisterID = new Label[10];
        Label[] lbCoursesToRegister = new Label[10];
        Label[] lbCoursesSeats = new Label[10];
        
        paneRegister.add(new Label("Course ID"), 0, 0);
        paneRegister.add(new Label("Course Name"), 1, 0);
        paneRegister.add(new Label("Seats Left"), 2, 0);
        
        Button btRegister = new Button("Rigester");
        paneRegister.add(btRegister, 0, 3);
        paneRegister.setHalignment(btRegister, HPos.RIGHT);
        
        String[] CoursesToRegisterID = new String[10];
        String[] CoursesToRegisterNAME = new String[10];
        String[] SeatsLeft = new String[10];
        int y = 0;
        try {
            //StudentID PREREQUISITE_COURSE_ID GREADE != 'U'
        String queryString1 = "SELECT COURSE_ID,COURSE_NAME,SEATS_LEFT FROM Courses WHERE Course_ID NOT IN "
                + "(SELECT Course_ID FROM prerequisite WHERE PREREQUISITE_COURSE_ID IN "
                + "(SELECT Course_ID FROM Courses WHERE Course_ID NOT IN "
                + "(SELECT Course_ID FROM Enrollment WHERE Student_ID = '"+StudentID+"' AND GREADE NOT IN ('U', 'F', 'W') ) ) ) AND Course_ID NOT IN "
                + "(SELECT Course_ID FROM Enrollment WHERE Student_ID = '"+StudentID+"' AND ( GREADE = 'U' OR GREADE NOT IN ('F', 'W') ) )";
        
        
        ResultSet rset = stmt.executeQuery(queryString1);
        
        
        while(rset.next()) {
          CoursesToRegisterID[y] = rset.getString(1);
          CoursesToRegisterNAME[y] = rset.getString(2);
          SeatsLeft[y] = rset.getString(3);
          
          lbCoursesToRegisterID[y] = new Label(CoursesToRegisterID[y]);
          lbCoursesToRegister[y] = new Label(CoursesToRegisterNAME[y]);
          lbCoursesSeats[y] = new Label(SeatsLeft[y]);
          
          paneRegister.add(lbCoursesToRegisterID[y], 0, y+1);
          paneRegister.add(lbCoursesToRegister[y], 1, y+1);
          paneRegister.add(lbCoursesSeats[y], 2, y+1);
          y++;
        }
        
        }
        catch (SQLException ex) {
            
        Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btRegister.setOnAction( e-> {
        GridPane enrollPane = new GridPane();
        enrollPane.setAlignment(Pos.CENTER);
        enrollPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        enrollPane.setHgap(40);
        enrollPane.setVgap(10);
        
        enrollPane.add(new Label("Course Token"), 0, 0);
        enrollPane.add(new Label("Course ID"), 1, 0);
        enrollPane.add(new Label("Course Day"), 2, 0);
        
        Label[] lbCoursesToken = new Label[10];
        Label[] lbCoursesID = new Label[10];
        Label[] lbCoursesDay = new Label[10];
        int z = 0;
        int m = 0;
        
        String[] EnrollTokenString = new String[10];

        try {
            while(z < 3 ){
            String queryString1 = "SELECT EnrollToken,COURSE_ID, DAY_ID "
                + "FROM COURSE_PERIOD_DAY WHERE COURSE_ID='"+CoursesToRegisterID[z]+"'";
            ResultSet rset = stmt.executeQuery(queryString1);
            while(rset.next()){
            lbCoursesToken[m] = new Label(rset.getString(1));
            enrollPane.add(lbCoursesToken[m], 0, m+1);
            enrollPane.add(new Label(rset.getString(2)), 1, m+1);
            enrollPane.add(new Label(rset.getString(3)), 2, m+1);
            m++;
            }
            z++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene enrollScene = new Scene(enrollPane,500,300);
        EnrollOfRegister.setScene(enrollScene);
        EnrollOfRegister.setTitle("Rigester Page");
        EnrollOfRegister.setResizable(false);
        EnrollOfRegister.show();

        GridPane paneRegisterstage = new GridPane();
        TextField txtCourseCode = new TextField();
        TextField txtEnrollToken = new TextField();
        Button btSubmit = new Button("Submit");
        paneRegisterstage.setHalignment(btSubmit, HPos.RIGHT);
        paneRegisterstage.add(btSubmit, 1, 2);
        paneRegisterstage.add(new Label("Write Course Code:"), 0, 0);
        paneRegisterstage.add(new Label("EnrollToken:"), 0, 1);
        paneRegisterstage.add(txtCourseCode, 1, 0);
        paneRegisterstage.add(txtEnrollToken, 1, 1);
        
        
        
        
        
        btSubmit.setOnAction( x-> {
        try {
        int counter = 0;

        String queryString1 = "SELECT Course_ID FROM Enrollment WHERE "
                + "STUDENT_ID ='"+StudentID+"' AND GREADE ='U'";
        
        ResultSet rset = stmt.executeQuery(queryString1);
        while(rset.next()) {
        counter++;
        }
        int k = 0;
        boolean found = false;
        int seatsno = 50;
        System.out.println(lbCoursesToken[k].getText());
        if(counter < 3){
            if ( lbCoursesToRegisterID[0].getText().equals(txtCourseCode.getText()) ){
                while(k<10){
                    if(lbCoursesToken[k].getText().equals( txtEnrollToken.getText() )){
                    String insertString = "insert into ENROLLMENT values"
                    + "('"+StudentID+"','"+lbCoursesToRegisterID[0].getText()+"' ,"
                    + " NOW(), '"+txtEnrollToken.getText()+"', 'U' )";
                    stmt.executeUpdate(insertString);
                    String queryString = "SELECT SEATS_LEFT FROM COURSES WHERE "
                    + "Course_ID ='"+lbCoursesToRegisterID[0].getText()+"'";
                    ResultSet rset2 = stmt.executeQuery(queryString);
                    if(rset2.next()){
                        String seats = rset2.getString(1);
                        seatsno = Integer.parseInt(seats);
                        seatsno -= 1;
                    }
                    String updateString = "UPDATE COURSES "
                    + "SET SEATS_LEFT ='"+seatsno+"' "
                    + "Where Course_ID ='"+lbCoursesToRegisterID[0].getText()+"'";
                    stmt.executeUpdate(updateString);
                    k++;
                    found = true;
                    JOptionPane.showMessageDialog(null, "You've registerd "+txtCourseCode.getText()
                    + " !");
                    break;
                    }
                    else{
                        k++;
                    }
                }
                k = 0;
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "You've registerd 3 courses this"
                    + "semster !");
        }
        if(!found){
            JOptionPane.showMessageDialog(null, "You've Entered wrong Course token!");
        }
        else
        {
            found = false;
        }
        
        }
        catch(Exception ex){
             Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
        
        
        
        Scene scene = new Scene(paneRegisterstage, 252,100);
        Rigester.setScene(scene);
        Rigester.setTitle("Rigester Page");
        Rigester.setResizable(false);
        Rigester.show();
        
        
        
        });
        
        
        //
        
        GridPane paneTextBoxes= new GridPane();
        paneTextBoxes.setAlignment(Pos.CENTER);
        paneTextBoxes.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneTextBoxes.setHgap(40);
        paneTextBoxes.setVgap(10);
        
        paneTextBoxes.add(new Label("Course ID"), 0, 0);
        paneTextBoxes.add(new Label("Course Name"), 1, 0);
        paneTextBoxes.add(new Label("Book Name"), 2, 0);
        
        Label[] lbCoursesID = new Label[10];
        Label[] lbCourseName = new Label[10];
        Label[] lbBookName = new Label[10];
        String[] courseIDString = new String[10];
        int a = 0;
        int m = 0;
        int counter = 0;
        try {
            //StudentID PREREQUISITE_COURSE_ID GREADE != 'U'
        String queryString1 = "SELECT DISTINCT Course_ID FROM ENROLLMENT WHERE "
                + "Student_ID ='"+StudentID+"'";
        
        ResultSet rset = stmt.executeQuery(queryString1);

        while(rset.next()) {
          courseIDString[a] = rset.getString(1);
          a++;
          counter++;
        }
        for(int b = 0; b < counter;b++){
            String queryString2 = "SELECT COURSE_ID,COURSE_NAME,TEXTBOOKS_NAME "
                + "FROM COURSES NATURAL JOIN COURSE_TEXTBOOKS NATURAL JOIN TEXTBOOKS "
                + "WHERE COURSE_ID ='"+courseIDString[b]+"'";
            ResultSet restBook = stmt.executeQuery(queryString2);
            if(restBook.next()) {
            lbCoursesID[b] = new Label(restBook.getString(1));
            lbCourseName[b] = new Label(restBook.getString(2));
            lbBookName[b] = new Label(restBook.getString(3));
            paneTextBoxes.add(lbCoursesID[b], 0, b+1);
            paneTextBoxes.add(lbCourseName[b], 1, b+1);
            paneTextBoxes.add(lbBookName[b], 2, b+1);
            }
        }
        
        }
        catch (SQLException ex) {
        Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //
        tabProfile.setContent(pane);
        tabScheduel.setContent(paneScheduel);
        tabResults.setContent(paneResult);
        tabRegister.setContent(paneRegister);
        tabTextBoxes.setContent(paneTextBoxes);
        tabPane.getTabs().addAll(tabProfile,tabScheduel,tabResults,tabRegister,tabTextBoxes);
        
        borderPane.setCenter(pane);
        borderPane.setTop(tabPane);
        
        Scene scene = new Scene(borderPane, 1300, 500);
        StudentPortalStage.setScene(scene);
        StudentPortalStage.setTitle("Student Portal");
        StudentPortalStage.show();
        
    }
    public void btWithdrawFun(){
        GridPane pane = new GridPane();
        
        TextField txtCourseCode = new TextField();
        Button btSubmit = new Button("Submit");
        pane.setHalignment(btSubmit, HPos.RIGHT);
        pane.add(btSubmit, 1, 1);
        pane.add(new Label("Write Course Code:"), 0, 0);
        pane.add(txtCourseCode, 1, 0);
        
        

        btSubmit.setOnAction( e-> {
        try {

        String CourseID = txtCourseCode.getText();
        String queryString1 = "SELECT COURSE_ID FROM ENROLLMENT WHERE "
            + "STUDENT_ID = '"+StudentID+"' AND "
            + "GREADE = 'U' AND COURSE_ID ='"+CourseID+"'";
        ResultSet rset = stmt.executeQuery(queryString1);
        if(rset.next()) {
            String updateString = "UPDATE ENROLLMENT "
            + "SET GREADE = 'W' "
            + "WHERE STUDENT_ID = '"+StudentID+"' AND "
            + "GREADE = 'U' AND COURSE_ID ='"+CourseID+"'";
            stmt.executeUpdate(updateString);
            JOptionPane.showMessageDialog(null, "You have successfully WITHDRAWED "
                    +CourseID+"from your current Courses, "
                    + "Please Re-Login to commit the change !");
        }
        else{
            JOptionPane.showMessageDialog(null, "You're not registried in this course,\n"
                    + "or you have entered the Course ID wrongly");
        }
        }
        catch(Exception ex){
            
        }
        });
        
        
        
        Scene scene = new Scene(pane, 252,100);
        WithdrawStage.setScene(scene);
        WithdrawStage.setTitle("Withdrawl Page");
        WithdrawStage.setResizable(false);
        WithdrawStage.show();;
    }
    
    public void StaffLogin(){
    
    // Create a pane and set its properties
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    // Place nodes in the pane
    pane.add(new Label("UserName"), 0, 0);
    pane.add(StaffUserName, 1, 0);
    pane.add(new Label("Password"), 0, 1); 
    pane.add(StaffPassword, 1, 1);
    Button btLogin = new Button("Login");
    pane.add(btLogin, 1, 2);
    GridPane.setHalignment(btLogin, HPos.RIGHT);
    pane.add(validAccountStaff, 1, 3);
    GridPane.setHalignment(validAccountStaff, HPos.RIGHT);
    btLogin.setOnAction(e -> CheckStaffAccount());
    validAccountStaff.setText("");
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    StaffLoginStage.setTitle("Staff Login Stage"); // Set the stage title
    StaffLoginStage.setScene(scene); // Place the scene in the stage
    StaffLoginStage.show(); // Display the stage
    }
    
    public void CheckStaffAccount(){
        String userName = StaffUserName.getText();
        String Password = StaffPassword.getText();
        
      try {
      String queryString = "SELECT * FROM STAFF WHERE "
              + "USERNAME = '"+userName+"' AND "
              + "PASSWORD = '"+Password+"'";

      ResultSet rset = stmt.executeQuery(queryString);

      if (rset.next()) {
        JOptionPane.showMessageDialog(null, "Welcome Eng."+rset.getString(2)+" "
                +rset.getString(3)+" !");
        StaffID = rset.getString(1);
        StaffLoginStage.close();
        staffPortal();
      }
      else {
        validAccountStaff.setText("Invalid UserName/Password");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    }
    public void staffPortal(){
    GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab tabProfile = new Tab("Staff Profile");
        Tab tabScheduel = new Tab("Schedual");
        Tab tabEditCourses = new Tab("Edit Courses");
        

        TextField txtName = new TextField();
        TextField txtDateOfBirth = new TextField();
        TextField txtSince = new TextField();
        TextField txtEmail = new TextField();
        TextField txtAddress = new TextField();
        TextField txtDepartment = new TextField();
        
        txtName.setEditable(false);
        txtDateOfBirth.setEditable(false);
        txtSince.setEditable(false);
        txtEmail.setEditable(false);
        txtAddress.setEditable(false);
        txtDepartment.setEditable(false);
        
        pane.add(new Label("Name"), 0, 0);
        pane.add(new Label("Date Of Birth"), 0, 1); 
        pane.add(new Label("Working Since"), 0, 2);
        pane.add(new Label("Email"), 0, 3);
        pane.add(new Label("Address"), 0, 4);
        pane.add(new Label("Department"), 0, 5);
        
        
        pane.add(txtName, 1, 0);
        pane.add(txtDateOfBirth, 1, 1); 
        pane.add(txtSince, 1, 2);
        pane.add(txtEmail, 1, 3);
        pane.add(txtAddress, 1, 4);
        pane.add(txtDepartment, 1, 5);
        
        Button btApply = new Button("Apply");
        pane.add(btApply, 1, 7);
        GridPane.setHalignment(btApply, HPos.LEFT);
        btApply.setDisable(true);
        
        Button btEdit = new Button("Edit Information");
        pane.add(btEdit, 1, 7);
        GridPane.setHalignment(btEdit, HPos.RIGHT);
        
        btEdit.setOnAction( e -> {
        btEdit.setDisable(true);
        txtDateOfBirth.setEditable(true);
        txtAddress.setEditable(true);
        txtEmail.setEditable(true);
        btApply.setDisable(false);
        JOptionPane.showMessageDialog(null, "You can Now Edit Address, Email"
                + " And Date of Birth");
        });
        
        
        btApply.setOnAction( e -> {
        try{
        
        String queryString = "SELECT * FROM STAFF WHERE STAFF_ID = '"+StaffID+"'";
        ResultSet rset = stmt.executeQuery(queryString);
        if (rset.next()) {
        if( txtDateOfBirth.getText() != rset.getString(4) ){
            
            String updateString = "UPDATE STAFF "
                + "SET DATE_OF_BIRTH = '"+txtDateOfBirth.getText()+"' "
                + "WHERE STAFF_ID = '"+StaffID+"'";
            stmt.executeUpdate(updateString);
        }
        }
        ResultSet rset1 = stmt.executeQuery(queryString);
        if (rset1.next()) {
        if( txtAddress.getText() != rset1.getString(5) ){
            
            String updateString = "UPDATE STAFF "
                + "SET ADDRESS = '"+txtAddress.getText()+"' "
                + "WHERE STAFF_ID = '"+StaffID+"'";
            stmt.executeUpdate(updateString);
        }
        }
        ResultSet rset2 = stmt.executeQuery(queryString);
        if (rset2.next()) {
        if( txtEmail.getText() != rset2.getString(9) ){
            
            String updateString = "UPDATE STAFF "
                + "SET EMAIL = '"+txtEmail.getText()+"' "
                + "WHERE STAFF_ID = '"+StaffID+"'";
            stmt.executeUpdate(updateString);
        }
        }
        ResultSet rset3 = stmt.executeQuery(queryString);
        if (rset3.next()) {
        if(txtDateOfBirth.getText() == rset3.getString(4) 
           && txtAddress.getText() == rset3.getString(5) 
           && txtEmail.getText() == rset3.getString(9)){
           JOptionPane.showMessageDialog(null, "You didn't make any changes !");
        }
        else{
            JOptionPane.showMessageDialog(null, "Changes has been Commited to "
            + "the DataBase!");
        }
        }
        
        btApply.setDisable(true);
        
        
        }
        catch (Exception e1){
         e1.printStackTrace();
        
        }
        btEdit.setDisable(false);
        });
        
        
        
        try {
        String queryString = "SELECT * FROM STAFF WHERE STAFF_ID = '"+StaffID+"'";
        String queryDepartment = "SELECT DEPARTMENT_NAME FROM DEPARTMENT WHERE "
                + "DEPARTMENT_ID = ( SELECT DEPARTMENT FROM STAFF "
                + "WHERE STAFF_ID = '"+StaffID+"' )";
        ResultSet rset = stmt.executeQuery(queryString);
        if (rset.next()) {
        
            txtName.setText(rset.getString(2)+" "+rset.getString(3));
            txtDateOfBirth.setText(rset.getString(4));
            txtSince.setText(rset.getString(6));
            txtEmail.setText(rset.getString(9));
            txtAddress.setText(rset.getString(5));
            
        }
        ResultSet rsetDept = stmt.executeQuery(queryDepartment);
        if (rsetDept.next()) {
            txtDepartment.setText(rsetDept.getString(1));
        }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Query unsuccessful");
        }
        GridPane paneScheduel = new GridPane();
        paneScheduel.setAlignment(Pos.CENTER);
        paneScheduel.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneScheduel.setHgap(40);
        paneScheduel.setVgap(10);
        
        Label lbSATURDAY2 = new Label();
        Label lbSATURDAY4 = new Label();
        Label lbSATURDAY6 = new Label();
        Label lbSATURDAY8 = new Label();
        Label lbSATURDAY10 = new Label();
        Label lbSATURDAY12 = new Label();
        paneScheduel.add(lbSATURDAY2, 1, 1, 2, 1);
        paneScheduel.add(lbSATURDAY4, 3, 1, 4, 1);
        paneScheduel.add(lbSATURDAY6, 5, 1, 6, 1);
        paneScheduel.add(lbSATURDAY8, 7, 1, 8, 1);
        paneScheduel.add(lbSATURDAY10, 9, 1, 10, 1);
        paneScheduel.add(lbSATURDAY12, 11, 1, 12, 1);
        
        Label lbSUNDAY2 = new Label();
        Label lbSUNDAY4 = new Label();
        Label lbSUNDAY6 = new Label();
        Label lbSUNDAY8 = new Label();
        Label lbSUNDAY10 = new Label();
        Label lbSUNDAY12 = new Label();
        paneScheduel.add(lbSUNDAY2, 1, 2, 2, 2);
        paneScheduel.add(lbSUNDAY4, 3, 2, 4, 2);
        paneScheduel.add(lbSUNDAY6, 5, 2, 6, 2);
        paneScheduel.add(lbSUNDAY8, 7, 2, 8, 2);
        paneScheduel.add(lbSUNDAY10, 9, 2, 10, 2);
        paneScheduel.add(lbSUNDAY12, 11, 2, 12, 2);
        
        Label lbMONDAY2 = new Label();
        Label lbMONDAY4 = new Label();
        Label lbMONDAY6 = new Label();
        Label lbMONDAY8 = new Label();
        Label lbMONDAY10 = new Label();
        Label lbMONDAY12 = new Label();
        paneScheduel.add(lbMONDAY2, 1, 3, 2, 3);
        paneScheduel.add(lbMONDAY4, 3, 3, 4, 3);
        paneScheduel.add(lbMONDAY6, 5, 3, 6, 3);
        paneScheduel.add(lbMONDAY8, 7, 3, 8, 3);
        paneScheduel.add(lbMONDAY10, 9, 3, 10, 3);
        paneScheduel.add(lbMONDAY12, 11, 3, 12, 3);
        
        Label lbTUESDAY2 = new Label();
        Label lbTUESDAY4 = new Label();
        Label lbTUESDAY6 = new Label();
        Label lbTUESDAY8 = new Label();
        Label lbTUESDAY10 = new Label();
        Label lbTUESDAY12 = new Label();
        paneScheduel.add(lbTUESDAY2, 1, 4, 2, 4);
        paneScheduel.add(lbTUESDAY4, 3, 4, 4, 4);
        paneScheduel.add(lbTUESDAY6, 5, 4, 6, 4);
        paneScheduel.add(lbTUESDAY8, 7, 4, 8, 4);
        paneScheduel.add(lbTUESDAY10, 9, 4, 10, 4);
        paneScheduel.add(lbTUESDAY12, 11, 4, 12, 4);
        
        Label lbWEDNSDAY2 = new Label();
        Label lbWEDNSDAY4 = new Label();
        Label lbWEDNSDAY6 = new Label();
        Label lbWEDNSDAY8 = new Label();
        Label lbWEDNSDAY10 = new Label();
        Label lbWEDNSDAY12 = new Label();
        paneScheduel.add(lbWEDNSDAY2, 1, 5, 2, 5);
        paneScheduel.add(lbWEDNSDAY4, 3, 5, 4, 5);
        paneScheduel.add(lbWEDNSDAY6, 5, 5, 6, 5);
        paneScheduel.add(lbWEDNSDAY8, 7, 5, 8, 5);
        paneScheduel.add(lbWEDNSDAY10, 9, 5, 10, 5);
        paneScheduel.add(lbWEDNSDAY12, 11, 5, 12, 5);
        
        Label lbTHURSDAY2 = new Label();
        Label lbTHURSDAY4 = new Label();
        Label lbTHURSDAY6 = new Label();
        Label lbTHURSDAY8 = new Label();
        Label lbTHURSDAY10 = new Label();
        Label lbTHURSDAY12 = new Label();
        paneScheduel.add(lbTHURSDAY2, 1, 6, 2, 6);
        paneScheduel.add(lbTHURSDAY4, 3, 6, 4, 6);
        paneScheduel.add(lbTHURSDAY6, 5, 6, 6, 6);
        paneScheduel.add(lbTHURSDAY8, 7, 6, 8, 6);
        paneScheduel.add(lbTHURSDAY10, 9, 6, 10, 6);
        paneScheduel.add(lbTHURSDAY12, 11, 6, 12, 6);
        
        paneScheduel.add(new Label("Day / Period"), 0, 0);
        paneScheduel.add(new Label("SATURDAY"), 0, 1); 
        paneScheduel.add(new Label("SUNDAY"), 0, 2);
        paneScheduel.add(new Label("MONDAY"), 0, 3);
        paneScheduel.add(new Label("TUESDAY"), 0, 4);
        paneScheduel.add(new Label("WEDNSDAY"), 0, 5);
        paneScheduel.add(new Label("THURSDAY"), 0, 6);
        paneScheduel.add(new Label("FRIDAY"), 0, 7);
        
        paneScheduel.add(new Label("2"),  2, 0);
        paneScheduel.add(new Label("4"),  4, 0); 
        paneScheduel.add(new Label("6"),  6, 0);
        paneScheduel.add(new Label("8"), 8, 0);
        paneScheduel.add(new Label("10"), 10, 0);
        paneScheduel.add(new Label("12"), 12, 0);
        
        String[] CourseID = new String[20];
        int i = 0;
        try {
        String queryString1 = "SELECT COURSE_ID FROM COURSES";
        ResultSet rset = stmt.executeQuery(queryString1);
        
        while(rset.next()) {
          CourseID[i] = rset.getString(1);
          i++;
        }
        String[] token = new String[20];
        String[] day = new String[20];
        // for loop
        for(int x = 0; x < i; x++){
            
        String queryString2 = "SELECT DAY_ID, PERIOD_ID FROM COURSE_PERIOD_DAY WHERE "
            + "COURSE_ID = '"+CourseID[x]+"'";
        
        
        String queryString3 = "SELECT COURSE_NAME FROM COURSES WHERE "
            + "COURSE_ID = '"+CourseID[x]+"'";
        
        ResultSet enroll = stmt.executeQuery(queryString2);
        
        if(enroll.next()){
        day[x] = enroll.getString(1);
        token[x] = enroll.getString(2);
        //SUNDAY
        if( "SUNDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY2.setText(courseName.getString(1));
                }
                
            }
            else if( "3".equals(token[x])  || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY4.setText(courseName.getString(1));
                }
                
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x]) || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x])  || "12".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbSUNDAY12.setText(courseName.getString(1));
                }
            }
            
            }
        //Monday
        if( "MONDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY2.setText(courseName.getString(1));
                }
            }
            else if( "3".equals(token[x])  || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY4.setText(courseName.getString(1));
                }
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x])  || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x])  || "12".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbMONDAY12.setText(courseName.getString(1));
                }
            }
            
            }
        //TUESDAY
        if( "TUESDAY".equals(day[x]) ){
            if( "1".equals(token[x])|| "2".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY2.setText(courseName.getString(1));
                }
                
            }
            else if( "3".equals(token[x]) || token[x] == "4"){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY4.setText(courseName.getString(1));
                }
                
            }
            else if( "5".equals(token[x]) || "6".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY6.setText(courseName.getString(1));
                }
                
            }
            else if( "7".equals(token[x]) || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY8.setText(courseName.getString(1));
                }
                
            }
            else if( "9".equals(token[x]) || "10".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY10.setText(courseName.getString(1));
                }
                
            }
            else if( "11".equals(token[x]) || "12".equals(token[x])){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTUESDAY12.setText(courseName.getString(1));
                }
                
            }
            }
        //WEDNSDAY
        if( "WEDNSDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY2.setText(courseName.getString(1));
                }
            }
            else if( "3".equals(token[x])  || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY4.setText(courseName.getString(1));
                }
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x])  || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x])  || "12".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbWEDNSDAY12.setText(courseName.getString(1));
                }
            }
            
            }
        // THURSDAY
        if( "TUESDAY".equals(day[x]) ){
            if( "1".equals(token[x])  || "2".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY2.setText(courseName.getString(1));
                }
            }
            else if( "3".equals(token[x]) || "4".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY4.setText(courseName.getString(1));
                }
            }
            else if( "5".equals(token[x])  || "6".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY6.setText(courseName.getString(1));
                }
            }
            else if( "7".equals(token[x])  || "8".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY8.setText(courseName.getString(1));
                }
            }
            else if( "9".equals(token[x])  || "10".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY10.setText(courseName.getString(1));
                }
            }
            else if( "11".equals(token[x]) || "12".equals(token[x]) ){
                ResultSet courseName = stmt.executeQuery(queryString3);
                if(courseName.next()){
                lbTHURSDAY12.setText(courseName.getString(1));
                }
            }
            }
        
        }
        }
        //for loop
        }
        catch (SQLException ex) {
            
        Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        //
        GridPane paneEditCourses = new GridPane();
        paneEditCourses.setAlignment(Pos.CENTER);
        paneEditCourses.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneEditCourses.setHgap(5.5);
        paneEditCourses.setVgap(5.5);
        
        paneEditCourses.add(new Label("Course ID"), 0, 0);
        paneEditCourses.add(new Label("Course Name"), 1, 0);
        paneEditCourses.add(new Label("Seats Left"), 2, 0);
        Label[] lbCourseID = new Label[10];
        Label[] lbCourseName = new Label[10];
        Label[] lbSeatsLeft = new Label[10];
        String[] CourseIDString = new String[10];
        int counter = 1;
        int a = 0;
        try {
        String queryString1 = "SELECT  COURSE_ID FROM STAFF "
                + "NATURAL JOIN TEATCH NATURAL JOIN COURSES "
                + "WHERE STAFF_ID = '"+StaffID+"'";
        ResultSet rset = stmt.executeQuery(queryString1);
        while(rset.next()) {
        CourseIDString[a] = rset.getString(1);
        a++;
        }
        for(int b = 0; b < a;b++){
            String queryString2 = "SELECT COURSE_NAME,SEATS_LEFT "
                + "FROM COURSES "
                + "WHERE COURSE_ID = '"+CourseIDString[b]+"'";
            ResultSet rsetCourses = stmt.executeQuery(queryString2);
            if(rsetCourses.next()) {
            lbCourseID[b] = new Label(CourseIDString[b]);
            lbCourseName[b] = new Label(rsetCourses.getString(1));
            lbSeatsLeft[b] = new Label(rsetCourses.getString(2));
            paneEditCourses.add(lbCourseID[b], 0, b+1);
            paneEditCourses.add(lbCourseName[b], 1, b+1);
            paneEditCourses.add(lbSeatsLeft[b], 2, b+1);
            counter++;
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        final int test = a;
        Button btEditCourses = new Button("Edit");
        paneEditCourses.add(btEditCourses, 0, counter);
        btEditCourses.setOnAction( e-> {
        
        GridPane paneEdit = new GridPane();
        
        TextField txtCourseCode = new TextField();
        TextField txtSeats = new TextField();
        Button btSubmit = new Button("Submit");
        paneEdit.setHalignment(btSubmit, HPos.RIGHT);
        paneEdit.add(btSubmit, 1, 2);
        paneEdit.add(new Label("Write Course Code:"), 0, 0);
        paneEdit.add(new Label("Edit Seats"), 0, 1);
        paneEdit.add(txtCourseCode, 1, 0);
        paneEdit.add(txtSeats, 1, 1);
        btSubmit.setOnAction( x->{
        
        boolean done = false;
        int seats = 0;
        seats = Integer.parseInt(txtSeats.getText());
        
        for(int m = 0;m < test;m++){
            if( seats < 0){
                JOptionPane.showMessageDialog(null, "You can't edit seats with"
                        + " Negative values !");
                done = true;
                break;
            }
            else if(seats > 51){
                JOptionPane.showMessageDialog(null, "You can't Enter Values more"
                        + " than 50 !\nReason: No Room with such capacity");
                done = true;
                break;
            }
            if(txtCourseCode.getText().equals(CourseIDString[m])){
                String updateString = "UPDATE COURSES "
                + "SET SEATS_LEFT='"+txtSeats.getText()+"' "
                + "WHERE COURSE_ID = '"+CourseIDString[m]+"'";
                try {
                    stmt.executeUpdate(updateString);
                }
                catch (SQLException ex) {
                    Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "You've edited the seats");
                done = true;
            }
            done = false;
        }
        if(!done){
            JOptionPane.showMessageDialog(null, "You can't edit the seats\n"
                    + "Reasons: You've entered wrong Course ID or you don't"
                    + " Teach it");
        }

        });
        
        Scene editScene = new Scene(paneEdit,252,100);
        EditCourse.setScene(editScene);
        EditCourse.setTitle("Edit Course Page");
        EditCourse.show();
        });
        //
        GridPane paneStudent = new GridPane();
        paneStudent.setAlignment(Pos.CENTER);
        paneStudent.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneStudent.setHgap(5.5);
        paneStudent.setVgap(5.5);
        Tab tabStudentsInfo = new Tab("Students Info");
        
        paneStudent.add(new Label("Student ID"), 0, 0);
        paneStudent.add(new Label("Student Name"), 1, 0);
        paneStudent.add(new Label("Grade"), 2, 0);
        
        Label[] lbStudentID = new Label[10];
        Label[] lbStudentName = new Label[10];
        Label[] lbStudentGrade = new Label[10];
        String[] StudentIDString = new String[10];
        String[] StudentGradeString = new String[10];
        int b = 0;
        try {
        String queryString1 = "SELECT STUDENT_ID, GREADE "
                + "FROM enrollment "
                + "NATURAL JOIN TEATCH "
                + "NATURAL JOIN STAFF where "
                + "STAFF_ID = '"+StaffID+"' AND GREADE = 'U'";
            ResultSet rset= stmt.executeQuery(queryString1);
            while(rset.next()){
                StudentIDString[b] = rset.getString(1);
                StudentGradeString[b] = rset.getString(2);
                b++;
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        for(int z = 0; z<b; z++){
        try {
        String queryString1 = "SELECT FIRSTNAME,LASTNAME "
                + "FROM Students "
                + "WHERE STUDENT_ID='"+StudentIDString[z]+"'";
            ResultSet rset= stmt.executeQuery(queryString1);
            if(rset.next()){
                lbStudentID[z] = new Label(StudentIDString[z]);
                lbStudentName[z] = new Label(rset.getString(1)+" "+rset.getString(2));
                lbStudentGrade[z] = new Label(StudentGradeString[z]);
                paneStudent.add(lbStudentID[z], 0, z+1);
                paneStudent.add(lbStudentName[z], 1, z+1);
                paneStudent.add(lbStudentGrade[z], 2, z+1);
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(CollagePortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        tabStudentsInfo.setContent(paneStudent);
        tabProfile.setContent(pane);
        tabScheduel.setContent(paneScheduel);
        tabEditCourses.setContent(paneEditCourses);
        
        tabPane.getTabs().addAll(tabProfile,tabScheduel,tabEditCourses,tabStudentsInfo);
        
        borderPane.setCenter(pane);
        borderPane.setTop(tabPane);
        
        Scene scene = new Scene(borderPane, 1300, 500);
        StaffPortalStage.setScene(scene);
        StaffPortalStage.show();
        StaffPortalStage.setTitle("Staff Portal");
    }
    
    private void initializeDB() {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");
      
      // Establish a connection
      Connection connection = DriverManager.getConnection
      ("jdbc:mysql://localhost/collage", "scott", "tiger");
      System.out.println("Database connected");
      
      // Create a statement
      stmt = connection.createStatement();
    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Connection Error: Unkown Database\n"
              + "Program won't work, please close the program and re-enter"
              + "The database name inside connection");
    }
  }
    
    
    
    
    
    public static void main(String[] args){
        launch(args);
    }
    
}
