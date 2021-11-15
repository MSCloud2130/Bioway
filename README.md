# Bioway

## Autores
- Camilo Buitrago
- Santiago Caroprese
- Juan Francisco Hamón
- Daniel Hernández
- Juan Carlos Suárez

## Descripción del diseño

La descripción de los elementos de diseño del sistema está incluida en la wiki del repositorio.

## Instrucciones de uso

### 1. Iniciar servicios

#### 1. Iniciar contendores
Ubíquese en la carpeta Bioway/Docker y ejecute el siguiente comando

> docker-compose up --build

#### 2. Configurar Gitea
1. Acceda desde el navegador a la URL http://localhost:3000
2. Cree una cuenta.
3. Cree un nuevo repositorio.
4. Suba al repositorio los contenidos de este repositorio.
5. Ejecute los siguientes comandos en la terminal:
> sudo docker exec -it jenkins bash
> 
> cd
> 
> cat .ssh/id_rsa.pub
6. Copie la cadena de caracteres que imprime el último comando y agruéguela a la cuenta de Gitea como clave SSH.

#### 3. Configurar Jenkins
1. Acceda desde el navegador a la URL http://localhost:8090
2. Cree un nuevo pipeline.
3. Vincule el pipeline al repositorio de Gitea.
4. Ejecute el pipeline.

Una vez termine de ejecutarse el pipeline correctamente, todos los microservicios estarán desplegados en sus respectivos contenedores.

### 2. Cliente Postman

Desde la sección de colecciones de Postman, se deben seguir las siguientes instrucciones:

1. Seleccionar > import > file Bioway upload file.
2. Se debe ir al directorio Bioway/Cliente.
3. Seleccionar el archivo Bioway.postman_collection.json y abrirlo.
4. Se abrirá la colección de Endpoints del proyecto. Estos están organizados por nivel de autenticación y por microservicio.
5. Para ejecutar un endpoint, selecciónelo y posteriormente haga click en send, a la derecha de la ventana.
6. Si el endpoint tiene valores variables en su dirección, puede cambiarlos libremente.
7. En el menu central del endpoint, hay una sección de body, si el endpoint aplica, puede cambiar libremente los valores del body. Esto es siempre y cuando se siga el formato de ejemplo predeterminado.

**NOTA**: Para obtener autorización para usar los enpoints que lo requieren, se debe usar el endpoint "login", con credenciales de un usuario válido en la base de datos del microservicio identity. Dependiendo de las credenciales del usuario utilizado, el sistema revisa el tipo de usuario y asigna un token con los permisos específicos para ese tipo de usuario. Tras realizar el inicio de sesión exitosamente se retorna el id del usuario y el token de seguridad. Para usar dicho token se puede acceder a las carpetas correspondientes a la autenticación necesaria o a los endpoints específicos, y en la sección de autorización se debe seleccionar la opción "Bearer Token", lo que habilita un espacio donde se debe pegar el token retornado por el inicio de sesión. Tambien es posible obtener dicho token al registrar un nuevo usuario. 
