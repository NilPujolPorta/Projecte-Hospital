package APP_Hospital.view.gui;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import APP_Hospital.exceptions.AlreadyAdded;
import APP_Hospital.exceptions.CategoryMissmatch;
import APP_Hospital.model.business.entities.Guardies;
import APP_Hospital.model.business.utils.utils;
import APP_Hospital.model.persistence.dao.impl.JDBCGuardiaDAO;
import APP_Hospital.model.persistence.exceptions.DAOException;

public class calendari {

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

        System.out.println(" - DEBUG: Mes ACTUAL: " + mesActual);
        System.out.println(" - DEBUG: Mes ENTRAT: " + mes);
        System.out.println(" - DEBUG: Any ACTUAL: " + anyActual);

        if (mes < mesActual) {
            anyActual += 1;
        }
        System.out.println(" - DEBUG: Any processat: " + anyActual);

        return anyActual;

    }

    /**
     * Mostrar menú d'opcions per escollir les dades del dia de guàrdia
     * Si el mes escollit no és correcte repetirà la pregunta
     * @param lectura2
     * 
     * @return int mes
     */
    public static void opcionsGuardia(Scanner lectura) {
        int mes = 1;
        boolean correcte = false;

        System.out.print("Escull un mes utilitzant el número de mes, per exemple 6" + "\n"
                + "Pots escollir des del mes actual fins els pròxims 11 mesos" + "\n");
        separador();

        while (!correcte) {
            System.out.print("Mes: ");
            mes = lectura.nextInt();
            
            correcte = (mes < 13 && mes > 0) ? true : false;
            if (!correcte) {
                System.out.println(
                        "\n" + "\n" + "Error: El mes escollit no és corecte, si us plau escull un mes de l'1 al 12!");
            }
        }

        veureCalendari(mes, lectura);
    }

    /**
     * Mostrar els diumenges del mes escollit
     * 
     * @param mes
     * @param lectura
     * @return array de diumenges del mes
     */
    public static void veureCalendari(int mes, Scanner lectura) {
        Calendar calendari = Calendar.getInstance();
        int[] diumenges = new int[5]; // Desar els diumenges per poder realitzar comprobacions
        int i = 0;

        int mesActual = Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()));
        int diaActual = Integer.parseInt(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime()));

        int anyActual = getAnyDelMes(mes);

        calendari.set(anyActual, mes - 1, 1, 0, 0, 0);

        if (mes == mesActual) {
            calendari.set(anyActual, mes - 1, diaActual, 0, 0, 0);

        }
        calendari.set(Calendar.MILLISECOND, 0);

        int dia = calendari.get(Calendar.DAY_OF_MONTH);
        System.out.println("\n" + "Dies disponibles: ");

        // mostrar només els diumenges
        while (calendari.get(Calendar.MONTH) + 1 == mes) {
            if (calendari.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                // obtenir la data de dia d'avui, si el mes és l'actual i el diumenge és
                // inferior al dia d'avui, no s'ha de mostrar
                System.out.println(" -> " + calendari.get(Calendar.DAY_OF_MONTH));
                diumenges[i] = calendari.get(Calendar.DAY_OF_MONTH);
                i++;
                calendari.add(Calendar.DAY_OF_MONTH, 7); // Saltar 7 dies

            } else {
                calendari.add(Calendar.DAY_OF_MONTH, 1); // Saltar 1 dia
            }
        }

        escollirDia(diumenges, mes, anyActual, lectura);

    }

    /**
     * Escollir un dia entre els diumenges mostrats
     * 
     * @param diumenges
     * @param lectura2
     * @return
     */
    private static void escollirDia(int[] diumenges, int mes, int anyActual, Scanner lectura) {
        boolean correcte = false;
            int dia = 1;
            // boolean trobat = false;
            while (!correcte) {
                System.out.print("\n" + "\n" + "Escull un dia: ");
                dia = lectura.nextInt();
                correcte = utils.contains(diumenges, dia);
                if (!correcte || dia == 0) {
                    System.out.println("\n" + "\n" + "Error: El dia escollit no és correcte");
                }
            }

            escollirZona(dia, mes, anyActual, lectura);
        

    }

    /**
     * Escollir la zona en la que es vol treballar
     * 
     * @param dia
     * @param mes
     * @param anyActual
     * @param lectura2
     */
    private static void escollirZona(int dia, int mes, int anyActual, Scanner lectura) {
        // obtenir les zones
        String[] zones = { "unitat1", "unitat3", "unitat4", "UCIES" };
        String zona = "";
        boolean correcte = false;
        int quantesZones = zones.length;
        separador();

        System.out.print("Zones a escollir: ");

        for (int i = 0; i < quantesZones; i++) {
            System.out.print(zones[i] + " ");
        }

        while (!correcte) {
            System.out.print("\n" + "\n" + "Escull una zona: ");
            zona = lectura.next();
            correcte = (Arrays.asList(zones).contains(zona));

            if (!correcte) {
                System.out.println("\n" + "Error: La zona escollida no és correcte");
            }
        }
        short zonanum = 5;
        if (zona == "unitat1") {
            zonanum = 0;
        } else if (zona == "unitat2") {
            zonanum = 1;
        } else if (zona == "unitat3") {
            zonanum = 2;
        } else if (zona == "unitat4") {
            zonanum = 3;
        } else if (zona == "UCIES") {
            zonanum = 4;
        }
        escollirTorn(dia, mes, anyActual, zonanum, lectura);
        
    }

    /**
     * Escollir el torn
     * 
     * @param dia
     * @param mes
     * @param anyActual
     * @param zona
     * @param lectura2
     */
    private static void escollirTorn(int dia, int mes, int anyActual, Short zona, Scanner lectura) {
        boolean correcte = false;
        String torn = "";
        // obetnir els torns
        String[] torns = { "dia", "nit" };

        int quantsTorns = torns.length;
        separador();
        System.out.print("Torns a escollir: ");

        for (int i = 0; i < quantsTorns; i++) {
            System.out.print(torns[i] + " ");
        }

        // escollir dia o nit
        while (!correcte) {
            System.out.print("\n" + "\n" + "Escull un torn: ");
            torn = lectura.next();
            correcte = (Arrays.asList(torns).contains(torn));

            if (!correcte) {
                System.out.println("\n" + "Error: El torn escollit no és correcte");
            }
        }
        
        short tornnum = 0;
        if (torn == "dia") {
            tornnum = 1;
        } else {
            tornnum = 0;
        }
        mostrarDades(dia, mes, anyActual, zona, tornnum, lectura);
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
     * @param lectura2
     */
    private static void mostrarDades(int dia, int mes, int anyActual, Short zona, Short torn, Scanner lectura) {
        boolean correcte = false;

        separador();
        System.out.println(" * Has escollit: ");

        String diaTractat;
        String mesTractat;

        if (dia < 10) {
            diaTractat = '0' + Integer.toString(dia);
        } else {
            diaTractat = Integer.toString(dia);
        }
        if (mes < 10) {
            mesTractat = '0' + Integer.toString(mes);
        } else {
            mesTractat = Integer.toString(mes);
        }

        String data = diaTractat + "-" + mesTractat + "-" + anyActual;

        System.out.println(data + " a " + zona + " el torn de " + torn + "\n");

        System.out.print(" * Estàs d'acord? (escriu: Si/No): ");
        String resposta = lectura.next();

        while (!correcte) {
            System.out.println("---" + resposta);
            switch (resposta.toLowerCase()) {
                case "si":
                    correcte = true;
                    break;

                case "no":
                    System.exit(0);

                    break;

                default:
                    System.out.print("\n" + "Error: Resposta Incorrecte (escriu: Si/No): ");
                    resposta = lectura.next();
                    break;
            }
        }

        String dataJunta = anyActual + mesTractat + diaTractat;
        Guardies g = new Guardies(dataJunta, (short) 1, torn, zona, (short) 10);
        try {
            JDBCGuardiaDAO.add(g);
        } catch (DAOException e1) {
            System.out.println("1.1");
        } catch (SQLException e1) {
            System.out.println("1.2");
        }
        try {
            g.reservarGuardia(Main.TreballadorLoggejat);
        } catch (AlreadyAdded e) {
            System.out.println("2.1");
        } catch (CategoryMissmatch e) {
            System.out.println("2.2");
        }

    }

    private static void separador() {
        System.out.println("\n" + "-----------------------------------------------------------" + "\n");

    }

}
