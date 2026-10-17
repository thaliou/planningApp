// 1. Importations necessaires pour creer les listes et dictionnaires

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Random;
import java.util.HashMap;


//

public class PlanningGeneratorApp{
    public static void main (String[] args) {
        //Declarons la liste des jours de cours
        List<String> jours = Arrays.asList("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi");

        //Declarons la liste des professeurs
        List<Map<String, String>> profs = new ArrayList<>();
        profs.add(Map.of("id", "X", "prenom", "M.", "nom", "X", "matiere", "Java"));
        profs.add(Map.of("id", "Y", "prenom", "M.", "nom", "Y", "matiere", "C"));
        profs.add(Map.of("id", "Z", "prenom", "M.", "nom", "Z", "matiere", "Python"));

        //Test d'affichage:
        // System.out.println("Donnees chargees avec succes !");
        // System.out.println("Jours disponible : " +jours);
        // System.out.println("Premier prof : " +profs.get(0).get("matiere"));

        List<Map<String, String>> monPlanning = genererPlanning(jours, profs);

        System.out.println("\n------PLANNING DE LA SEMAINE-----");

        for(Map<String, String> cours : monPlanning) {
            System.out.println("Le cours de : " +cours.get("jour")+ ", "+cours.get("matiere") + " sera dispense par : "+cours.get("nomDuProf"));
        }
    }

    public static List<Map<String, String>> genererPlanning(List<String> jours, List<Map<String, String>> profs) {
        List<Map<String, String>> planningGenere = new ArrayList<>();
        String profPrecedent = null;
        Random random = new Random();

        for(String jour : jours) {
            List<Map<String, String>> profDispos = new ArrayList<>();

            for(Map<String, String> prof : profs) {
                if(!prof.get("id").equals(profPrecedent)) {
                    profDispos.add(prof);
                }
            }

            int indexAleatoire = random.nextInt(profDispos.size());
            Map<String, String> profChoisi = profDispos.get(indexAleatoire);

            //Creons l'entree du plannig
            Map<String, String> coursDuJour = new HashMap<>();
            coursDuJour.put("jour", jour);
            coursDuJour.put("matiere", profChoisi.get("matiere"));
            coursDuJour.put("nomDuProf", profChoisi.get("prenom") + " " + profChoisi.get("nom"));

            planningGenere.add(coursDuJour);

            //4. Memoriser ce prof pour ne pas le reprendre demain
            profPrecedent = profChoisi.get("id");
        }
        return planningGenere;
    }

}