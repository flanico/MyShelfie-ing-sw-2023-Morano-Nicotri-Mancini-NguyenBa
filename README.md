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

Successivamente indicare il numero delle due porte da utilizzare per la comunicazione Socket e RMI (se si vogliono utilizzare le porte di default premere invio). 
Quando appare la scritta "server is running", il server è pronto per accettare connessioni.

### Aprire il client
Per aprire il client, eseguire il seguente comando da terminale aperto nella cartella del Jar:

```java -jar ClientApp.jar```

Inizialmente inserire c/g per eseguire il gioco con l'interfaccia a linea di comando [c] o con l'interfaccia grafica [g] (tutti i sistemi operativi sono supportati).

#### Cli
Inizialmente inserire s/r per scegliere se utilizzare la connessione Socket [s] o RMI [r].
Successivamente inserire l'indirizzo IP del server e la porta del server (premere invio per utilizzare i valori delle porte di default).

#### Gui
Nella pagina della connessione, se si preme il tasto "continue" senza modificare nessun valore, il gioco si connetterà al server Socket/RMI con i valori delle porte di default.

## Tools
Per la realizzazione del progetto sono stati utilizzati i seguenti tools:
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE utilizzato per lo sviluppo del progetto
* [Maven](https://maven.apache.org/) - Dependency Management
* [JavaFX](https://openjfx.io/) - GUI 
* [Scene Builder](https://gluonhq.com/products/scene-builder/) - GUI 
* [draw.io](https://app.diagrams.net/) - Diagrammi UML e Sequence Diagram

## Q&A
### Come posso capire qual è l'effetto delle common goal cards sulla GUI?
Per capire l'effetto delle common goal cards sulla GUI, basta cliccare con il mouse sopra la carta.
### Cosa fare se la CLI non stampa i colori correttamente?
Se la CLI non stampa i colori correttamente, è necessario aprire il CMD con amministratore e digitare il seguente comando:

```REG ADD HKCU\CONSOLE /f /v VirtualTerminalLevel /t REG_DWORD /d 1```
### Cosa fare se la versione di java del mio sistema operativo non è compatibile con quella utilizzata per il progetto?
Per far funzionare correttamente il programma è necessario installare la versione di java 19 o superiore.
Il download si può trovare a questo [link](https://www.oracle.com/it/java/technologies/downloads/).