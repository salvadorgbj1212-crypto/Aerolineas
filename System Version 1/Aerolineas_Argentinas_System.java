package Programacion_Java;
import java.util.Scanner;

public class Aerolineas_Argentinas_System {
    static Scanner sc = new Scanner(System.in);
    public static int reserva = 0;
    static String Registro() {
        System.out.println(" --- Registro de Usuario ---");
        System.out.print("Ingrese su nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese su número de DNI: ");
        int dni = sc.nextInt();
        if(dni <= 0 || dni > 99999999) {
            System.out.println("Número de DNI inválido. Por favor, ingrese un número de DNI válido.");
            return "Registro fallido.";
        }
        System.out.println("Ingrese Dirección de correo electrónico: ");
        String correo = sc.next();
        if(correo.contains("@") && correo.contains(".com")) {
            System.out.println("Correo electrónico válido.");
        } else {
            System.out.println("Correo electrónico inválido. Por favor, ingrese un correo válido.");
            return "Registro fallido.";
        }
        System.out.println("Usuario registrado correctamente.");
        System.out.println("Usario Registrado exitosamente!!");
        System.out.println("Bienvenido/a, " + nombre + "!");
        return correo;
    }
    static void Menu_Principal() {
        while(true) {
            System.out.println(" -- AEROLINEAS ARGENTINAS --");
            System.out.println("1. Buscar Vuelos");
            System.out.println("2. Reservar Vuelo");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            switch(opcion) {
                case 1:
                    String r = Busqueda_Vuelos();
                    System.out.println(r);

                    break;
                case 2:
                    System.out.println("Funcionalidad de reserva de vuelos en desarrollo.");
                    break;
                case 3:
                    String C = Cancelar_Reserva();
                    System.out.println(C);
                    break;
                case 4:
                    System.out.println("Gracias por usar Aerolíneas Argentinas. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 4.");
                    break;
            }
        }
    }
    public static String Busqueda_Vuelos() {
        System.out.println(" --- Búsqueda de Vuelos ---");
        System.out.print("Ingrese ciudad de origen: ");
        String origen = sc.nextLine();
        System.out.println("Vuelos disponibles desde " + origen + " en desarrollo.");
        System.out.println("Búsqueda completada.");
        System.out.println("-------");
        System.out.println("1. Rio de Janeiro, Brasil");
        System.out.println("-------");
        System.out.println("2. Santiago, Chile");
        System.out.println("-------");
        System.out.println("3. Lima, Perú");
        System.out.println("-------");
        System.out.println("4. Buenos Aires, Argentina");
        System.out.println("-------");
        System.out.println("5. Manhattan, Estados Unidos");
        System.out.println("-------");
        System.out.println("Seleccione un vuelo de los mostrados: ");
        int eleccion = sc.nextInt();
        switch(eleccion) {
            case 1:
                System.out.println("Has seleccionado vuelo a Rio de Janeiro, Brasil.");
                reserva++;
                break;
            case 2:
                System.out.println("Has seleccionado vuelo a Santiago, Chile.");
                reserva++;
                break;
            case 3:
                System.out.println("Has seleccionado vuelo a Lima, Perú.");
                reserva++;
                break;
            case 4:
                System.out.println("Has seleccionado vuelo a Buenos Aires, Argentina.");
                reserva++;
                break;
            case 5:
                System.out.println("Has seleccionado vuelo a Manhattan, Estados Unidos.");
                reserva++;
                break;
            default:
                System.out.println("Opción no válida.");
                break;
            }
            return "Búsqueda de vuelos finalizada.";
        }
        static String Cancelar_Reserva() {
            System.out.println(" --- Cancelación de Reserva ---");
            System.out.println("Vuelos reservados: " + " (Funcionalidad en desarrollo)");
            System.out.print("Ingrese su número de reserva: ");
            int numReserva = sc.nextInt();
            if(numReserva > 0 )
                return "Reserva número " + numReserva + " cancelada correctamente.";
            else {
                return "Número de reserva inválido.";
            }
        }
    
        public static void main(String[] args) {
            Registro();
            Menu_Principal();
        }
    }

    


