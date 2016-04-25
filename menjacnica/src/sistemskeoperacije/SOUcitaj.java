package sistemskeoperacije;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import menjacnica.Valuta;

public class SOUcitaj 
{
	public static void ucitajIzFajla(String putanja, LinkedList<Valuta> kursnaLista)
	{
		ObjectInputStream in = null;
		
		try
		{
			in = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(putanja)));
			
			LinkedList<Valuta> lista = (LinkedList<Valuta>)(in.readObject());
			
			kursnaLista.clear();
			kursnaLista.addAll(lista);
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				in.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
