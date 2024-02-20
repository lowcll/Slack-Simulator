import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.*;

public class GWackClientGUI {


JFrame frame;
JTextArea messageTextInput;    
JTextArea messageTextsArea;    
JTextArea activeMembersArea;   
JTextField usernameInput;      
JTextField portArea;           
JTextField ipArea;            
JButton sendMessage;           
JButton Inactive;              
JButton Busy;               
JButton Active;               
 int messageScreenCounter;                                       
 ArrayList<String> ActiveMembers = new ArrayList<String>() ;     
 String username;                                                
 String text;                                                  
 ColorUIResource white= new ColorUIResource(255, 255, 255); // White color
 ColorUIResource black= new ColorUIResource(211, 211, 211); // Light Grey Color
 private static final int HEIGHT = 500;
 private static final int WIDTH = 800;


 public GWackClientGUI(){

  //Frame and title (Window)    
  frame = new JFrame("GWack -- GW Slack Simulator (disconnected)");//give title to frame
  frame.setSize(WIDTH,HEIGHT);//set frame size

  //Panel where all connection related objects are
  JPanel connectionPanel = new JPanel();//set up connectionpanel
        connectionPanel.setLayout(new GridLayout(0,1));//set up dimensions            
        connectionPanel.setBackground(white);//make it white

  //Panel where active members reside
  JPanel membersPanel = new JPanel();//set up active members panel
        membersPanel.setLayout(new BorderLayout());//set up its layout
        membersPanel.setBackground(white);//make it white
  
  //Set panel where status buttons reside
  JPanel statusPanel = new JPanel();//set up status buttons panel
        membersPanel.add(statusPanel,BorderLayout.SOUTH);//set it under the members panel
        statusPanel.setBackground(white);//make it white

  //Set panel where messages sent are shown
  JPanel messageBoardPanel = new JPanel();//set up message board panel
        messageBoardPanel.setLayout(new BorderLayout());//set it in middle
        messageBoardPanel.setBackground(white);//set it white

  //Whole Compose message  Area
  JPanel bottomPanel = new JPanel();//set up whole bottom area             

  //composeMessagePanel 
  JPanel composePanel = new JPanel();//set panel to compose        
        bottomPanel.add(composePanel,BorderLayout.NORTH);//add compose panel to bottom panel
        bottomPanel.setBackground(white);//set it white
        composePanel.setBackground(white);//set it white 

  //Name tag
  JLabel usernameLabel = new JLabel("Name:");//set name label next to user input
        connectionPanel.add(usernameLabel);//add it to connection panel
        connectionPanel.setLayout(new FlowLayout(FlowLayout.TRAILING)); //make it trail
  //Insert name area
  usernameInput = new JTextField("",15);//Set the usernameInput 
        usernameInput.setBackground(black);//set the color to grey
        connectionPanel.add(usernameInput);//add the input area to to connection panel

  //IP tag
  JLabel ipLabel = new JLabel("IP Address:");//set IP Address label next to ip input
        connectionPanel.add(ipLabel);//add it to connection panel
        //Insert IP area
        ipArea = new JTextField("",15);//set the size
        ipArea.setBackground(black);//set the color to grey
        connectionPanel.add(ipArea);//add it to the connection panel

  //Port Tag
  JLabel portLabel = new JLabel("Port:");//set Port: label next to port input
        portLabel.setBounds(0,0,0,0);//set its size
        connectionPanel.add(portLabel);//add it to connection panel
        portArea = new JTextField("",10);//insert port area
        portArea.setBackground(black);//set the color to grey
        connectionPanel.add(portArea);//add it to connection panel


  JButton connect = new JButton("  Connect  ");//create connection button
        connectionPanel.add(connect);//add it to the connection panel
        connect.addActionListener(new ActionListener(){//give action to button
    public void actionPerformed(ActionEvent e) {//give action to button 

        
        //Check if userinput is empty
        if(usernameInput.getText().equals("") || usernameInput.getText().isEmpty()) { 
            JOptionPane.showMessageDialog(frame, //Show error message window
            "Please Input Name.",
            "Error Connecting",
            JOptionPane.ERROR_MESSAGE);
            return;
         } 
        //Check if ipInput is empty
        if(ipArea.getText().equals("") || ipArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, //Show error message window
            "Please Input IP.",   
            "Error Connecting",
            JOptionPane.ERROR_MESSAGE);
            return;
         }
         //Check if portInput is empty
        if(portArea.getText().equals("") || portArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, //Show error message window
            "Please Input Port.",
            "Error Connecting",
            JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
         username = usernameInput.getText(); //get the username and make it a string

            if (connect.getText().equals("  Connect  ")){//If button reads connect
                //text changes
                connect.setText("Disconnect");//Set it to disconnect
                frame.setTitle("GWack -- GW Slack Simulator (connected)");//Change frame title     
                //functionality changes    
                sendMessage.setEnabled(true);//Allow message to be sent
                Active.setEnabled(true);//Allow active button to be clicked
                Inactive.setEnabled(true);//Allow inactive button to be clicked
                Busy.setEnabled(true);//Allow busy button to be clicked
                usernameInput.setEditable(false);//Set so username cant be changed
                ipArea.setEditable(false);//Set so ip cant be changed
                portArea.setEditable(false);//Set so port cant be changed  
                //color sets
                usernameInput.setBackground(black);//Set background color to light grey
                ipArea.setBackground(black);//Set background color to light grey  
                portArea.setBackground(black);//Set background color to light grey
                //Active Members Printing
                ActiveMembers.add(username);//Add username to active members list
                  for (String curUser : ActiveMembers)//Iterate through active members list
             { 		    
                activeMembersArea.append(curUser + "\n");//Add the curUsername to the active members list
             }
        } 
              else if (connect.getText().equals("Disconnect")){//If button reads disconnect
                //text changes                                  
                connect.setText("  Connect  ");//Set it to connect
                frame.setTitle("GWack -- GW Slack Simulator (disconnected)");//Change frame title     
               //functionality changes                                                 
                sendMessage.setEnabled(false);//Disallow message to be sent
                Active.setEnabled(false);//Disallow active button to be clicked
                Inactive.setEnabled(false);//Disallow inactive button to be clicked
                Busy.setEnabled(false);//Disallow busy button to be clicked
                usernameInput.setEditable(true);//Set so username can be changed
                ipArea.setEditable(true);//Set so ip can be changed
                portArea.setEditable(true);//Set so port can be changed 
                //color sets                                                            
                usernameInput.setBackground(black);//Set background color to light grey 
                ipArea.setBackground(black);//Set background color to light grey 
                portArea.setBackground(black);//Set background color to light grey 
                //Active Members Printing                                                  
                activeMembersArea.setText(null);//clear active members list                                                               
               ActiveMembers.remove(username);//Remove username to active members list                                         
               for (String curUser : ActiveMembers)                                        
                { 		   
             activeMembersArea.append(curUser + "\n");//Re-print list                 
              }
            }
    }
 });

  JLabel memberLabel = new JLabel("Members Online");//Set members online tag
  membersPanel.add(memberLabel,BorderLayout.NORTH);//Add label to membersPanel
  activeMembersArea = new JTextArea(null,10,10); //set the active user text area
  activeMembersArea.setBackground(black);//set it to light grey
  membersPanel.add(activeMembersArea);//add active members to membersPanel
  activeMembersArea.setEditable(false);//Set it so it cant be edited

  JLabel messageLabel = new JLabel("Messages");//Set message board label
  messageBoardPanel.add(messageLabel,BorderLayout.NORTH);//add it to board panel
  JLabel gap = new JLabel(" ");//lil gap
  messageBoardPanel.add(gap,BorderLayout.WEST);//add it to board panel
  messageTextsArea = new JTextArea(null,10,15); //set up the text adea
  messageTextsArea.setBackground(black);//set it to light grey
  messageBoardPanel.add(messageTextsArea);//add it to boardPanel
  messageTextsArea.setEditable(false);//make it uneditable

  JLabel composeLabel = new JLabel("Compose");//Set compose tag
  composePanel.add(composeLabel,BorderLayout.NORTH);//add it to compose panel

  messageTextInput = new JTextArea("Insert message here",4,20); //set TextInput area
  messageTextInput.setBackground(black);//set it to light grey
  composePanel.add(messageTextInput,BorderLayout.SOUTH);//add it to composePanel

messageTextInput.addKeyListener(new KeyAdapter() {//Read for key
    @Override
    public void keyPressed(KeyEvent e) {//Upon Key Pressed
      if(connect.getText().equals("Disconnect")){//Only works if its connected to a server
        if(e.getKeyChar() == '\n'){//if Enter key is pressed
          text = messageTextInput.getText();//get the text input and set it to string
          username = usernameInput.getText();//get username
            if(messageScreenCounter < 100){//Once it hits 100, remove top message
                 messageTextsArea.append("[" + username + "] : " + text + "\n");//Print message 
                 messageTextInput.selectAll();//Select text in input area
                 messageTextInput.replaceSelection(null);//set it to null
                messageScreenCounter++;//message counter++
             }else{
               String s = messageTextsArea.getText();//Get string text of message Text area
                s = s.substring(s.indexOf("\n")+1);//add null factor and one
                messageTextsArea.setText(s);//set text to that
                messageTextsArea.append("[" + username + "] : " + text + "\n");//print message
                messageTextInput.selectAll();//select text in input area
                messageTextInput.replaceSelection(null);//set it all to null
            }
         }
      }                    
    }
 });
  sendMessage = new JButton("Send");//set up send buttom  
  sendMessage.setEnabled(false);//make it unclickable
  sendMessage.addActionListener(new ActionListener(){//Add an action to button                                                                                                        
   public void actionPerformed(ActionEvent e) {//Add an action button                                                           
      text = messageTextInput.getText();//get the text input and set it to string                                                                                               
      username = usernameInput.getText();//get username                                                                                              
      if(messageScreenCounter < 100){//Once it hits 100, remove top message                            
        messageTextsArea.append("[" + username + "] : " + text + "\n");//print message                                                                
        messageTextInput.selectAll();//select text in input area                            
        messageTextInput.replaceSelection(null);//set it all to null                                                 
      messageScreenCounter++;//messagecounter ++                    
      }else{                                                                 
          String s = messageTextsArea.getText();//Get string text of message Text area                                        
          s = s.substring(s.indexOf("\n")+1);//add null factor and one                                         
          messageTextsArea.setText(s);//set text to that                             
          messageTextsArea.append("[" + username + "] : " + text + "\n"); //print message                                                                 
          messageTextInput.selectAll();//select text in input area                              
        messageTextInput.replaceSelection(null);//set it all to null                                                
      }
     }
} );

//Inactive Button
 Inactive = new JButton("Inactive");//New button for Inactive                                  
 Inactive.setEnabled(false);//Set it to be unclickable                                         
 Inactive.addActionListener(new ActionListener(){//Add action to button             
  public void actionPerformed(ActionEvent e) {//Add action to button          
    Inactive.setEnabled(false);//Set inactive to be unclickable                                         
    Busy.setEnabled(true);//Set Busy to be clickable                                                
    Active.setEnabled(true);//Set Active to be clickable                                           
    for (String curUser : ActiveMembers)//Iterate thru all users                                               
          { 		    
            if(usernameInput.getText().equals(curUser)){//check for our user                                                                     
             activeMembersArea.setText(curUser + " [Inactive] ");//Add "[Inactive]"                                                                            
          }                                                                                                                                    
    }
  }
});

//Busy Button
Busy = new JButton("Busy");//New button for Busy                                                 
Busy.setEnabled(false);//Set it to be unclickable                                          
 Busy.addActionListener(new ActionListener(){//Add action to button                                                   
  public void actionPerformed(ActionEvent e) {//Add action to button                                                       
    Inactive.setEnabled(true);//Set inactive to be clickable                                      
    Busy.setEnabled(false);//Set Busy to be unclickable                                    
    Active.setEnabled(true);//Set Active to be clickable                                     
    for (String curUser : ActiveMembers)//Iterate thru all users                                              
          { 		    
            if(usernameInput.getText().equals(curUser)){//check for our user                                                              
             activeMembersArea.setText(curUser + " [Busy] ");//Add "[Busy]"                                                                      
          }
    }
  }
});

Active = new JButton("Active");//New button for Active                                             
Active.setEnabled(false);//Set it to be unclickable                                                  
Active.addActionListener(new ActionListener(){//Add action to button                                                                   
  public void actionPerformed(ActionEvent e) {//Add action to button                                                                   
    Inactive.setEnabled(true);//Set inactive to be clickable                                                    
    Busy.setEnabled(true);///Set Busy to be clickable                                                
    Active.setEnabled(false);//Set Active to be unclickable                                                   
    for (String curUser : ActiveMembers)//Iterate thru all users                                                           
          { 		    
            if(usernameInput.getText().equals(curUser)){//check for our user                                 
             activeMembersArea.setText(curUser + " [Active] ");//Add "[Active]"                                                                                       
          }
    }
  }
});

  //Set Bottom Panel
  bottomPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));//set layout for bottomPanel
  bottomPanel.add(sendMessage,BorderLayout.SOUTH);//add sendmessage to bottomPanel

 //status buttons
  statusPanel.add(Active,BorderLayout.SOUTH);//add active to statusPanel
  statusPanel.add(Busy,BorderLayout.SOUTH);//add busy to statusPanel
  statusPanel.add(Inactive,BorderLayout.SOUTH);//add inactive to statusPanel

  //Add panels to frame
  frame.add(connectionPanel,BorderLayout.NORTH);//add connectionPanel to frame
  frame.add(membersPanel,BorderLayout.WEST);//add membersPanel to frame
  frame.add(messageBoardPanel,BorderLayout.CENTER);//add messageBoardPanel to frame   
  frame.add(bottomPanel,BorderLayout.SOUTH);//add bottomPanel to frame            

  //Put it all together
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Make it so it exits on close
  frame.pack();//pack it all 
  frame.setVisible(true);//make it visible
 }

 public static void main(String[] args){
  new GWackClientGUI();
 }
}