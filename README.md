# Bioway

## Instrucciones de uso

A continuacion se detallan los comandos que se deben ejecutar en consola para ejecutar todos los componentes del proyecto.

Para cada componente, se debe usar una consola de comandos separada.

### 1. Iniciar servicios
A. Servicio SOAP ProductSearch

Ubicarse en el directorio  BIOWAY/Source/ProductSearch

Windows:

> mvn clean install
> ./mvnw clean package
> java -jar --illegal-access=permit target/ProductSearch-0.0.1-SNAPSHOT.jar

Linux: 

> mvn clean install
> mvn spring-boot:run

A continuación se mostraran todos los directorios en los que se debe estar ubicado en la consola de comandos, para correr cada servicio. Cuando se este en el directorio indicado, se debe ejecutar los siguientes comandos:

> mvn clean install
> mvn spring-boot:run

B. Servicio Products: BIOWAY/Source/products

C. Servicio Customers: BIOWAY/Source/customers

D. Servicio Purchases: BIOWAY/Source/purchases

E. Servicio Carts: BIOWAY/Source/carts

F. Servicio Identity: BIOWAY/Source/identity

G. Servicio Suppliers:  BIOWAY/Source/suppliers

H. Servicio Payment:  BIOWAY/Source/payments

I. EurekaServer: BIOWAY/Source/eurekaserver

J. API Gateway: BIOWAY/Source/apigateway

### 2. Cliente Postman

Desde la sección de colecciones de Postman, se deben seguir las siguientes instrucciones:

1. Seleccionar > import > file > upload file.
2. Se debe ir al directorio BIOWAY/Cliente.
3. Seleccionar el archivo Bioway.postman_collection.json y darle a abrir.
4. Se abrira la colección de Endpoints del proyecto. Estos estan organizados por microservicio.
5. Para ejecutar un endpoint, seleccionelo y posteriormente haga click en send, a la derecha de la ventana.
6. Si el endpoint tiene valores variables en su dirección, puede cambiarlos libremente.
7. En el menu central del endpoint, hay una sección de body, si el endpoint aplica, puede cambiar libremente los valores del body. Esto es siempre y cuando se siga el formato de ejemplo predeterminado.



