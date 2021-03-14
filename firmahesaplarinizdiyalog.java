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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class firmahesaplarinizdiyalog implements ActionListener {
	
	static JButton buttonekle,buttonduzenle,buttonsil,buttonvarsayilan,buttonkapat ;
	static JFrame frame ;
	static JLabel baslik2 ;
	static JTable tablo;
	static JScrollPane sp;
	static JPanel panel3,panel4;
	static boolean eklebasildi=false,silebasildi=false,duzenlebasildi=false;
	static String selectedData ="",secilenvarsayilanmi="";
	
	private enum evethayir {
		buttonekle,buttonduzenle,buttonsil,buttonvarsayilan,buttonkapat;
		}
		
	public static void firmahesaplari() {
	
		buttonekle= new JButton("         Ekle         ");
		buttonduzenle = new JButton("      Duzenle     ");
		buttonsil= new JButton("         Sil            ");
		buttonkapat= new JButton("X (KAPAT)");
		buttonvarsayilan = new JButton("Varsayýlan Yap");
		frame = new JFrame("Þirketlerim");
				
		baslik2 = new JLabel(" Ýþlem Yapýlacak Þirketinizi Seçin veya Yenisini Ekleyin" );
	
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setResizable(false);
	
		buttonekle.setVisible(true);
		buttonekle.addActionListener(new firmahesaplarinizdiyalog());
		buttonekle.setActionCommand(evethayir.buttonekle.name());
	
		buttonduzenle.setVisible(true);
		buttonduzenle.addActionListener(new firmahesaplarinizdiyalog());
		buttonduzenle.setActionCommand(evethayir.buttonduzenle.name());
		
		buttonsil.setVisible(true);
		buttonsil.addActionListener(new firmahesaplarinizdiyalog());
		buttonsil.setActionCommand(evethayir.buttonsil.name());
	
		buttonkapat.setVisible(true);
		buttonkapat.addActionListener(new firmahesaplarinizdiyalog());
		buttonkapat.setActionCommand(evethayir.buttonkapat.name());
		
		buttonvarsayilan.setVisible(true);
		buttonvarsayilan.addActionListener(new firmahesaplarinizdiyalog());
		buttonvarsayilan.setActionCommand(evethayir.buttonvarsayilan.name());
		
		String[] sutun = { "KOD","Þirketimin Ünvaný","Varsayýlan"} ;                      // Tablo oluþturma 
		int cnk=0;
	
		try{                                              // MYSQL Veri tabanýnýn kaç satýr olduðunu bulup array boyutlama
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from firmalarim");  
			while(rs.next()) {
			cnk++;
			}
			con.close();  
			
			}catch(Exception e){ System.out.println(e);}  
		
		 String[][] satir= new String[cnk][3];
		 cnk=0;
		
		try{                                              //Veri tabanýndakileri array'e aktarma
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from firmalarim");  
			while(rs.next()) {
			 satir[cnk][0] = rs.getString(1);
			 satir[cnk][1] = rs.getString(2).toUpperCase();
			 satir[cnk][2] = rs.getString(11);
			 cnk++;
			}
			 con.close();  
			 cnk=0;
			}catch(Exception e){ System.out.println(e);}  
		    
	    
		panel3=new JPanel();
		panel4=new JPanel();
		
		
		tablo= new JTable(satir,sutun); 
		tablo.setDefaultEditor(Object.class, null);	// tabloya elle düzeltme yapılamasın
		sp=new JScrollPane(tablo); 
		sp.setPreferredSize(new Dimension(500,100));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
        tablo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
		
		TableColumnModel columnModel = tablo.getColumnModel();
		columnModel.getColumn(0).setMaxWidth(50);
		columnModel.getColumn(1).setMaxWidth(550);
		columnModel.getColumn(2).setMaxWidth(100);
		 
	    panel3.add(buttonvarsayilan);
	    panel3.add(buttonekle);
	    panel3.add(buttonsil);
	    panel3.add(buttonduzenle);
	    panel3.add(buttonkapat);
	    
	    panel4.add(baslik2);
	  
	    Container contentPane = frame.getContentPane();
	  
	    contentPane.add(sp, BorderLayout.PAGE_END);
	    contentPane.add(panel3, BorderLayout.PAGE_START);
	    contentPane.add(panel4, BorderLayout.CENTER);
	 
	    frame.pack();
        frame.setLocation(500,90);
        frame.setVisible(true);
	    
        ListSelectionModel cellSelectionModel = tablo.getSelectionModel();               // listeden seçileni dinleme kýsmý
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
           
        	  if(!e.getValueIsAdjusting())  {    						// bu IF iki defa yazmamasý için konuldu. 
      
        		  int selectedRow     = tablo.getSelectedRow();
          		selectedData = (String) tablo.getValueAt(selectedRow,0 );
          		secilenvarsayilanmi= (String) tablo.getValueAt(selectedRow,2 );
        	  }
          }
     });
    	
	}

	@Override
	public void actionPerformed(ActionEvent f) {
		
		if (f.getActionCommand()==evethayir.buttonekle.name()) {
			eklebasildi=true;
			firmahesaplaninizdegistireklesil.ekle();
				
		}
		
		if (f.getActionCommand()==evethayir.buttonduzenle.name()) {
			
			if(selectedData.equals("")) {
				return;
			}
			duzenlebasildi=true;
			firmahesaplaninizdegistireklesil.ekle();
			
		}

		if (f.getActionCommand()==evethayir.buttonsil.name()) {
			if(selectedData.equals("")) {
				return;
				}
			silebasildi=true;
			firmahesaplaninizdegistireklesil.ekle();
	
		}

		if (f.getActionCommand()==evethayir.buttonkapat.name()) {
			
			
			anaekran.frame.dispose();
			anaekran.anametod();
			frame.dispose();
			
		}
		
		if (f.getActionCommand()==evethayir.buttonvarsayilan.name()) {
			if(selectedData.equals("")) {
				return;
			}
			firmahesaplaninizdegistireklesil.varsayilanyap();
			
		}

		eklebasildi=false;
		duzenlebasildi=false;
		silebasildi=false;
		
    	}
	}
