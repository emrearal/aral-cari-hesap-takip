package alverdef;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class carikartdegistireklesil implements ActionListener {
	
	static JLabel baslik,lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9,lb10,lb11;
	static JButton evet,hayir;
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JDialog yavru ;
	static JTextField jt1,jt2,jt3,jt4,jt5,jt6,jt7,jt8,jt9,jt10;
	static String ekranj1,ekranj2,ekranj3,ekranj4,ekranj5,ekranj6,ekranj7,ekranj8,ekranj9,ekranj10;
		
	private enum evethayir {
		basvazgec,baskaydet;
	}
	
public static void ekle() {
	
	yavrudugme= new JButton("VAZGE�");
	yavrudugme2 = new JButton("ONAY");
	yavru = new JDialog(anaekran.frame,"Firma Cari Kart� ��lemleri",true); 
	yavru.setResizable(false);
		
	baslik= new JLabel("");
	baslik.setBounds(200,10,250,20);
	
	if (alverdef.anaekran.eklebasildi==true) {
		baslik.setText("F�RMA CAR� KARTI EKLE");
	}
	
	if (alverdef.anaekran.duzenlebasildi==true) {
		baslik.setText("F�RMA CAR� KARTI D�ZENLE");
	}

	if (alverdef.anaekran.silebasildi==true) {
		baslik.setText("F�RMA CAR� KARTI S�L");
	}
	
	yavru.setSize(600,450); 
	yavru.setLocation(450,150);
	yavru.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
	yavru.add(yavrudugme);
	yavru.add(yavrudugme2);
	yavru.add(baslik);
	
	yavrudugme.setBounds(70,330,200,50);
	yavrudugme.setVisible(true);
	yavrudugme.addActionListener(new carikartdegistireklesil());
	yavrudugme.setActionCommand(evethayir.basvazgec.name());
	
	yavrudugme2.setBounds(340,330,200,50);
	yavrudugme2.setVisible(true);
	yavrudugme2.addActionListener(new carikartdegistireklesil());
	yavrudugme2.setActionCommand(evethayir.baskaydet.name());
		
	jt1= new JTextField();
	jt1.setBounds(130,50,420,20);
	jt1.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt1);
		
	jt2= new JTextField();
	jt2.setBounds(130,90,170,20);
	jt2.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt2);
	
	jt3= new JTextField();
	jt3.setBounds(130,130,170,20);
	jt3.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt3);
	
	jt10= new JTextField();
	jt10.setBounds(380,130,170,20);
	jt10.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt10);
	
	jt4= new JTextField();
	jt4.setBounds(130,170,170,20);
	jt4.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt4);
	
	jt5= new JTextField();
	jt5.setBounds(380,170,170,20);
	jt5.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt5);
	
	jt6= new JTextField();
	jt6.setBounds(130,210,170,20);
	jt6.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt6);
	
	jt7= new JTextField();
	jt7.setBounds(380,210,170,20);
	jt7.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt7);
	
	jt8= new JTextField();
	jt8.setBounds(380,90,170,20);
	jt8.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt8);
	
	jt9= new JTextField();
	jt9.setBounds(130,280,170,20);
	jt9.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt9);
	jt9.addKeyListener(new java.awt.event.KeyAdapter() {    // limit alan�na sadece double girme izni

        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
                @SuppressWarnings("unused")
				double number = Double.parseDouble(jt9.getText());
                
            } catch (Exception e) {
              jt9.setText("");
            }
        }
    });
	
		if (anaekran.duzenlebasildi==true | anaekran.silebasildi==true | anaekran.gosterbasildi==true ) {
	     	veriarama(anaekran.selectedData); 
		
	}
	
	lb1= new JLabel("Firma �nvan�:");
	lb1.setHorizontalAlignment(SwingConstants.RIGHT);
	lb1.setBounds(20,50,100,20);
	yavru.add(lb1);
	
	lb2= new JLabel("Telefon:");
	lb2.setHorizontalAlignment(SwingConstants.RIGHT);
	lb2.setBounds(20,90,100,20);
	yavru.add(lb2);
	
	
	lb3= new JLabel("Adres:");
	lb3.setHorizontalAlignment(SwingConstants.RIGHT);
	lb3.setBounds(20,130,100,20);
	yavru.add(lb3);
	
	lb10= new JLabel("Ki�i          :");
	lb10.setHorizontalAlignment(SwingConstants.RIGHT);
	lb10.setBounds(270,130,100,20);
	yavru.add(lb10);
	
	lb4= new JLabel("�ehir:");
	lb4.setHorizontalAlignment(SwingConstants.RIGHT);
	lb4.setBounds(20,170,100,20);
	yavru.add(lb4);
	
	lb5= new JLabel("�l�e          :");
	lb5.setHorizontalAlignment(SwingConstants.RIGHT);
	lb5.setBounds(270,170,100,20);
	yavru.add(lb5);
		
	lb6= new JLabel("Vergi Dairesi:");
	lb6.setHorizontalAlignment(SwingConstants.RIGHT);
	lb6.setBounds(20,210,100,20);
	yavru.add(lb6);
	
	lb7= new JLabel("Vergi No :");
	lb7.setHorizontalAlignment(SwingConstants.RIGHT);
	lb7.setBounds(270,210,100,20);
	yavru.add(lb7);
	
	lb8= new JLabel("E-mail     :");
	lb8.setHorizontalAlignment(SwingConstants.RIGHT);
	lb8.setBounds(270,90,100,20);
	yavru.add(lb8);
		
	lb9= new JLabel("Bor� Limiti:");
	lb9.setHorizontalAlignment(SwingConstants.RIGHT);
	lb9.setBounds(20,280,100,20);
	yavru.add(lb9);
	
	lb11= new JLabel("");                                // Bunu koymay�nca tablo kay�yor. Neden bilmiyorum. 
	lb11.setHorizontalAlignment(SwingConstants.RIGHT);
	lb11.setBounds(20,310,100,20);
	yavru.add(lb11);

	if (alverdef.anaekran.gosterbasildi==true | alverdef.anaekran.silebasildi==true) {
		baslik.setText(anaekran.secilifirmaadi.toUpperCase());
		
		if (alverdef.anaekran.gosterbasildi==true) {
		yavrudugme2.setVisible(false);
		yavrudugme.setText("KAPAT");
		}
		
		jt1.setEditable(false);
		jt2.setEditable(false);
		jt3.setEditable(false);
		jt4.setEditable(false);
		jt5.setEditable(false);
		jt6.setEditable(false);
		jt7.setEditable(false);
		jt8.setEditable(false);
		jt9.setEditable(false);
	   jt10.setEditable(false);
		}
	
		yavru.setVisible(true); 
	}
	
