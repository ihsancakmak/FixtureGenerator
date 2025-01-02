import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        List<String> teams = new ArrayList<>();

        System.out.println("Please enter a number of teams you want to add.");
        int teamCount = in.nextInt();
        in.nextLine();

        for (int i=0; i<teamCount; i++) {
            System.out.print("Team " + (i + 1) + "= ");
            teams.add(in.nextLine());
        }

        if (teams.size() % 2 != 0) {
            teams.add("Bay");
        }

        List<List<String>> fixtures = generateFixtures(teams);

        // Print fixtures
        Collections.shuffle(fixtures);
        for (int week = 0; week < fixtures.size(); week++) {
            System.out.println("Week " + (week + 1) + " Fixtures:");
            for (String match : fixtures.get(week)) {
                System.out.println(match);
            }
            System.out.println();
        }

    }
    private static List<List<String>> generateFixtures(List<String> teams) {
        int teamCount = teams.size();
        List<List<String>> fixtures = new ArrayList<>();


        for (int week = 0; week < teamCount - 1; week++) {
            List<String> matches = new ArrayList<>();
            for (int i = 0; i < teamCount / 2; i++) {
                String home = teams.get(i);
                String away = teams.get(teamCount - 1 - i);
                matches.add(home + " vs " + away);
            }
            fixtures.add(matches);

            // Rotate teams (except the first one)
            String last = teams.remove(teams.size() - 1);
            teams.add(1, last);
        }
        for (int week = 0; week < teamCount - 1; week++) {
            List<String> matches = new ArrayList<>();
            for (int i = 0; i < teamCount / 2; i++) {
                String home = teams.get(i);
                String away = teams.get(teamCount - 1 - i);
                matches.add(away + " vs " + home); // Away match
            }
            fixtures.add(matches);

            // Rotate teams (except the first one)
            String last = teams.remove(teams.size() - 1);
            teams.add(1, last);
        }

        return fixtures;
    }
}