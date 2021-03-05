package alverdef;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
	
public class caribul implements ActionListener {
	
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JDialog yavru ;
	static JLabel baslik2,baslik ;
	static JTextField alan;
	
	private enum evethayir {
		basevet,bashayir;
	}
	
			public static void nebulayim() {
				yavrudugme= new JButton("Tamam");
				yavrudugme2 = new JButton("Ýptal");
				yavru = new JDialog(anaekran.frame,"Cari Arama Ekraný",true); 
				yavru.setResizable(false);
				
				alan=new JTextField() ;
				alan.setBounds(30,37,240,20);
					
				baslik2 = new JLabel("Aranacak Firma Adýný Yazýn " );
				baslik2.setBounds(70,8,270,20);
				
				baslik = new JLabel("(*)Tüm Kayýtlar Ýçin Boþ Býrakýp Tamam'a Basýn" );
				baslik.setBounds(15,90,270,20);
				
				yavru.setSize(310,150); 
				yavru.setLayout(null);
				yavru.setLocation(800,50);
				yavru.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				yavru.add(yavrudugme);
				yavru.add(yavrudugme2);
				yavru.add(baslik2);
				yavru.add(alan);
				yavru.add(baslik);
				
				yavrudugme.setBounds(40,65,80,20);
				yavrudugme.setVisible(true);
				yavrudugme.addActionListener(new caribul());
				yavrudugme.setActionCommand(evethayir.basevet.name());
				
				yavrudugme2.setBounds(180,65,80,20);
				yavrudugme2.setVisible(true);
				yavrudugme2.addActionListener(new caribul());
				yavrudugme2.setActionCommand(evethayir.bashayir.name());
				
				yavru.setVisible(true); 
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getActionCommand()==evethayir.basevet.name())	{
					anaekran.aranankelime=alan.getText().trim();
					
					if (anaekran.aranankelime.equals("")) {
						anaekran.frame.dispose();
						anaekran.anametod();
					    yavru.dispose();
					}
					
					if (anaekran.aranankelime.length()<3) {
						anaekran.aranankelime="";
						alan.setText("");
						baslik2.setText("En Az 3 Karakter Girmelisiniz");
						return;
					}
					anaekran.frame.dispose();
					anaekran.anametod();
				    yavru.dispose();
					
				}
		    
				if (e.getActionCommand()==evethayir.bashayir.name())	{
					anaekran.aranankelime="";
				    yavru.dispose();
					
				}
		
	}

}
