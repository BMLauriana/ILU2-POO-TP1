package histoire;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		
		Village village = new Village("village",20,30);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		Gaulois abraracourcix = new Gaulois("Abraracourcix",2);
		
		
		//Test liberer etal
		Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test liberer etal\n");
		
		//Test acheter produit acheteur null
		village.installerVendeur(bonemine, "fleurs", 20);
		Etal etalFleur = village.rechercherEtal(bonemine);
		etalFleur.acheterProduit(3, null);
		System.out.println("Fin du test acheteur null\n");
		//Test acheter produit quantite negative
		try {
			etalFleur.acheterProduit(-3, abraracourcix);
		}catch(IllegalArgumentException e){
			System.out.println("La quantite a acheter est negative\n");
		}	
		System.out.println("Fin du test quantite negative\n");
		//Test acheter produit etal vide
		try {
			etal.acheterProduit(2, abraracourcix);
		}catch(IllegalArgumentException e){
			System.out.println("L'etal est null\n");
		}
		System.out.println("Fin du test etal vide\n");
	}
	

}
