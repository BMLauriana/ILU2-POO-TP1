package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class Scenario {

	public static void main(String[] args) {
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		Gaulois abraracourcix = new Gaulois("Abraracourcix",2);
		Village village = new Village("village",20,30);
		
//		village.ajouterHabitant(bonemine);
//		village.ajouterHabitant(assurancetourix);
//		village.ajouterHabitant(asterix);
//		village.ajouterHabitant(obelix);
//		village.ajouterHabitant(druide);
//		village.ajouterHabitant(abraracourcix);
//		village.afficherVillageois();

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		System.out.println(village.rechercherVendeursProduit("fleurs"));
		System.out.println(village.installerVendeur(assurancetourix, "lyres", 5));
		System.out.println(village.installerVendeur(obelix, "menhirs", 2));
		System.out.println(village.installerVendeur(druide, "fleurs", 10));

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		Etal etalFleur = village.rechercherEtal(bonemine);
		System.out.println(etalFleur.afficherEtal());
		System.out.println(etalFleur.acheterProduit(10, abraracourcix));
		System.out.println(etalFleur.acheterProduit(15, obelix));
		System.out.println(etalFleur.acheterProduit(15, assurancetourix));
		System.out.println(village.partirVendeur(bonemine));
		System.out.println(village.afficherMarche());
		
		Etal etal = new Etal();
		try {
			etalFleur.acheterProduit(-3, abraracourcix);
		}catch(IllegalArgumentException e){
			System.out.println("La quantite a acheter est negative\n");
		}	

		
		try {
			etal.acheterProduit(2, abraracourcix);
		}catch(IllegalArgumentException e){
			System.out.println("L'etal est null\n");
		}

	}

}
