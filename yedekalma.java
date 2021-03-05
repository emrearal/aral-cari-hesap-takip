package alverdef;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class yedekalma {
	static int processComplete;
	static File fileToSave;
	
	public static void yedekle() {
		
		JFrame parentFrame = new JFrame();
   	 
    	JFileChooser fileChooser = new JFileChooser();                    // kaydedilecek excel dosyasının ismini seçme 
    	fileChooser.setDialogTitle("Specify a file to save");   
    	 
    	int userSelection = fileChooser.showSaveDialog(parentFrame);
    	 
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    fileToSave = fileChooser.getSelectedFile();
    	  
    	     }
		
		
		
		
        try {
            
            Process runtimeProcess = Runtime.getRuntime().exec("C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump "
            		+ "-u "+anaekran.sqluser+" -p"+anaekran.sqlpass+" --add-drop-database -B alverdefdb -r "+fileToSave+".sql");
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {

                bilgipenceresi.duyur("      Yedekleme Başarılı");

            } else {
            	bilgipenceresi.duyur("      Yedekleme Hatası");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
//"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);
