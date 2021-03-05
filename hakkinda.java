package alverdef;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class hakkinda implements ActionListener {
	
	static JTextArea area ;
	static JFrame f;
	
	public static void hakk(String hakki) {
		    JPanel panel=new JPanel();
			f= new JFrame(); 
			f.setResizable(false);
	        area=new JTextArea();  
	        area.setEditable(false);
	        area.setBounds(10,30, 200,200);  
	        area.setLineWrap(true);
	        area.setWrapStyleWord(true);
	        JScrollPane sp=new JScrollPane(area);
	        sp.setPreferredSize(new Dimension(180,120));
	        
	        JButton dugme=new JButton("Tamam");
	        dugme.addActionListener( new hakkinda());
	        dugme.setBounds(10, 210, 110, 40);
	        panel.add(dugme);
	        
	        Container contentPane = f.getContentPane();
	  	  
		    contentPane.add(sp, BorderLayout.PAGE_START);
		    contentPane.add(panel, BorderLayout.PAGE_END);
		    
		    if (hakki.equals("hakkinda")) {
		    area.setText("        ***Yazýlým Hakkýnda*** \r\n\r\n 2021-Hasan Emre Aral \r\n emre@aral.web.tr \r\n v.1.0 ALFA");
		    } else {
		    	 
		    	 area.setText("                ***YARDIM*** \r\nProgram Java 1.8 ve üzerinde çalýþýr.\r\nMysql versiyonu 5.7 olmalýdýr.\r\nDaha detaylý bilgi için emre@aral.web.tr 'ye mail atýnýz.");	
		    	
		    }
		
		    f.setLocation(450, 300);
	        f.pack();
	        f.setLayout(null);  
	        f.setVisible(true);  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		f.dispose();
		
	}

}
