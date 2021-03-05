package alverdef;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.JXDatePicker;

public class carihareketdegistireklesil implements ActionListener {
	
	static JLabel baslik,lb4,lb5,lb6,lb7,lb8,lb11,label,label2;
	static JButton yavrudugme,yavrudugme2,b;
	static JDialog yavru ;
	static JTextField jt4,jt5,jt6,jt7,jt8;
	static String ekranj4,ekranj5,ekranj6,ekranj7,ekranj8,data,data2,ekrantip;
	static JList<String> list1,list2;
	static JXDatePicker picker;
	static DecimalFormat df;

	private enum evethayir {
		basvazgec,baskaydet,sec;
	}
	
public static void ekle() {
	
	yavru = new JDialog(anaekran.frame," Cari Hareket İşlemleri Ekranı",true); 
	yavru.setResizable(false);

	yavrudugme= new JButton("VAZGEÇ");
	yavrudugme2 = new JButton("ONAY");
	
	baslik= new JLabel("");
	baslik.setBounds(10,10,500,20);
	
	picker = new JXDatePicker();                        //Tarih Seçici
    if (carihareketler.eklebasildi==true) {
    	picker.setDate(Calendar.getInstance().getTime()); 	
    }	                
    picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
    picker.setVisible(true);
	picker.setBounds(90,220,170,20);
	yavru.add(picker);
	
	    label = new JLabel();              // 2 listeyi sergile
	    label.setBounds(25,170,300,40);  
	    
	    data2="FATURA"; data="BORC";
	    label.setText("Fiş Tipi    :    "+data2+" "+data); 
	    
	    b=new JButton("Seç");  
	    b.setBounds(250,50,75,75);  
	    b.addActionListener(new carihareketdegistireklesil());
		b.setActionCommand(evethayir.sec.name());
	    
	    final DefaultListModel<String> l1 = new DefaultListModel<>();  
	      l1.addElement("BORC"); 
	      l1.addElement("ALACAK");  
	      list1= new JList<>(l1);  
	      list1.setBounds(150,50, 75,75);
	      list1.setSelectedIndex(0);
	      DefaultListModel<String> l2 = new DefaultListModel<>();  
	      l2.addElement("FATURA");  
	      l2.addElement("DEKONT");  
	      l2.addElement("ODEME");  
	      l2.addElement("ACILIS");  
	      list2 = new JList<>(l2);
	      list2.setSelectedIndex(0);
	      list2.setBounds(50,50, 75,75);  
	 	
	if (alverdef.carihareketler.eklebasildi==true) {
		baslik.setText(anaekran.secilifirmaadi+" FİRMASINA CARİ HAREKET EKLE");
	}
	
	if (alverdef.carihareketler.duzenlebasildi==true) {
		baslik.setText(anaekran.secilifirmaadi+" FİRMASININ CARİ HAREKETİNİ DÜZENLE");
	}

	if (alverdef.carihareketler.silebasildi==true) {
		baslik.setText(anaekran.secilifirmaadi+" FİRMASININ CARİ HAREKETİNİ SİL");
	}
	
	yavru.setSize(640,450); 
	yavru.setLocation(450,150);
	yavru.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
	yavrudugme.setBounds(70,330,180,40);
	yavrudugme.setVisible(true);
	yavrudugme.addActionListener(new carihareketdegistireklesil());
	yavrudugme.setActionCommand(evethayir.basvazgec.name());
	
	yavrudugme2.setBounds(350,330,180,40);
	yavrudugme2.setVisible(true);
	yavrudugme2.addActionListener(new carihareketdegistireklesil());
	yavrudugme2.setActionCommand(evethayir.baskaydet.name());
	
	yavru.add(yavrudugme);
	yavru.add(yavrudugme2);
	yavru.add(baslik);
	yavru.add(list1); yavru.add(list2); yavru.add(b); yavru.add(label);  
		
	jt5= new JTextField();
	jt5.setBounds(90,260,170,20);
	jt5.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt5);
	
