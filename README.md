# Calidad, tests y gestión de deuda técnica


## ¿Qué vamos a ver?

A lo largo de mi carrera profesional trabajar en diferentes proyectos me han proporcionado una visión clara de qué es básico y qué no para que un proyecto y un equipo funcione tecnicamente bien. 

A continuación y a modo de recopilatorio iré desgranando punto a punto.

## <a name="index">Índice</a>

- [Organización del código](#code-organization)
- [Nomenclaturas](#code-naming)
- [Formato del código](#code-format)
- [Test unitarios](#unit-tests)
    * Json Unit test vs Instancio
    * Jacoco pluging % coverage
    * Test de mutación
    * Test threadSafe
- [Test integración](#int-tests)
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

Otra de las premisas se trata de cómo nombrar clases, variables, etc. Para ello puede ayudar seguir standards como por ejemplo el de [Oracle](#https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html).

## <a name="code-format">Formato del código</a> [&#8593;](#index)

Aunque no es algo fundamental, resulta de gran ayuda mantener un mismo formato en todo el código redactado (espaciado, máximo de caracteres por linea, ordenación de imports, etc.). Para ello existen diferentes formas de llevarlo a cabo: individualmente mediante configuración de cada IDE (aunque es posible que alinear IDEs de diferente tipo resulte complicado) o mediante algún plugin de maven (que resulte genérico para todos). En este proyecto se ha utilizado un plugin basado en un standard de Google que permite ajustar estos aspectos una vez se lanza `mvn install`, pero no cambia nada del código operativo.

## <a name="unit-tests">Test unitarios</a> [&#8593;](#index)

<img src="doc/tests.jpg" alt="Tests everywhere"/>



### Json Unit test vs Instancio

### Jacoco pluging % coverage


<img src="doc/jacoco_plugin_before.png" alt="Jacoco before"/>

`target/site/jacoco/index.html`
<img src="doc/jacoco_before.png" alt="Jacoco before"/>

### Test de mutación

`mvn -DwithHistory test-compile org.pitest:pitest-maven:mutationCoverage`
`/target/pit-reports/index.html`

<img src="doc/pitest_before.png" alt="Jacoco before"/>

### Test threadSafe

## <a name="int-tests">Test de integración</a> [&#8593;](#index)

### Dependencias Embebido

### Test-container

## <a name="e2e-tests">Test e2e</a> [&#8593;](#index)

## <a name="static-analysis">Análisis estático</a> [&#8593;](#index)

<img src="doc/sonar_before.png" alt="SonarQube before"/>

`mvn sonar:sonar -Dsonar.token=sqa_291373ce7a6937b9c5ba407ab3946d1b5b3efcdc`
localhost:9000 -> admin - admin

## <a name="dependency-security">Seguridad en las dependencias</a> [&#8593;](#index)

## <a name="team">Equipo, equipo y equipo</a> [&#8593;](#index)

Ninguno de estos puntos comentados tiene sentido si cada miembro del equipo funciona de manera independiente. Llegar a acuerdos para trabajar de una manera uniforme es crítico en un proyecto. De lo contrario podremos encontrarnos con Frankensteins en los que cada fichero está hecho de una forma diferente. Esto ralentiza los desarrollos, la búsqueda de errores y su mantenimiento.
Por lo tanto, una vez se componga el equipo o cuando un nuevo miembro se integre al mismo deberá dejarse claro cuales son las normas internas a seguir. Si es necesario discutir alguna que resulte controvertida siempre es mejor que encontrarnos sorpresas o futuras discursiones.
