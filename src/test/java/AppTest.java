import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Add restaurants to any cuisine");
  }

  @Test
  public void cuisineIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#type").with("American");
    submit(".btn-success");
    assertThat(pageSource()).contains("American");
  }

  @Test
  public void allRestaurantsAreDisplayedOnCuisinePage() {
    Cuisine myCuisine = new Cuisine("Chinese");
    myCuisine.save();
    Restaurant firstRestaurant = new Restaurant("Panda Express", myCuisine.getId());
    firstRestaurant.save();
    Restaurant secondRestaurant = new Restaurant("Golden Bamboo", myCuisine.getId());
    secondRestaurant.save();
    String cuisinePath = String.format("http://localhost:4567/cuisines/%d", myCuisine.getId());
    goTo(cuisinePath);
    assertThat(pageSource()).contains("Panda Express");
    assertThat(pageSource()).contains("Golden Bamboo");
  }



}
