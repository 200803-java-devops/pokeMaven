import java.util.ArrayList;

/**
 * A class that represents the team, stores the name and a list with each of the trainer's monsters.
 * The active monster is whatever monster is currently 'out'.
 */

public class Team {
  String trainerName;
  ArrayList<Monster> teamList = new ArrayList<>();
  Monster activeMonster;

  Team(String trainerName) {
    this.trainerName = trainerName;
  }

  @Override
  public String toString() {
    return "Team{"
            + "trainerName='"
            + trainerName
            + '\''
            + ", teamList="
            + teamList
            + ", activeMonster="
            + activeMonster
            + '}';
  }

  void addMonster(Monster monster) {
    if (teamList.size() <= 6) {
      this.teamList.add(monster);
    } else {
      System.err.println("You can only have 6 pokemon");
    }
  }

  public ArrayList<Monster> getTeamList() {
    return teamList;
  }

  void removeMonster(int ID) {
    teamList.remove(ID);
  }

  void setActiveMonster() {
    for (Monster monster : teamList) {
      if (!monster.fainted) {
        activeMonster = monster;
      }
    }
  }

  Monster getMonster(int id) {
    return teamList.get(id);
  }

  boolean canContinue() {
    boolean canContinue = false;
    for (Monster monster : teamList) {
      canContinue = canContinue || !monster.fainted;
    }
    return canContinue;
  }

  int numPokemon() {
    return teamList.size();
  }
}
