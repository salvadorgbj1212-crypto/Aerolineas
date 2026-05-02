import sqlite3
import hashlib

#Conectamos a la base de datos
def conectar():
    conexion = sqlite3.connect("aerolineas.db")
    cursor = conexion.cursor()
    return conexion, cursor

#creamos las tablas necesarias(vuelos, usuarios y reservas)
def crear_tablas():
    conexion, cursor = conectar()
    cursor.execute('''CREATE TABLE IF NOT EXISTS vuelos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        origen TEXT NOT NULL,
                        destino TEXT NOT NULL,
                        fecha TEXT NOT NULL,
                        precio REAL NOT NULL,
                        capacidad INTEGER NOT NULL)''')
    
    cursor.execute('''CREATE TABLE IF NOT EXISTS usuarios (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre TEXT UNIQUE NOT NULL,
                        contraseña TEXT NOT NULL)''')
    
    cursor.execute('''CREATE TABLE IF NOT EXISTS reservas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        usuario_id INTEGER NOT NULL,
                        vuelo_id INTEGER NOT NULL,
                        FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                        FOREIGN KEY (vuelo_id) REFERENCES vuelos(id))''')
    
    conexion.commit()
    #todavia no cerramos la conexion porque la vamos a usar en otras funciones, la cerraremos al final del programa

#funciones necesarias para el registro inicial
def registrar_usuario():
    conexion, cursor = conectar()
    nombre = input("Ingrese su nombre completo: ")
    pass_raw = input("Ingrese su contraseña: ")
    contraseña = hashlib.sha256(pass_raw.encode()).hexdigest()
    cursor.execute("INSERT INTO usuarios (nombre, contraseña) VALUES (?, ?)", (nombre, contraseña))
    conexion.commit()
def iniciar_sesion():
    conexion, cursor = conectar()
    nombre = input("Ingrese su nombre completo: ")
    pass_raw = input("Ingrese su contraseña: ")
    contraseña = hashlib.sha256(pass_raw.encode()).hexdigest()
    cursor.execute("SELECT id, nombre FROM usuarios WHERE nombre = ? AND contraseña = ?", (nombre, contraseña))
    resultado = cursor.fetchone()
    conexion.close()
    if resultado:
        print("Inicio de sesión exitoso.")
        return resultado  # Devolvemos (id, nombre) para distinguir admin de cliente
    else:
        print("Nombre de usuario o contraseña incorrectos.")
        return None
#funciones del menu inicial
def menu_inicio():
    while True:
        print("1. Registrar usuario")
        print("2. Iniciar sesión")
        print("3. Salir")
        opcion = input("Seleccione una opción: ")
        if opcion == '1':
            registrar_usuario()
        elif opcion == '2':
            resultado = iniciar_sesion()
            if resultado:
                uid, nombre = resultado
                if nombre.lower() == "admin":
                    print(" --- ACCESO ROOT DETECTADO ---")
                    menu_admin()
                else:
                    print(f"Sesión iniciada como Usuario ID: {uid}")
                    menu_cliente(uid)
        elif opcion == '3':
            print("Gracias por usar el sistema de aerolíneas. ¡Hasta luego!")
            break
        else:
            print("Opción no válida. Intente nuevamente.")
#funciones relacionadas co los vuelos
def tabla_vuelos():
    conexion, cursor = conectar()
    cursor.execute("SELECT * FROM vuelos")
    vuelos = cursor.fetchall()
    print("Vuelos disponibles:")
    for vuelo in vuelos:
        print(f"ID: {vuelo[0]}, Origen: {vuelo[1]}, Destino: {vuelo[2]}, Fecha: {vuelo[3]}, Precio: {vuelo[4]}")
    conexion.close()
def registrar_vuelo():
    conexion, cursor = conectar()
    origen = input("Ingrese el origen del vuelo: ")
    destino = input("Ingrese el destino del vuelo: ")
    fecha = input("Ingrese la fecha del vuelo (YYYY-MM-DD): ")
    precio = float(input("Ingrese el precio del vuelo: "))
    capacidad = int(input("Ingrese la capacidad del vuelo: "))
    cursor.execute("INSERT INTO vuelos (origen, destino, fecha, precio, capacidad) VALUES (?, ?, ?, ?, ?)", 
                   (origen, destino, fecha, precio, capacidad))
    conexion.commit()
def reservar_vuelo(usuario_id):
    conexion, cursor = conectar()
    vuelo_id = int(input("Ingrese el ID del vuelo que desea reservar: "))
    cursor.execute("INSERT INTO reservas (usuario_id, vuelo_id) VALUES (?, ?)", (usuario_id, vuelo_id))
    conexion.commit()
def ver_reservas(usuario_id):
    conexion, cursor = conectar()
    cursor.execute('''SELECT vuelos.origen, vuelos.destino, vuelos.fecha, vuelos.precio 
                      FROM reservas 
                      JOIN vuelos ON reservas.vuelo_id = vuelos.id 
                      WHERE reservas.usuario_id = ?''', (usuario_id,))
    reservas = cursor.fetchall()
    print("Sus reservas:")
    for reserva in reservas:
        print(f"Origen: {reserva[0]}, Destino: {reserva[1]}, Fecha: {reserva[2]}, Precio: {reserva[3]}")
    conexion.close()
def menu_cliente(usuario_id):
    while True:
        print("1. Ver vuelos disponibles")
        print("2. Reservar vuelo")
        print("3. Ver mis reservas")
        print("4. Cerrar sesión")
        opcion = input("Seleccione una opción: ")
        if opcion == '1':
            tabla_vuelos()
        elif opcion == '2':
            reservar_vuelo(usuario_id)
        elif opcion == '3':
            ver_reservas(usuario_id)
        elif opcion == '4':
            print("Cerrando sesión...")
            break
        else:
            print("Opción no válida. Intente nuevamente.")
def menu_admin():
    while True:
        print("1. Registrar nuevo vuelo")
        print("2. Ver vuelos disponibles")
        print("3. Cerrar sesión")
        opcion = input("Seleccione una opción: ")
        if opcion == '1':
            registrar_vuelo()
        elif opcion == '2':
            tabla_vuelos()
        elif opcion == '3':
            print("Cerrando sesión...")
            break
        else:
            print("Opción no válida. Intente nuevamente.")
def crear_admin():
    conexion, cursor = conectar()
    nombre = "" #introduce el nombre del admin que quieras crear, por ejemplo "admin"
    pass_raw = "" #introduce la contraseña del admin que quieras crear, por ejemplo "admin123"
    contraseña = hashlib.sha256(pass_raw.encode()).hexdigest()
    cursor.execute("INSERT OR IGNORE INTO usuarios (nombre, contraseña) VALUES (?, ?)", (nombre, contraseña))
    conexion.commit()
def main():
    crear_tablas()
    crear_admin()  # Crea el usuario admin si no existe
    menu_inicio()

main()