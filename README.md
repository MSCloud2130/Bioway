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

**NOTA:** Los servicios utilizan MongoDB. Por lo tanto, antes de ejecutar los servicios, es necesario iniciar una instancia de MongoDB en localhost:27017.

A continuación, se detallan los comandos que se deben ejecutar en consola para ejecutar cada uno de los componentes del proyecto. Para cada componente, se debe usar una consola de comandos separada. Inicie los servicios en el orden indicado:

#### 1. Servidor eureka
Ubíquese en la carpeta Bioway/Source/eurekaserver y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 2. Servicio SOAP ProductSearch:

Ubíquese en la carpeta Bioway/Source/ProductSearch y ejecute los siguientes comandos

##### Windows:

> mvn clean install
> 
> ./mvnw clean package
> 
> java -jar --illegal-access=permit target/ProductSearch-0.0.1-SNAPSHOT.jar

##### Linux: 

> mvn clean install
> 
> mvn spring-boot:run

#### 3. Servicio Products

Ubíquese en la carpeta Bioway/Source/products y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 4. Servicio Customers

Ubíquese en la carpeta Bioway/Source/customers y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 5. Servicio Purchases

Ubíquese en la carpeta Bioway/Source/purchases y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 6. Servicio Carts

Ubíquese en la carpeta Bioway/Source/carts y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 7. Servicio Identity

Ubíquese en la carpeta Bioway/Source/identity y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 8. Servicio Suppliers

Ubíquese en la carpeta Bioway/Source/suppliers y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 9. Servicio Payment

Ubíquese en la carpeta Bioway/Source/payments y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run

#### 10. API Gateway

Ubíquese en la carpeta Bioway/Source/apigateway y ejecute los siguientes comandos:

> mvn clean install
> 
> mvn spring-boot:run



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
