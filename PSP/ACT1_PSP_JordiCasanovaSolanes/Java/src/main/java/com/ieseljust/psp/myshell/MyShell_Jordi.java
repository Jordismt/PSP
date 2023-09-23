package com.ieseljust.psp.myshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author jordismt
 */

public class MyShell_Jordi {

    public static void main(String[] args) {
        System.out.println("Bienvenido a MyShell. Ingresa 'exit' para salir.");

        while (true) {
            System.out.print("MyShell> ");

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String userInput = reader.readLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                executeCommand(userInput);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void executeCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("\u001B[32m" + line); // Color verde para la salida
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("\u001B[32mEl comando ha finalizado con código de salida: " + exitCode);
            } else {
                System.out.println("\u001B[31mEl comando ha finalizado con código de salida: " + exitCode); // Color rojo para errores
            }
            System.out.print("\u001B[0m"); // Restablecer el color
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e); // Color rojo para errores
            System.out.print("\u001B[0m"); // Restablecer el color
        }
    }
}

