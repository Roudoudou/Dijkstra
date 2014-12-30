package fr.dijkstra;

import java.util.*;

/**
 * Created by clucas on 23/12/2014.
 */
public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String from, String to){

        /*Réglages pour les tests aux limites*/

        if (this.vertices.isEmpty()){System.out.println("Le graphe est vide"); return 0;}

        if (Objects.equals( from, to )){System.out.println("La ville d'arrivee est aussi celle de depart"); return 0;}

        List<String> nomsVillesGraphe = new ArrayList<String>();

        for (Vertex vertex : this.vertices) {
            nomsVillesGraphe.add(vertex.getName());
        }

        if (!nomsVillesGraphe.contains(from)){System.out.println("La ville de depart n'est pas dans le graphe"); return 0;}

        if (!nomsVillesGraphe.contains(to)){System.out.println("La ville d'arrivee n'est pas dans le graphe"); return 0;}



        /*Début de l'algo'. Idée retenue en faisant croître les chemins en complexifiant peu à peu les tests.* */

        int distanceOptimale = 0; /*On y stocke la longueur du meilleur chemin déjâ trouvé pour aller de la ville de départ à la ville d'arrivée*/

        int longueurMinimaleCheminsPossibles = 0; /*C'est la longueur minimale parmis tous les chemins partant de la ville de départ et passant par le meme nombre de ville qu'il y a eu d'itération de la boucle while*/

        int nombreIteration = 0;

        int tailleMaxChemin = this.vertices.size(); /*Une taille que le chemin le plus court ne peut pas depasser*/

        for (Vertex vertex : this.vertices) {
            if (Objects.equals(from, vertex.getName())) {

                /*On s'interesse à la liste de tous les chemins possibles partant de la bonne ville et passant par le meme nombre de ville rangés dans possiblePath.*/

                List<Edge> possiblePaths = new ArrayList<Edge>();  /*Un chemin est representé par un Edge dont l'attribut target est la dernière étape et l'attribut distance est la longueur.*/
                Edge depart = new Edge(vertex, 0);
                possiblePaths.add(depart);
                while (distanceOptimale == 0 || longueurMinimaleCheminsPossibles < distanceOptimale) {

                /*A chaque itération, on remplace dans possiblePaths tous les chemins possibles partant de la ville de départ passant par n villes avec tous les chemins possibles partant de la
                ville de départ passant par n+1 villes. Ce n'est plus la peine de chercher le plus court chemin parmi ces derniers lorsque leur distance minimale a dépassée celle du meilleur bon chemin déjâ trouvé.*/

                    nombreIteration ++;

                    if (nombreIteration > tailleMaxChemin+2 && distanceOptimale == 0){System.out.println("Il n'y a pas de chemin possible entre ces deux villes"); distanceOptimale = 0; break;}

                    /*Remplacement par les chemins passant par une ville de plus grâce à deux boucles for.
                    Pour chaque chemin de possiblePaths, on va chercher les nouveaux chemins possibles parmis tous ses voisins dans le graphe*/

                    for (ListIterator<Edge> iter = possiblePaths.listIterator(); iter.hasNext();) {

                        Edge chemin = iter.next();

                        iter.remove();

                        for (ListIterator<Edge> iter2 = chemin.getTarget().getEdges().listIterator(); iter2.hasNext();) {

                            Edge etape = iter2.next();

                            if (this.vertices.contains(etape.getTarget())) {

                                Edge newEtape = new Edge(etape.getTarget(),etape.getDistance() + chemin.getDistance());

                                iter.add(newEtape);


                                if (Objects.equals(etape.getTarget().getName(), to) && (distanceOptimale == 0 || distanceOptimale > newEtape.getDistance())) {

                                    distanceOptimale = newEtape.getDistance(); /*Si le chemin qu'on ajoute est le nouveau bon plus court chemin' on stocke sa longueur*/
                                }

                            }

                        }


                    }

                    longueurMinimaleCheminsPossibles = 0;

                    for (ListIterator<Edge> iter = possiblePaths.listIterator(); iter.hasNext();){

                        Edge chemin = iter.next();

                        if (longueurMinimaleCheminsPossibles == 0 || longueurMinimaleCheminsPossibles > chemin.getDistance()){

                            longueurMinimaleCheminsPossibles = chemin.getDistance();
                        }
                    }
                }
            }
        }

        System.out.println("La distance de "+from+" a "+to+" vaut "+distanceOptimale);
        return distanceOptimale;
    }
}