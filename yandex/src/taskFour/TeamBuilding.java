package taskFour;

import taskFour.graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TeamBuilding {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;

    public static void main(String[] args) {
        new TeamBuilding();
    }

    public TeamBuilding() {
        Integer[] nAndM = parse(getUserInputLine());
        int n = nAndM[0];
        int m = nAndM[1];

        Integer[][] edges = new Integer[m][2];
        for (int i = 0; i < m; i++) {
            edges[i] = parse(getUserInputLine());
        }

        Graph<Integer> graph = new Graph<>(edges);
        System.out.println(graph);
        List<Set<Integer>> cliques = graph.getMaximalCliques();
        System.out.println(cliques);
    }

    private Integer[] parse(String input) {
        return Arrays.stream(input.split("\s"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }

    private String getUserInputLine() {
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
