package sistemskeoperacije;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SOObrisiValutu 
{
	public static void obrisiValutu(Valuta valuta, LinkedList<Valuta> kursnaLista)
	{
		if (!kursnaLista.contains(valuta))
			throw new RuntimeException("CARE nema ove valute....");
		kursnaLista.remove(valuta);}
}
