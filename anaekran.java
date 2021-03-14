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

public class anaekran implements ActionListener {
	
	private enum actions {
		buttonekle,buttonduzenle,buttonsil,buttoncarihareketler,mitfirmahesaplari,buttoncaribul,buttoncarigoster
		,mithesapmakinesi,mitnotdefteri,mitchekstresi,mityedekal,mitmizan,mitalacaklar,mitborclar,mityardim,mithakkinda;
	  }

	static JFrame frame ;
	static JPanel panel4,panel3;
	static JButton buttonekle,buttonduzenle,buttonsil,buttoncarihareketler,buttoncaribul,buttoncarigoster;
	static JMenuBar menubar;
	static JMenu firmalar,yedek,araclar,yardim,raporlar;
	static JMenuItem firmahesaplari,yedekal,geriyukle,notdefteri,hesapmakinesi
					,hakkinda,yardimyardim,mizan,alacaklar,borclar;
	static JScrollPane sp;
	static JTable tablo;
	static JLabel baslik2;
	static String selectedData ="",aranankelime="",varsayilansirketadi="",secilifirmaadi="",secilifirmalimit="";
	
	static String sqluser="root",sqlpass="123456",sqlip="localhost:3306";         // Emre Laptop sql localhost
	
	static boolean eklebasildi=false,silebasildi=false,duzenlebasildi=false,gosterbasildi=false;
	static int varsayilansirketkodu;
				
