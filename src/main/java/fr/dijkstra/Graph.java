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

        if (this.vertices.isEmpty()){System.out.println("Le graphe est vide"); return 0;}
        if (Objects.equals( from, to )){System.out.println("La ville d'arrivee est aussi celle de depart"); return 0;}

        int distanceOptimale = 0;
        int longueurMinimaleCheminsPossibles = 0;
        int nombreIteration = 0;
        int tailleMaxChemin = this.vertices.size();
        List<String> nomsVillesGraphe = new ArrayList<String>();

        for (Vertex vertex : this.vertices) {
            nomsVillesGraphe.add(vertex.getName());
        }
        if (!nomsVillesGraphe.contains(from)){System.out.println("La ville de depart n'est pas dans le graphe"); return 0;}
        if (!nomsVillesGraphe.contains(to)){System.out.println("La ville d'arrivee n'est pas dans le graphe"); return 0;}


        for (Vertex vertex : this.vertices) {
            if (Objects.equals(from, vertex.getName())) {

                List<Edge> possiblePaths = new ArrayList<Edge>();
                Edge depart = new Edge(vertex, 0);
                possiblePaths.add(depart);
                while (distanceOptimale == 0 || longueurMinimaleCheminsPossibles < distanceOptimale) {
                    nombreIteration ++;
                    if (nombreIteration > tailleMaxChemin+2){System.out.println("Il n'y a pas de chemin possible entre ces deux villes"); distanceOptimale = 0; break;}
                    System.out.println("nouvelle iteration n"+nombreIteration);
                    for (ListIterator<Edge> iter = possiblePaths.listIterator(); iter.hasNext();) {
                        Edge chemin = iter.next();
                        iter.remove();
                        for (ListIterator<Edge> iter2 = chemin.getTarget().getEdges().listIterator(); iter2.hasNext();) {
                            Edge etape = iter2.next();
                            if (this.vertices.contains(etape.getTarget())) {

                                Edge newEtape = new Edge(etape.getTarget(),etape.getDistance() + chemin.getDistance());

                                iter.add(newEtape);
                                System.out.println("ville : "+etape.getTarget().getName()+" distance : "+newEtape.getDistance());
                                System.out.println("distance chemin precedant : " + chemin.getDistance());


                                if (Objects.equals(etape.getTarget().getName(), to) && (distanceOptimale == 0 || distanceOptimale > newEtape.getDistance())) {
                                    distanceOptimale = newEtape.getDistance();
                                    System.out.println("Nouvelle Distance! : "+distanceOptimale);
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
                    System.out.println("longueur minimale : " + longueurMinimaleCheminsPossibles);
                }
            }
        }

        System.out.println("La distance de "+from+" a "+to+" vaut "+distanceOptimale);
        return distanceOptimale;
    }
}

/*int dmin = 0;
        int distance = 0;
        List<Path> possiblePaths = new ArrayList<Path>();
        List<Path> possiblePaths2 = new ArrayList<Path>();
        for (Vertex vertex : this.vertices) {
            if (Objects.equals(from, vertex.getName())) {
                List<Vertex> startVertex = new ArrayList<Vertex>();
                startVertex.add(vertex);
                Path depart = new Path(0, startVertex);
                possiblePaths.add(depart);
                while ((dmin <= distance || distance == 0) && !possiblePaths.isEmpty()) {
                    possiblePaths2=possiblePaths;
                    for (Path path : possiblePaths) {
                        for (Edge newStep : path.getSteps().get(path.getSteps().size() - 1).getEdges()) {
                            if (this.vertices.contains(newStep.getTarget()) && !path.getSteps().contains(newStep.getTarget())) {/*//*&& !path.getSteps().contains(newStep.getTarget()*//**//*){
                                int newLongueur = path.getLongueur() + newStep.getDistance();
                                List<Vertex> newSteps = new ArrayList<Vertex>();
                                newSteps = path.getSteps();
                                newSteps.add(newStep.getTarget());
                                System.out.println("nouvelle ville : " + newStep.getTarget().getName());
                                Path newPath = new Path(newLongueur, newSteps);
                                possiblePaths2.add(newPath);
                            }
                        }
                        possiblePaths2.remove(path);
                    }
                    possiblePaths=possiblePaths2;
                    for (Path path : possiblePaths) {
                        if (Objects.equals(path.getSteps().get(path.getSteps().size() - 1).getName(), to) && (distance>path.getLongueur() || distance == 0)) {
                            distance = path.getLongueur();
                            System.out.println("distance trouvée : "+distance);

                        }
                    }
                    dmin = 0;
                    for (Path path : possiblePaths){
                        if (dmin == 0 || dmin > path.getLongueur()){
                            dmin = path.getLongueur();
                            System.out.println("dmin : "+dmin);
                        }
                    }
                }


            }

            }*/

/*int distance=0;
        int dmin = 0;
        List<Path> possiblePaths = new ArrayList<Path>();
        List<Path> possiblePaths2 = new ArrayList<Path>();
        for (Vertex vertex : this.vertices) {
            if (Objects.equals(from, vertex.getName())) {
                List<Vertex> startVertex = new ArrayList<Vertex>();
                startVertex.add(vertex);
                Path depart = new Path(0,startVertex);
                possiblePaths.add(depart);

                while ((dmin < distance || distance == 0) && (!possiblePaths.isEmpty())) {
                    possiblePaths2 = possiblePaths;

                    for (Path path : possiblePaths) {
                        if (Objects.equals(path.getSteps().get(path.getSteps().size() - 1).getName(), to)) {
                            if (( distance > path.getLongueur() ) || distance == 0){
                                distance = path.getLongueur();
                            }
                        }

                        for (Edge newStep : path.getSteps().get(path.getSteps().size() - 1).getEdges()){
                            if ( this.vertices.contains(newStep.getTarget()) *//*&& !path.getSteps().contains(newStep.getTarget()*//*){
                                int newLongueur = path.getLongueur() + newStep.getDistance();
                                List<Vertex> newSteps = new ArrayList<Vertex>();
                                newSteps = path.getSteps();
                                newSteps.add(newStep.getTarget());
                                Path newPath = new Path(newLongueur, newSteps);
                                possiblePaths2.add(newPath);
                            }
                        }

                    possiblePaths2.remove(path);
                    }
                    possiblePaths = possiblePaths2;
                    dmin = 0;
                    for (Path path : possiblePaths){
                        if ( (path.getLongueur() < dmin) || dmin == 0 ){
                            dmin = path.getLongueur();
                        }
                    }
                }


                }




            }

        return distance;*/
/*int distance = 0;

        for (Vertex vertex : this.vertices){
            if (Objects.equals( from , vertex.getName() ) )
                for (Edge edge : vertex.getEdges() ) {
                    if ( Objects.equals(edge.getTarget().getName(), to) && this.vertices.contains(edge.getTarget()) ){
                        distance = edge.getDistance();
                    }

                }
                if (distance == 0){
                    for (Edge edge : vertex.getEdges()){
                        if (this.vertices.contains(edge.getTarget())) {
                            for (Edge edge2 : edge.getTarget().getEdges()) {
                                if (Objects.equals(edge2.getTarget().getName(), to) && this.vertices.contains(edge2.getTarget()) ) {
                                    distance = edge2.getDistance() + edge.getDistance();
                                    break;
                                }
                                if (Objects.equals(edge2.getTarget().getName(), to) && this.vertices.contains(edge2.getTarget())) {
                                    break;
                                }
                            }
                        }

                    }
                }


        }




                    /*if (this.vertices.contains(edge.getTarget()) && Objects.equals(edge.getTarget().getName(), to))
                        distance = edge.getDistance();*/

