package sistemskeoperacije;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import menjacnica.Valuta;

public class SOUpisi
{
	public static void upisiUFajl(String putanja, LinkedList<Valuta> kursnaLista)
	{
		ObjectOutputStream out = null;
		
		try
		{
			out = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(putanja)));
			
			out.writeObject(kursnaLista);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				out.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
