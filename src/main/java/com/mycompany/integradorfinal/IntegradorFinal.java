/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.integradorfinal;

/**
 *
 * @author mara
 */

import java.io.IOException; // HAY QUE DECLARAR LA EXCEPCION.
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IntegradorFinal {

    public static void main(String[] args) throws IOException { // HAY QUE DECLARAR LA EXCEPCION.
        List<String> resultados = Files.readAllLines(Paths.get(args[0]));
        List<String> predicciones = Files.readAllLines(Paths.get(args[1]));
        int puntaje = contarPrediccionesCorrectas(resultados, predicciones);
        System.out.println(puntaje);
    }

    private static int contarPrediccionesCorrectas(List<String> resultados, List<String> predicciones) {
        int puntaje = 0;
        
        // empiezo por la segunda linea porque la primera tiene los nombres de las columnas
        // estamos asumiendo que los archivos vienen con los mismos partidos en el mismo orden
        for (int numLinea = 1; numLinea < resultados.size(); numLinea++){
            String resultado = resultados.get(numLinea);
            String prediccion = predicciones.get(numLinea);
            
            String[] resultadoPartes = resultado.split(",");
            String[] prediccionPartes = prediccion.split(",");
            
            int goles1 = Integer.parseInt(resultadoPartes[1]); // el primer valor es el nombre del equipo 1, el segundo sus goles
            int goles2 = Integer.parseInt(resultadoPartes[2]); // el tercer valor son los goles del equipo 2
            
            String prediccionGano1 = prediccionPartes[1];
            String prediccionEmpate = prediccionPartes[2];
            String prediccionGano2 = prediccionPartes[3];
            
            if (prediccionGano1.equals("X") && goles1 > goles2){
                puntaje++;
            }
            else if (prediccionGano2.equals("X") && goles1 < goles2){
                puntaje++;
            }
            else if (prediccionEmpate.equals("X") && goles1 == goles2){
                puntaje++;
            }
        }
        return puntaje;
    }
}
