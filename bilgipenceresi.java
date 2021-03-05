package alverdef;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	public class bilgipenceresi implements ActionListener {
		
		static JFrame f;
		
		public static void duyur(String duyuru) {
			    JPanel panel=new JPanel();
				f= new JFrame(); 
				f.setBounds(200, 100,200, 100);
				f.setResizable(false);
				
		        JLabel duyur =new JLabel(duyuru);
		        
		        JButton dugme=new JButton("Kapat");
		        dugme.addActionListener( new bilgipenceresi());
		        dugme.setBounds(10, 210, 80, 30);
		        panel.add(dugme);
		        
		        Container contentPane = f.getContentPane();
		  	  
			    contentPane.add(duyur, BorderLayout.PAGE_START);
			    contentPane.add(panel, BorderLayout.PAGE_END);
			    
			    f.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			f.dispose();
		}

	}