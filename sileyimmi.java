package alverdef;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
	
public class sileyimmi  {
	
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JDialog yavru ;
	static JLabel baslik ;
	static String cevap="";
		
			public static void sonkarar(String nereden) {
				
				yavrudugme= new JButton("Evet");
				yavrudugme2 = new JButton("Ýptal");
				
				if (nereden.equals("carikart")) {
				yavru = new JDialog(carikartdegistireklesil.yavru,"EMÝN MÝSÝNÝZ ???",true); 
				}
				
				if (nereden.equals("firmakart")) {
					yavru = new JDialog(firmahesaplaninizdegistireklesil.yavru,"EMÝN MÝSÝNÝZ ???",true); 
					}
				
				if (nereden.equals("carihareket")) {
					yavru = new JDialog(carihareketdegistireklesil.yavru,"EMÝN MÝSÝNÝZ ???",true); 
					}
				
				
				yavru.setResizable(false);
					
				baslik = new JLabel("         Varsa Ýliþkili Tüm Kayýtlar da Silinecektir" );
				baslik.setBounds(15,5,270,20);
				
				yavru.setSize(310,100); 
				yavru.setLayout(null);
				yavru.setLocation(800,50);
				yavru.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				yavru.add(yavrudugme);
				yavru.add(yavrudugme2);
				yavru.add(baslik);
				
				yavrudugme.setBounds(40,30,80,20);
				yavrudugme.setVisible(true);
				yavrudugme.addActionListener(new ActionListener() {     
		            public void actionPerformed(ActionEvent e) {
		            	cevap="evet";
					    yavru.dispose();
		            }
		        });
				
				yavrudugme2.setBounds(180,30,80,20);
				yavrudugme2.setVisible(true);
				yavrudugme2.addActionListener(new ActionListener() {     
		            public void actionPerformed(ActionEvent f) {
		            	cevap="hayýr";
					    yavru.dispose();
		            }
		        });
				yavru.setVisible(true);
			}
}