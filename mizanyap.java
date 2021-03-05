					// EXCEL'E BAKÝYELER MÝZANI ALMAK // 
package alverdef;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class mizanyap{
	static File fileToSave;
	static String[][] satir;
    static double borc=0,alacak=0,bakiye=0,genelbakiye=0;
    static int cnk;
    static String[] firmalistesi ; 	
    static String[] firmaadlari;  	
	public static void excelyap() {
	  	 
			cnk=0;
		
			
			try{                                              // MYSQL Veri tabanýnýn da satýr sayýsý bulma
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery
						("select * from carikartlari  ;");		
			
				while(rs.next()) {
				 cnk++;
				}
				con.close();  
				
				}catch(Exception e){ System.out.println(e);}  
			
			firmalistesi = new String[cnk];
			firmaadlari= new String[cnk];
			
			cnk=0;
				
			
			try{                                              // MYSQL Veri tabanýnýn da satýr sayýsý bulma
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery
						("select * from carikartlari order by carikart_1unvan ASC ;");		
			
				while(rs.next()) {
				firmalistesi[cnk]= rs.getString(1);
				firmaadlari[cnk]=rs.getString(2);
					cnk++;
				}
				con.close();  
				
				}catch(Exception e){ System.out.println(e);}  
			
			satir  = new String[cnk][3];
					 
			 for (int i=0 ; i<cnk; i++  ) {
				 
				 try{                                              // MYSQL Veri tabanýnýn kaç satýr olduðunu bulup array boyutlama
						Class.forName("com.mysql.jdbc.Driver");  
						Connection con=DriverManager.getConnection(  
								"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
						Statement stmt=con.createStatement();  
						ResultSet rs=stmt.executeQuery
								("select * from fiskayitlari where firmalarim_0kod='"+anaekran.varsayilansirketkodu+"' AND carikart_0kod='"+firmalistesi[i]+"' ;");		
						
						while(rs.next()) {
												
							String ilkyedi = rs.getString(5).substring(3,7);
							
							  if (ilkyedi.equals("borc")) {       // borç kaydý ise
								
								borc=borc+Double.parseDouble(rs.getString(6)); 
							
							    }
							  if (ilkyedi.equals("alac")) {      // alacak kaydý ise
								
								alacak=alacak+Double.parseDouble(rs.getString(6)); 
						
							  }	
							 							 
					     	}
						bakiye=borc-alacak;
						
							  
						con.close();  
						
						}catch(Exception e){ System.out.println(e);}
				 
				 satir[i][0]=firmalistesi[i];
				 satir[i][1]=firmaadlari[i].toUpperCase();
				 satir[i][2]=String.valueOf(bakiye);
				 genelbakiye=genelbakiye+bakiye;
				 bakiye=0;
				 borc=0;
				 alacak=0;
				 								 
				 }
				 
		   	
    	JFrame parentFrame = new JFrame();
    	 
    	JFileChooser fileChooser = new JFileChooser();                    // kaydedilecek excel dosyasýnýn ismini seçme  fayl çuuzýr . 
    	fileChooser.setDialogTitle("Specify a file to save");   
    	 
    	int userSelection = fileChooser.showSaveDialog(parentFrame);
    	 
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    fileToSave = fileChooser.getSelectedFile();
    	  
    	     }
    	    
    	try {                 //excel oluþtur
    		
    		String kayit=fileToSave+".xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
          
            HSSFRow rowhead = sheet.createRow((short)2);
            rowhead.createCell(0).setCellValue("***"+anaekran.varsayilansirketadi.toUpperCase()+ "   BAKÝYELER MÝZANI ***");
            
            rowhead = sheet.createRow((short)5);
            rowhead.createCell(0).setCellValue("CH KOD:");
            rowhead.createCell(1).setCellValue("FÝRMA ÜNVANI");
            rowhead.createCell(2).setCellValue("BAKÝYE");
          
    	    for (int i=0 ; i<cnk; i++ ) {
    	    HSSFRow row = sheet.createRow((short)i+7);
     	 
    	    row.createCell(0).setCellValue(satir[i][0]);
            row.createCell(1).setCellValue(satir[i][1]);
         	row.createCell(2).setCellValue(Double.parseDouble(satir[i][2]));
         	
            if (Double.parseDouble(satir[i][2])>0) {
    	    	row.createCell(3).setCellValue("   BORÇLU");	
    	    }
    	    
    	    if (Double.parseDouble(satir[i][2])<0) {
    	    	row.createCell(3).setCellValue("   ALACAKLI");	
    	    }
         	         	 	
            }
    	    
    	    HSSFRow row = sheet.createRow((short)cnk+9);
    	    row.createCell(2).setCellValue("TOPLAM ");
    	    
    	    
    	    row = sheet.createRow((short)cnk+10);
    	    row.createCell(2).setCellValue(genelbakiye);
    	    
    	    if (genelbakiye>0) {
    	    	row.createCell(3).setCellValue("   BORÇLU");	
    	    }
    	    
    	    if (genelbakiye<0) {
    	    	row.createCell(3).setCellValue("   ALACAKLI");	
    	    }
    	    
    	    
    	    
    	    genelbakiye=0;
            cnk=0;
           
    	    for (int i=1 ; i<4; i++ ) {
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
    }
	
}
