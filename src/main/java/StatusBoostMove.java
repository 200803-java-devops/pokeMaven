import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Move;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StatusBoostMove extends MoveBase {
  Map<String, Integer> statsMap;
  boolean targetOpp;
  String name;
  int priority = 0;
  int PP = 0;
  int MaxPP;

  StatusBoostMove(final int moveID) {
    final PokeApi pokeApi = new PokeApiClient();
    final Move move = pokeApi.getMove(moveID);
    name = move.getName();
    PP = move.getPp();
    MaxPP = PP;
    statsMap = new HashMap<>();
    for (int i = 0; i < move.getStatChanges().size(); i++) {
      statsMap.put(move.getStatChanges().get(i).getStat().getName(), move.getStatChanges().get(i).getChange());
    }
    targetOpp = !move.getTarget().getName().equals("user");
  }

  StatusBoostMove(final String str) {
    statsMap = new HashMap<>();
    final Scanner in = new Scanner(str);
    in.useDelimiter(",");
    this.name = in.next();
    targetOpp = in.next().equals("true");
    PP = in.nextInt();
    in.useDelimiter("`");
    while (in.hasNext()) {
      final Scanner out = new Scanner(in.next());
      out.useDelimiter(",");
      statsMap.put(out.next(), out.nextInt());
    }
    MaxPP = PP;

  }

  @Override
  void execute(final Monster user, final Monster defender) {
    System.out.println(user.getNAME() + " used " + name);
    Monster target;
    if (targetOpp) {
      target = defender;
    } else {
      target = user;
    }
    if (statsMap.containsKey("speed")) {
      target.setSpdMod(target.getSpdMod() + statsMap.get("speed"));
      if (target.getSpdMod() + statsMap.get("speed") > 6) {
        System.out.println("Speed is maxed!");
        target.setSpdMod(6);
      }
      if (target.getSpdMod() <= -6) {
        System.out.println("speed is min!");
        target.setSpdMod(-6);
      }
    }
    if (statsMap.containsKey("attack")) {
      target.setAtkMod(target.getAtkMod() + (statsMap.get("attack")));
      if (target.getAtkMod() >= 6) {
        System.out.println("attack is maxed!");
        target.setAtkMod(6);
      }
      if (target.getAtkMod() <= -6) {
        System.out.println("attack is min!");
        target.setAtkMod(-6);
      }
    }
    if (statsMap.containsKey("defense")) {
      target.setDefMod(target.getDefMod() + (statsMap.get("defense")));
      if (target.getDefMod() >= 6) {
        System.out.println("defense is maxed!");
        target.setDefMod(6);
      }
      if (target.getDefMod() <= -6) {
        System.out.println("defense is min!");
        target.setDefMod(-6);
      }
    }
    if (statsMap.containsKey("special-attack")) {
      target.setSpcMod(target.getSpcMod() + (statsMap.get("special-attack")));
      if (target.getSpcMod() >= 6) {
        System.out.println("Special is maxed!");
        target.setSpcMod(6);
      }
      if (target.getSpcMod() <= -6) {
        System.out.println("Special is min!");
        target.setSpcMod(-6);
      }
    }
    usePP();
  }

  public String printMove() {
    final StringBuilder toPrint = new StringBuilder("2\n" + name + "," + targetOpp + "," + PP + ",");
    for (final String str : statsMap.keySet()) {
      toPrint.append(str).append(",").append(statsMap.get(str)).append("`");
    }
    toPrint.append("\n");
    return toPrint.toString();
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  int getPriority() {
    return priority;
  }

  @Override
  int estimateDamage(Monster user, Monster defender) {
    return 0;
  }

  @Override
  public int getPP() {
    return PP;
  }

  @Override
  public int getMaxPP() {
    return MaxPP;
  }

  @Override
  void usePP() {
    PP = PP - 1;
  }
}

