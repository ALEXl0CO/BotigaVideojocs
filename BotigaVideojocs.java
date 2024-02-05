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
        };
        
        int stockVideojocs[][] = stockVideojocs(juegos, MIN_STOCK, MAX_STOCK);
        
        visualizarSopa(stockVideojocs);
        
        
        
        
        
        
    }
    
    
    ////////////////////////////// .Método que interactuan con la sopa de letras. //////////////////////////////
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
    
    
    ////////////////////////////// .Métodos menú. //////////////////////////////
    public static void mostrarMenu() {
        
    }
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    public static int[][] stockVideojocs(String[][] matriz, int min, int max) {
        
        int stock[][] = new int[matriz.length][2];
        
        for (int y = 0; y < stock.length; y++) {
            
            for (int x = 0; x < stock[y].length; x++) {
                
                if (x == 0) {
                    stock[y][x] = y;
                } else {
                    stock[y][x] = generarRandom(min, max);
                }
            }
        }
        
        return stock;
    }
    
    public static int generarRandom(int min, int max) {
        return random.nextInt(min, max + 1);
    }
}
