import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame implements ActionListener {

	private JButton wyb_plik;
	public Main() {
		
		setSize(300,200);
		setTitle("Szyfracja");
		setLayout(null);
		
		wyb_plik= new JButton("Wybierz Plik");
		wyb_plik.setBounds(50,100,120,25);
		add(wyb_plik);
		wyb_plik.addActionListener(this);
		
	}
	public static void main(String[] args) {

		Main aplikacja = new Main();
		aplikacja.setDefaultCloseOperation(EXIT_ON_CLOSE);
		aplikacja.setVisible(true);
		
	}
	
	public void approvedSelection(String plik){
		
		Scanner wtekst = new Scanner(plik);
		String tekst1 = wtekst.nextLine();
		
		byte[] tabtekst = tekst1.getBytes();
		StringBuilder tekstb = new StringBuilder();
		  for (byte b : tabtekst){
		     int war = b;
		     for (int i = 0; i < 8; i++){
		        tekstb.append((war & 128) == 0 ? 0 : 1);
		        war <<= 1;
		     }
		     tekstb.append(' ');
		  }
		 char[] tabtekstb = new char[tekstb.length()];
		 	for( int xt = 0; xt < tekstb.length(); xt++){
				tabtekstb[xt] = tekstb.charAt(xt);	
				System.out.print(tabtekstb);
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
			
				approvedSelection(plik);
				/*
						 	*/
		
			}
			
		}
		
	}
}
