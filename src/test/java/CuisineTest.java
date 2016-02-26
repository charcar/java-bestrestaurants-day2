import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Cuisine myCuisine = new Cuisine("Mediterranean");
    myCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(myCuisine));
  }

  @Test
  public void find_findCuisinInDatabase_true(){
    Cuisine myCuisine = new Cuisine("Mediterranean");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
    assertTrue(myCuisine.equals(savedCuisine));
  }

  @Test
  public void delete_removesNameOfRestaurantAndId() {
    Cuisine myCuisine = new Cuisine("Mediterranean");
    myCuisine.save();
    myCuisine.delete();
    assertEquals(myCuisine.all().size(), 0);
  }
}
