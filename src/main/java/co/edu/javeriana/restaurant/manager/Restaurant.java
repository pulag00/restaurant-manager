package co.edu.javeriana.restaurant.manager;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase principal para gestionar un restaurante
 * @author Nombre del Equipo
 */
public class Restaurant {
    private String name;
    private List<String> menu;
    private double totalRevenue;
    private List<String> reservations;

    /**
     * Constructor del restaurante
     * @param name Nombre del restaurante
     */
    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.reservations = new ArrayList<>(); // Modificacion para reservaciones
    }
    /**
     * Obtiene el nombre del restaurante
     * @return nombre del restaurante
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene una copia del menú
     * @return lista de items del menú
     */
    public List<String> getMenu() {
        return new ArrayList<>(menu);
    }

    /**
     * Obtiene los ingresos totales
     * @return ingresos acumulados
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // DEVELOPER 1: GESTION DE MENU

    // TODO: Agregar métodos para gestionar menú y órdenes
    // Estos serán implementados por los developers en diferentes branches
    /**
     * Agrega un item al menú con su precio
     * @param item Nombre del item
     * @param price Precio del item
     * @throws IllegalArgumentException si el item está vacío o el precio es nega
    tivo
     */
    public void addMenuItem(String item, double price) {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del item no puede estar vacío");
        }
        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        // Forzar decimal con punto (esto está modificado)
        menu.add(item + " - $" + String.format(java.util.Locale.US, "%.2f", price));
    }

    /**
     * Remueve un item del menú por nombre
     * @param item Nombre del item a remover
     * @return true si el item fue removido, false si no existía
     */
    public boolean removeMenuItem(String item) {
        if (item == null || item.trim().isEmpty()) {
            return false;
        }
        return menu.removeIf(menuItem -> menuItem.startsWith(item.trim()));
    }
    /**
     * Obtiene el número de items en el menú
     * @return cantidad de items
     */
    public int getMenuSize() {
        return menu.size();
    }

    // DEVELOPER 2: PROCESAMIENTO DE ORDENES

    /***
    * Procesa una orden y actualiza los ingresos
    * @param item Nombre del item ordenado
    * @param price Precio de la orden
    * @throws IllegalArgumentException si el precio no es válido o item vacío
    */
   public void processOrder(String item, double price) {
    if (price <= 0) {
        throw new IllegalArgumentException("El precio debe ser positivo");
    }
    if (item == null || item.trim().isEmpty()) {
        throw new IllegalArgumentException("El item no puede estar vacío");
    }
    totalRevenue += price;
    }
    /**
     * Obtiene el número aproximado de órdenes procesadas
     * @return cantidad estimada de órdenes
     */
    public int getOrderCount() {
        return (int) Math.round(totalRevenue / 10.0);
    }

    /**
     * Resetea los ingresos totales (para testing o nuevo período)
     */
    public void resetRevenue() {
        totalRevenue = 0.0;
    }
    

    //DEVELOPER 3: SISTEMA DE RESERVAS
    
    /**
     * Crea una nueva reserva
     * @param customerName Nombre del cliente
     * @param partySize Tamaño del grupo
     * @param dateTime Fecha y hora de la reserva
     * @throws IllegalArgumentException si los datos no son válidos
     */
    public void makeReservation(String customerName, int partySize, String dateTime) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es requerido");
        }
        if (partySize <= 0) {
            throw new IllegalArgumentException("El tamaño del grupo debe ser positivo");
        }
        if (dateTime == null || dateTime.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha y hora son requeridas");
        }

        String reservation = String.format("%s - %d personas - %s",
                customerName, partySize, dateTime);
        reservations.add(reservation);
    }
    /**
     * Obtiene todas las reservas
     * @return lista de reservas
     */
    public List<String> getReservations() {
        return new ArrayList<>(reservations);
    }
    /**
     * Obtiene el número de reservas activas
     * @return cantidad de reservas
     */
    public int getReservationCount() {
        return reservations.size();
    }
    /**
     * Cancela una reserva por nombre de cliente
     * @param customerName Nombre del cliente
     * @return true si se canceló, false si no existía
     */
    public boolean cancelReservation(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            return false;
        }
        return reservations.removeIf(res -> res.startsWith(customerName.trim()));
    }

    //DEVELOPER 3: SISTEMA DE RESERVAS

    /**
     * Calcula el valor promedio de las órdenes
     * @return promedio de ingresos por orden
     */
    public double getAverageOrderValue() {
        int orderCount = getOrderCount();
        if (orderCount == 0) {
            return 0.0;
        }
        return totalRevenue / orderCount;
    }
    /**
     * Verifica si el restaurante está generando buenos ingresos
     * @param threshold Umbral mínimo de ingresos
     * @return true si los ingresos superan el umbral
     */
    public boolean isPerformingWell(double threshold) {
        return totalRevenue >= threshold;
    }
    /**
     * Obtiene un resumen del estado del restaurante
     * @return String con estadísticas
     */
    public String getStatisticsSummary() {
        return String.format(
                "Restaurant: %s\n" +
                        "Items en menú: %d\n" +
                        "Reservas activas: %d\n" +
                        "Órdenes procesadas: %d\n" +
                        "Ingresos totales: $%.2f\n" +
                        "Valor promedio por orden: $%.2f",
                name,
                menu.size(),
                reservations.size(),
                getOrderCount(),
                totalRevenue,
                getAverageOrderValue()
        );
    }
}