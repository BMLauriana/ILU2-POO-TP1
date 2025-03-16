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
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etal[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		private int trouverEtalLibre() {
			for(int i=0;i<etal.length;i++) {
				if(!(etal[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		private Etal[] trouverEtals(String produit) {
			Etal [] etalAvecProduit = new Etal[etal.length];
			int indiceEtalAvecProduit =0;
			for(int i=0;i<etal.length;i++) {
				if (etal[i].contientProduit(produit)) {
					etalAvecProduit[indiceEtalAvecProduit] = etal[i];
					indiceEtalAvecProduit+=1;
				}
			}
			return etalAvecProduit;
		}
		private Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etal.length;i++) {
				if (gaulois.equals(etal[i].getVendeur())){
					return etal[i];
				}
			}
			return null;
		}
		private String afficherMarche() {
			int nombreEtalVide=0;
			StringBuilder chaine = new StringBuilder();
			for(int i=0;i<etal.length;i++) {
				if (etal[i].afficherEtal().equals("L'étal est libre")) {
					nombreEtalVide+=1;
				}else {
					chaine.append(etal[i].afficherEtal()+"\n");
				}
			}
			if(nombreEtalVide!=0) {
				chaine.append("Il reste "+nombreEtalVide +" etals non utilises dans le marche.\n");
			}
			return chaine.toString();
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

	public String afficherVillageois() throws VillageSansChefException{
		if (chef ==null) {
			throw new VillageSansChefException("Le village n'a pas de chef\n");
		}
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
		chaine.append("Le vendeur " +vendeur.getNom()+" cherche un endroit pour vendre "+nbProduit+ " " +produit+".\n");
		int indiceEtal = marche.trouverEtalLibre();
		if(indiceEtal==-1) {
			chaine.append("Le vendeur n'a pas trouve d'etal.\n");
		}else{
			marche.utiliserEtal(indiceEtal,vendeur,produit,nbProduit);
			chaine.append("Le vendeur "+ vendeur.getNom() + " vend des "+produit+" a l'etal numero "+ indiceEtal +"\n");
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit){
		StringBuilder chaine = new StringBuilder();
		Etal[] etalProduit = marche.trouverEtals(produit);
		if(etalProduit[0]==null) {
			chaine.append("Il n'y a pas de vendeur qui vend des "+produit+" au marche\n");
			return chaine.toString();		
		}else if (etalProduit[1]==null) {
			Gaulois nomVendeur = etalProduit[0].getVendeur();
			chaine.append("Seul le vendeur "+nomVendeur.getNom()+" propose des "+produit+" au marche\n");
			return chaine.toString();
		}else{
			chaine.append("Les vendeurs qui proposent des "+produit+" sont :\n");
			int indice=0;
			while(etalProduit[indice]!=null) {
				Gaulois nomDesVendeurs = etalProduit[indice].getVendeur();
				chaine.append("- " +nomDesVendeurs.getNom()+" \n");
				indice+=1;
			}
			return chaine.toString();
		}
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		Etal etalVendeur = marche.trouverVendeur(vendeur);
		return etalVendeur;
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		Etal etalALiberer = new Etal();
		etalALiberer = marche.trouverVendeur(vendeur);
		chaine.append(etalALiberer.libererEtal());
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marche du village \"le village des irreductibles\" possede plusieurs etals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
	

}