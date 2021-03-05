package alverdef;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class notdefteripenceresi implements ActionListener {
	
	static JTextArea area ;
	
	public static void notdefteri() {
		    JPanel panel=new JPanel();
			JFrame f= new JFrame("Not Defteri"); 
			f.setResizable(false);
	        area=new JTextArea();  
	        area.setBounds(10,30, 200,200);  
	        area.setLineWrap(true);
	        area.setWrapStyleWord(true);
	        JScrollPane sp=new JScrollPane(area);
	        sp.setPreferredSize(new Dimension(272, 331));
	        
	        JButton dugme=new JButton("Kaydet");
	        dugme.addActionListener( new notdefteripenceresi());
	        dugme.setBounds(10, 210, 80, 30);
	        panel.add(dugme);
	        
	        Container contentPane = f.getContentPane();
	  	  
		    contentPane.add(sp, BorderLayout.PAGE_START);
		    contentPane.add(panel, BorderLayout.PAGE_END);
		    
		    try{                                              
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
						
						Statement stmt=con.createStatement();  
				        ResultSet rs=stmt.executeQuery("SELECT * FROM notdefteri ;"); 
				
				while(rs.next()) {
					
					 rs.getString(1);  
					 area.setText( rs.getString(1));
				       
				}
				con.close();  
				
				}catch(Exception e){ System.out.println(e);}  
			
	        
	        f.pack();
	        f.setLayout(null);  
	        f.setVisible(true);  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
	     PreparedStatement ps = null;
	     String ekranj1=area.getText();
	     try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alverdefdb?useUnicode=true&characterEncoding=UTF-8","emrearal", "123456"); 
	        ps = con.prepareStatement("update notdefteri set notlarim=?;");
	        ps.setString(1,ekranj1);
	        ps.executeUpdate();
	        con.close();
	        
	        } catch (Exception f) {
	           f.printStackTrace();
	     }	
	}

}
