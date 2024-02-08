/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acts_entregables;

import java.util.Arrays;
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
    
    private static final String ORDENAR_STOCK = "menor"; // Si pones menor, ordena de menor a mayor la columna del stock
    private static final String ORDENAR_PRECIO = "mayor"; // Si pones mayor, ordena de mayor a menor la columna del stock
    
    
    public static void main(String[] args) {
        
        random = new Random();
        teclado = new Scanner(System.in);
        
        mostrarMenu(MIN_MENU, MAX_MENU);
    }
    
    
    ////////////////////////////// .Pedir entero usuario. //////////////////////////////
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
    public static String[][] stockVideojocs(String[][] matriz, int min, int max) {
        String stock[][] = new String[matriz.length][2];

        for (int y = 0; y < stock.length; y++) {
            for (int x = 0; x < stock[y].length; x++) {
                if (x == 0) {
                    stock[y][x] = String.valueOf(y + 1); // Esto permite que los IDs del stock coincidan con los IDs del catálogo de juegos.
                } else {
                    stock[y][x] = String.valueOf(generarRandom(min, max));
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

        String stockVideojocs[][] = stockVideojocs(juegos, MIN_STOCK, MAX_STOCK);
        
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
            
            String ventas[][] = new String[0][0];
            
            switch (enteroUsuario) {
                case OPCION_CONSULTAR_CATALOGO -> mostrarCatalogoVideojuegos(1, juegos, 2, juegos, 3, juegos, "Código", "Nombre", "Precio");
                case OPCION_STOCK -> mostrarCatalogoVideojuegos(1, juegos, 2, juegos, 2, stockVideojocs, "Código", "Nombre", "Stock");
                case OPCION_CONSULTAR_CATALOGO_ORDENADO_STOCK -> {
                    String ordenadoPorStock[][] = catalogoOrdenadoStock(juegos, stockVideojocs, 3, ORDENAR_STOCK);
                    mostrarCatalogoVideojuegos(1, ordenadoPorStock, 2, ordenadoPorStock, 3, ordenadoPorStock, "Código", "Nombre", "Stock");
                }
                case OPCION_CONSULTAR_CATALOGO_ORDENADO_PRECIO -> {
                    String ordenadoPorPrecio[][] = catalogoOrdenadoStock(juegos, 3, ORDENAR_PRECIO);
                    mostrarCatalogoVideojuegos(1, ordenadoPorPrecio, 2, ordenadoPorPrecio, 3, ordenadoPorPrecio, "Código", "Nombre", "Precio");
                }
                case OPCION_REGISTRAR_VENTA -> {
                }
                case OPCION_REGISTRAR_DEVOLUCION -> {
                    devolucion(ventas);
                }
                case OPCION_SALIR -> salir = true;
            }
        } while (!salir);
        
        System.out.println("Adiós");
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////// .Método para imprimir cada tabla. //////////////////////////////
    // Este imprime cada columna y cabecera especificadas por los parámetros que recibe.
    public static void mostrarCatalogoVideojuegos(int columnaUno, String[][] arrayBidimensionalUno, 
                                                  int columnaDos, String[][] arrayBidimensionalDos, 
                                                  int columnaTres, String[][] arrayBidimensionalTres, 
                                                  String cabUno, String cabDos, String cabTres) {
        
        // Arrays que almacenan el tamaño máximo de cada columna para luego imprimir los espacios adecuados para que cuadre todo.
        int espaciosColumnaUno = espaciosColumna(arrayBidimensionalUno, columnaUno);
        int espaciosColumnaDos = espaciosColumna(arrayBidimensionalDos, columnaDos);
        int espaciosColumnaTres = espaciosColumna(arrayBidimensionalTres, columnaTres);
        
        // Esto comprueba si la longitud de la cabezera es superior a la longitud máxima de las columnas para que siempre quepa todo.
        if (cabUno.length() > espaciosColumnaUno) {
            espaciosColumnaUno = cabUno.length();
        }
        if (cabDos.length() > espaciosColumnaDos) {
            espaciosColumnaDos = cabDos.length();
        }
        if (cabTres.length() > espaciosColumnaTres) {
            espaciosColumnaTres = cabTres.length();
        }
        
        System.out.println(""); // Espacio en blanco para que se vea una separación antes de mostrar la tabla
        
        mostrarLineaSeparadora(espaciosColumnaUno, espaciosColumnaDos, espaciosColumnaTres); // Este método genera guiones según la cantidad de espacios para cada columna.
        System.out.printf("| %-" + espaciosColumnaUno + "s | %-" + espaciosColumnaDos + "s | %-" + espaciosColumnaTres + "s |\n", cabUno, cabDos, cabTres); // Esto hace lo mismo que la siguiente explicacioón.
        mostrarLineaSeparadora(espaciosColumnaUno, espaciosColumnaDos, espaciosColumnaTres);

        // Aquí se muestra todo el contenido de cada columna fila a fila. Los espacios se calculan mediante `%-Xs`. Como X la conocemos previamente
        //  con los cálculos previos a los espacios, se concatena el `%-` con la variable de espacios y luego la `s` ya que lo estamos imprimiendo como String.
        for (int i = 0; i < arrayBidimensionalUno.length; i++) {
            System.out.printf("| %-" + espaciosColumnaUno + "s | %-" + espaciosColumnaDos + "s | %-" + espaciosColumnaTres + "s |\n",
                    arrayBidimensionalUno[i][columnaUno - 1], arrayBidimensionalDos[i][columnaDos - 1], arrayBidimensionalTres[i][columnaTres - 1]);
        }
        
        mostrarLineaSeparadora(espaciosColumnaUno, espaciosColumnaDos, espaciosColumnaTres);
        
        System.out.println(""); // Espacio en blanco para que se vea una separación después de mostrar la tabla
    }
    
    // Este método recorre todos los datos de la columna y comprueba cuál es el que mayor longitud tiene. Devuelve el resultado más grande.
    public static int espaciosColumna(String[][] juegos, int columna) {
        
        int tamanyoMax = 0;
        
        for (int y = 0; y < juegos.length; y++) {

            if (juegos[y][columna - 1].length() > tamanyoMax) {
                tamanyoMax = juegos[y][columna - 1].length();
            }
        }
        
        return tamanyoMax;
    }
    
    // Este método imprime los mases: `+`. Al lado de cada `+` hay un `%s`, este tiene como valor que se repita el guión `-` tantas veces
    //  como el espacio máximo de cada columna más dos huecos más debido al espacio en blanco antes y después cada texto de la cabecera.
    public static void mostrarLineaSeparadora(int anchoColumnaUno, int anchoColumnaDos, int anchoColumnaTres) {
        System.out.printf("+%s+%s+%s+\n", "-".repeat(anchoColumnaUno + 2), "-".repeat(anchoColumnaDos + 2), "-".repeat(anchoColumnaTres + 2));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////// .Métodos para ordenar. //////////////////////////////
    // Este método junto con el de abajo tienen el nombre igual pero realizan distintas
    //  opciones. Este recibe como parámetro el catálogo completo de videojuegis,
    //  el stock de cada videojuego, la columna a ordenar (será la de stock) y
    //  cómo se quiere ordenar la columna (de mayor a menor o al revés).
    public static String[][] catalogoOrdenadoStock(String[][] videojuegos, String[][] stock, int columna, String tipo) {

        String[][] stockOrdenado = catalogoOrdenadoStock(stock, 2, tipo); // Esta línea obtiene el stock. Tiene el mismo nombre que este método pero reciben distintos parámetros.
        String arrayAuxiliar[][] = new String[videojuegos.length][videojuegos[0].length]; // Aquí se crea una copia vacía del catálogo completo.
        
        // Este for pone el stock ordenado de menor a mayor en la columna que toca. Una
        //  vez hecho esto, comprueba que la ID asociada a cada stock coincida con un
        //  valor en el catálogo. Una vez encuentre la coincidencia, guarda el ID y
        //  el nombre del videojuego en las posiciones correctas y sale del bucle.
        for (int y = 0; y < arrayAuxiliar.length; y++) {
            
            arrayAuxiliar[y][columna - 1] = stockOrdenado[y][1];
            
            for (int x = 0; x < arrayAuxiliar.length; x++) {
                
                if (Integer.parseInt(stockOrdenado[y][0]) == Integer.parseInt(videojuegos[x][0])) {
                    arrayAuxiliar[y][0] = videojuegos[x][0];
                    arrayAuxiliar[y][1] = videojuegos[x][1];
                    break;
                }
            }
        }
        
        return arrayAuxiliar;
    }
    
    // Este método comprueba si el valor a ordenar es entero o decimal y si se desea ordenar de
    //  menor a mayor o de mayor a menor.
    public static String[][] catalogoOrdenadoStock(String [][] videojuegos, int columna, String tipo) {
        
        String arrayAuxiliar[][] = videojuegos;
        boolean entero = esEntero(videojuegos, columna);
        
        if (entero) {
            if (tipo.equals("mayor")) {
                return ordenarMayorEntero(arrayAuxiliar, columna);
            } else {
                return ordenarMenorEntero(arrayAuxiliar, columna);
            }
        } else {
            if (tipo.equals("mayor")) {
                return ordenarMayorFloat(arrayAuxiliar, columna);
            } else {
                return ordenarMenorFloat(arrayAuxiliar, columna);
            }
        }
    }
    
    // Este método comprueba si la columna pasada como parámetro es
    //  entera o no
    public static boolean esEntero(String[][] array, int columna) {

        // Este método recorre todas las posiciones, en caso de que
        //  encuentre un `.`, significa que hay valores decimales,
        //  así que se tratará como float.
        for (int x = 0; x < array.length; x++) {

            if (array[x][columna - 1].contains(".")) {
                return false;
            }
        }
        return true;
    }
    
    // Este método ordena el array bidimensional basándose en una columna de 
    //  menor a mayor y conteniendo valores enteros.
    public static String[][] ordenarMenorEntero(String[][] array, int columna) {

        for (int x = 0; x < array.length; x++) {
            for (int y = x + 1; y < array.length; y++) {
                if (Integer.parseInt(array[y][columna - 1]) < Integer.parseInt(array[x][columna - 1])) {
                    String auxiliar[] = array[x];
                    array[x] = array[y];
                    array[y] = auxiliar;
                }
            }
        }
        
        return array;
    }
    
    // Este método ordena el array bidimensional basándose en una columna de 
    //  mayor a menor y conteniendo valores enteros.
    public static String[][] ordenarMayorEntero(String[][] array, int columna) {
        
        for (int x = 0; x < array.length; x++) {
            for (int y = x + 1; y > array.length; y++) {
                if (Integer.parseInt(array[y][columna - 1]) < Integer.parseInt(array[x][columna - 1])) {
                    String auxiliar[] = array[x];
                    array[x] = array[y];
                    array[y] = auxiliar;
                }
            }
        }
        
        return array;
    }

    // Este método ordena el array bidimensional basándose en una columna de 
    //  menor a mayor y conteniendo valores decimales.
    public static String[][] ordenarMenorFloat(String[][] array, int columna) {

        for (int x = 0; x < array.length; x++) {
            for (int y = x + 1; y < array.length; y++) {
                if (Float.parseFloat(array[y][columna - 1]) < Float.parseFloat(array[x][columna - 1])) {
                    String auxiliar[] = array[x];
                    array[x] = array[y];
                    array[y] = auxiliar;
                }
            }
        }
        
        return array;
    }
    
    // Este método ordena el array bidimensional basándose en una columna de 
    //  mayor a menor y conteniendo valores decimales.
    public static String[][] ordenarMayorFloat(String[][] array, int columna) {
        
        for (int x = 0; x < array.length; x++) {
            for (int y = x + 1; y < array.length; y++) {
                if (Float.parseFloat(array[y][columna - 1]) > Float.parseFloat(array[x][columna - 1])) {
                    String auxiliar[] = array[x];
                    array[x] = array[y];
                    array[y] = auxiliar;
                }
            }
        }
        
        return array;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    
    
    public static void devolucion(String[][] ventas) {
        
        if (ventas.length != 0) {
            
        } else {
            System.out.println("\nTienes que realizar alguna compra antes.\n");
        }
    }
}
