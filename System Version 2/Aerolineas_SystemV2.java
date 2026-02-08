package Programacion_Java;
import java.util.Scanner;
public class Aerolineas_SystemV2 {
    public int reservas;
    public int numeroVuelo;
    Scanner scanner = new Scanner(System.in);
    public String RegistroV2() {
        System.out.println(" --- Bienvenido al Registro de Usuario ---");
        System.out.println("Ingrese su nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su número de DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el número
         if(dni <= 0 || dni > 99999999) {
            System.out.println("Número de DNI inválido. Por favor, ingrese un número de DNI válido.");
            return "Registro fallido.";
        }
        System.out.println("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
        if(correo.contains("@") && correo.contains(".com")) {
            System.out.println("Correo electrónico válido.");
        } else {
            System.out.println("Correo electrónico inválido. Por favor, ingrese un correo válido.");
            return "Registro fallido.";
        }
        return nombre + " " + dni + " " + correo;
    }
    public String BusquedaV2() {
        System.out.println("--- Busqueda de Vuelos ---");
        System.out.println("Ingrese el origen del vuelo: ");
        String origenVuelo = scanner.nextLine();
        System.out.println("Ingrese el destino del vuelo: ");
        String destinoVuelo = scanner.nextLine();
        System.out.println("Vuelo encontrado: " + origenVuelo + " a " + destinoVuelo);
        numeroVuelo = (int) (Math.random() * 1000) + 1; // Genera un número de vuelo aleatorio entre 1 y 1000
        System.out.println("Número de vuelo: " + numeroVuelo);
        return "Finalizar";
    }
    public String ReservaV2() {
        System.out.println("--- Reserva de Vuelo ---");
        System.out.println("Ingrese el número de vuelo que desea reservar: ");
        int numeroVueloCon = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el número
        if(numeroVueloCon == numeroVuelo) {
            System.out.println("Vuelo " + numeroVueloCon + " reservado exitosamente.");
             reservas++;

        } else {
            System.out.println("Número de vuelo incorrecto. No se pudo reservar el vuelo.");
        }
        return "Finalizar";
    }
    public String CancelacionV2() {
        System.out.println("--- Cancelacion de Reserva ---");
        if(reservas > 0) {
            System.out.println("Ingrese el número de vuelo que desea cancelar: ");
            int numeroVueloCan = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el número
            if(numeroVueloCan == numeroVuelo) {
                System.out.println("Reserva del vuelo " + numeroVueloCan + " cancelada exitosamente.");
                reservas--;
            } else {
                System.out.println("Número de vuelo incorrecto. No se pudo cancelar la reserva.");
            }
        } else {
            System.out.println("No hay reservas para cancelar.");
        }
        return "Cancelación finalizada.";
    }
    public String CerrarSesionV2() {
        System.out.println("--- Cerrar Sesión ---");
        System.out.println("Sesión cerrada exitosamente. ¡Hasta luego!");
        return "Sesión cerrada exitosamente.";
    }
    public void MenuV2() {
        while(true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registro de Usuario");
            System.out.println("2. Búsqueda de Vuelos");
            System.out.println("3. Reserva de Vuelo");
            System.out.println("4. Cancelación de Reserva");
            System.out.println("5. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el número
            switch(opcion) {
                case 1:
                    String r = RegistroV2();
                    System.out.println(r);
                    break;
                case 2:
                    String b = BusquedaV2();
                    System.out.println(b);
                    break;
                case 3:
                    String re = ReservaV2();
                    System.out.println(re);
                    break;
                case 4:
                    String cr = CancelacionV2();
                    System.out.println(cr);
                    break;
                case 5:
                    String cs = CerrarSesionV2();
                    System.out.println(cs);
                    return; // Salir del menú y finalizar el programa
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    public static void main(String[] args) {  
       Aerolineas_SystemV2 sistema = new Aerolineas_SystemV2();
       String r = sistema.RegistroV2(); 
       if(!r.equals("Registro fallido.")) {
        sistema.MenuV2();
       }
       else {
        System.out.println("No se pudo completar el registro. El programa finalizará.");
       }
    }
}