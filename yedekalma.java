package alverdef;


public class yedekalma {
	static int processComplete;
	
	public static void yedekle() {
		
		
        try {
            
            Process runtimeProcess = Runtime.getRuntime().exec("C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump -u "+anaekran.sqluser+" -p"+anaekran.sqlpass+" --add-drop-database -B alverdefdb -r C:/Users/emrea/eclipse-workspace/backup.sql");
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {

                bilgipenceresi.duyur("      Yedekleme Ba�ar�l�");

            } else {
            	bilgipenceresi.duyur("      Yedekleme Hatas�");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
//"jdbc:mysql://"+anaekran.sqlip+"/alverdefdb?useUnicode=true&characterEncoding=UTF-8",anaekran.sqluser,anaekran.sqlpass);