@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand()==evethayir.basvazgec.name()) {
			yavru.dispose();
			
		}
		
		if (e.getActionCommand()==evethayir.baskaydet.name()) {
			
			if (anaekran.eklebasildi==true) {
				firmaveriekle();
			    }
			
			if (anaekran.duzenlebasildi==true) {
				firmaveridegistir(anaekran.selectedData);
			    }
			
			if (anaekran.silebasildi==true) {
				
				firmaverisil(anaekran.selectedData);
			    }
			
			return;
			
		}
		
	}

private void firmaverisil(String ekrankod) {
	sileyimmi.sonkarar("carikart");
	if (sileyimmi.cevap.equals("evet")) {
	
		try {     // cari kart silme   
     
		Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
        PreparedStatement st = connection.prepareStatement("DELETE FROM carikartlari WHERE carikart_0kod ='" +ekrankod + "';");
        st.executeUpdate(); 
  
	} catch(Exception e) {
        System.out.println(e);
    }
		try {     // cari karta ba�l� fi� kay�tlar�n� da sil 
		     
			Class.forName("com.mysql.jdbc.Driver");
	        Connection connection = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
	        PreparedStatement st = connection.prepareStatement("DELETE FROM fiskayitlari WHERE carikart_0kod ='" +ekrankod + "';");
	        st.executeUpdate(); 
	  
			} catch(Exception e) {
	        System.out.println(e);
			}
		
	anaekran.frame.dispose();
	anaekran.anametod();
    yavru.dispose();
	}
	yavru.dispose();
}