	public static void anametod() {
		
					
		try{                                                        //Varsayılan firma  hangisi işaretlenmiş ?                                
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(
				   "jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
					
					Statement stmt=con.createStatement();  
			        ResultSet rs=stmt.executeQuery("SELECT * FROM firmalarim WHERE firmalarim_10varsay = 'Evet';"); 
			        while (rs.next()) {
			       varsayilansirketkodu=Integer.parseInt(rs.getString(1));
				   varsayilansirketadi=rs.getString(2).toUpperCase();
			        }
				
			con.close();  
			
			}catch(Exception e){ System.out.println(e);}  
		
		buttonekle = new JButton("     Cari Kart Ekle    ");                                     //Düğmeler 
		buttonekle.addActionListener(new anaekran());
		buttonekle.setActionCommand(actions.buttonekle.name());
		
		buttonduzenle = new JButton("Cari Kart Düzenle");
		buttonduzenle.addActionListener(new anaekran());
		buttonduzenle.setActionCommand(actions.buttonduzenle.name());
		
		buttonsil = new JButton("      Cari Kart Sil     ");
		buttonsil.addActionListener(new anaekran());
		buttonsil.setActionCommand(actions.buttonsil.name());
				
		buttoncarihareketler = new JButton("C/H HAREKETLERİ");
		buttoncarihareketler.addActionListener(new anaekran());
		buttoncarihareketler.setActionCommand(actions.buttoncarihareketler.name());
		
		buttoncaribul = new JButton("     Cari Kart Bul      ");
		buttoncaribul.addActionListener(new anaekran());
		buttoncaribul.setActionCommand(actions.buttoncaribul.name());
		
		buttoncarigoster=new JButton ("Cari Kartı Göster");
		buttoncarigoster.addActionListener(new anaekran());
		buttoncarigoster.setActionCommand(actions.buttoncarigoster.name());
		
		baslik2 = new JLabel("'Silme','Düzenleme','Cari Hesap Hareketleri'ni görmek için tablo üzerinde tıklayarak firma seçimi yapın" );
			
		panel3=new JPanel();
		panel4=new JPanel();
		frame = new JFrame();
		frame.setTitle("ARAL CARİ HESAP TAKİP PROGRAMI.     Varsayılan Şirketim :  "+varsayilansirketadi );
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		menubar = new JMenuBar();                                 // Menü oluşturma
		
		firmalar = new JMenu("ŞİRKETLERİM"); 
        yedek = new JMenu("YEDEK"); 
        araclar = new JMenu("ARAÇLAR");
        yardim = new JMenu("YARDIM");
        raporlar= new JMenu("RAPORLAR");
		
		firmahesaplari=new JMenuItem("Şirketlerim");
		firmahesaplari.addActionListener(new anaekran());
		firmahesaplari.setActionCommand(actions.mitfirmahesaplari.name());
		
		hesapmakinesi=new JMenuItem("Hesap Makinesi");
		hesapmakinesi.addActionListener(new anaekran());
		hesapmakinesi.setActionCommand(actions.mithesapmakinesi.name());
		
		notdefteri=new JMenuItem("Not Defteri");
		notdefteri.addActionListener(new anaekran());
		notdefteri.setActionCommand(actions.mitnotdefteri.name());
		
		yedekal=new JMenuItem("Yedek Al");
		yedekal.addActionListener(new anaekran());
		yedekal.setActionCommand(actions.mityedekal.name());
		
		mizan=new JMenuItem("Mizan");
		mizan.addActionListener(new anaekran());
		mizan.setActionCommand(actions.mitmizan.name());
		
		hakkinda=new JMenuItem("Hakkında");
		hakkinda.addActionListener(new anaekran());
		hakkinda.setActionCommand(actions.mithakkinda.name());
		
		yardimyardim=new JMenuItem("Yardım");
		yardimyardim.addActionListener(new anaekran());
		yardimyardim.setActionCommand(actions.mityardim.name());
		
		geriyukle=new JMenuItem("Geri Yükle");  // Henüz yapılandırılmadı. Devre dışı 
		
		alacaklar=new JMenuItem("Satıcı Faturaları Listesi");
		alacaklar.addActionListener(new anaekran());
		alacaklar.setActionCommand(actions.mitalacaklar.name());
		
		borclar=new JMenuItem("Alıcı Faturaları Listesi");
		borclar.addActionListener(new anaekran());
		borclar.setActionCommand(actions.mitborclar.name());
		 
       menubar.add(firmalar);
       menubar.add(raporlar);
       menubar.add(araclar);
       //menubar.add(yedek);   
       menubar.add(yardim);
     
       firmalar.add(firmahesaplari);
       //yedek.add(yedekal);  uzak sql sunucuda çalismaz.
       //yedek.add(geriyukle); şimdilik yedek geri yüklemeyi kullanmıyoruz.
       araclar.add(notdefteri);
       araclar.add(hesapmakinesi);
       yardim.add(hakkinda);   
       yardim.add(yardimyardim);
       raporlar.add(mizan);
       raporlar.add(alacaklar);
       raporlar.add(borclar);
        
       frame.setJMenuBar(menubar);
       
       panel3.add(buttoncarigoster);
       panel3.add(buttoncaribul);   
	   panel3.add(buttonekle);
	   panel3.add(buttonsil);
	   panel3.add(buttonduzenle);
	   panel3.add(buttoncarihareketler);
	   
	   panel4.add(baslik2);
	   
	   String[] sutun = { "KOD","Firma Ünvanı","Şehir","Limit"};                      // Tablo oluşturma 
		int cnk=0;
	   
	   if (!aranankelime.equals("")) {    // ARAMA MI YAPACAĞIZ YOKSA HEPSİNİ DÖKECEK MİYİZ ?
		   
		   // arama yapacaksak :
		   try{                                              
				Class.forName("com.mysql.jdbc.Driver");    // ARANAN SONUÇTAN KAÇ TANE VAR
				Connection con=DriverManager.getConnection(
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
						
						Statement stmt=con.createStatement();  
				        ResultSet rs=stmt.executeQuery("SELECT * FROM carikartlari WHERE carikart_1unvan LIKE '%" +aranankelime+ "%';"); 
				
				        while(rs.next()) {
							cnk++;
							}
							con.close();  
							
							}catch(Exception e){ System.out.println(e);}  
						
						 String[][] satir= new String[cnk][4];     // sonuç sayısına göre array boyutlama
						 cnk=0;
			
				try{                                              
				   Class.forName("com.mysql.jdbc.Driver");    // dizine aktar
				 Connection con=DriverManager.getConnection(
						 "jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
										
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("SELECT * FROM carikartlari WHERE carikart_1unvan LIKE '%" +aranankelime+ "%';"); 
		   
					while(rs.next()) {
						 satir[cnk][0] = rs.getString(1);
						 satir[cnk][1] = rs.getString(2).toUpperCase();
						 satir[cnk][2] = rs.getString(5);
						 satir[cnk][3] = rs.getString(10);
						 cnk++;
						}
						 con.close();  
						 cnk=0;
						}catch(Exception e){ System.out.println(e);}  
				
				tablo= new JTable(satir,sutun);  
				sp=new JScrollPane(tablo); 
				sp.setPreferredSize(new Dimension(700, 350));
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		        
				
		        tablo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);     //tabloyu ortama
		        tablo.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		        tablo.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		        tablo.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		        
				TableColumnModel columnModel = tablo.getColumnModel();
				columnModel.getColumn(0).setMaxWidth(50);
				columnModel.getColumn(1).setMaxWidth(550);
				columnModel.getColumn(2).setMaxWidth(100);
				columnModel.getColumn(3).setMaxWidth(200);
			    
			    aranankelime="";
		   
	   } else {   // Birşey aranmıyorsa herşeyi ekrana bas
  
		   	try{                                              // MYSQL Veri tabanının kaç satır olduğunu bulup array boyutlama
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from carikartlari");  
			while(rs.next()) {
			cnk++;
			}
			con.close();  
			
			}catch(Exception e){ System.out.println(e);}  
		
		     String[][] satir= new String[cnk][4];
		     cnk=0;
		
		 	try{                                              //Veri tabanındakileri array'e aktarma
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from carikartlari");  
			while(rs.next()) {
				satir[cnk][0] = rs.getString(1);
				 satir[cnk][1] = rs.getString(2).toUpperCase();
				 satir[cnk][2] = rs.getString(5);
				 satir[cnk][3] = rs.getString(10);
			 cnk++;
			}
			 con.close();  
			 cnk=0;
			}catch(Exception e){ System.out.println(e);}  
		
		tablo= new JTable(satir,sutun);   
		sp=new JScrollPane(tablo); 
		sp.setPreferredSize(new Dimension(700, 350));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();   //Hücre içeriğini ortalama
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
        tablo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);   //Hücre genişliklei ayarlama
        tablo.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
		TableColumnModel columnModel = tablo.getColumnModel();
		columnModel.getColumn(0).setMaxWidth(50);
		columnModel.getColumn(1).setMaxWidth(550);
		columnModel.getColumn(2).setMaxWidth(100);
		columnModel.getColumn(3).setMaxWidth(200);
		
	   }
	    
	 //İSTER ARAMA SONUCU İSTER TÜM LİSTE OLSUN BURADA OLUŞTURULAN TABLOYU BAS.
	   
	    Container contentPane = frame.getContentPane();
	  
	    contentPane.add(sp, BorderLayout.PAGE_END);
	    contentPane.add(panel3, BorderLayout.PAGE_START);
	    contentPane.add(panel4, BorderLayout.CENTER);
	   
	    frame.pack();
        frame.setLocationRelativeTo(null);
	    
	    frame.setVisible(true);
	     
	    ListSelectionModel cellSelectionModel = tablo.getSelectionModel();               // listeden seçileni dinleme kısmı
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
           
        	  if(!e.getValueIsAdjusting())  {    						// bu IF iki defa yazmaması için konuldu. 
      
        		  int selectedRow     = tablo.getSelectedRow();
          		selectedData = (String) tablo.getValueAt(selectedRow,0 );
          		secilifirmaadi=(String) tablo.getValueAt(selectedRow,1 );
          		secilifirmalimit=(String) tablo.getValueAt(selectedRow,3 );
        	  }
          }
     });
	  	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()==actions.mitfirmahesaplari.name()) {
			
			firmahesaplarinizdiyalog.firmahesaplari();
			
		}
		if (e.getActionCommand()==actions.mithesapmakinesi.name()) {
			
			alverdef.hesapmakinesi.hesapmak();
			
		}
		
        if (e.getActionCommand()==actions.mitnotdefteri.name()) {
        	
        	notdefteripenceresi.notdefteri();
			
		}
        
        if (e.getActionCommand()==actions.mitchekstresi.name()) {
        	
        	if (selectedData.equals("")) {
				return;
			}
        		alverdef.chekstresi.excelyap();
			}
		
        if (e.getActionCommand()==actions.mityedekal.name()) {
        	
        	yedekalma.yedekle();
			}
        
        
        if (e.getActionCommand()==actions.mitmizan.name()) {
        	
        	mizanyap.excelyap();
			}
        
        if (e.getActionCommand()==actions.mitalacaklar.name()) {
        	
        	faturalistesi.tarihlerisec("alacak");
        }
        	
        if (e.getActionCommand()==actions.mitborclar.name()) {
             	
             	faturalistesi.tarihlerisec("borc");
        
        	 }
        
        if (e.getActionCommand()==actions.mityardim.name()) {
              	
              	alverdef.hakkinda.hakk("yardim");
         
         	 }	 
        	 
        if (e.getActionCommand()==actions.mithakkinda.name()) {
          	
          	alverdef.hakkinda.hakk("hakkinda");
     
     	 }		 
        	 
         
		if (e.getActionCommand()==actions.buttoncarihareketler.name()) {
			
			if (selectedData.equals("")) {
				return;
			}
			carihareketler.carihareketlerpenceresi();
			
		}
		
		if (e.getActionCommand()==actions.buttonekle.name()) {
			eklebasildi=true;
			
			carikartdegistireklesil.ekle();
				
		}
		
		if (e.getActionCommand()==actions.buttonduzenle.name()) {
			
			if (selectedData.equals("")) {
				return;
			}
			duzenlebasildi=true;
			carikartdegistireklesil.ekle();
			
		}

		if (e.getActionCommand()==actions.buttonsil.name()) {
	
			if (selectedData.equals("")) {
				return;
			}
			silebasildi=true;
			carikartdegistireklesil.ekle();
	
		}
	
		if (e.getActionCommand()==actions.buttoncaribul.name()) {
			
			caribul.nebulayim();
			
		}
		
        if (e.getActionCommand()==actions.buttoncarigoster.name()) {
        	if (selectedData.equals("")) {
				return;
			}
        	 gosterbasildi=true;
        	 carikartdegistireklesil.ekle();
			
		}
		eklebasildi=false;
		duzenlebasildi=false;
		silebasildi=false;
		gosterbasildi=false;
		
	}

	public static void main (String[] args) {
		
		anametod();
    		
    	}
}
