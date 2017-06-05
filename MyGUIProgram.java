//This is my program of a very simple text editor
import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
/*Well, I have used Hungarian notation for declaring the variable names. It is bad I've heard but  
 * so far it is serving my 
 * purpose pretty well */
public class MyGUIProgram{
	//The following variables will decide the look of the application
	static JFrame fFrame=new JFrame("Simple text editor");
	static JButton bSave=new JButton("SAVE");
	static JButton bExit=new JButton("EXIT");
	static JButton bOpen=new JButton("OPEN");
	static JButton bClear=new JButton("CLEAR");
	static JTextField tfFileName=new JTextField();
	static JTextArea taEdit=new JTextArea();
	static JLabel lbHeading=new JLabel("LOG:"); 
	static JTextArea lbErrorMessage=new JTextArea();
	//The following variables will implement the mechanism of the application
	static String strFileName=null;
	static String strCont=null;
	static String strDisp=null;
	
	public static void main(String args[]){
		fFrame.setSize(940,480);
		bSave.setBounds(260,400,100,40);
		bExit.setBounds(370,400,100,40);
		bOpen.setBounds(150,400,100,40);
		bClear.setBounds(725,400,100,40);
		fFrame.add(bSave);
		fFrame.add(bExit);
		fFrame.add(bOpen);
		fFrame.add(bClear);
		tfFileName.setBounds(10,10,620,20);
		fFrame.add(tfFileName);
		taEdit.setBounds(10,40,620,330);
		fFrame.add(taEdit);
		lbHeading.setBounds(640,10,150,20);
		fFrame.add(lbHeading);
		lbErrorMessage.setBounds(640,40,250,330);
		fFrame.add(lbErrorMessage);
		bSave.addActionListener(new ActionListener()
		{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			strFileName=tfFileName.getText();	
			strCont=taEdit.getText();
			try {
				fwrite(strFileName,strCont);
				lbErrorMessage.setText("Saved! to "+strFileName);
			} catch (IOException e) {
				
				
			}
		}
		});
		bExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
			
		});
		bOpen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String strInFileName;
				strInFileName=tfFileName.getText();
				try {
					FileInputStream in=new FileInputStream(strInFileName);
					int i=0,n=0;String s;
					StringWriter sw=new StringWriter();
					try {
						while((i=in.read())!=-1){
							sw.append((char)i);
						}
						in.close();
						strDisp=sw.toString();
						taEdit.setText(strDisp);
					
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						
					}
				} catch (FileNotFoundException e1) {
					lbErrorMessage.setText("The File does\nnot exit or\nyou have given an invalid\ndirectory name");
					
					
				}
			}});
		bClear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				lbErrorMessage.setText(null);
				
			}
			
		});
		ImageIcon icon=new ImageIcon("icon.png");
		fFrame.setIconImage(icon.getImage());
		fFrame.setLayout(null);
		fFrame.setVisible(true);
		
	}
	public static void fwrite(String fn,String cont) throws IOException{
		try {
			FileOutputStream fout=new FileOutputStream(fn);
            byte b[]=cont.getBytes();//converting string into byte array    
            fout.write(b);
            fout.close();
		} catch (FileNotFoundException e) {
			lbErrorMessage.setText("Error in writing to file\nPerhaps you have supplied\nthe wrong directory name.");
			
		}
		
	}
}