import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RestaurantTest {

  Restaurant restaurant;

  LocalTime openingTime = LocalTime.parse("10:30:00");
  LocalTime closingTime = LocalTime.parse("22:00:00");

  @BeforeEach
  public void beforeEach() {
    restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);

    restaurant.addToMenu("Sweet corn soup", 120);
    restaurant.addToMenu("Vegetable lasagne", 280);
  }

  // >>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
    Restaurant restMock = Mockito.spy(restaurant);

    LocalTime newCurrentTime = LocalTime.parse("13:00:00");

    Mockito.when(restMock.getCurrentTime()).thenReturn(newCurrentTime);

    assertEquals(true, restMock.isRestaurantOpen());
  }

  @Test
  public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
    Restaurant restMock = Mockito.spy(restaurant);

    LocalTime newCurrentTime = LocalTime.parse("01:00:00");

    Mockito.when(restMock.getCurrentTime()).thenReturn(newCurrentTime);

    assertEquals(false, restMock.isRestaurantOpen());

  }

  // <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  // >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void adding_item_to_menu_should_increase_menu_size_by_1() {
    int initialMenuSize = restaurant.getMenu().size();
    restaurant.addToMenu("Sizzling brownie", 450);
    assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
  }

  @Test
  public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
    int initialMenuSize = restaurant.getMenu().size();
    restaurant.removeFromMenu("Vegetable lasagne");
    assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
  }

  @Test
  public void removing_item_that_does_not_exist_should_throw_exception() {
    assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
  }
  // <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}