/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acts_entregables;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alexloco
 */
public class BotigaVideojocs {
    
    public static Random random;
    public static Scanner teclado;
    
    private static final int OPCION_CONSULTAR_CATALOGO = 1;
    private static final int OPCION_STOCK = 2;
    private static final int OPCION_CONSULTAR_CATALOGO_ORDENADO_STOCK = 3;
    private static final int OPCION_CONSULTAR_CATALOGO_ORDENADO_PRECIO = 4;
    private static final int OPCION_REGISTRAR_VENTA = 5;
    private static final int OPCION_REGISTRAR_DEVOLUCION = 6;
    private static final int OPCION_SALIR = 7;
    
    private static final int MIN_MENU = 1;
    private static final int MAX_MENU = 7;
    
    private static final int MIN_STOCK = 0;
    private static final int MAX_STOCK = 20;
    
    
    public static void main(String[] args) {
        
        random = new Random();
        teclado = new Scanner(System.in);
        
        String[][] juegos = {
            {"1", "Super Mario Bros", "19.99"},
            {"2", "The Legend of Zelda", "24.99"},
            {"3", "Sonic the Hedgehog", "14.99"},
            {"4", "Tetris", "9.99"},
            {"5", "Pac-Man", "4.99"},
            {"6", "Street Fighter II", "29.99"},
            {"7", "Doom", "39.99"},
            {"8", "Minecraft", "19.99"},
            {"9", "The Sims", "34.99"},
            {"10", "Grand Theft Auto V", "49.99"}
        }; // Tabla con todos los videojuegos y sus precios
        
        int stockVideojocs[][] = stockVideojocs(juegos, MIN_STOCK, MAX_STOCK);
        
        mostrarMenu(MIN_MENU, MAX_MENU);
    }
    
    
    ////////////////////////////// .Métodos de prueba. //////////////////////////////
    public static void visualizarSopa(int[][] sopaLetras) {

        System.out.println("");

        for (int[] fila : sopaLetras) {

            for (int columna : fila) {

                System.out.print(columna + " ");
            }

            System.out.println();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////// .Pedir entero usuario. //////////////////////////////
    /////////////////
    // Este método recibe 3 parámetros. El primero es el mensaje que se quiere
    //  mostrar por pantalla. El segundo el número mínimo deseado. El tercero
    //  el número máximo deseado. En resumen, el rango.
    public static int obtenerEnteroUsuario(String mensaje, int min, int max) {

        do {
            int numUser = obtenerEnteroUsuario(mensaje);

            if (numUser >= min && numUser <= max) {
                return numUser;
            } else {
                System.out.println("Introduce una opción válida\n");
            }
        } while (true);
    }
    /////////////////
    // Este método es llamado por el anterior, el cual imprime por pantalla
    //  el mensaje pasado por parámetro en el método anterior y comprueba
    //  el valor introducido por el usuario. En caso de no ser Int, devuelve
    //  un mensaje de error, repitiendo el ciclo hasta que sea válido.
    public static int obtenerEnteroUsuario(String mensaje) {
        do {
            System.out.print(mensaje);
            if (teclado.hasNextInt()) {
                return teclado.nextInt();
            }
            System.out.println("Error! Tipo Incorrecto\n");
            teclado.next();
        } while (true);
    }
    ////////////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////// .Funciones que utilizan los métodos. //////////////////////////////
    // Este método genera un número aleatorio entre el número máximo y mínimo que se le pase como
    //  parámetro.
    public static int generarRandom(int min, int max) {
        return random.nextInt(min, max + 1);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////// .Método stock videojuegos. //////////////////////////////
    // Este método genera un array bidimensional, en la primera columna guarda el ID y en
    //  la segunda columna almacena el stock de dicho videojuego generado aleatoriamente
    //  entre el rango 0-20.
    public static int[][] stockVideojocs(String[][] matriz, int min, int max) {

        int stock[][] = new int[matriz.length][2];

        for (int y = 0; y < stock.length; y++) {

            for (int x = 0; x < stock[y].length; x++) {

                if (x == 0) {
                    stock[y][x] = (y + 1); //Esto permite que los IDs del stock coincidan con los IDs del catálogo de juegos.
                } else {
                    stock[y][x] = generarRandom(min, max);
                }
            }
        }

        return stock;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////// .Métodos menú. //////////////////////////////
    // Este método es el general que muestra cada opción del menú y a su vez
    //  dentro de este se llaman a los demás métodos.
    public static void mostrarMenu(int min, int max) {
        
        boolean salir = false;
        
        do {
            System.out.println("Bienvenido a la tienda de videojuegos. Elija una opción:");
            System.out.printf("   %d. Consultar catálogo de videojuegos\n", OPCION_CONSULTAR_CATALOGO);
            System.out.printf("   %d. Consultar stock de videojuegos\n", OPCION_STOCK);
            System.out.printf("   %d. Mostrar catálogo ordenado por stock\n", OPCION_CONSULTAR_CATALOGO_ORDENADO_STOCK);
            System.out.printf("   %d. Mostrar catálogo ordenado por precio\n", OPCION_CONSULTAR_CATALOGO_ORDENADO_PRECIO);
            System.out.printf("   %d. Registrar venta de videojuegos\n", OPCION_REGISTRAR_VENTA);
            System.out.printf("   %d. Registrar devolución de videojuegos\n", OPCION_REGISTRAR_DEVOLUCION);
            System.out.printf("   %d. Salir de la aplicación", OPCION_SALIR);
            System.out.println("\n");
            
            int enteroUsuario = obtenerEnteroUsuario("Introduce opción: ", min, max);
            
            switch (enteroUsuario) {
                case OPCION_CONSULTAR_CATALOGO -> {
                }
                case OPCION_STOCK -> {
                }
                case OPCION_CONSULTAR_CATALOGO_ORDENADO_STOCK -> {
                }
                case OPCION_CONSULTAR_CATALOGO_ORDENADO_PRECIO -> {
                }
                case OPCION_REGISTRAR_VENTA -> {
                }
                case OPCION_REGISTRAR_DEVOLUCION -> {
                }
                case OPCION_SALIR -> salir = true;
            }
        } while (!salir);
        
    }
    ////////////////////////////////////////////////////////////////////////////
}
