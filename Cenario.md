# Ferramenta distribuída para quebra de senha

Neste projeto foi desenvovido uma solução distribuída de quebra de senhas utilzando o software [John The Ripper](https://www.openwall.com/john).
A solução é composta por uma máquina mestre que repassa a tarefa de quebra de senhas para 3 ou mais máquinas trabalhadoras, sendo que ao finalizarem a tarefa retornam os resultados obtidos para a máquina mestre.

### Definições de projeto

O projeto foi desevolvido com RMI, sendo que o **serviço de registry** é iniciado dentro do programa da máquina Master. 
Tanto o código da máquina master como o código das máquinas trabalhadoras foram desenvolvidas em Java seguindo a seguinte hierarquia de diretórios.

**Master**:
```
STD29006
  |_Master
  |  |_Gerenciador.java
  |  |_Master.java
  |  |_Menu.java
  |  |_Notificacao.java
  |_NotificacaoDistribuida.java
  |_Status.java
  |_TrabalhadorDistribuido.java

```
**Trabalhador**:
```
STD29006
  |_Trabalhador
  |  |_Cracker.java
  |  |_Principal.java
  |  |_Trabalhador.java
  |_NotificacaoDistribuida.java
  |_Status.java
  |_TrabalhadorDistribuido.java
```
### Máquina Master

Utilizando o Gradle para gerar o jar do projeto será criado um arquivo .jar no subdiretório do projeto `build/libs` com o nome
`std-1.0.jar`

Os seguintes parâmetros deverão ser passados por linha de comando:
* nomeServidor : IP da máquina onde está sendo executado o registry
* porta: a porta utilizada pelo registry

A execução do código deverá ser realizada da seguinte forma:
```
java -jar std-1.0.jar nomeServidor porta
```
Exemplo:
```
java -jar std-1.0.jar 127.0.0.1 12345
```


Caso os parâmetros nomeServidor e porta não forem passados os seguintes valores serão utilizados como default:

* nomeServidor : 127.0.0.1
* porta: 1099

### Máquina Trabalhador

Semelhante ao master, no subdiretório do projeto `build/libs` será criado um arquivo com o nome `trabalhador-1.0.jar` ao utilizar o Gradle para obter o jar.

Os seguintes parâmetros deverão ser passados por linha de comando:
* nomeServidor : IP da máquina onde está sendo executado o registry
* porta: a porta utilizada pelo registry

A execução do código deverá ser realizada da seguinte forma:
```
java -jar trabalhador-1.0.jar nomeServidor porta
```
Exemplo:
```
java -jar trabalhador-1.0.jar 127.0.0.1 12345
```
Caso os parâmetros nomeServidor e porta não forem passados os seguintes valores serão utilizados como default:

* nomeServidor : 127.0.0.1
* porta: 1099