	jt7= new JTextField();
	jt7.setBounds(350,260,245,20);
	jt7.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt7);
	
	jt8=new JTextField();
	jt8.setBounds(505,220,90,20);
	jt8.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt8);
	
	jt8.addKeyListener(new java.awt.event.KeyAdapter() {    // kdv alanına sadece double girme izni

        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
         	
				double miktar = Double.parseDouble(jt8.getText());
            	if (miktar-(miktar/1.18)<0) {
		        jt6.setText("");
                jt8.setText("");
	            }
            } catch (Exception e) {
              
              jt8.setText("");
              
            }
        }
    });
	
	jt6= new JTextField();
	jt6.setBounds(350,220,110,20);
	jt6.setHorizontalAlignment(SwingConstants.RIGHT);
	yavru.add(jt6);
	jt6.setText("1");
	
	jt6.addKeyListener(new java.awt.event.KeyAdapter() {    // tutar alanına sadece double girme izni

        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
            	
            	df = new DecimalFormat("#0.00");
        	
            	double miktar = Double.parseDouble(jt6.getText());
                               	
				jt8.setText((df.format(miktar-(miktar/1.18))));
				String tencere= jt8.getText();
				jt8.setText(virgulunoktayap (tencere));
				
				if (miktar<0 ) { 
             	   jt6.setText("");
                    jt8.setText("");
                }
				
            } catch (Exception e) {
              jt6.setText("");
              jt8.setText("");
              
            }
        }
    });

	lb4= new JLabel("Tarih        :");
	lb4.setHorizontalAlignment(SwingConstants.RIGHT);
	lb4.setBounds(-20,220,100,20);
	yavru.add(lb4);
	
	lb5= new JLabel("Evrak No :");
	lb5.setHorizontalAlignment(SwingConstants.RIGHT);
	lb5.setBounds(-20,260,100,20);
	yavru.add(lb5);
	
	lb7= new JLabel("Açıklama :");
	lb7.setHorizontalAlignment(SwingConstants.RIGHT);
	lb7.setBounds(240,260,100,20);
	yavru.add(lb7);

	lb6= new JLabel("Top.Tutar:");
	lb6.setHorizontalAlignment(SwingConstants.RIGHT);
	lb6.setBounds(240,220,100,20);
	yavru.add(lb6);
	
	lb8= new JLabel("KDV:");
	lb8.setHorizontalAlignment(SwingConstants.RIGHT);
	lb8.setBounds(440,220,60,20);
	yavru.add(lb8);
	
	lb11= new JLabel("");                                // Bunu koymayınca tablo kayıyor. Neden bilmiyorum. 
	lb11.setBounds(1,1,1,1);
	yavru.add(lb11);
	
if (carihareketler.duzenlebasildi==true | carihareketler.silebasildi==true ) {
		
		veriarama(carihareketler.selectedData); 
		
	}

	if (carihareketler.silebasildi==true) {

		jt5.setEditable(false);
		jt6.setEditable(false);
		jt7.setEditable(false);
		jt8.setEditable(false);
		list1.setVisible(false);
		list2.setVisible(false);
		b.setVisible(false);
				
		}
	
	yavru.setVisible(true); 
	}
	
@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand()==evethayir.basvazgec.name()) {
			yavru.dispose();
			
		}
		
		if (e.getActionCommand()==evethayir.baskaydet.name()) {
			
			if (carihareketler.eklebasildi==true) {
				firmaveriekle();
			    }
			
			if (carihareketler.duzenlebasildi==true) {
				firmaveridegistir(carihareketler.selectedData);
			    }
			
			if (carihareketler.silebasildi==true) {
				
				firmaverisil(carihareketler.selectedData);
			    }
			
		}
		if (e.getActionCommand()==evethayir.sec.name()) {   // Borç alacak ve evrak türü seçiyoruz
			
			data="";data2="";			 
				
	             if (list1.getSelectedIndex() != -1) {                       
	                data = list1.getSelectedValue();   
	                label.setText(data);  
	             }  
	            
	             if(list2.getSelectedIndex() != -1){  
	            	 data2 = list2.getSelectedValue();
	             }  
	           
	            	 label.setText("Fiş Tipi    :    "+data2+" "+data); 
	             if (!data2.equals("FATURA")) {
	            	 jt8.setVisible(false);
	            	 lb8.setVisible(false);
	            	 }else {
	            		 jt8.setVisible(true);
		            	 lb8.setVisible(true);	 
	            	 }
			}
}

private void firmaverisil(String ekrankod) {
	
	sileyimmi.sonkarar("carihareket");
	if (sileyimmi.cevap.equals("evet")) {
	
		try {  
     
		Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
        PreparedStatement st = connection.prepareStatement("DELETE FROM fiskayitlari WHERE fisno ='" +ekrankod + "';");
        st.executeUpdate(); 
  
		} catch(Exception e) {
        System.out.println(e);
		}
		degiskentemizle();
		yavru.dispose();
		carihareketler.frame.dispose();
		anaekran.frame.setVisible(false);
		anaekran.frame.setVisible(true);
		carihareketler.carihareketlerpenceresi();
	}
	
	yavru.dispose();
}

