# Slice of Life (implementação)
**Organização:** <br>
As classes Order, Client, Pizza, Address, Payment, Flavor e Card são classes que cuidam das funções diretamente a ver com as classes que atuam entre si no processo do pedido de pizza. 

Já as controllers são responsáveis por controlar quais métodos de quais classes serão usados quando o usuário estiver em uma interface específica.
As controllers compartilham a classe SharedControl para terem acesso ao pedido de qualquer interface. A ordem de telas corresponde a seguinte ordem de controllers: InitialController, ChoosePizzaController, ChooseFlavorController, PaymentController, ReviewController e FinalController.

As classes Sugary, Salty e PizzaInfo usam como base a classe Pair, que recebe uma opção e um preço, servindo para que, ao querer mudar as opções ofertadas, não seja necessário alterar toda a interface, apenas os vetores que guardam as opções.

