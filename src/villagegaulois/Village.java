package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	private static class Marche{
		private Etal[] etal ;
		
		private Marche(int nbEtal) {
			etal = new Etal[nbEtal];
			for(int i=0; i<nbEtal;i++) {
				etal[i]=new Etal();
			}
		}
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etal[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		public int trouverEtalLibre() {
			for(int i=0;i<etal.length;i++) {
				if(!(etal[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		public Etal[] trouverEtals(String produit) {
			Etal [] etalAvecProduit = new Etal[etal.length];
			int indiceEtalAvecProduit =0;
			for(int i=0;i<etal.length;i++) {
				if (etal[i].contientProduit(produit)) {
					etalAvecProduit[indiceEtalAvecProduit] = etal[i];
				}
			}
			return etalAvecProduit;
		}
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etal.length;i++) {
				if (gaulois.equals(etal[i].getVendeur())){
					return etal[i];
				}
			}
			return null;
		}
		public void afficherMarche() {
			int nombreEtalVide=0;
			for(int i=0;i<etal.length;i++) {
				if (etal[i].afficherEtal().equals("L'étal est libre")) {
					nombreEtalVide+=1;
				}else {
					System.out.println(etal[i].afficherEtal()+"\n");
				}
			}
			if(nombreEtalVide!=0) {
				System.out.println("Il reste "+nombreEtalVide +" etals non utilises dans le marche.\n");
			}
		}
	}
	
	
	

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur+" cherche un endroit pour vendre "+nbProduit+ produit+".\n");
		int indiceEtal = marche.trouverEtalLibre();
		if(indiceEtal==-1) {
			
		}
		return chaine.toString();
	}
}