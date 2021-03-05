					// excel'e çýktý alma
package alverdef;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class chekstresi{
	static File fileToSave;
	static String[][] satir;
    static double borc=0,alacak=0;
    static int cnk;
    
    static JButton yavrudugme2,b;
	static JDialog yavru ;
	static JXDatePicker picker,picker2;
	static JPanel panel,panel2,panel3;
	static String tarih1="",tarih2="",komut="";
	static boolean hepsini=false;
 		 
		public static void tarihlerisec() {
			
			hepsini=false;
			komut="";
			
			yavru = new JDialog(anaekran.frame," Tarih Aralýðý Seçimi",true); 
			yavru.setResizable(true);
			yavru.setBounds(450,300, 250, 150);
			
			panel= new JPanel();
			panel2=new JPanel();
			panel3=new JPanel();
			
			JCheckBox kutucuk = new JCheckBox("Tüm Kayýtlarý Listele");
			kutucuk.addActionListener(new ActionListener(){  
			    public void actionPerformed(ActionEvent e){  
			    	if (hepsini==true) {
			    	hepsini=false;			    	
			    	}else {
			    	hepsini=true;
			    	}
			    	
			    	return;			    		    
			    }  
			});  
						
			picker = new JXDatePicker();                        //Tarih Seçici 1
		    picker.setDate(Calendar.getInstance().getTime()); 	
		    picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		    picker.setVisible(true);
			panel.add(picker);
			
			picker2 = new JXDatePicker();                        //Tarih Seçici 2 
		    picker2.setDate(Calendar.getInstance().getTime()); 
			picker2.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		    picker2.setVisible(true);
			panel.add(picker2);

			yavrudugme2 = new JButton("ONAY");
			yavrudugme2.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent e){  
				    
				    	DateFormat df = new SimpleDateFormat("yyyy.MM.dd"); 
				    	tarih1=df.format(picker.getDate());
				    	tarih2=df.format(picker2.getDate());
				   
				    	yavru.dispose();
				    	excelyap();
				    		    
				    	}  
						});  
			
			panel2.add(yavrudugme2);
			panel3.add(kutucuk);
			yavru.add(panel, BorderLayout.PAGE_START);
			yavru.add(panel2, BorderLayout.PAGE_END);
			yavru.add(panel3, BorderLayout.CENTER);
			
			yavru.setVisible(true);
		
		}
		
		public static void excelyap() {
	
		cnk=0;
		
			try{                                              // MYSQL Veri tabanýnýn kaç satýr olduðunu bulup array boyutlama
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				Statement stmt=con.createStatement();  
				
			    if ( hepsini==false) {
			    	
	komut="select * from fiskayitlari where firmalarim_0kod='"+anaekran.varsayilansirketkodu+"' AND carikart_0kod='"+anaekran.selectedData+"' AND (tarih Between '"+tarih1+"' and '"+tarih2+"' ) order by tarih ASC ;";	
			    } else {
			    		    	
    komut= "select * from fiskayitlari where firmalarim_0kod='"+anaekran.varsayilansirketkodu+"' AND carikart_0kod='"+anaekran.selectedData+"' order by tarih ASC ;";	
			    }
			    
			    ResultSet rs=stmt.executeQuery(komut);
				while(rs.next()) {
				cnk++;
				}
				con.close();  
				
				}catch(Exception e){ System.out.println(e);}  
			
			satir  = new String[cnk][7];
			 
			 cnk=0;
			
			try{                                              //Veri tabanýndakileri array'e aktarma
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				
				Statement stmt=con.createStatement();  
				
				ResultSet rs=stmt.executeQuery(komut);
				
				while(rs.next()) {
					
					satir[cnk][0] = rs.getString(1);  // fis no oku aktar
					satir[cnk][1] = rs.getString(4);  // tarih oku aktar
					
					String ilkyedi = rs.getString(5).substring(3,7);
					String yedisonrasi = rs.getString(5).substring(7);
					  if (ilkyedi.equals("borc")) {       // borç kaydý ise
						satir[cnk][2] = rs.getString(6); 
						borc=borc+Double.parseDouble(rs.getString(6)); 
						satir[cnk][3] = "0";
					    }
					  if (ilkyedi.equals("alac")) {      // alacak kaydý ise
						satir[cnk][2] =  "0"; 
						satir[cnk][3] =rs.getString(6);
						alacak=alacak+Double.parseDouble(rs.getString(6)); 
					    }
					    satir[cnk][4] = carihareketler.sifrecoz(rs.getString(5).substring(0,7));  // fiþ tipi
					    satir[cnk][5] = yedisonrasi;                                              // evrak no
					    satir[cnk][6] = rs.getString(7);
				 
				 cnk++;
				}
				 con.close();  
				 
				}catch(Exception e){ System.out.println(e);}  
	   	
    	JFrame parentFrame = new JFrame();
    	 
    	JFileChooser fileChooser = new JFileChooser();                    // kaydedilecek excel dosyasýnýn ismini seçme 
    	fileChooser.setDialogTitle("Specify a file to save");   
    	 
    	int userSelection = fileChooser.showSaveDialog(parentFrame);
    	 
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    fileToSave = fileChooser.getSelectedFile();
    	  
    	     }
    	    
    	try {                                                              //excel oluþtur
    		
    		String kayit=fileToSave+".xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
      
            HSSFRow rowhead = sheet.createRow(0);
            rowhead.createCell(0).setCellValue("                                   "+anaekran.varsayilansirketadi.toUpperCase());
          
            rowhead = sheet.createRow(2);
            if (hepsini==false) {
            rowhead.createCell(0).setCellValue(tarih1+" VE "+tarih2+ " TARÝHLERÝ ARASI" );
            }
            
            rowhead = sheet.createRow(3);
            rowhead.createCell(0).setCellValue("***"+anaekran.secilifirmaadi.toUpperCase()+ "  ADLI FÝRMANIN CARÝ HESAP EKSTRESÝ***");
             
            rowhead = sheet.createRow(5);
            rowhead.createCell(0).setCellValue("FÝÞ NO:");
            rowhead.createCell(1).setCellValue("TARÝH");
            rowhead.createCell(2).setCellValue("BORÇ");
            rowhead.createCell(3).setCellValue("ALACAK");
            rowhead.createCell(4).setCellValue("FÝÞ TÝPÝ");
            rowhead.createCell(5).setCellValue("EVRAK NO");
            rowhead.createCell(6).setCellValue("AÇIKLAMA");
          
    	    for (int i=0 ; i<cnk; i++ ) {
    	    HSSFRow row = sheet.createRow(i+7);
            row.createCell(0).setCellValue(satir[i][0]);
            row.createCell(1).setCellValue(satir[i][1]);
           	row.createCell(2).setCellValue(Double.parseDouble(satir[i][2]));	
            row.createCell(3).setCellValue(Double.parseDouble(satir[i][3]));
            row.createCell(4).setCellValue(satir[i][4]);
            row.createCell(5).setCellValue(satir[i][5]);
            row.createCell(6).setCellValue(satir[i][6]);
            }
    	    
    	    HSSFRow row = sheet.createRow(cnk+9);
    	    row.createCell(2).setCellValue("TOPLAM BORÇ");
    	    row.createCell(3).setCellValue("TOPLAM ALACAK");
    	    row.createCell(4).setCellValue("       BAKÝYE");
    	    
    	    row = sheet.createRow(cnk+10);
    	    row.createCell(2).setCellValue(borc);
    	    row.createCell(3).setCellValue(alacak);
    	    row.createCell(4).setCellValue(borc-alacak);
    	    if ((borc-alacak)>0) {
    	    	row.createCell(5).setCellValue("   BORÇLU");	
    	    }
    	    
    	    if ((borc-alacak)<0) {
    	    	row.createCell(5).setCellValue("   ALACAKLI");	
    	    }
    	    
    	    rowhead = sheet.createRow(cnk+17);
            rowhead.createCell(0).setCellValue("BU FÝRMANIN TANIMLANMIÞ LÝMÝTÝ                                               "
    	    +anaekran.secilifirmalimit.toUpperCase());
    	    
    	    for (int i=1 ; i<6; i++ ) {
            	sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOut = new FileOutputStream(kayit);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
           
        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    	try {
    		File a = new File(fileToSave+".xls");
			Desktop.getDesktop().open(a);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
      
    	borc=0;
    	alacak=0;
    	cnk=0;
    	tarih1="";tarih2="";komut="";
    	
}
}
