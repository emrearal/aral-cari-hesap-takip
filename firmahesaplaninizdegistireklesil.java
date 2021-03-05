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

public class firmahesaplaninizdegistireklesil implements ActionListener {
	
	static JLabel baslik,lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb10;
	static JButton evet,hayir;
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JDialog yavru ;
	static JTextField jt1,jt2,jt3,jt4,jt5,jt6,jt7,jt8;
	static String ekranj1,ekranj2,ekranj3,ekranj4,ekranj5,ekranj6,ekranj7,ekranj8;
	
	private enum evethayir {
		basvazgec,baskaydet;
	}
	
public static void ekle() {
	
	
	yavrudugme= new JButton("VAZGEÇ");
	yavrudugme2 = new JButton("ONAY");
	yavru = new JDialog(anaekran.frame,"Þirketlerimin Ýþlemleri",true); 
	yavru.setResizable(false);
		
	baslik= new JLabel("");
	baslik.setBounds(230,10,250,20);
	
	if (firmahesaplarinizdiyalog.eklebasildi==true) {
		baslik.setText("ÞÝRKETÝMÝ EKLE");
	}
	
	if (firmahesaplarinizdiyalog.duzenlebasildi==true) {
		baslik.setText("ÞÝRKETÝMÝ DÜZENLE");
	}

	if (firmahesaplarinizdiyalog.silebasildi==true) {
		baslik.setText("ÞÝRKETÝMÝ SÝL");
	}
	
	yavru.setSize(600,380); 
	yavru.setLocation(450,150);
	yavru.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
	yavru.add(yavrudugme);
	yavru.add(yavrudugme2);
	yavru.add(baslik);
	
	yavrudugme.setBounds(70,260,200,50);
	yavrudugme.setVisible(true);
	yavrudugme.addActionListener(new firmahesaplaninizdegistireklesil());
	yavrudugme.setActionCommand(evethayir.basvazgec.name());
	
	yavrudugme2.setBounds(340,260,200,50);
	yavrudugme2.setVisible(true);
	yavrudugme2.addActionListener(new firmahesaplaninizdegistireklesil());
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
		
		if (firmahesaplarinizdiyalog.duzenlebasildi==true | firmahesaplarinizdiyalog.silebasildi==true ) {
		
		veriarama(firmahesaplarinizdiyalog.selectedData);
		
	}
	
	lb1= new JLabel("Firma Ünvaný:");
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
	
	lb4= new JLabel("Þehir:");
	lb4.setHorizontalAlignment(SwingConstants.RIGHT);
	lb4.setBounds(20,170,100,20);
	yavru.add(lb4);
	
	lb5= new JLabel("Ýlçe          :");
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
		
	lb10= new JLabel("");                                // Bunu koymayýnca tablo kayýyor. Neden bilmiyorum. 
	lb10.setHorizontalAlignment(SwingConstants.RIGHT);
	lb10.setBounds(20,310,100,20);
	yavru.add(lb10);

	
	if (firmahesaplarinizdiyalog.silebasildi==true) {
	
		jt1.setEditable(false);
		jt2.setEditable(false);
		jt3.setEditable(false);
		jt4.setEditable(false);
		jt5.setEditable(false);
		jt6.setEditable(false);
		jt7.setEditable(false);
		jt8.setEditable(false);
		
		}
		
	yavru.setVisible(true); 
	}
	
@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand()==evethayir.basvazgec.name()) {
			yavru.dispose();
			
		}
		
		if (e.getActionCommand()==evethayir.baskaydet.name()) {
			
			if (firmahesaplarinizdiyalog.eklebasildi==true) {
				firmaveriekle();
			    }
			
			if (firmahesaplarinizdiyalog.duzenlebasildi==true) {
				firmaveridegistir(firmahesaplarinizdiyalog.selectedData);
			    }
			
			if (firmahesaplarinizdiyalog.silebasildi==true) {
				
				firmaverisil(firmahesaplarinizdiyalog.selectedData);
			    }
			
			return;
			
		}
		
	}

private void firmaverisil(String ekrankod) {
	
	if (firmahesaplarinizdiyalog.secilenvarsayilanmi.equals("Evet")) {
		baslik.setText("VARSAYILAN ÞÝRKET SÝLÝNEMEZ");
		return;
	}
	
	sileyimmi.sonkarar("firmakart");
	if (sileyimmi.cevap.equals("evet")) {
	
	try {           // Þirketim silme iþlemi 
     
		Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
        PreparedStatement st = connection.prepareStatement("DELETE FROM firmalarim WHERE firmalarim_0kod ='" +ekrankod + "';");
        st.executeUpdate(); 
  
	} catch(Exception e) {
        System.out.println(e);
    }
	
	try {     // þirkete baðlý fiþ kayýtlarýný da sil 
	     
		Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
        PreparedStatement st = connection.prepareStatement("DELETE FROM fiskayitlari WHERE firmalarim_0kod ='" +ekrankod + "';");
        st.executeUpdate(); 
  
		} catch(Exception e) {
        System.out.println(e);
		}
	  
	firmahesaplarinizdiyalog.frame.dispose();
    yavru.dispose();
    anaekran.frame.setVisible(false);       // Aca ekraný açýp kapamanýn sebebi pencerelerin yanlýþ sýralama ile açýlmamalarý
    anaekran.frame.setVisible(true);
	 firmahesaplarinizdiyalog.firmahesaplari();
	}
 yavru.dispose();
}

