import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
	private String tekst1;
	private JButton wyb_plik, gen_pass, butt_szyfr;
	private JLabel lp;
	private JTextField tp;
	private char[] tab_tekst_bity;
	

	
	public Main() {
		
		setSize(300,400);
		setTitle("Szyfracja");
		setLayout(null);
		
		wyb_plik= new JButton("Wybierz Plik");
		wyb_plik.setBounds(90,25,120,25);
		add(wyb_plik);
		wyb_plik.addActionListener(this);
		
		lp = new JLabel("Podaj has³o:");
		lp.setBounds(25,50,120,25);
		add (lp);
		
		
		tp = new JTextField("");
		tp.setBounds(25,75,150,25);
		add(tp);
		tp.addActionListener(this);

		gen_pass= new JButton("Generuj");
		gen_pass.setBounds(175,75,100,25);
		add(gen_pass);
		gen_pass.addActionListener(this);
		
		butt_szyfr= new JButton("Szyfruj");
		butt_szyfr.setBounds(25,250,100,25);
		add(butt_szyfr);
		butt_szyfr.addActionListener(this);
	}
	public static void main(String[] args) {

		Main aplikacja = new Main();
		aplikacja.setDefaultCloseOperation(EXIT_ON_CLOSE);
		aplikacja.setVisible(true);
		
	}
	
	public void szyfrowanie(){
		byte[] tab_tekst_zak = new byte[tekst1.length()];
		for(int k=0; k<licznik; k++){
			if(tab_tekst_bity[k]== tabszyfrb[k]){
				if(tab_tekst_bity[k]==' '){
					tab_tekst_zak[k]=' ';
				}
				else{
					tab_tekst_zak[k]='0';}
			}
			else{
				tab_tekst_zak[k]='1';
			}
		}
}
	
	public void approvedSelection(File tekst) throws FileNotFoundException{
		
		
		Scanner wtekst = new Scanner(tekst);
		tekst1 = wtekst.nextLine();
		
		byte[] tab_tekst = tekst1.getBytes();
		StringBuilder tekst_bity = new StringBuilder();
		
		
		  for (byte b : tab_tekst){
		     int war = b;
		     for (int i = 0; i < 8; i++){
		        tekst_bity.append((war & 128) == 0 ? 0 : 1);
		        war <<= 1;
		     }
		     tekst_bity.append(' ');
		  }
		  char[] tab_tekst_bity = new char[tekst_bity.length()];
		 	for( int xt = 0; xt < tekst_bity.length()-1; xt++){
				tab_tekst_bity[xt] = tekst_bity.charAt(xt);		
		 	}
		 	System.out.print(tab_tekst_bity);

	}
	@Override
	public void actionPerformed(ActionEvent e){
		Object z = e.getSource();
		
		if(z==wyb_plik){

			JFileChooser fc = new JFileChooser();
			if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
				String plik = fc.getSelectedFile().getAbsolutePath();
				JOptionPane.showMessageDialog(null,"Wybrany plik:" + fc.getSelectedFile().getAbsolutePath());
				File tekst = new File(plik);
				try {
					approvedSelection(tekst);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			}
			
		}
		else if(z == tp || z == butt_szyfr){
			
		}
		
	}
}
