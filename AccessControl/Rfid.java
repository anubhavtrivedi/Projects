import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fazecast.jSerialComm.SerialPort;

public class Rfid {
	
	static SerialPort chosenPort;
	
	public static String getProperty(String cardID){
		Properties prop=new Properties();
		System.out.println(cardID);
		String details=null;
		try {
			Reader w=Files.newBufferedReader(Paths.get("data.txt"));
			prop.load(w);
			details=prop.getProperty(cardID,"Does NOt Exists");
			
		} catch (IOException e) {
			
			e.getClass();
		}
		return details;
		
	}
	public static void setLearningProperty(){
		MachineLearning m=null;
		try(ObjectInputStream i=new ObjectInputStream(Files
				.newInputStream(Paths.get("machineLearning.dat")))
				){
			m=(MachineLearning)i.readObject();
			for(int j=1;j<4;j++){
			if(m.c[j]%2==1){
				m.cflag[j]++;
				
			}System.out.println(m.c[j]+"  "+m.cflag[j]);
		}
			ObjectOutputStream o=new ObjectOutputStream(Files
				.newOutputStream(Paths.get("machineLearning.dat")));
			o.writeObject(m);
			o.close();
			}
		catch(Exception e){
			System.out.println(e.getCause());
		}
	}
	public static void getLearningProperty(String cardID){
		Properties prop=new Properties();
		String details=null;
		MachineLearning m= null;
		try {ObjectInputStream i=new ObjectInputStream(Files
				.newInputStream(Paths.get("machineLearning.dat")));
				
			try {
				m=(MachineLearning)i.readObject();
				i.close();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}

			ObjectOutputStream o=new ObjectOutputStream(Files
				.newOutputStream(Paths.get("machineLearning.dat")));
		
			Reader w=Files.newBufferedReader(Paths.get("data.txt"));
			prop.load(w);
			details=prop.getProperty(cardID,"Does NOt Exists");
			if(details.equals("\n\n\tAnubhav Trivedi\n\tPRN-1400000185\n"
				+"\tRollno- 26\n\tDepartment- Computer Science"))
			{
				m.c[1]+=1;
			}
			if(details.equals("\n\n\tAnshika Agarwal\n\tPRN-1400000184\n"
				+"\tRollno- 25\n\tDepartment- Computer Science"))
				{
					m.c[2]+=1;
				}
			if(details.equals("\n\n\tKEY CHAIN \n\t RFID TAG"))
				{
					m.c[3]+=1;
				}
			
			o.writeObject(m);
			o.close();
			
		} catch (IOException e) {
			
			e.getClass();
		}
	
		
	}
	public static void setProperty(String cardID,String cardDI1,String cardID2){
		Properties prop=new Properties();
		prop.setProperty(cardID,"\n\n\tAnubhav Trivedi\n\tPRN-1400000185\n"
				+"\tRollno- 26\n\tDepartment- Computer Science");
		prop.setProperty(cardDI1,"\n\n\tKEY CHAIN \n\t RFID TAG");
		
		prop.setProperty(cardID2,"\n\n\tAnshika Agarwal\n\tPRN-1400000184\n"
				+"\tRollno- 25\n\tDepartment- Computer Science");
		
		
		try {
			Writer w=Files.newBufferedWriter(Paths.get("data.txt"));
			prop.store(w,"1");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
				
	}
	public static void main(String[] args) {
		// create and configure the window
		JFrame window = new JFrame();
		window.setTitle("RFID Controled Access");
		window.setSize(400, 400);
		JTextArea textBox=new javax.swing.JTextArea();
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create a drop-down box and Action button, placing them
		final JComboBox<String> portList = new JComboBox<String>();
		final JButton logFile = new JButton("LogFile");
		final JButton getInfo = new JButton("ShowDetails");
		final JButton allowAll = new JButton("AllowAll");
		final JButton reset = new JButton("RESET");
		final JButton freezeSystem = new JButton("FreezeSystem");
		
		JPanel topPanel = new JPanel();
		JPanel panel = new JPanel();
		topPanel.add(portList);
		topPanel.add(getInfo);
		//topPanel.add(logFile);
		JPanel midPanel= new JPanel();
		midPanel.add(allowAll);
		midPanel.add(reset);
		midPanel.add(freezeSystem);
		textBox.setColumns(20);
		textBox.setRows(14);
		window.add(topPanel, BorderLayout.NORTH);
		window.add(midPanel, BorderLayout.CENTER);
		window.add(textBox, BorderLayout.SOUTH);
		//delete
		
		
		
		// populate the drop-down box
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++)
			portList.addItem(portNames[i].getSystemPortName());
		
		
				// the AllowAll button functioning
				allowAll.addActionListener(new ActionListener(){
					@Override public void actionPerformed(ActionEvent arg0) {
						if(allowAll.getText().equals("AllowAll")) {
							chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
							chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
							if(chosenPort.openPort()) {
								allowAll.setText("Disconnect");
								portList.setEnabled(false);
								Thread thread = new Thread(){
									@Override public void run() {
									try {Thread.sleep(100); } catch(Exception e) {}
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										for(int k=0;k<5;k++){try {Thread.sleep(100); } catch(Exception e) {}
											output.println("1"); 		//sending allow all value
											output.flush();
											
										}
									}
								};
								thread.start();
							}
						} else {
							chosenPort.closePort();
							portList.setEnabled(true);
							allowAll.setText("AllowAll");
						}
					}
				});
				// the FreezeAll button functioning
				freezeSystem.addActionListener(new ActionListener(){
					@Override public void actionPerformed(ActionEvent arg0) {
						if(freezeSystem.getText().equals("FreezeSystem")) {
							chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
							chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
							if(chosenPort.openPort()) {
								freezeSystem.setText("Disconnect");
								portList.setEnabled(false);
								Thread thread = new Thread(){
									@Override public void run() {
									try {Thread.sleep(100); } catch(Exception e) {}
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										for(int k=0;k<5;k++){try {Thread.sleep(100); } catch(Exception e) {}
											output.println("2");   //sending block value
											output.flush();
											
										}
									}
								};
								thread.start();
							}
						} else {
							chosenPort.closePort();
							portList.setEnabled(true);
							freezeSystem.setText("FreezeSystem");
						}
					}
				});


				// the Reset button functioning
				reset.addActionListener(new ActionListener(){
					@Override public void actionPerformed(ActionEvent arg0) {
						if(reset.getText().equals("RESET")) {
							chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
							chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
							if(chosenPort.openPort()) {
								reset.setText("Disconnect");
								portList.setEnabled(false);
								Thread thread = new Thread(){
									@Override public void run() {
									try {Thread.sleep(100); } catch(Exception e) {}
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										for(int k=0;k<5;k++){
											try {Thread.sleep(100); } catch(Exception e) {}
											output.println("10");   //sending reset value
											output.flush();
											
										}
									}
								};
								thread.start();
							}
						} else {
							chosenPort.closePort();
							portList.setEnabled(true);
							reset.setText("RESET");
						}
					}
				});
				// the getInfo button functioning
				getInfo.addActionListener(new ActionListener(){
					@Override public void actionPerformed(ActionEvent arg0) {
						SerialPort ports[] = SerialPort.getCommPorts();
			             
		                SerialPort port = ports[0];
		                if(port.openPort()) {
		                        System.out.println("Successfully opened the port.");
		                } else {
		                        System.out.println("Unable to open the port.");
		                        return;
		                }
		                port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		               String key=null;
		               int count=0;
		                // enter into an infinite loop that reads from the port and updates the GUI
		                Scanner data = new Scanner(port.getInputStream());
		                while(data.hasNextLine()) {
		                //	System.out.println(data.nextLine());
		                	if(count==1){
		                      key=data.nextLine();
		                      break;}
		                	count++;
		                }
		                while(data.hasNextLine()) {
		                //	System.out.println(data.nextLine());
		                	if(count==1){
		                      key=data.nextLine();break;}
		                	count++;
		                }
		                while(data.hasNextLine()) {
		                	//System.out.println(data.nextLine());
		                	if(count==1){
		                      key=data.nextLine();break;}
		                	count++;
		                }
		                
		                if(key.length()>=4){
		                	System.out.println(key);
		                	key=getProperty(key);
		                	textBox.setText(key);
		                	
		                }
		         
		                port.closePort();
					}
				});
				
				// the getInfo button functioning
				logFile.addActionListener(new ActionListener(){
					@Override public void actionPerformed(ActionEvent arg0) {
					
		               
		              
					}
				});
		// show the window
		window.setVisible(true);
	}
	
	

}