package csc207.phase2.UTFantasy.Character;

import androidx.annotation.NonNull;

import java.util.HashMap;

import csc207.phase2.UTFantasy.Pet.Pokemon;
import csc207.phase2.UTFantasy.Products.Product;
import csc207.phase2.UTFantasy.mapUseCase.Map;

public class Player extends Person {

  private int x;
  private int y;
  private String gender;
  private Map playerMap;
  private HashMap<Product, Integer> bag;
  private boolean showingScore = true;
  private WildPokemonObserver observer;
  private Product selectedProduct;

  /**
   * construct a new player
   *
   * @param name the name of the player
   * @param gender the gender of the player
   */
  public Player(String name, String gender) {
    super(name);
    this.gender = gender;
    this.money = 1000;
    this.bag = new HashMap<>();
    this.setX(10);
    this.setY(10);
    this.setDirection("down");
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public String getGender() {
    return gender;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public Map getPlayerMap() {
    return playerMap;
  }

  public void setPlayerMap(Map map) {
    this.playerMap = map;
  }

  public HashMap<Product, Integer> getBag() {
    return bag;
  }

  public Product getSelectedProduct() {
    return selectedProduct;
  }

  public void setSelectedProduct(Product selectedProduct) {
    this.selectedProduct = selectedProduct;
  }

  /** add num of item's to the player's bag */
  public void addItem(Product item, int num) {
    try {
      //noinspection ConstantConditions
      bag.put(item, bag.get(item) + num);
    } catch (NullPointerException e) {
      bag.put(item, num);
    }
  }

  public void useItem(Product product) {
    Integer num = bag.get(product);
    if (num != null) {
      if (num == 1) bag.remove(product);
      else bag.remove(product, 1);
    }
  }

  public void swapPokemon(int i1) {
    if (i1 < 6) {
      Pokemon temp = pokemonList.remove(i1);
      pokemonList.add(temp);
    }
  }

  public void discardPokemon(int i1) {
    pokemonList.remove(i1);
  }

  @NonNull
  @Override
  public String toString() {
    return "Player: "
        + name
        + ", "
        + gender
        + ", PokemonNum: "
        + pokemonList.size()
        + ", TotalLV: ."
        + getPlayerPokemonMaxLV()
        + ".";
  }

  public int getPlayerPokemonMaxLV() {
    int maxLv = 0;
    for (Pokemon pokemon : pokemonList) {
      if (pokemon.getLevel() > maxLv) maxLv = pokemon.getLevel();
    }
    return maxLv;
  }

  public boolean isShowingScore() {
    return showingScore;
  }

  public void setShowingScore(boolean showingScore) {
    this.showingScore = showingScore;
  }

  public void addObserver(WildPokemonObserver observer) {
    this.observer = observer;
  }

  public void notifyChange() {
    if (observer != null) observer.checkWildPokeBattle();
  }
}
