import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
	@Override
	public void actionPerformed(ActionEvent e){
		Object z = e.getSource();
		
		if(z==wyb_plik){
			JFileChooser fc = new JFileChooser();
			if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
				File plik = fc.getSelectedFile();
				JOptionPane.showMessageDialog(null,"Wybrany plik:" + fc.getName());
			}
		}
		
	}
}
