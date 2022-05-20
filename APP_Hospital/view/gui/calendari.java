package APP_Hospital.view.gui;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;

import APP_Hospital.model.business.utils.utils;

public class calendari {

    private int anyActual;
    // final String EXIT = "exit";

    /**
     * Obtenim l'any del mes seleccionat
     * Si el número de mes, és inferior al del mes actual, retorna l'any següent.
     * 
     * @param mes
     * @return any
     */
    public static int getAnyDelMes(int mes) {
        Calendar calendari = Calendar.getInstance();
        int anyActual = calendari.get(Calendar.YEAR);
        int mesActual = calendari.get(Calendar.MONTH);
        mesActual += 1;
        if (mesActual < mes) {
            anyActual += 1;
        }

        return anyActual;
    }

    /**
     * Mostrar menú d'opcions
     * Si el mes escollit no és correcte repetirà la pregunta
     * 
     * @return int mes
     */
    public static void menuOpcions() {
        int mes = 1;
        boolean correcte = false;
        while (!correcte) {
            System.out.print("Escull un mes utilitzant el número de mes, per exemple 6" + "\n"
                    + "Pots escollir des del mes actual fins els pròxims 11 mesos" + "\n"
                    + "-----------------------------------------" + "\n" + "Mes:");
            Scanner lectura = new Scanner(System.in);
            mes = lectura.nextInt();
            correcte = (mes < 13 && mes > 0) ? true : false;
        }

        veureCalendari(mes);
    }

    /**
     * Mostrar els diumenges del mes escollit
     * 
     * @param mes
     * @return array de diumenges del mes
     */
    public static void veureCalendari(int mes) {
        Calendar calendari = Calendar.getInstance();
        int[] diumenges = new int[5]; // Desar els diumenges per poder realitzar comprobacions
        int i = 0;

        int anyActual = getAnyDelMes(mes);
        calendari.set(anyActual, mes - 1, 1, 0, 0, 0);
        calendari.set(Calendar.MILLISECOND, 0);

        System.out.println("--->" + mes);

        // mostrar només els diumenges
        while (calendari.get(Calendar.MONTH) + 1 == mes) {
            if (calendari.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                System.out.println(calendari.get(Calendar.DAY_OF_MONTH));
                diumenges[i] = calendari.get(Calendar.DAY_OF_MONTH);
                i++;
                calendari.add(Calendar.DAY_OF_MONTH, 7); // Saltar 7 dies

            } else {
                calendari.add(Calendar.DAY_OF_MONTH, 1); // Saltar 1 dia
            }
        }

        escollirDia(diumenges, mes, anyActual);

    }

    /**
     * Escollir un dia entre els diumenges mostrats
     * 
     * @param diumenges
     * @return
     */
    private static void escollirDia(int[] diumenges, int mes, int anyActual) {
        boolean correcte = false;
        Scanner lectura = new Scanner(System.in);
        int dia = 1;

        // boolean trobat = false;
        while (!correcte) {
            System.out.println("----------------------------------------");
            System.out.println("Escull un dia: ");
            dia = lectura.nextInt();
            correcte = utils.contains(diumenges, dia);
            if (!correcte || dia == 0) {
                System.out.println("Error: El dia escollit no és correcte");
            }
        }

        escollirZona(dia, mes, anyActual);

    }

    /**
     * Escollir la zona en la que es vol treballar
     * 
     * @param dia
     * @param mes
     * @param anyActual
     */
    private static void escollirZona(int dia, int mes, int anyActual) {
        Scanner lectura = new Scanner(System.in);
        // obtenir les zones
        String cercar = "zones";
        String[] zones = { "unitat1", "unitat3", "unitat4", "UCIES" };
        String zona = "";
        boolean correcte = false;
        int quantesZones = zones.length;
        System.out.println("\n" + "----------------------------------------");
        System.out.print("Zones a escollir: ");

        for (int i = 0; i < quantesZones; i++) {
            System.out.print(zones[i] + " ");
        }

        while (!correcte) {
            System.out.println("\n" + "Escull una zona: ");
            zona = lectura.next();
            correcte = (Arrays.asList(zones).contains(zona));

            if (!correcte) {
                System.out.println("Error: La zona escollida no és correcte");
            }
        }

        escollirTorn(dia, mes, anyActual, zona);
    }

    /**
     * Escollir el torn
     * 
     * @param dia
     * @param mes
     * @param anyActual
     * @param zona
     */
    private static void escollirTorn(int dia, int mes, int anyActual, String zona) {
        boolean correcte = false;
        String torn = "";
        Scanner lectura = new Scanner(System.in);
        // obetnir els torns
        String cercar = "torns";
        String[] torns = { "dia", "nit" };

        int quantsTorns = torns.length;
        System.out.println("\n" + "----------------------------------------");
        System.out.print("Torns a escollir: ");

        for (int i = 0; i < quantsTorns; i++) {
            System.out.print(torns[i] + " ");
        }

        // escollir dia o nit
        while (!correcte) {
            System.out.println("\n" + "Escull un torn: ");
            torn = lectura.nextLine();
            correcte = (Arrays.asList(torns).contains(torn));

            if (!correcte) {
                System.out.println("Error: El torn escollit no és correcte");
            }
        }

        mostrarDades(dia, mes, anyActual, zona, torn);
    }

    /**
     * Mostrar les dades escollides per l'usuri per validar si són correctes
     * procedir a desar la guardia
     * 
     * @param dia
     * @param mes
     * @param anyActual
     * @param zona
     * @param torn
     */
    private static void mostrarDades(int dia, int mes, int anyActual, String zona, String torn) {
        Scanner lectura = new Scanner(System.in);
        boolean correcte = false;

        System.out.println("----------------------------------------");
        System.out.println("Has escollit: ");
        System.out.println(dia + "/" + mes + "/" + anyActual + " a " + zona + " el torn de " + torn);
        System.out.print("Estàs d'acord? (escriu: Si/No): ");
        String resposta = lectura.nextLine();

        while (!correcte) {
            System.out.println("---" + resposta);

            switch (resposta.toLowerCase()) {
                case "si":
                    correcte = true;
                    break;

                case "No":
                    System.exit(0);

                    break;

                default:
                    System.out.print("Error: Resposta Incorrecte (escriu: Si/No): ");
                    resposta = lectura.next();
                    break;
            }

        }

        // ara tenim totes les dades i són correcte, procedir a desar la guardia

    }

}
