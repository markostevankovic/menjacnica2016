package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import sistemskeoperacije.SOIzvrsiTransakciju;
import sistemskeoperacije.SOUpisi;
import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler 
{
	public static MenjacnicaGUI startFrame;
	public static MenjacnicaInterface menjacnica;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					menjacnica = new Menjacnica();
					
					startFrame = new MenjacnicaGUI();
					startFrame.setLocationRelativeTo(null);
					startFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() 
	{
		int option = JOptionPane.showConfirmDialog(
				null,
				"Napustate aplikaciju?",
				"Exit",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void showDodajKursGUI()
	{
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setLocationRelativeTo(startFrame.getContentPane());
		prozor.setVisible(true);
	}
	
	public static void prikaziAboutProzor()
	{
		JOptionPane.showMessageDialog(
				null,
				"Author: Bojan Tomic vs Marko Stevankovic, Verzija 1.0", 
				"O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void prikaziSveValute(){
		MenjacnicaTableModel model = (MenjacnicaTableModel)(startFrame.getTable().getModel());
		model.staviSveValuteUModel(GUIKontroler.vratiKursnuListu());
	}
	
	public static void prikaziObrisiKursGUI(Valuta valuta)
	{
		if (valuta != null) 
		{
			ObrisiKursGUI prozor = new ObrisiKursGUI(valuta);
			prozor.setLocationRelativeTo(startFrame.getContentPane());
			prozor.setVisible(true);
		}
		else 
		{
			JOptionPane.showMessageDialog(
					null, 
					"You have to choose/select at least one row...",
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void prikaziIzvrsiZamenuGUI(Valuta valuta)
	{
		if (valuta != null)
		{
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(valuta);
			prozor.setLocationRelativeTo(startFrame.getContentPane());
			prozor.setVisible(true);
		}
		else 
		{
			JOptionPane.showMessageDialog(
					null,
					"You have to choose/select at least one row...",
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void sacuvajUFajl()
	{
		try
		{
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showSaveDialog(startFrame.getContentPane());

			if (option == JFileChooser.APPROVE_OPTION) 
			{
				File file = fileChooser.getSelectedFile();
				SOUpisi.upisiUFajl(file.getAbsolutePath(), menjacnica.vratiKursnuListu());
			}
		} 
		catch (Exception exc) 
		{
			JOptionPane.showMessageDialog(
					null,
					exc.getMessage(),
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void ucitajIzFajla()
	{
		try 
		{
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(startFrame.getContentPane());

			if (option == JFileChooser.APPROVE_OPTION) 
			{
				File file = fileChooser.getSelectedFile();
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				GUIKontroler.prikaziSveValute();
			}	
		} 
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(
					null,
					exc.getMessage(),
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void unesiKurs(String naziv, String skraceniNaziv, int sifra, String prodajni, String kupovni, String srednji)
	{
		try 
		{
			Valuta valuta = new Valuta(sifra, skraceniNaziv, naziv, Double.parseDouble(kupovni), Double.parseDouble(srednji), Double.parseDouble(prodajni));
			
			menjacnica.dodajValutu(valuta);
			
			GUIKontroler.prikaziSveValute();
		} 
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(
					null,
					exc.getMessage(),
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
			// exc.printStackTrace();
		}
		
	}
	
	public static void obrisiKurs(Valuta valuta)
	{
		try
		{
			menjacnica.obrisiValutu(valuta);
			GUIKontroler.prikaziSveValute();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(
					null,
					"",
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static double izvrsiZamenu(String iznos, boolean prodaja, Valuta valuta)
	{
		try
		{
			// double vrednost = SOIzvrsiTransakciju.izvrsiTransakciju(valuta, prodaja,(Double.parseDouble(iznos)) );
			
			double vrednost = menjacnica.izvrsiTransakciju(valuta, prodaja, (Double.parseDouble(iznos)));
			return vrednost;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(
					null, 
					"",
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
		return 0;	
	}
	
	public static List<Valuta> vratiKursnuListu() {
		return menjacnica.vratiKursnuListu();
	}
}