private void firmaveridegistir(String ekrankod) {
	
	ekranioku();
	
	if ((ekranj1.length()<5)) {
		baslik.setText("�NVAN EN AZ 5 KARAKTER OLMALIDIR");
		return;
	  }
	 Connection con = null;
     PreparedStatement ps = null;
     try {
        con = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass); 
        
        String query = "update carikartlari set carikart_1unvan=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj1);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
       
        query = "update carikartlari set carikart_2tel=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj2);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
                  
        query = "update carikartlari set carikart_3adres=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj3);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update carikartlari set carikart_4il=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj4);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update carikartlari set carikart_5ilce=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj5);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update carikartlari set carikart_6vd=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj6);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update carikartlari set carikart_7vn=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj7);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update carikartlari set carikart_8email=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj8);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update carikartlari set carikart_9limit=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj9);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update carikartlari set carikart_10kisi=? where carikart_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj10);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        } catch (Exception e) {
           e.printStackTrace();
     }	
     
     anaekran.frame.dispose();
 	anaekran.anametod();
     yavru.dispose();

}

private void firmaveriekle() {
	
	ekranioku();
	
	if ((ekranj1.length()<5)) {
		baslik.setText("�NVAN EN AZ 5 KARAKTER OLMALIDIR");
		return;
				
	}
	
	try{                                             
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);	
		Statement stmt=con.createStatement();  
		String emir1 = "INSERT INTO carikartlari";
		String emir2 ="(carikart_1unvan,carikart_2tel,carikart_3adres,carikart_4il,carikart_5ilce,carikart_6vd,carikart_7vn,carikart_8email,carikart_9limit,carikart_10kisi)";
		String emir3 =	"VALUES ('"+ekranj1+"','"+ekranj2+"','"+ekranj3+"','"+ekranj4+"','"+ekranj5+"','"+ekranj6+"','"+ekranj7+"','"+ekranj8+"','"+ekranj9+"','"+ekranj10+"')";
		stmt.executeUpdate(emir1+emir2+emir3);
		con.close();  
		
		}catch(Exception e){ System.out.println(e);}  
	
	anaekran.frame.dispose();
	anaekran.anametod();
    yavru.dispose();
 	 
}
private void ekranioku() {
	
	ekranj1=jt1.getText().trim().toLowerCase();
	ekranj2=jt2.getText().trim();
	ekranj3=jt3.getText().trim();
	ekranj4=jt4.getText().trim();
	ekranj5=jt5.getText().trim();
	ekranj6=jt6.getText().trim();
	ekranj7=jt7.getText().trim();
	ekranj8=jt8.getText().trim();
	ekranj9=jt9.getText().trim();
	if (ekranj9.equals("")) {
		ekranj9="0";
	}
	ekranj10=jt10.getText().trim();
}

private static void veriarama(String ekrankod) {
	
	try{                                              
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				
				Statement stmt=con.createStatement();  
		        ResultSet rs=stmt.executeQuery("SELECT * FROM carikartlari WHERE carikart_0kod =" +ekrankod + ";"); 
		
		while(rs.next()) {
			
			 jt1.setText( rs.getString(2).toUpperCase());
			 jt2.setText( rs.getString(3));
			 jt3.setText( rs.getString(4));
			 jt4.setText( rs.getString(5));
			 jt5.setText( rs.getString(6));
			 jt6.setText( rs.getString(7));
			 jt7.setText( rs.getString(8));
			 jt8.setText( rs.getString(9));
			 jt9.setText( rs.getString(10));
			 jt10.setText(rs.getString(11));
	          
		}
		con.close();  
		
		}catch(Exception e){ System.out.println(e);}  
	
}
}