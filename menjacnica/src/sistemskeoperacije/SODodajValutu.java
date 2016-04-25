package sistemskeoperacije;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SODodajValutu 
{
	public static void dodajValutu(Valuta valuta, LinkedList<Valuta> kursnaLista)
	{
		if (valuta==null)
			throw new RuntimeException("A care ne moze valuta null...");
		
		if (kursnaLista.contains(valuta))
			throw new RuntimeException("Kralju...vec si je uneo...");
		
		kursnaLista.add(valuta);
	}
}
