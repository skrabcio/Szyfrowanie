import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
// deszyfracja
// generator hasla
// jezyk czytelny

public class Main extends JFrame implements ActionListener {
	private String tekst1, klucz, tekst_jawny="";
	private JButton wyb_plik, gen_pass, butt_szyfr;
	private JLabel lp,ls;
	private JTextField tp;
	private JTextArea text_szyfrogram;
	private char[] tab_tekst_bity;
	private char[] tab_klucz_bity;
	private char[] tab_tekst_zak;
	
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
		
		ls = new JLabel("Szyfrogram:");
		ls.setBounds(25,100,120,25);
		add (ls);
		
		text_szyfrogram = new JTextArea("");
		text_szyfrogram.setBounds(25,125,250, 150);
		text_szyfrogram.setLineWrap(true);
		text_szyfrogram.setEditable(false);
		text_szyfrogram.setVisible(true);
		add(text_szyfrogram);
		
		
		butt_szyfr= new JButton("Szyfruj");
		butt_szyfr.setBounds(25,300,100,25);
		add(butt_szyfr);
		butt_szyfr.addActionListener(this);
	}
	public static void main(String[] args) {

		Main aplikacja = new Main();
		aplikacja.setDefaultCloseOperation(EXIT_ON_CLOSE);
		aplikacja.setVisible(true);
		
	}
	public void klucz_szyfr(String klucz){
		
		Scanner wszyfr = new Scanner(klucz);
		String klucz_text = wszyfr.nextLine();
		String klucz_uzup = klucz_text;
	
		if(tekst1.length()>klucz_text.length()){
			for(int i=klucz_text.length()-1; i<=tekst1.length(); i+=klucz_uzup.length()){
				klucz_text += klucz_uzup;
			}
	}
		
	byte[] tab_klucz = klucz_text.getBytes();
	 StringBuilder klucz_bity = new StringBuilder();
		 for (byte b : tab_klucz){
		    int war = b;
		    for (int i = 0; i < 8; i++){
		       klucz_bity.append((war & 128) == 0 ? 0 : 1);
		       war <<= 1;
		    }
		    klucz_bity.append(' ');
		 }
	tab_klucz_bity = new char[klucz_bity.length()];
	for(int i = 0; i < klucz_bity.length(); i++){
		tab_klucz_bity[i] = klucz_bity.charAt(i);	
	}
	System.out.println(tab_klucz_bity);
	szyfrowanie();
	}
	// *****************************************************************************
	public void gen_klucz(){
		
	}
	//******************************************************************************
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
		  tab_tekst_bity = new char[tekst_bity.length()];
		 	for( int xt = 0; xt < tekst_bity.length()-1; xt++){
				tab_tekst_bity[xt] = tekst_bity.charAt(xt);	
		 	}
		 	System.out.println(tab_tekst_bity);

	}
	public void szyfrowanie(){
		tab_tekst_zak = new char[tab_tekst_bity.length];
		for(int k=0; k<tab_tekst_bity.length-1; k++){
			if(tab_tekst_bity[k]== tab_klucz_bity[k]){
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
		System.out.println(tab_tekst_zak);
		tekst();
}
	//*****************************************************************************
	public void tekst() {
		char[] tab_zak = new char[tab_tekst_zak.length-1];
		for(int x=0; x<tab_tekst_zak.length-1;x++){
			tab_zak[x]=tab_tekst_zak[x];
		}
		String dekod = new String(tab_zak);
		
		StringTokenizer st = new StringTokenizer(dekod," ");
        while(st.hasMoreTokens()){
        
        	int ascii = Integer.parseInt(st.nextToken(), 2);
            char character = (char)ascii;
            tekst_jawny += character;
            zapis();
        	
        }
		
	}
	//*****************************************************************************
	public void zapis() {
		
		PrintWriter zapis;
		try {
			text_szyfrogram.setText(String.valueOf(tekst_jawny));
			zapis = new PrintWriter("C:\\Users\\skrab\\Desktop\\projekt\\szyfrogram.txt");
			zapis.println(tekst_jawny);
			zapis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		klucz= tp.getText();
		klucz_szyfr(klucz);
		}
		
	}
}
