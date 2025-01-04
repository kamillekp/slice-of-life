# Slice of Life (trabalho de tcp)
**Etapa 0:** documento que descreve o projeto, seu escopo e o funcionamento inicial planejado. <br>
**Etapa 1:** documento com requisitos, interface e descrição de classes. <br>
**Etapa 2:** implementação do projeto idealizado nas etapas anteriores.

## Organização para a etapa 2:

As classes Order, Client, Pizza, Address, Payment, Flavor e Card são classes que cuidam das funções diretamente a ver com as classes que atuam entre si no processo do pedido de pizza.

Já as controllers são responsáveis por controlar quais métodos de quais classes srão usados quando o usuário estiver em uma interface específica. As controllers compartilham a classe SharedControl para terem acesso ao pedido de qualquer interface. A ordem de telas corresponde a seguinte ordem de controllers: InitialController, ChoosePizzaController, ChooseFlavorController, PaymentController, ReviewController e FinalController.

As classes Sugary, Salty e PizzaInfo usam como base a classe Pair, que recebe uma opção e um preço, servindo para que, ao querer mudar as opções ofertadas, não seja necessário alterar toda a interface, apenas os vetores que guardam as opções.

## Organização para a etapa 3:

Foi implementado um pipeline CI/CD utilizando o GitHub Actions. A build, a análise e o empacotamento do código são realizados automaticamente a cada push na branch main ou pull request dela. O processo de build do projeto é feito de forma automatizada através do Maven, garantindo que todas as dependências sejam resolvidas e que o projeto seja compilado corretamente antes de ser empacotado.

## Como rodar a aplicação?
**1)** Antes de rodar a aplicação, certifique-se de ter os seguintes requisitos instalados:

[Java JDK 23](https://www.oracle.com/java/technologies/downloads/)

[Maven](https://maven.apache.org/download.cgi)

Depois de clonar o repositório, tendo os requisitos instalados e estando no diretório do projeto, execute o seguinte comando para instalar as dependências do projeto e compilá-lo:

`mvn clean install -U`

O projeto será compilado, todos os testes e a análise do código já serão executados

A partir desse momento já é possível rodar a aplicação com o comando:

`mvn javafx:run`

**2)** Alternativamente, é possível empacotar a aplicação para criar seu próprio jar. Para isso é necessário ter o Maven e o JDK 23, então usar o seguinte comando:

`mvn clean package`

Isso vai gerar novamente o arquivo "slice-of-life-1.0-SNAPSHOT.one-jar.jar" na pasta "target", que pode ser executado com:

`java -jar target/slice-of-life-1.0-SNAPSHOT.one-jar.jar`

**3)** Se quiser apenas o executável .jar, é possível baixá-lo na aba "Actions" do repositório. Nela, será possível ver todos os workflows executados. Clique no último que foi completado com sucesso (marcado com ✔). Na parte de Artifacts, baixe o arquivo "javafx-application". Descompacte o arquivo e execute:

`java -jar slice-of-life-1.0-SNAPSHOT.one-jar.jar`
