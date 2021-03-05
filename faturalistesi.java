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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class faturalistesi{
	static File fileToSave;
	static String[][] satir;
	static String nelistesi="",tariharasi="";
    static double toplamtutar=0,toplamkdv=0,toplammatrah=0;
    static int cnk;
    
    static JButton yavrudugme2,b;
	static JDialog yavru ;
	static JXDatePicker picker,picker2;
	static JPanel panel,panel2,panel3;
	static String tarih1="",tarih2="",komut="";
	static boolean hepsini=false;
		
    public static void tarihlerisec(String liste) {
		
		hepsini=false;
		komut="";
		tariharasi="";
		nelistesi="";
		
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
			    	excelyap(liste);
			    		    
			    	}  
					});  
		
		panel2.add(yavrudugme2);
		panel3.add(kutucuk);
		yavru.add(panel, BorderLayout.PAGE_START);
		yavru.add(panel2, BorderLayout.PAGE_END);
		yavru.add(panel3, BorderLayout.CENTER);
		
		yavru.setVisible(true);
	
	}
	
  	public static void excelyap(String liste) {
	  	 
			cnk=0;
		
			if (liste.equals("alacak")) {
				nelistesi="' AND (tip LIKE '%fatalac%' OR tip LIKE '%dekalac%' )";
			}
			
			if (liste.equals("borc")) {
				nelistesi="' AND (tip LIKE '%fatborc%' OR tip LIKE '%dekborc%' )";
			}
			
		    if ( hepsini==false) {
		    
		    tariharasi= " AND (tarih between '"+tarih1+"' and '"+tarih2+"') order by tarih ASC";

		    } else {
		    		    	
   	        tariharasi=" order by tarih ASC";
		    }
		    
		    komut="select * from fiskayitlari where firmalarim_0kod='"+anaekran.varsayilansirketkodu+nelistesi+tariharasi;
		
			
			try{                                              // MYSQL Veri tabanýnýn kaç satýr olduðunu bulup array boyutlama
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery(komut);
	
		
				while(rs.next()) {
				cnk++;
				}
				con.close();  
				
				}catch(Exception e){ System.out.println(e);}  
			
			satir  = new String[cnk][6];
			
			 cnk=0;
			
			try{                                              //Veri tabanýndakileri array'e aktarma
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery(komut);
				
				while(rs.next()) {
					
					satir[cnk][0] = rs.getString(4);  // tarih 
					satir[cnk][1] = rs.getString(3);  // cari hesap kodu
					satir[cnk][2] = carihareketler.sifrecoz(rs.getString(5).substring(0,7));  //tip 
					satir[cnk][3] = rs.getString(6);  // tutar
					satir[cnk][4] = rs.getString(5).substring(7);  // Evrak no
					satir[cnk][5] = rs.getString(8);  // kdv tutarý
					if (satir[cnk][2].substring(0, 6).equals("DEKONT")) {
						satir[cnk][5]="0";
					}							 
				 cnk++;
				}
				 con.close();  
				
				}catch(Exception e){ System.out.println(e);}  
	  
		
				for (int i=0 ; i<cnk ; i++)    {
					try{                                              //cari kod hangi firma adýna denk geliyor cari kartlarý tablaosunda bak ve array i deðiþtir.
						Class.forName("com.mysql.jdbc.Driver");  
						Connection con=DriverManager.getConnection(  
								"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
				
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery ("select * from carikartlari where carikart_0kod='"+satir[i][1]+"' ;");
				
			        while(rs.next()) {
					
					satir[i][1] = rs.getString(2).toUpperCase();   
				 
				   }
				  con.close();  
			
			    	}catch(Exception e){ System.out.println(e);}  
			
				}
			
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
            
            if (liste.equals("alacak")) {
            rowhead.createCell(0).setCellValue("SATICI FATURALARI ( FATURA ALACAK VE DEKONT ALACAK) LÝSTESÝ");
            }
            
            if (liste.equals("borc")) {
                rowhead.createCell(0).setCellValue("ALICI FATURALARI ( FATURA BORÇ VE DEKONT BORÇ) LÝSTESÝ");
                }
            
            rowhead = sheet.createRow(5);
            rowhead.createCell(1).setCellValue("TARÝH:");
            rowhead.createCell(2).setCellValue("ÜNVAN");
            rowhead.createCell(3).setCellValue("TÜR");
            rowhead.createCell(4).setCellValue("MATRAH");  
            rowhead.createCell(5).setCellValue("KDV % ");
            rowhead.createCell(6).setCellValue("KDV");  
            rowhead.createCell(7).setCellValue("TOPLAM TUTAR"); 
            rowhead.createCell(8).setCellValue("EVRAK  NO:");
          
    	    for (int i=0 ; i<cnk; i++ ) {
    	    Double tutar =  Double.parseDouble(satir[i][3]);
    	    Double kdvtutari=Double.parseDouble(satir[i][5]);
            Double matrah= tutar -kdvtutari;
                      
            int kdvorani = (int) Math.round((kdvtutari/matrah)*100);	
    	  
            HSSFRow row = sheet.createRow((i+7));
            row.createCell(1).setCellValue(satir[i][0]);
            row.createCell(2).setCellValue(satir[i][1]);
           	row.createCell(3).setCellValue(satir[i][2]);	
           	row.createCell(4).setCellValue(matrah); 
            row.createCell(5).setCellValue(kdvorani);
            row.createCell(6).setCellValue(satir[i][5]); 
            row.createCell(7).setCellValue(tutar);
            row.createCell(8).setCellValue(satir[i][4]);
            
            toplamtutar=toplamtutar+Double.parseDouble(satir[i][3]);
            toplamkdv=toplamkdv+kdvtutari;
            toplammatrah=toplammatrah+matrah;
            }
    	    
    	    HSSFRow row = sheet.createRow(cnk+9);
    	    row.createCell(7).setCellValue("           TOPLAM");
    	    row.createCell(6).setCellValue("TOPLAM");
    	    row.createCell(4).setCellValue("TOPLAM");
    	    
    	    
    	    row = sheet.createRow(cnk+10); 
    	    row.createCell(7).setCellValue(toplamtutar);
    	    row.createCell(6).setCellValue(toplamkdv);
    	    row.createCell(4).setCellValue(toplammatrah);
    	         	    
    	    for (int i=1 ; i<11; i++ ) {
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
      
    	toplamtutar=0;
    	toplamkdv=0;
    	toplammatrah=0;
    	cnk=0;
    	tarih1="";tarih2="";komut="";
    	nelistesi="";tariharasi="";
	}
}
