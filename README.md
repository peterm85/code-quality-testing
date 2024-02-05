# Calidad, tests y gestión de deuda técnica


## ¿Qué vamos a ver?

A lo largo de mi carrera profesional trabajar en diferentes proyectos me han proporcionado una visión clara de qué es básico y qué no para que un proyecto y un equipo funcione tecnicamente bien. 

A continuación y a modo de recopilatorio iré desgranando punto a punto desde una perspectiva de desarrollo:

## <a name="index">Índice</a>

- [Organización del código](#code-organization)
- [Nomenclaturas](#code-naming)
- [Formato del código](#code-format)
- [Test unitarios](#unit-tests)
    * Json Unit test vs Instancio
    * Jacoco pluging % coverage
    * Test de mutación
    * Test threadSafe
- [Test integración](#integration-tests)
    * Dependencias Embebido
    * Test-container
- [Test e2e](#e2e-tests)
    * Postman
- [Análisis estático](#static-analysis)
- [Seguridad en las dependencias](#dependency-security)
- [Equipo, equipo y equipo](#team)

### Requisitos
- Hardware: Intel Core i7, 16Gb RAM
- Docker v19.03.5?
- Spring Boot 3
- SonarQube

## <a name="code-organization">Organización del código</a> [&#8593;](#index)

La organización del código desde mi punto de vista es algo fundamental en todo proyecto. Proporciona orden, coherencia y facilidad de búsqueda a la hora de encontrar algún fichero.
Una estrategia con la que he visto grandes resultados es la seguida por la arquitectura hexagonal: separación por capas ( puertos - adaptadores - aplicación - dominio ). Saber en cual de estos niveles se está realizando una modificación de código es vital para que un proyecto no desvaríe al cabo del tiempo. La peor sensación como desarrollador es sentir que trabajas en el caos.

## <a name="code-naming">Nomenclaturas</a> [&#8593;](#index)

Otra de las premisas se trata de cómo nombrar clases, variables, etc. Para ello puede ayudar seguir estándares como por ejemplo el de [Oracle](#https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html).

## <a name="code-format">Formato del código</a> [&#8593;](#index)

Aunque no es algo fundamental, resulta de gran ayuda mantener un mismo formato en todo el código redactado (espaciado, máximo de caracteres por linea, ordenación de imports, etc.). Para ello existen diferentes formas de llevarlo a cabo: individualmente mediante configuración de cada IDE (aunque es posible que alinear IDEs de diferente tipo resulte complicado) o mediante algún plugin de maven (que resulte genérico para todos). En este proyecto se ha utilizado el plugin `googleformatter-maven-plugin` basado en un estandar de Google que permite ajustar estos aspectos una vez se lanza `mvn install`, pero sin cambiar nada del código productivo.

## <a name="unit-tests">Test unitarios</a> [&#8593;](#index)

<img src="doc/tests.jpg" alt="Tests everywhere"/>

No hace falta decir que algo fundamental en un proyecto software es que éste sea testeable. Y cuando me refiero a esto, quiero decir de forma automática.

No es la primera vez que trabajo en un proyecto en el que no se tienen tests automáticos o estos son muy escasos. El resultado es desperdiciar infinidad de horas probando manualmente y de forma recurrente una funcionalidad que se desarrolló hace meses cada vez que se va a subir un cambio a producción o, peor que eso, descubrir que un error que ya corregiste vuelve a reproducirse. Literalmente he visto caer proyectos enteros por esta razón. Las prisas por subir nuevas funcionalidades y la presión de algunos stakeholders iluminados que no le dan importancia a este tema provocan que al final no se realicen tests, sean pobres o de mala calidad. Sin embargo, es nuestra responsabilidad como desarrolladores (y no digo que sea fácil) pelear para hacer las cosas bien. Y esto debería cumplirse independientemente que tengamos o no disponible compañeros especializados de QA. La entrega de un código no debería ser dependiente a otros. Los desarrolladores debemos estar seguros que el código que entregamos funciona correctamente.

Para ello, tener un framework que agilice la generación de tests unitarios es muy útil para mantener esta dinámica. Además de tecnologías conocidas como JUnit y Mockito voy a mencionar dos sistemas de generar datos de prueba que me han beneficiado mucho:

### Instancio

Se trata de una librería que genera aleatoriamente datos de nuestros POJOs/DTOs de cara a realizar tests cuando no nos importa mucho el contenido de los mismos.

### Json unit test

Este otro caso es un método que se apoya en librerías de Json para generar objetos. Es muy útil cuando tenemos que tener controlados los datos que vamos a utilizar, reduce drásticamente el código necesario para preparar el tests y permite realizar comparaciones sobre un objeto esperado.

### Jacoco pluging % coverage

Este plugin de maven nos ayuda a no olvidarnos de realizar tests unitarios. Una vez lanzado el comando `mvn install` detecta el nivel de cobertura sobre nuevo código y lanza una alerta si no llegamos al mínimo que hemos estipulado.

<img src="doc/jacoco_plugin_before.png" alt="Jacoco before"/>

### Test de mutación

Una de las debilidades más importantes de un test unitario es que al cambiar algo en el código, el test siga pasando sin más. Algo debería avisarnos de que el comportamiento ha sido modificado, ¿no creeis?.
Por ello existen herramientas como los test de mutación que nos ayudan a desarrollar tests más robustos.

`mvn -DwithHistory test-compile org.pitest:pitest-maven:mutationCoverage`

En el informe que se genera al lanzar el pluging de maven se indica cómo de buenos son nuestros tests, su robustez y, en caso de que haga falta, qué nos faltaría chequear.

`/target/pit-reports/index.html`

<img src="doc/pitest_before.png" alt="Jacoco before"/>

### Test threadSafe

PENDIENTE

## <a name="integration-tests">Test de integración</a> [&#8593;](#index)

### Dependencias Embebido

### Test-container

## <a name="e2e-tests">Test e2e</a> [&#8593;](#index)

## <a name="static-analysis">Análisis estático</a> [&#8593;](#index)

Una herramienta muy útil a la hora de desarrollar es un analizador estático. Éste sirve de dashboard para dar visibilidad sobre métricas de smells, vulnerabilidades, cobertura, código duplicado, etc. En caso de que el CI/CD de tu proyecto no lo tenga aún integrado es posible tenerlo con reglas default sobre un contenedor docker.

Al lanzar el siguiente comando maven enviaremos el reporte al servidor.

`mvn sonar:sonar -Dsonar.user=admin -Dsonar.password=admin`

http://localhost:9000 -> U: admin - P: admin

<img src="doc/sonar_before.png" alt="SonarQube before"/>

## <a name="dependency-security">Seguridad en las dependencias</a> [&#8593;](#index)

## <a name="team">Equipo, equipo y equipo</a> [&#8593;](#index)

Ninguno de estos puntos anteriores tiene sentido si cada miembro del equipo trabaja de manera independiente. Llegar a acuerdos para trabajar uniformemente es crítico para un proyecto. De lo contrario podremos encontrarnos con Frankensteins en los que cada fichero está hecho de una forma diferente. Esto además ralentiza los desarrollos, la búsqueda de errores y su mantenimiento.
Por lo tanto, una vez se componga el equipo o cuando un nuevo miembro se integre al mismo deberá dejarse claro cuales son las normas internas a seguir. Si es necesario discutir alguna que resulte controvertida siempre es mejor que encontrarnos sorpresas o futuras discursiones.
