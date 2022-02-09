import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantServiceTest {

  RestaurantService service = new RestaurantService();
  Restaurant restaurant;

  @BeforeEach
  void init() {
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
    restaurant.addToMenu("Sweet corn soup", 120);
    restaurant.addToMenu("Vegetable lasagne", 280);
  }
  //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  void searching_for_existing_restaurant_should_return_expected_restaurant_object()
      throws restaurantNotFoundException {
    Restaurant r = service.findRestaurantByName("Amelie's cafe");
    assertEquals("Amelie's cafe", r.getName());
  }


  @Test
  void searching_for_non_existing_restaurant_should_throw_exception() {
    assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("Swiggy"));
  }
  //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>
  //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  void remove_restaurant_should_reduce_list_of_restaurants_size_by_1()
      throws restaurantNotFoundException {
    int initialNumberOfRestaurants = service.getRestaurants().size();
    service.removeRestaurant("Amelie's cafe");
    assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
  }

  @Test
  void removing_restaurant_that_does_not_exist_should_throw_exception() {
    assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
  }

  @Test
  void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
    int initialNumberOfRestaurants = service.getRestaurants().size();
    service.addRestaurant(
        "Stories Breweries", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
    assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
  }
  // <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}
