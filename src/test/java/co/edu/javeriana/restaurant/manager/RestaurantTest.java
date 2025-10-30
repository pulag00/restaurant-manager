package co.edu.javeriana.restaurant.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests para la clase Restaurant
 */
@DisplayName("Tests de Restaurant")
public class RestaurantTest {

    private Restaurant restaurant;
    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant("La Pizzeria");
    }

    @Test
    @DisplayName("Test crear restaurante con nombre válido")
    public void testCreateRestaurant() {
        assertEquals("La Pizzeria", restaurant.getName());
        assertTrue(restaurant.getMenu().isEmpty());
        assertEquals(0.0, restaurant.getTotalRevenue(), 0.001);
    }

    //Test del menú
    @Test
    @DisplayName("Test agregar item válido al menú")
    public void testAddMenuItem() {
        restaurant.addMenuItem("Pizza Margherita", 12.99);

        assertEquals(1, restaurant.getMenuSize());
        List<String> menu = restaurant.getMenu();
        assertTrue(menu.get(0).contains("Pizza Margherita"));
        assertTrue(menu.get(0).contains("$12.99"));
    }
    @Test
    @DisplayName("Test agregar múltiples items al menú")
    public void testAddMultipleMenuItems() {
        restaurant.addMenuItem("Pizza Margherita", 12.99);
        restaurant.addMenuItem("Pasta Carbonara", 10.50);
        restaurant.addMenuItem("Tiramisu", 6.99);

        assertEquals(3, restaurant.getMenuSize());
    }
    @Test
    @DisplayName("Test agregar item con nombre vacío lanza excepción")
    public void testAddMenuItemEmptyName() {
        assertThrows(IllegalArgumentException.class,
                () -> restaurant.addMenuItem("", 10.0));

        assertThrows(IllegalArgumentException.class,
                () -> restaurant.addMenuItem(null, 10.0));
    }
    @Test
    @DisplayName("Test agregar item con precio negativo lanza excepción")
    public void testAddMenuItemNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> restaurant.addMenuItem("Pizza", -5.0));
    }
    @Test
    @DisplayName("Test remover item existente del menú")
    public void testRemoveMenuItem() {
        restaurant.addMenuItem("Pizza Margherita", 12.99);
        restaurant.addMenuItem("Pasta Carbonara", 10.50);

        boolean removed = restaurant.removeMenuItem("Pizza Margherita");

        assertTrue(removed);
        assertEquals(1, restaurant.getMenuSize());
        assertFalse(restaurant.getMenu().get(0).contains("Pizza"));
    }
    @Test
    @DisplayName("Test remover item inexistente retorna false")
    public void testRemoveNonExistentMenuItem() {
        restaurant.addMenuItem("Pizza", 12.99);

        boolean removed = restaurant.removeMenuItem("Pasta");

        assertFalse(removed);
        assertEquals(1, restaurant.getMenuSize());
    }

    @Test 
    @DisplayName("Test procesar orden actualiza ingresos correctamente") 
    public void testProcessOrder() { 
        restaurant.processOrder("Pizza Margherita", 12.99); 
        
        assertEquals(12.99, restaurant.getTotalRevenue(), 0.01); 
    } 
        
    @Test 
    @DisplayName("Test procesar múltiples órdenes suma ingresos") 
    public void testProcessMultipleOrders() { 
        restaurant.processOrder("Pizza", 12.99); 
        restaurant.processOrder("Pasta", 10.99); 
        restaurant.processOrder("Bebida", 3.50); 
        
        assertEquals(27.48, restaurant.getTotalRevenue(), 0.01); 
    } 
    
    @Test 
    @DisplayName("Test procesar orden con precio cero lanza excepción") 
    public void testProcessOrderZeroPrice() { 
        assertThrows(IllegalArgumentException.class, 
        () -> restaurant.processOrder("Pizza", 0)); 
    } 
    
    @Test 
    @DisplayName("Test procesar orden con precio negativo lanza excepción") 
    public void testProcessOrderNegativePrice() { 
        assertThrows(IllegalArgumentException.class, 
        () -> restaurant.processOrder("Pizza", -10.0)); 
    } 
    
    @Test 
    @DisplayName("Test procesar orden con item vacío lanza excepción") 
    public void testProcessOrderEmptyItem() { 
        assertThrows(IllegalArgumentException.class, 
        () -> restaurant.processOrder("", 10.0)); 
    } 
    
    @Test 
    @DisplayName("Test contar órdenes procesadas") 
    public void testGetOrderCount() { 
        restaurant.processOrder("Orden 1", 10.0); 
        restaurant.processOrder("Orden 2", 20.0); 
        assertEquals(3, restaurant.getOrderCount()); // 30/10 = 3
    } 
    
    @Test 
    @DisplayName("Test resetear ingresos") 
    public void testResetRevenue() { 
        restaurant.processOrder("Pizza", 12.99); 
        assertEquals(12.99, restaurant.getTotalRevenue(), 0.01); 
        
        restaurant.resetRevenue(); 
        assertEquals(0.0, restaurant.getTotalRevenue(), 0.01); 
    }

}
