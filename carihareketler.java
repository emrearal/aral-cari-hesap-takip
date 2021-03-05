package alverdef;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class carihareketler implements ActionListener {
	
	private enum actions {
		buttonekle,buttonduzenle,buttonsil,buttonhepsinisil,buttoncarihareketler,ekstreyazdir;
	  }

	static JFrame frame ;
	static JPanel panel3,panel5;
	static JButton buttonekle,buttonduzenle,buttonsil,ekstreyazdir;
	static JMenuBar menubar;
	static JMenu firmalar,islemler,yedek,araclar,yardim;
	static JMenuItem firmahesaplari,hareketler,yedekal,geriyukle,notdefteri,sifredegistir,hakkinda,yardimyardim;
	static JScrollPane sp;
	static JTable tablo;
	static JLabel txtborc,txtalacak,txtbakiye;
	static NumberFormat df;
	static String isaret="",selectedData ="";
	static double borc;
	static double alacak;
	static double bakiye;
	static boolean eklebasildi=false,silebasildi=false,duzenlebasildi=false;
	
	public static void carihareketlerpenceresi() {
		
		alacak=0;
		borc=0;
		bakiye=0;
		
		    buttonekle = new JButton("   Hareket Ekle   ");                                     //Düðmeler 
			buttonekle.addActionListener(new carihareketler());
			buttonekle.setActionCommand(actions.buttonekle.name());
			
			buttonduzenle = new JButton("Hareket Düzenle");
			buttonduzenle.addActionListener(new carihareketler());
			buttonduzenle.setActionCommand(actions.buttonduzenle.name());
			
			buttonsil = new JButton("     Hareket Sil     ");
			buttonsil.addActionListener(new carihareketler());
			buttonsil.setActionCommand(actions.buttonsil.name());
			
			ekstreyazdir = new JButton(" Ekstre Yazdir (Excel)");
			ekstreyazdir.addActionListener(new carihareketler());
			ekstreyazdir.setActionCommand(actions.ekstreyazdir.name());
									
		panel5=new JPanel();	
		panel3=new JPanel();
			
		frame = new JFrame();
		frame.setTitle(anaekran.secilifirmaadi+" Cari Hareketleri ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		  
		panel3.add(buttonekle);
		panel3.add(buttonsil);
		panel3.add(buttonduzenle);
		panel3.add(ekstreyazdir);
		
		String[] sutun = { "FÝÞ NO","TARÝH","BORÇ","ALACAK","FÝÞ TÝPÝ","EVRAK NO","AÇIKLAMA"} ;                      // Tablo oluþturma 
		int cnk=0;
	
		try{                                              // MYSQL Veri tabanýnýn kaç satýr olduðunu bulup array boyutlama
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery
	("select * from fiskayitlari where firmalarim_0kod='"+anaekran.varsayilansirketkodu+"' AND carikart_0kod='"+anaekran.selectedData+"' ;");			
		
			while(rs.next()) {
			cnk++;
			}
			con.close();  
			
			}catch(Exception e){ System.out.println(e);}  
		
		 String[][] satir= new String[cnk][7];
		 
		 cnk=0;
		
		try{                                              //Veri tabanýndakileri array'e aktarma
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery
("select * from fiskayitlari where firmalarim_0kod='"+anaekran.varsayilansirketkodu+"' AND carikart_0kod='"+anaekran.selectedData+"' order by tarih ASC; ");
					
					while(rs.next()) {
				
				satir[cnk][0] = rs.getString(1);  // fis no oku aktar
				satir[cnk][1] = rs.getString(4);  // tarih oku aktar
				
				String ilkyedi = rs.getString(5).substring(3,7);
				String yedisonrasi = rs.getString(5).substring(7);
				  if (ilkyedi.equals("borc")) {       // borç kaydý ise
					satir[cnk][2] = rs.getString(6); 
					borc=borc+Double.parseDouble(rs.getString(6)); 
					satir[cnk][3] = " ";
				    }
				  if (ilkyedi.equals("alac")) {      // alacak kaydý ise
					satir[cnk][2] =  " "; 
					satir[cnk][3] =rs.getString(6);
					alacak=alacak+Double.parseDouble(rs.getString(6)); 
				    }
				  satir[cnk][4] = sifrecoz(rs.getString(5).substring(0,7));
				  satir[cnk][5] = yedisonrasi;
				  satir[cnk][6] = rs.getString(7);
			 
			 cnk++;
			}
			 con.close();  
			 cnk=0;
			}catch(Exception e){ System.out.println(e);}  
	        
	    tablo= new JTable(satir,sutun);   
		sp=new JScrollPane(tablo); 
		sp.setPreferredSize(new Dimension(1000, 350));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
        tablo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
		TableColumnModel columnModel = tablo.getColumnModel();
		columnModel.getColumn(0).setMaxWidth(50);
		columnModel.getColumn(1).setMaxWidth(100);
		columnModel.getColumn(2).setMaxWidth(100);
		columnModel.getColumn(3).setMaxWidth(100);
		columnModel.getColumn(4).setMaxWidth(130);
		columnModel.getColumn(5).setMaxWidth(150);
		columnModel.getColumn(6).setMaxWidth(400);
		
		df = NumberFormat.getInstance(Locale.ENGLISH);      //bindelik ayracý için yeni format
		
		bakiye= alacak-borc;
	
		txtborc=new JLabel("Toplam Borç: "+df.format(borc)+"   ");
		txtalacak=new JLabel("Toplam Alacak: "+df.format(alacak)+"   ");
		if (borc-alacak>0) {
			isaret=" Borçlu";
		} else {
			isaret=" Alacaklý";
		}
		if (borc==alacak) {
			isaret="Sýfýr";
		}
		bakiye=Math.abs(bakiye);
		txtbakiye=new JLabel("*BAKÝYE: "+df.format(bakiye)+isaret+"*");
		
		panel5.add(txtborc);
		panel5.add(txtalacak);
		panel5.add(txtbakiye);
		
	    Container contentPane = frame.getContentPane();
	    
	    contentPane.add(panel3, BorderLayout.PAGE_START);
	    contentPane.add(sp, BorderLayout.CENTER);
	    contentPane.add(panel5, BorderLayout.PAGE_END);
	   
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	    ListSelectionModel cellSelectionModel = tablo.getSelectionModel();               // listeden seçileni dinleme kýsmý
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
           
        	  if(!e.getValueIsAdjusting())  {    						// bu IF iki defa yazmamasý için konuldu. 
      
        		  int selectedRow     = tablo.getSelectedRow();
          		selectedData = (String) tablo.getValueAt(selectedRow,0 );
          		    		          		
        	  }
          }
     });
	
	}

	static String sifrecoz(String kod) {
		
		switch (kod) {
		
		case "fatborc":
			carihareketdegistireklesil.data2="FATURA";
			carihareketdegistireklesil.data="BORC";
			kod="FATURA BORC";
		break;	
		
		case "fatalac":
			carihareketdegistireklesil.data2="FATURA";
			carihareketdegistireklesil.data="ALACAK";
			kod="FATURA ALACAK";
		break;
		
		case "dekborc":
			carihareketdegistireklesil.data2="DEKONT";
			carihareketdegistireklesil.data="BORC";
			kod="DEKONT BORC";
			break;
			
		case "dekalac":
			carihareketdegistireklesil.data2="DEKONT";
			carihareketdegistireklesil.data="ALACAK";
			kod="DEKONT ALACAK";
			break;
			
		case "odeborc":
			carihareketdegistireklesil.data2="ODEME";
			carihareketdegistireklesil.data="BORC";
			kod="ODEME BORC";
			break;
			
		case "odealac":
			carihareketdegistireklesil.data2="ODEME";
			carihareketdegistireklesil.data="ALACAK";
			kod="ODEME ALACAK";
			break;
			
		case "acýborc":
			carihareketdegistireklesil.data2="ACILIS";
			carihareketdegistireklesil.data="BORC";
			kod="ACILIS BORC";
			break;
			
		case "acýalac":
			carihareketdegistireklesil.data2="ACILIS";
			carihareketdegistireklesil.data="ALACAK";
			kod="ACILIS ALACAK";
			break;
					
		}
		return kod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()==actions.buttonekle.name()) {
		
			eklebasildi=true;
			carihareketdegistireklesil.ekle();
		}
		
		if (e.getActionCommand()==actions.buttonduzenle.name()) {
			if(!selectedData.equals("")) {
				duzenlebasildi=true;
				carihareketdegistireklesil.ekle();
			}
			
	    }
		
		if (e.getActionCommand()==actions.buttonsil.name()) {
			if(!selectedData.equals("")) {
				silebasildi=true;
				carihareketdegistireklesil.ekle();
			}
	    }
        
		if (e.getActionCommand()==actions.ekstreyazdir.name()) {
			
				chekstresi.tarihlerisec();
			  }
		
		eklebasildi=false;
		duzenlebasildi=false;
		silebasildi=false;
		
}
}
