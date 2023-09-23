package com.ieseljust.psp.myshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyShell2 {
    public static void main(String[] args) {
        while (true) {
            try {
                // llegir el comando del usuari
                System.out.print("\u001B[32mMyShell2> "); // Color vert per a la entrada
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = reader.readLine().trim();

                // Dividir el comando en tuberías (pipes)
                String[] commands = input.split("\\|");

                // Lista para almacenar procesos
                List<Process> processes = new ArrayList<>();

                // executar cada comando en una tubería separada
                for (String command : commands) {
                    ProcessBuilder builder = new ProcessBuilder(command.trim().split("\\s+"));
                    builder.redirectErrorStream(true);

                    // Iniciar el proces
                    Process process = builder.start();
                    processes.add(process);
                }

                // Esperar a que acaben els procesos
                for (Process process : processes) {
                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        System.out.print("\u001B[32m"); // Color vert per a la ixida
                    } else {
                        System.out.print("\u001B[31m"); // Color roig per als errors
                    }
                    System.out.println("Proceso terminado con código de salida: " + exitCode);
                    System.out.print("\u001B[0m"); // Restablecer el color
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

