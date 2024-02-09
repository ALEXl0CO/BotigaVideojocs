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
    
    private static final String ORDENAR_STOCK = "menor"; // Si pones menor, ordena de menor a mayor la columna del stock
    private static final String ORDENAR_PRECIO = "mayor"; // Si pones mayor, ordena de mayor a menor la columna del stock
    
    
    public static void main(String[] args) {
        
        random = new Random();
        teclado = new Scanner(System.in);
        
        mostrarMenu(MIN_MENU, MAX_MENU);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// .Pedir entero usuario. //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
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
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// .Funciones que utilizan los métodos. //////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Este método genera un número aleatorio entre el número máximo y mínimo que se le pase como
    //  parámetro.
    public static int generarRandom(int min, int max) {
        return random.nextInt(min, max + 1);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// .Método stock videojuegos. //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
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
    
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// .Métodos menú. //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
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

        String stockVideojocs[][] = stockVideojocs(juegos, MIN_STOCK, MAX_STOCK); // Stock de cada videojuego entre el rango establecido.
        int ventas[][] = new int[0][3];
        
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
                    ventas = venta(juegos, stockVideojocs, ventas);
                }
                case OPCION_REGISTRAR_DEVOLUCION -> {
                    ventas = devolucion(ventas, juegos, stockVideojocs);
                }
                case OPCION_SALIR -> salir = true;
            }
        } while (!salir);
        
        System.out.println("Adiós");
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// .Método para imprimir cada tabla. //////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
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
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////// .Métodos para ordenar. ////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        
        if (esEntero(videojuegos, columna)) {
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
            for (int y = x + 1; y < array.length; y++) {
                if (Integer.parseInt(array[y][columna - 1]) > Integer.parseInt(array[x][columna - 1])) {
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// .Métodos ventas. //////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    // En este método se recoge todo lo que tiene que ver con las ventas de los
    //  videojuegos que hay en el catálogo.
    public static int[][] venta(String[][] catalogo, String[][] stock, int[][] ventas) {

        // Estas 2 líneas sirven para mostrar los juegos y el stock disponible para cada videojuego
        //  en caso de que el usuario no recuerde el stock o cualquier problema.
        String ordenadoPorStock[][] = catalogoOrdenadoStock(catalogo, stock, 3, ORDENAR_STOCK);
        mostrarCatalogoVideojuegos(1, ordenadoPorStock, 2, ordenadoPorStock, 3, ordenadoPorStock, "Código", "Nombre", "Stock");
        
        int ticketVentas[][] = ventas; // Inicializa el ticketVentas como el ticket existente para poder añadir cosas en él.
        int contadorCodigo = codigoVenta(ventas); // Aqui se comprueba el siguiente código de venta que debe tener toda la compra.
        char confirmación = 'S';
        
        // Esta línea guarda el tamaño original del arary, para que en caso de que el usuario introduzca
        //  más stock del que hay y se vaya sin comprar nada no le muestre el ticket.
        int tamanyoOriginal = ticketVentas.length;
        
        contadorCodigo++; // Esto sirve para que el número obtenido en `contadorCodigo` tenga +1 para toda la compra.
        do {
            //Pregunta al usuario sobre el código del videojuego y la cantidad de unidades que desea.
            int codigoVideojuego = obtenerEnteroUsuario("Introduzca el código del videojuego que desea comprar: ", 1, catalogo.length);
            int unidadesVideojuego = obtenerEnteroUsuario("Introduzca la cantidad de unidades que desea comprar: ", MIN_STOCK, MAX_STOCK);
            
            if (unidadesVideojuego == 0) {
                
                System.out.println("No puedes comprar 0 unidades de un videojuego.");
                confirmación = sigue("¿Desea comprar algún videojuego más? (S/N): ");
                
            } else if (hayStock(stock, codigoVideojuego, unidadesVideojuego)) {
                
                // Si hay stock y cumple con los requisitos, entra aquí.
                ticketVentas = añadirFila(ticketVentas); // Esto añade una línea al ticket auxiliar creado en este método a raíz del ticket original.
                // Estas 3 líneas añaden la información requerida para el ticket a la posición del tamaño
                //  máximo (alargado +1 anteriormente) -1 para ir directamente al final del ticket.
                ticketVentas[ticketVentas.length - 1][0] = contadorCodigo;
                ticketVentas[ticketVentas.length - 1][1] = codigoVideojuego;
                ticketVentas[ticketVentas.length - 1][2] = unidadesVideojuego;
                
                // Esta línea llama al método para eliminar las unidades compradas de stock.
                stock = eliminarStock(stock, codigoVideojuego, unidadesVideojuego);
                
                // Le pregunta al usuario si quiere seguir comprando.
                confirmación = sigue("¿Desea comprar algún videojuego más? (S/N): ");
                
            } else {
                System.out.println("Lo siento, no tenemos este videojuego disponible.");
                confirmación = sigue("¿Desea comprar algún videojuego más? (S/N): ");
            }
        } while (confirmación == 'S');
        
        // Comprobante de que el usuario ha comprado algo o no para mostrar el ticket o no.
        if (tamanyoOriginal == ticketVentas.length) {
            System.out.println("No has comprado nada, hasta la próxima.\n");
        } else {
            // Esta línea llama al método que mostrará el resumen de la venta.
            mostrarResumeDeVenta(ticketVentas, catalogo, contadorCodigo);
        }
        
        return ticketVentas;
    }
    
    // Este método recorre el array bidimensional entero de todas las ventas y devuelve
    //  el último código de venta. 
    public static int codigoVenta(int[][] ventas) {
        
        int codMax = 0;
        
        for (int y = 0; y < ventas.length; y++) {
            if (ventas[y][0] > codMax) {
                codMax = ventas[y][0];
            }
        }
        
        return codMax;
    }
    
    // Este método comprueba si hay stock disponible para el videojuego seleccionado.
    public static boolean hayStock(String[][] stockVideojuego, int codigoVideojuego, int unidadesVideojuego) {
        
        for (int y = 0; y < stockVideojuego.length; y++) {
            
            if (Integer.parseInt(stockVideojuego[y][0]) == codigoVideojuego) {
                return Integer.parseInt(stockVideojuego[y][1]) >= unidadesVideojuego;
            }
        }
        return false;
    }
    
    // Este método obtiene la respuesta del usuario para seguir comprando o no.
    public static char sigue(String mensaje) {
        
        System.out.print(mensaje);
        do {
            if (teclado.hasNext()) {
                String datos = teclado.next();
                System.out.println("");
                return datos.charAt(0);
            }
            teclado.next();
        } while (true);
    }
    
    // Este método añade una fila extra al "ticket" (el total de ventas).
    public static int[][] añadirFila(int[][] ticket) {
        
            int auxiluar[][] = new int[ticket.length + 1][3];

            for (int y = 0; y < ticket.length; y++) {
                for (int x = 0; x < ticket[y].length; x++) {
                    auxiluar[y][x] = ticket[y][x];
                }
            }

        return auxiluar;
    }
    
    // Este método elimina el stock según cuántas unidades se compren del videojuego.
    public static String[][] eliminarStock(String[][] stock, int codigoVideojuego, int cantidad) {

        for (int y = 0; y < stock.length; y++) {
            if (Integer.parseInt(stock[y][0]) == codigoVideojuego) {
                int stockActual = Integer.parseInt(stock[y][1]);
                stockActual -= cantidad;
                stock[y][1] = String.valueOf(stockActual);
            }
        }

        return stock;
    }
    
    // Este método muestra el resumen de la venta.
    public static void mostrarResumeDeVenta(int[][] ticket, String[][] catalogo, int codigoVenta) {
        
        int contador = 0;
        
        System.out.printf("Resumen de la venta (código %d)", codigoVenta);
        
        // Esto de aquí comprueba cuántas fias corresponden al código de venta de la compra del usuario.
        for (int y = 0; y < ticket.length; y++) {
            if (ticket[y][0] == codigoVenta) {
                contador++;
            }
        }
        
        String miniTicket[][] = new String[contador][3]; // Crea un "mini ticket" que tiene como tamaño la cantidad total de compras de un mismo cóigo de venta. 
        int indice = 0;
        
        // Este bucle rellena el "mini ticket" con la información adecuada.
        for (int y = 0; y < ticket.length; y++) {
            
            if (ticket[y][0] == codigoVenta) {
                
                miniTicket[indice][0] = String.valueOf(ticket[y][1]);
                miniTicket[indice][1] = nombreVideojuego(catalogo, miniTicket[indice][0]); // Aquí se llama al método para obtener el nombre correspondiente al ID vendido.
                miniTicket[indice][2] = String.valueOf(ticket[y][2]);
                indice++;
            }
        }
        
        // Aquí se muestra el mini ticket en con el mismo formato que el catálogo.
        mostrarCatalogoVideojuegos(1, miniTicket, 2, miniTicket, 3, miniTicket, "Código", "Nombre", "Unidades");
        
        calculoDinero(catalogo, miniTicket);
        
        System.out.println("");
        
    }
    
    // Este método obtiene el nombre del videojuego de la venta correspondiente.
    public static String nombreVideojuego(String[][] catalogo, String codigo) {
        
        for (int y = 0; y < catalogo.length; y++) {
            
            if (Integer.parseInt(catalogo[y][0]) == Integer.parseInt(codigo)) {
                return catalogo[y][1];
            }
        }
        return null;
    }
    
    // Este método calcula el dinero total que el usuario tendrá que pagar.
    public static void calculoDinero(String[][] catalogo, String[][] miniTicket) {
        
        double total = 0;
        
        for (int y = 0; y < miniTicket.length; y++) {
            
            int codigo = Integer.parseInt(miniTicket[y][0]);
            total = total + (Integer.parseInt(miniTicket[y][2]) * Double.parseDouble(catalogo[precioVideojuego(catalogo, codigo)][2]));
        }
        
        System.out.printf("Total a pagar: %.2f €\n", total);
    }
    
    // Este método calcula el dinero total que el usuario tendrá que pagar con un código de venta específico.
    public static void calculoDinero(String[][] catalogo, String[][] miniTicket, int codigoVenta) {
        
        double total = 0;
        
        for (int y = 0; y < miniTicket.length; y++) {
            
            if (codigoVenta == Integer.parseInt(miniTicket[y][0])) {
                
                total = total + (Integer.parseInt(miniTicket[y][2]) * Double.parseDouble(catalogo[precioVideojuego(catalogo, miniTicket[y][1])][2]));
            }
        }
        
        System.out.printf("Su devolución ha sido realizada. Se devuelve un importe de %.2f €\n", total);
    }
    
    // Este método deuvelve el índice donde se ubica el precio del juego con codigo deseado.
    public static int precioVideojuego(String[][] catalogo, int codigo) {
        
        int indice = 0;
        
        for (int y = 0; y < catalogo.length; y++) {
            
            if (Integer.parseInt(catalogo[y][0]) == codigo) {
                return indice;
            }
            
            indice++;
        }
        return -1;
    }
    
    // Este método deuvelve el índice donde se ubica el precio del juego con nombre deseado.
    public static int precioVideojuego(String[][] catalogo, String nombre) {
        
        int indice = 0;
        
        for (int y = 0; y < catalogo.length; y++) {
            
            if (nombre.equals(catalogo[y][1])) {
                return indice;
            }
            
            indice++;
        }
        return -1;
    }
    //////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// .Métodos devoluciones. //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    public static int[][] devolucion(int[][] ventas, String[][] catalogo, String[][] stock) {
        
        if (ventas.length != 0) {
            
            char confirmación = 'S';
            
            do {
                
                if (ventas.length != 0) {
                    String castVentasString[][] = castVentasString(ventas, catalogo);
                    mostrarCatalogoVideojuegos(1, castVentasString, 2, castVentasString, 3, castVentasString, "Código venta", "Nombre", "Unidades");
                    
                    int codigoVenta = obtenerEnteroUsuario("Introduzca el código de venta: ");

                    if (existeCodigo(ventas, codigoVenta)) {
                        System.out.println("Procesando devolución......\n");
                        calculoDinero(catalogo, castVentasString, codigoVenta);
                        stock = recuperarStock(ventas, stock, codigoVenta);
                        ventas = eliminarFilaConCodigo(ventas, codigoVenta);
                        confirmación = sigue("¿Desea intentar otra devolución? (S/N): ");
                    } else {
                        System.out.println("No podemos localizar esta venta.");
                        confirmación = sigue("¿Desea intentar otra devolución? (S/N): ");
                    }
                } else {
                    System.out.println("No tienes más productos para devolver.\n");
                    confirmación = 'N';
                }
            } while (confirmación == 'S');
            
        } else {
            System.out.println("\nTienes que realizar alguna compra antes de poder devolverla.\n");
        }
        
        return ventas;
    }
    
    // Este método hace un casting a String del ticket para que pueda ser impresa con el método de imprimir
    //  tablas. También, cambia la columna central del ID al nombre del videojuego para que sea más fácil
    //  poder identificar a qué se refiere cada cosa.
    public static String[][] castVentasString(int[][] ventas, String[][] catalogo) {
        
        String castVentasString[][] = new String[ventas.length][ventas[0].length];
        for (int y = 0; y < castVentasString.length; y++) {
            for (int x = 0; x < castVentasString[y].length; x++) {
                if (x == 1) {
                    castVentasString[y][x] = nombreVideojuego(catalogo, String.valueOf(ventas[y][1]));
                } else {
                    castVentasString[y][x] = Integer.toString(ventas[y][x]);
                }
            }
        }
        
        return castVentasString;
    }
    
    // Este método elimina todas las filas con las que el código coincida. Para eliminarlas creo una
    //  matriz igual pero sin las filas del código. Luego copio una matriz a otra (no se copian los
    //  valores del código que se desea eliminar).
    public static int[][] eliminarFilaConCodigo(int[][] ventas, int codigo) {
        
        int contadorVecesCodigoDistinto = 0;
        
        for (int y = 0; y < ventas.length; y++) {
            if (ventas[y][0] != codigo) {
                contadorVecesCodigoDistinto++;
            }
        }
        
        int aux[][] = new int[contadorVecesCodigoDistinto][3];
        int indice = 0;
        
        for (int y = 0; y < ventas.length; y++) {
            
            if (ventas[y][0] != codigo) {
                
                for (int x = 0; x < ventas[y].length; x++) {
                    
                    aux[indice][x] = ventas[y][x];
                }
                indice++;
            }
        }
        
        return aux;
    }
    
    // Este método comprueba si existe el codigo de venta a la hora de devolver un producto.
    public static boolean existeCodigo(int[][] ventas, int codigo) {
        
        for (int y = 0; y < ventas.length; y++) {
            if (ventas[y][0] == codigo) {
                return true;
            }
        }
        
        return false;
    }
    
    
    public static String[][] recuperarStock(int[][] ventas, String[][] stock, int codigo) {
        
        for (int y = 0; y < ventas.length; y++) {
            
            if (codigo == ventas[y][0]) {
                
                int idVideojuego = ventas[y][1];
                
                for (int x = 0; x < stock.length; x++) {
                    
                    if (idVideojuego == Integer.parseInt(stock[x][0])) {
                        
                        stock[x][1] = String.valueOf(Integer.parseInt(stock[x][1]) + ventas[y][2]);
                    }
                }
            }
        }
        
        return stock;
    }
    ////////////////////////////////////////////////////////////////////////////////////
}