private void firmaveridegistir(String ekrankod) {
	
	ekranioku();
	
	if ((ekranj1.length()<5)) {
		baslik.setText("ÜNVAN EN AZ 5 KARAKTER OLMALIDIR");
		return;
	  }
	
	 Connection con = null;
     PreparedStatement ps = null;
     try {
        con = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
        
        String query = "update firmalarim set firmalarim_1unvan=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj1);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
       
        query = "update firmalarim set firmalarim_2tel=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj2);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
                  
        query = "update firmalarim set firmalarim_3adres=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj3);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update firmalarim set firmalarim_4il=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj4);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update firmalarim set firmalarim_5ilce=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj5);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update firmalarim set firmalarim_6vd=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj6);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update firmalarim set firmalarim_7vn=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj7);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update firmalarim set firmalarim_8email=? where firmalarim_0kod=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj8);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        con.close();
        
        } catch (Exception e) {
           e.printStackTrace();
     }	
     
    
     firmahesaplarinizdiyalog.frame.dispose();
     yavru.dispose();
     anaekran.frame.setVisible(false);       // Bunun sebebi pencerelerin yanlýþ sýralama ile açýlmamalarý
     anaekran.frame.setVisible(true);
 	 firmahesaplarinizdiyalog.firmahesaplari();
 	
}

private void firmaveriekle() {
	
	ekranioku();
	
	if ((ekranj1.length()<5)) {
		baslik.setText("ÜNVAN EN AZ 5 KARAKTER OLMALIDIR");
		return;
	
				
	}
	
	try{                                             
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);	
		Statement stmt=con.createStatement();  
		String emir1 = "INSERT INTO firmalarim";
		String emir2 ="(firmalarim_1unvan,firmalarim_2tel,firmalarim_3adres,firmalarim_4il,firmalarim_5ilce,firmalarim_6vd,firmalarim_7vn,firmalarim_8email,firmalarim_10varsay)";
		String emir3 =	"VALUES ('"+ekranj1+"','"+ekranj2+"','"+ekranj3+"','"+ekranj4+"','"+ekranj5+"','"+ekranj6+"','"+ekranj7+"','"+ekranj8+"','Hayýr');";
		stmt.executeUpdate(emir1+emir2+emir3);
		con.close();  
		
		}catch(Exception e){ System.out.println(e);}  
	
	firmahesaplarinizdiyalog.frame.dispose();
    yavru.dispose();
    anaekran.frame.setVisible(false);       // Bunun sebebi pencerelerin yanlýþ sýralama ile açýlmamalarý
    anaekran.frame.setVisible(true);
	 firmahesaplarinizdiyalog.firmahesaplari();
	 
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
	
}

private static void veriarama(String ekrankod) {
	
	try{                                              
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				
				Statement stmt=con.createStatement();  
		        ResultSet rs=stmt.executeQuery("SELECT * FROM firmalarim WHERE firmalarim_0kod =" +ekrankod + ";"); 
		
		while(rs.next()) {
			
			 rs.getString(1);  
			 jt1.setText( rs.getString(2).toUpperCase());
			 jt2.setText( rs.getString(3));
			 jt3.setText( rs.getString(4));
			 jt4.setText( rs.getString(5));
			 jt5.setText( rs.getString(6));
			 jt6.setText( rs.getString(7));
			 jt7.setText( rs.getString(8));
			 jt8.setText( rs.getString(9));
				          
		}
		con.close();  
		
		}catch(Exception e){ System.out.println(e);}  
	
}

public static void varsayilanyap() {
	try {    //  tüm þirketleri varsayýlanýný "hayýr "yap
	     
		Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
        PreparedStatement st = connection.prepareStatement("update firmalarim set firmalarim_10varsay='Hayýr';");
        st.executeUpdate(); 
        connection.close();
  
	} catch(Exception e) {
        System.out.println(e);
    }
	
	try {    // seçilen firmanýn varsayýlan hücresini " evet" yap
	     
		Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
        PreparedStatement st = connection.prepareStatement("update firmalarim set firmalarim_10varsay='Evet' where firmalarim_0kod="+firmahesaplarinizdiyalog.selectedData+";");
        st.executeUpdate(); 
        connection.close();
  
	} catch(Exception e) {
        System.out.println(e);
    }
	
	anaekran.frame.dispose();
	firmahesaplarinizdiyalog.frame.dispose();
	anaekran.anametod();
	
}
}