private void firmaveridegistir(String ekrankod) {
	
	ekranioku();
	
	 Connection con = null;
     PreparedStatement ps = null;
     try {
        con = DriverManager.getConnection("jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass); 
        
        String query = "update fiskayitlari set tarih=? where fisno=? ";
        ps = con.prepareStatement(query);
        ps.setString (1, ekranj4);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
       
        query = "update fiskayitlari set tip=? where fisno=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekrantip);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
                  
        query = "update fiskayitlari set tutar=? where fisno=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj6);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update fiskayitlari set aciklama=? where fisno=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj7);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
        
        query = "update fiskayitlari set kdv=? where fisno=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, ekranj8);
        ps.setString(2, ekrankod);
        ps.executeUpdate();
      
        } catch (Exception e) {
           e.printStackTrace();
     }	
     degiskentemizle();
     yavru.dispose();
     carihareketler.frame.dispose();
 	anaekran.frame.setVisible(false);
 	anaekran.frame.setVisible(true);
 	carihareketler.carihareketlerpenceresi();
   
}

private void firmaveriekle() {
	
	ekranioku();
	
	try{                                             
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);		
		Statement stmt=con.createStatement();  
		String emir1 = "INSERT INTO fiskayitlari";
		String emir2 ="(firmalarim_0kod,carikart_0kod,tarih,tip,tutar,aciklama,kdv)";
		String emir3 =	"VALUES ('"+anaekran.varsayilansirketkodu+"','"+anaekran.selectedData+"','"+ekranj4+"','"+ekrantip+"','"+ekranj6+"','"+ekranj7+"','"+ekranj8+"')";
		stmt.executeUpdate(emir1+emir2+emir3);
		con.close();  
		
		}catch(Exception e){ System.out.println(e);}  
	degiskentemizle();
    yavru.dispose();
    carihareketler.frame.dispose();
	anaekran.frame.setVisible(false);
	anaekran.frame.setVisible(true);
	carihareketler.carihareketlerpenceresi();
 	 
}
private void ekranioku() {
	
	DateFormat df = new SimpleDateFormat("yyyy.MM.dd"); 
    ekranj4 = df.format(picker.getDate()); 
	ekranj5=jt5.getText().trim();
	ekranj7=jt7.getText().trim();
	ekranj6=jt6.getText().trim();
	if (ekranj6.equals("")) {
		ekranj6="1";
	}
	ekrantip=((data2.substring(0,3)+data.substring(0,4)).toLowerCase())+ekranj5;
	ekranj8=jt8.getText().trim();
	if (ekranj8.equals("")) {
		ekranj8="0";
	}
	
}

private static void veriarama(String ekrankod) {
	
	try{                                              
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				
				Statement stmt=con.createStatement();  
		        ResultSet rs=stmt.executeQuery("SELECT * FROM fiskayitlari WHERE fisno =" +ekrankod + ";"); 
		
		while(rs.next()) {
			
			 Date date=new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(4));  
			 picker.setDate(date);
			 jt5.setText( rs.getString(5).substring(7));
		     jt6.setText( rs.getString(6));
		     jt7.setText( rs.getString(7));
		     jt8.setText( rs.getString(8));
		     
		     String fistipi = carihareketler.sifrecoz(rs.getString(5).substring(0,7));
		     
		     label.setText("Fiş Tipi    :    "+fistipi);  
		         if (!fistipi.substring(0, 6).equals("FATURA")) {
				 jt8.setVisible(false);
				 lb8.setVisible(false);
				 } 
		}
		con.close();  
		
		}catch(Exception e){ System.out.println(e);}  
}

private static void degiskentemizle() {
	ekranj4="";
	ekranj5="";
	ekranj7="";
	ekranj6="";
	ekranj8="";
	ekrantip="";
	data2="";
	data="";
	carihareketler.selectedData="";
	
}

private static String virgulunoktayap (String tencere) {
	
	int yeri=tencere.indexOf(",");
		
	String parca1= tencere.substring(0, yeri);
	String parca2= tencere.substring(yeri+1);
	String tava=parca1+"."+parca2;
	
	return tava;
}

}
