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

    // DEVELOPER 1: TEST DE MENU
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
    
    // DEVELOPER 2: TEST DE PROCESAMIENTO ORDENES
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


    // DEVELOPER 3: TEST RESERVAS
    
    @Test
    @DisplayName("Test crear reserva válida")
    public void testMakeReservation() {
        restaurant.makeReservation("Juan Pérez", 4, "2024-12-25 19:00");
        assertEquals(1, restaurant.getReservationCount());
        List<String> reservations = restaurant.getReservations();
        assertTrue(reservations.get(0).contains("Juan Pérez"));
        assertTrue(reservations.get(0).contains("4 personas"));
    }
    @Test
    @DisplayName("Test crear múltiples reservas")
    public void testMakeMultipleReservations() {
        restaurant.makeReservation("Cliente 1", 2, "2024-12-25 18:00");
        restaurant.makeReservation("Cliente 2", 4, "2024-12-25 19:00");
        restaurant.makeReservation("Cliente 3", 6, "2024-12-25 20:00");

        assertEquals(3, restaurant.getReservationCount());
    }
    @Test
    @DisplayName("Test crear reserva con nombre vacío lanza excepción")
    public void testMakeReservationEmptyName() {
        assertThrows(IllegalArgumentException.class,
                () -> restaurant.makeReservation("", 4, "2024-12-25 19:00"));
    }
    @Test
    @DisplayName("Test crear reserva con tamaño inválido lanza excepción")
    public void testMakeReservationInvalidSize() {
        assertThrows(IllegalArgumentException.class,
                () -> restaurant.makeReservation("Juan", 0, "2024-12-25 19:00"));

        assertThrows(IllegalArgumentException.class,
                () -> restaurant.makeReservation("Juan", -2, "2024-12-25 19:00"));
    }
    @Test
    @DisplayName("Test crear reserva sin fecha lanza excepción")
    public void testMakeReservationNoDateTime() {
        assertThrows(IllegalArgumentException.class,
                () -> restaurant.makeReservation("Juan", 4, ""));
    }
    @Test
    @DisplayName("Test cancelar reserva existente")
    public void testCancelReservation() {
        restaurant.makeReservation("Juan Pérez", 4, "2024-12-25 19:00");
        restaurant.makeReservation("María López", 2, "2024-12-25 20:00");

        boolean cancelled = restaurant.cancelReservation("Juan Pérez");

        assertTrue(cancelled);
        assertEquals(1, restaurant.getReservationCount());
    }
    @Test
    @DisplayName("Test cancelar reserva inexistente retorna false")
    public void testCancelNonExistentReservation() {
        restaurant.makeReservation("Juan", 4, "2024-12-25 19:00");

        boolean cancelled = restaurant.cancelReservation("Pedro");

        assertFalse(cancelled);
        assertEquals(1, restaurant.getReservationCount());
    }

    //Developer 4

    @Test
    @DisplayName("Test calcular valor promedio de órdenes")
    public void testGetAverageOrderValue() {
        restaurant.processOrder("Orden 1", 20.0);
        restaurant.processOrder("Orden 2", 30.0);

        // 50 total / 5 órdenes estimadas = 10
        double average = restaurant.getAverageOrderValue();
        assertEquals(10.0, average, 0.01);
    }
    @Test
    @DisplayName("Test promedio con cero órdenes")
    public void testGetAverageOrderValueNoOrders() {
        assertEquals(0.0, restaurant.getAverageOrderValue(), 0.01);
    }
    @Test
    @DisplayName("Test verificar buen desempeño")
    public void testIsPerformingWell() {
        restaurant.processOrder("Orden", 100.0);

        assertTrue(restaurant.isPerformingWell(50.0));
        assertFalse(restaurant.isPerformingWell(150.0));
    }

    /*
    @Test
    @DisplayName("Test obtener resumen de estadísticas")
    public void testGetStatisticsSummary() {
        Restaurant restaurant = new Restaurant("La Pizzeria");
        restaurant.addMenuItem("Pizza", 12.99);
        restaurant.makeReservation("Juan", 4, "2024-12-25 19:00");
        restaurant.processOrder("Orden", 25.50);

        String summary = restaurant.getStatisticsSummary();

        assertNotNull(summary);
        assertTrue(summary.contains("La Pizzeria"));
        assertTrue(summary.contains("25.50"));
    }
    */
}
