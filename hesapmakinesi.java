package alverdef;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class hesapmakinesi implements ActionListener {

	static JFrame frame;
	static JPanel panel;
	static JButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
	static JButton buttonplus,buttonminus,buttondiv,buttonmult,buttonequ,buttonclr,buttonpoint;
	static JTextField text1;
	static String d1="",d2="",operator="",eskioperator="+" ,disp; 
	static double a1=0,a2=0;
	
	public static void torpule() {
		if (disp.length()>2) {
			String numune = disp.substring(disp.length()-2);
			     if (numune.equals(".0")) { 
			    	 disp=disp.substring(0,disp.length()-2);
			     }
		
		}
		
		int noktapoz= disp.indexOf(".");
		
		if (noktapoz!=-1) {
			String noktasonrasi=disp.substring(noktapoz, disp.length());
			
			int noktasonrasikacbas= noktasonrasi.length();
			
			if (noktasonrasikacbas>4) {
				disp=disp.substring(0,noktapoz+4);
			}
			
		}
	}
	
	public static void rakambas() {
		
			disp = text1.getText();
		
			d2 = disp;
			disp = d1+d2 ;
			text1.setText(disp);
			d1 = disp ;
			d2="";
			
			}
		
	public static void operbas() {
		
		double sonuc=0;
		disp ="";
		esittir();
		eskioperator=operator;
		
		if (operator.equals("+")) {
			disp = text1.getText();
			a2 = Double.valueOf(disp);
			sonuc= a1+a2;
					
		}
					
		if (operator.equals("x")) {
			if (a2==0) {
				a2=1 ;
				}
			if (a1==0) {
				a1=1;
				}
			disp = text1.getText();
			a2 = Double.valueOf(disp);
			sonuc= a1*a2;
			
		}
		
		if (operator.equals("eksi")) {
			
			disp = text1.getText();
			a2 = Double.valueOf(disp);
			sonuc= a2-a1;
			
		}
		
		if (operator.equals("/")) {
			
			if (a1==0) {
			
				a1=1;
			
			disp = text1.getText();
			a2 = Double.valueOf(disp);
			sonuc= a2/a1;
			a1=0;
			}
			if (a1!=0) {
			
			disp = text1.getText();
			a2 = Double.valueOf(disp);
			sonuc= a1/a2;
			
			}			
			
		}
	     
		disp=Double.toString(sonuc);
		
		torpule();
		
		text1.setText(disp);
		eskioperator=operator;
		
		a1=sonuc;
		d1="";	
		
	}
	
	public static void esittir() {
		
		if(eskioperator.equals("+") && operator.equals("") && a1==0 && a2==00) {
			return;
		}
		
		
		double sonuc = 0;
		disp = text1.getText();
		a2 = Double.valueOf(disp);
		if (eskioperator.equals("+")) {
		sonuc= a1+a2;
		}
		if (eskioperator.equals("x")) {
			if (a2==0) {
				a2=1 ;
				}
			if (a1==0) {
				a1=1;
				
			}
			
			sonuc= a1*a2;
			}
		
		if (eskioperator.equals("eksi")) {
			sonuc= a1-a2;
				
			}
		if (eskioperator.equals("/")) {
			
			if (a1==0) {
				a1=1;
				disp = text1.getText();
				a2 = Double.valueOf(disp);
				sonuc= a2/a1;
				a1=0;
			}
			
			if (a1!=0) {
				
				disp = text1.getText();
				a2 = Double.valueOf(disp);
				sonuc= a1/a2;
				}			
			
		}	
		
		disp=Double.toString(sonuc);
		
		torpule();
	
		text1.setText(disp);
		a2=0;
		a1=0;
		d1="";
		d2="";
		
	}
	
	private enum actions {
	    bir,iki,uc,dort,bes,alti,yedi,sekiz,dokuz,sifir,arti,eksi,bolu,carpi,esit,clr,nokta
	  }
	
	public static void hesapmak() {
		frame=new JFrame("HESAP MAKİNESİ");
		frame.setResizable(false);
		panel=new JPanel();
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");
		button0 = new JButton("0");
		buttonplus = new JButton("+");
		buttonminus = new JButton("-");
		buttondiv = new JButton("/");
		buttonmult = new JButton("x");
	    buttonequ = new JButton("=");
		buttonclr = new JButton("CLR");
		buttonpoint = new JButton(".");
		
		text1 = new JTextField ("0");
		text1.setHorizontalAlignment(SwingConstants.RIGHT);
		text1.setEditable(false);
		
		text1.setFont(new Font("Serif", Font.PLAIN, 30));
			
		buttondiv.setFont (new Font("Serif", Font.PLAIN, 25));
		buttondiv.addActionListener(new hesapmakinesi());
		buttondiv.setActionCommand(actions.bolu.name());
		
		buttonmult.setFont(new Font("Serif", Font.PLAIN, 25));
		buttonmult.addActionListener(new hesapmakinesi());
		buttonmult.setActionCommand(actions.carpi.name());
		
		buttonequ.setFont(new Font("Serif", Font.PLAIN, 25));
		buttonequ.addActionListener(new hesapmakinesi());
		buttonequ.setActionCommand(actions.esit.name());
		
		buttonplus.setFont(new Font("Serif", Font.PLAIN, 25));
		buttonplus.addActionListener(new hesapmakinesi());
		buttonplus.setActionCommand(actions.arti.name());
		
		buttonpoint.setFont(new Font("Serif", Font.PLAIN, 25));
		buttonpoint.addActionListener(new hesapmakinesi());
		buttonpoint.setActionCommand(actions.nokta.name());
		
		button1.setFont(new Font("Serif", Font.PLAIN, 25));
		button1.addActionListener(new hesapmakinesi());
		button1.setActionCommand(actions.bir.name());
		
		button2.setFont(new Font("Serif", Font.PLAIN, 25));
		button2.addActionListener(new hesapmakinesi());
		button2.setActionCommand(actions.iki.name());
		
		button3.setFont(new Font("Serif", Font.PLAIN, 25));
		button3.addActionListener(new hesapmakinesi());
		button3.setActionCommand(actions.uc.name());
		
		button4.setFont(new Font("Serif", Font.PLAIN, 25));
		button4.addActionListener(new hesapmakinesi());
		button4.setActionCommand(actions.dort.name());
		
		button5.setFont(new Font("Serif", Font.PLAIN, 25));
		button5.addActionListener(new hesapmakinesi());
		button5.setActionCommand(actions.bes.name());
		
		button6.setFont(new Font("Serif", Font.PLAIN, 25));
		button6.addActionListener(new hesapmakinesi());
		button6.setActionCommand(actions.alti.name());
		
		button7.setFont(new Font("Serif", Font.PLAIN, 25));
		button7.addActionListener(new hesapmakinesi());
		button7.setActionCommand(actions.yedi.name());
		
		button8.setFont(new Font("Serif", Font.PLAIN, 25));
		button8.addActionListener(new hesapmakinesi());
		button8.setActionCommand(actions.sekiz.name());
		
		button9.setFont(new Font("Serif", Font.PLAIN, 25));
		button9.addActionListener(new hesapmakinesi());
		button9.setActionCommand(actions.dokuz.name());
		
		button0.setFont(new Font("Serif", Font.PLAIN, 25));
		button0.addActionListener(new hesapmakinesi());
		button0.setActionCommand(actions.sifir.name());
		
		buttonminus.setFont(new Font("Serif", Font.PLAIN, 25));
		buttonminus.addActionListener(new hesapmakinesi());
		buttonminus.setActionCommand(actions.eksi.name());
		
		buttonclr.setFont(new Font("Serif", Font.PLAIN, 20));
		buttonclr.addActionListener(new hesapmakinesi());
		buttonclr.setActionCommand(actions.clr.name());
		
		frame.setSize(295,410);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setLayout(null);
		
		text1.setBounds(20, 13, 240, 40);
		button7.setBounds(20,70,50,50);
		button8.setBounds(80,70,50,50);
		button9.setBounds(140,70,50,50);
		buttondiv.setBounds(210,70,50,50);
		button4.setBounds(20,140,50,50);
		button5.setBounds(80,140,50,50);
		button6.setBounds(140,140,50,50);
		buttonmult.setBounds(210,140,50,50);
		button1.setBounds(20,210,50,50);
		button2.setBounds(80,210,50,50);
		button3.setBounds(140,210,50,50);
		buttonminus.setBounds(210,210,50,50);
		button0.setBounds(20,280,50,50);
		buttonpoint.setBounds(80,280,50,50);
		buttonequ.setBounds(140,280,50,50);
		buttonplus.setBounds(210,280,50,50);
		buttonclr.setBounds(20,335,90,25);
		
		frame.add(panel);
		
		panel.add(button0);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(button7);
		panel.add(button8);
		panel.add(button9);
		panel.add(button0);
		panel.add(buttonclr);
		panel.add(buttondiv);
		panel.add(buttonminus);
		panel.add(buttonmult);
		panel.add(buttonplus);
		panel.add(buttonequ);
		panel.add(buttonpoint);
		panel.add(text1);
				
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getActionCommand()==actions.bir.name()) {
			text1.setText("1");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.iki.name()) {
			text1.setText("2");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.uc.name()) {
			text1.setText("3");
			rakambas();	
						
	     }
		if (e.getActionCommand()==actions.dort.name()) {
			text1.setText("4");
			rakambas();	
		 }
		if (e.getActionCommand()==actions.bes.name()) {
			text1.setText("5");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.alti.name()) {
			text1.setText("6");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.yedi.name()) {
			text1.setText("7");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.sekiz.name()) {
			text1.setText("8");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.dokuz.name()) {
			text1.setText("9");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.sifir.name()) {
			text1.setText("0");
			rakambas();	
		}
		
		if (e.getActionCommand()==actions.nokta.name()) {
			
			disp = text1.getText();
			int y =disp.indexOf(".");
			if  (y==-1)  {
				text1.setText(".");
				rakambas ();
				
		                }
		
	    }
		
		if (e.getActionCommand()==actions.esit.name()) {
			
			esittir();
		}
		
		if (e.getActionCommand()==actions.arti.name()) {
			
			  operator="+";
			  operbas();
		}
		
		if (e.getActionCommand()==actions.bolu.name()) {
			
			  operator="/";
			  operbas();
		}
		
		if (e.getActionCommand()==actions.eksi.name()) {
			
			  operator="eksi";
			  operbas();
		}
		
		if (e.getActionCommand()==actions.carpi.name()) {
			
			  operator="x";
			  operbas();
		}
		
		if (e.getActionCommand()==actions.clr.name()) {
			
			text1.setText("0");
			a1=0;
			a2=0;
			d1="";
			d2="";
			operator="";
			eskioperator="+";
	}
}
		
}
