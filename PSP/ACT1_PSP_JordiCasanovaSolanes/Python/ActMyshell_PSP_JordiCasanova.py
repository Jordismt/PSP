import subprocess

print("Bienvenido a MyShell. Ingresa 'exit' para salir.")

def execute_command(user_input):
    try:
        # Ejecutar el comando y redirigir la salida a la consola
        result = subprocess.run(user_input, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        
        # Mostrar la salida estándar y los errores
        if result.stdout:
            print("Salida estándar:")
            print(result.stdout)
        if result.stderr:
            print("Errores:")
            print(result.stderr)
        
        # Mostrar el código de salida
        print(f"Código de salida: {result.returncode}")
    except Exception as e:
        print(f"Error al ejecutar el comando: {str(e)}")




while True:
    # Mostrar el prompt de MyShell
    user_input = input("MyShell> ")
    
    # Salir de MyShell si el usuario ingresa "exit"
    if user_input.lower() == "exit":
        break
    
    # Ejecutar el comando ingresado por el usuario
    execute_command(user_input)


