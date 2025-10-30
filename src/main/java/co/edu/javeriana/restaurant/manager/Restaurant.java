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

    /**
     * Constructor del restaurante
     * @param name Nombre del restaurante
     */
    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.totalRevenue = 0.0;
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

}