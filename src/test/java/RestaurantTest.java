import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfDescriptionAreTheSame() {
    Restaurant restaurantOne = new Restaurant("White Owl", 0);
    Restaurant restaurantTwo = new Restaurant("White Owl", 0);
    assertTrue(restaurantOne.equals(restaurantTwo));
  }

  @Test
  public void save_assignsIdToObject() {
    Restaurant myRestaurant = new Restaurant("Pok Pok", 0);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(myRestaurant.getId(), savedRestaurant.getId());
  }

  @Test
  public void update_updatesNameOfRestaurant() {
    Restaurant myRestaurant = new Restaurant("Roxys", 0);
    myRestaurant.save();
    myRestaurant.update("Smallpoxys");
    assertEquals(myRestaurant.getName(), "Smallpoxys");
  }

  @Test
  public void delete_removesNameOfRestaurantAndId() {
    Restaurant myRestaurant = new Restaurant("Roxys", 0);
    myRestaurant.save();
    myRestaurant.delete();
    assertEquals(myRestaurant.all().size(), 0);
  }

  @Test
  public void save_saveCuisineIdIntoDB_true() {
    Cuisine myCuisine = new Cuisine("Somali");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("Habibi", myCuisine.getId());
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), myCuisine.getId());
  }

  @Test
  public void find_findRestaurantsInDatabase_true() {
    Restaurant myRestaurant = new Restaurant("Pok Pok", 0);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertTrue(myRestaurant.equals(savedRestaurant));
  }


}
