# My Shelfie - Progetto di Ingegneria del Software 2022/2023 [PSP23]
 ![alt text](./src/main/resources/Graphics/piccola.jpg) 
### Componenti del gruppo
* [Alessandro Mancini](https://github.com/alemancio5)
* [Chiara Thien Thao Nguyen Ba](https://github.com/chiaranb)
* [Flavia Nicotri](https://github.com/flanico)
* [Stefano Morano](https://github.com/stefano-morano)

## Introduzione
MyShelfie è un gioco da tavolo di tipo strategico, in cui i giocatori si sfidano per arredare nel miglior modo possibile la propria libreria.

Il progetto consiste nell'implementazione di un videogioco basato sul gioco da tavolo, il cui server deve essere in grado di gestire partite in connessione Socket e partite in connessione RMI.

MyShelfie può essere giocato in due modalità: da terminale con interfaccia CLI o con interfaccia grafica GUI.

Le regole del gioco si possono trovare in questa [pagina](https://www.craniocreations.it/prodotto/my-shelfie). 

## Funzionalità implementate
| Funzionalità        | Stato |
|:--------------------|:-----:|
| Regole Semplificate |   ✅   |
| Regole Complete     |   ✅   |
| Socket              |   ✅   |
| RMI                 |   ✅   |
| CLI                 |   ✅   |
| GUI                 |   ✅   |
| Chat                |   ✅   |
| Persistenza         |   ✅   |
| Resilienza          |   ❌   |
| Partite Multiple    |   ❌   |

## Come eseguire il programma
Assicurarsi di avere installato Java 19 o superiore nel proprio sistema operativo.

### Aprire il server
Per aprire il server, eseguire il seguente comando da terminale aperto nella cartella del Jar:

```java -jar ServerApp.jar```

Successivamente indicare il numero delle due porte da utilizzare per la comunicazione Socket e RMI ( se si vogliono utilizzare le porte di default premere invio ). 
Quando appare la scritta "server is running", il server è pronto per accettare connessioni.

### Aprire il client per MacOS
Per aprire il client da un computer con sistema operativo MacOS, eseguire il seguente comando da terminale aperto nella cartella del Jar:

```java -jar ClientAppMacOS.jar```

Inizialmente inserire s/r per scegliere se utilizzare la connessione Socket o RMI.

Successivamente inserire l'indirizzo IP del server e la porta scelta lato server, utilizzata per la comunicazione scelta ( premere invio per utilizzare la porta di default).

### Aprire il client per Windows
Per aprire il client da un computer con sistema operativo Windows, eseguire il seguente comando da terminale aperto nella cartella del Jar:

```java -jar ClientAppWindows.jar```

Inizialmente inserire s/r per scegliere se utilizzare la connessione Socket o RMI.

Successivamente inserire l'indirizzo IP del server e la porta scelta lato server, utilizzata per la comunicazione scelta ( premere invio per utilizzare la porta di default).

## Tools
Per la realizzazione del progetto sono stati utilizzati i seguenti tools:
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE utilizzato per lo sviluppo del progetto
* [Maven](https://maven.apache.org/) - Dependency Management
* [JavaFX](https://openjfx.io/) - GUI 
* [Scene Builder](https://gluonhq.com/products/scene-builder/) - GUI 
* [draw.io](https://app.diagrams.net/) - Diagrammi UML e Sequence Diagram

