# Slice of Life (trabalho de tcp)
**Etapa 0:** documento que descreve o projeto, seu escopo e o funcionamento inicial planejado. <br>
**Etapa 1:** documento com requisitos, interface e descrição de classes. <br>
**Etapa 2:** implementação do projeto idealizado nas etapas anteriores.

**Organização para a etapa 2:**
As classes Order, Client, Pizza, Address, Payment, Flavor e Card são classes que cuidam das funções diretamente a ver com as classes que atuam entre si no processo do pedido de pizza.

Já as controllers são responsáveis por controlar quais métodos de quais classes srão usados quando o usuário estiver em uma interface específica. As controllers compartilham a classe SharedControl para terem acesso ao pedido de qualquer interface. A ordem de telas corresponde a seguinte ordem de controllers: InitialController, ChoosePizzaController, ChooseFlavorController, PaymentController, ReviewController e FinalController.

As classes Sugary, Salty e PizzaInfo usam como base a classe Pair, que recebe uma opção e um preço, servindo para que, ao querer mudar as opções ofertadas, não seja necessário alterar toda a interface, apenas os vetores que guardam as opções.

