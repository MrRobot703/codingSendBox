package taskFour.graph;

import java.util.*;

public class Graph<T> {

    private final Map<T, Set<T>> graph;
    private Set<T> maximalClique;
    private List<Set<T>> maximalCliques;

    public Graph(T[][] edgesValues) {
        graph = new HashMap<>();
        Set<T> neighbors;
        for(T[] edge: edgesValues) {
            for (int i = 0; i < edge.length; i++) {
                if (graph.containsKey(edge[i])) {
                    neighbors = graph.get(edge[i]);
                    neighbors.add(edge[(i + 1)%edge.length]);
                } else {
                    neighbors = new HashSet<>();
                    neighbors.add(edge[(i + 1)%edge.length]);
                    graph.put(edge[i], neighbors);
                }
            }
        }
    }

    public List<Set<T>> getMaximalCliques() {
        maximalClique = new HashSet<>();
        maximalCliques = new ArrayList<>();

        Set<T> V = new HashSet<>(graph.keySet());
        //expand(V, new HashSet<>(V));
        //expandWithoutPruning(V);
        expandWithPruning(V, new HashSet<>(V));

        return maximalCliques;
    }

    private void expandWithPruning(Set<T> subgraph, Set<T> fresh) {
        if (subgraph.isEmpty()) {
            maximalCliques.add(new HashSet<>(maximalClique));
        } else {
            for (T vertex: subgraph) {
                maximalClique.add(vertex);
                Set<T> neighbors = graph.get(vertex);
                expandWithPruning(intersection(subgraph, neighbors), intersection(fresh, neighbors));
                fresh.remove(vertex);
                maximalClique.remove(vertex);
            }
        }
    }

    private void expandWithoutPruning (Set<T> subgraph) {
        if (subgraph.isEmpty()) {
            maximalCliques.add(new HashSet<>(maximalClique));
        } else {
            for (T vertex: subgraph) {
                maximalClique.add(vertex);
                expandWithoutPruning(intersection(subgraph, neighbors(vertex)));
                maximalClique.remove(vertex);
            }
        }
    }

    private void expand(Set<T> subgraph, Set<T> candidates) {
        if (subgraph.isEmpty()) {
            maximalCliques.add(new HashSet<>(maximalClique));
        } else {
            Set<T> largestNeighborhood = getNextCandidates(subgraph, candidates);
            Set<T> notNeighbors = difference(candidates, largestNeighborhood);
            for (T vertex: notNeighbors) {
                maximalClique.add(vertex);
                Set<T> neighbors = neighbors(vertex);
                this.expand(intersection(subgraph, neighbors), intersection(candidates, neighbors));
                candidates.remove(vertex);
                maximalClique.remove(vertex);
            }
        }
    }

    private Set<T> getNextCandidates(Set<T> subgraph, Set<T> candidates) {
        Set<T> nextCandidate;
        Set<T> nextLargestCandidate = null;
        int maxOrder = -1;
        int order;
        for (T vertex: subgraph) {
            nextCandidate = neighbors(vertex);
            order = intersection(candidates, nextCandidate).size();
            if (maxOrder < order) {
                maxOrder = order;
                nextLargestCandidate = nextCandidate;
            }
        }
        return nextLargestCandidate;
    }

    private T getPivot(Set<T> subgraph, Set<T> candidates) {
        T pivot = null;
        int maxOrder = -1;
        int order;
        for (T vertex: subgraph) {
            order = intersection(candidates, neighbors(vertex)).size();
            if (maxOrder < order) {
                maxOrder = order;
                pivot = vertex;
            }
        }
        return pivot;
    }

    private Set<T> neighbors(T vertex) {
        return graph.get(vertex);
    }

    private Set<T> difference(Set<T> A, Set<T> B) {
        Set<T> result = new HashSet<>(A);
        result.removeAll(B);
        return result;
    }

    private Set<T> intersection(Set<T> A, Set<T> B) {
        Set<T> result = new HashSet<>(A);
        result.retainAll(B);
        return result;
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
