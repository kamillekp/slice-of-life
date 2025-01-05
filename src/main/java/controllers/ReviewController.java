package controllers;

import application.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;


/**
 * Controlador responsável por gerenciar a tela de revisão do pedido, exibindo informações sobre as pizzas,
 * sabores, e dados do cliente e pagamento.
 */
public class ReviewController {

	/** VBox onde as tabelas das pizzas serão adicionadas dinamicamente. */
	@FXML private VBox tablesVBox;

	/** TextFlow para exibir informações adicionais sobre o pedido. */
	@FXML private TextFlow textFlow2;

	/** TableView que exibe as pizzas listadas no pedido. */
	@FXML private TableView<Pizza> tableViewListagemPizzas;

	/** Coluna da tabela que exibe os sabores das pizzas. */
	@FXML private TableColumn<Pizza, Void> colPizzas;

	/** Coluna da tabela que exibe o tamanho das pizzas. */
	@FXML private TableColumn<Pizza, String> colTamanho;

	/** Coluna da tabela que exibe o número de sabores das pizzas. */
	@FXML private TableColumn<Pizza, Integer> colNumSabores;

	/** Coluna da tabela que exibe se a pizza tem borda ou não. */
	@FXML private TableColumn<Pizza, Boolean> colBorda;

	/** Botão para adicionar outra pizza ao pedido. */
	@FXML private Button anotherPizzaButton;

	/** Botão para editar uma pizza existente no pedido. */
	@FXML private Button editButton;

	/** Label que exibe o preço total do pedido. */
	@FXML private Label priceText;

	/** Imagem da pizza exibida na tela. */
	@FXML private ImageView pizzaImage;

	/** Instância compartilhada do controle de dados da aplicação. */
	private final SharedControl sharedControl = SharedControl.getInstance();

	/** Objeto que representa o pedido atual do cliente. */
	private final Order order = sharedControl.getOrder();

	/**
	 * Inicializa a tela de revisão, configurando os dados a serem exibidos.
	 */
	@FXML public void initialize() {
		Image image1 = new Image(getClass().getResourceAsStream("/assets/image1.png"));
		pizzaImage.setImage(image1);

		final double tablesVerticalSpacing = 20;
		final double tablesRowHeight = 25;
		final double tablesMinMarginBottom = 20;
		final int tablesMarginBottomByNumLines = 10;

		final int maxNumberOfPizzas = 5;

		tablesVBox.setSpacing(tablesVerticalSpacing);

		priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", order.getPrice()));

		ArrayList<Pizza> pizzas = order.getPizzas();
		createPizzasListTable(pizzas, tablesRowHeight, tablesMarginBottomByNumLines, tablesMinMarginBottom);

		for (int idPizza = 1; idPizza <= pizzas.size(); idPizza++) {
			createPizzaFlavourTable(
					pizzas.get(idPizza - 1),
					idPizza,
					tablesRowHeight,
					tablesMarginBottomByNumLines,
					tablesMinMarginBottom);
		}

		editPersonalData();
		initializeTextFlow();

		anotherPizzaButton.setDisable(pizzas.size() >= maxNumberOfPizzas);
	}

	/**
	 * Inicializa o TextFlow com as informações do cliente e do pedido.
	 */
	private void initializeTextFlow() {
		appendToTextFlow(order.getClient().getName() + " " + order.getClient().getSurname() + "\n", false, 20, Pos.CENTER_LEFT);

		Payment payment = order.getClient().getPayment();
		Address adress = order.getClient().getAddress();

		if (payment != null && adress != null) {
			appendToTextFlow(
					" ".repeat(4) + "Endereço\n",
					true, 14, Pos.CENTER_LEFT);

			appendToTextFlow(
					" ".repeat(8) + "CEP: " + adress.getZipCode() + "\n",
					false, 12, Pos.CENTER_LEFT);

			appendToTextFlow(
					" ".repeat(8) + "Rua/Avenida " + adress.getStreet() + ", " + adress.getCity() + "\n",
					false, 12, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "Número: " + adress.getNumber() + "\n",
					false, 12, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "Complemento: " + adress.getComplement() + "\n",
					false, 12, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(4) + "Pagamento\n",
					true, 14, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "Total do pedido: " + "R$ " + String.format("%.2f", order.getPrice()) + "\n",
					false, 12, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "Tipo: " + payment.getType() + "\n",
					false, 12, Pos.CENTER_LEFT);

			if (Objects.equals(payment.getType(), "Pix")) {
				appendToTextFlow(" ".repeat(8) + "Código:\nuumDgBX2bVG0MjImljO2GYmnbxIRcX0TX2cr8A93lKp",
						false, 12, Pos.CENTER_LEFT);
			}
		}
	}

	/**
	 * Cria a tabela de sabores das pizzas no pedido.
	 * @param pizza A pizza para a qual a tabela de sabores será criada.
	 * @param contNumPizzas O ID da pizza que está sendo processada.
	 * @param rowHeight A altura das linhas da tabela.
	 * @param tableMarginBottonByLines O valor de margem inferior adicional por linha.
	 * @param tableMinMarginBottom A margem mínima inferior.
	 */
	private void createPizzaFlavourTable(
			final Pizza pizza,
			final int contNumPizzas,
			final double rowHeight,
			final int tableMarginBottonByLines,
			final double tableMinMarginBottom
	) {

		VBox pizzaSection = new VBox(5);
		pizzaSection.setAlignment(Pos.CENTER);

		double currentPizzaPrice = order.getPizzas().get(contNumPizzas - 1).getPrice();

		Label pizzaLabel = new Label("Pizza " + contNumPizzas + " - " + "R$ " + String.format("%.2f", currentPizzaPrice));
		pizzaLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16px");

		List<Flavour> savoryFlavours = pizza.getSaltyFlavours();
		List<Flavour> sweetFlavours = pizza.getSugaryFlavours();

		TableView<Flavour> tableSavoryFlavors =
				createSavoryFlavorsTable(
						savoryFlavours,
						0,
						rowHeight,
						tableMarginBottonByLines,
						tableMinMarginBottom);

		TableView<Flavour> tableSweetFlavors =
				createSweetFlavorsTable(
						sweetFlavours,
						savoryFlavours.size(),
						rowHeight,
						tableMarginBottonByLines,
						tableMinMarginBottom);


		// Nunca deveria acontecer
		if (tableSavoryFlavors == null && tableSweetFlavors == null) {
			pizzaSection.getChildren().addAll(pizzaLabel);
		} else if (tableSavoryFlavors == null) {
			pizzaSection.getChildren().addAll(pizzaLabel, tableSweetFlavors);
		} else if (tableSweetFlavors == null) {
			pizzaSection.getChildren().addAll(pizzaLabel, tableSavoryFlavors);
		} else {
			pizzaSection.getChildren().addAll(pizzaLabel, tableSavoryFlavors, tableSweetFlavors);
		}

		tablesVBox.getChildren().add(pizzaSection);

	}

	/**
	 * Cria a tabela de sabores salgados na interface gráfica.
	 * @param flavours A lista de sabores salgados.
	 * @param contNumFlavors O contador de sabores já exibidos.
	 * @param rowHeight A altura de cada linha na tabela.
	 * @param tableMarginBottonByLines A margem inferior adicional por linha.
	 * @param tableMinMarginBottom A margem inferior mínima da tabela.
	 * @return A tabela criada com os sabores salgados.
	 */
	private TableView<Flavour> createSavoryFlavorsTable(
			final List<Flavour> flavours,
			final int contNumFlavors,
			final double rowHeight,
			final int tableMarginBottonByLines,
			final double tableMinMarginBottom
	) {

		SaltyMenu salty = new SaltyMenu();

		if (flavours.isEmpty()) {
			return null;
		}


		TableView<Flavour> table = new TableView<>();
		table.setItems(FXCollections.observableArrayList(flavours));

		TableColumn<Flavour, String> colNum = createColumn(table, "", 0.15,
				data -> {
					int index = table.getItems().indexOf(data.getValue());
					int displayIndex = index + 1 + contNumFlavors;
					return new SimpleStringProperty("Pizza " + displayIndex);
				}
		);

		colNum.setStyle("-fx-font-weight: bold");

		createColumn(table, "Queijo", 0.21,
				data -> {
					String ingredient = salty.getFirstFromType("cheese", data.getValue().getIngredients());
					return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
				}
		);

		createColumn(table, "Proteína", 0.21,
				data -> {
					String ingredient = salty.getFirstFromType("protein", data.getValue().getIngredients());
					return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
				}
		);

		createColumn(table, "Vegetais", 0.21,
				data -> {
					String ingredient = salty.getFirstFromType("vegetable", data.getValue().getIngredients());
					return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
				}
		);

		createColumn(table, "Folhas", 0.21,
				data -> {
					String ingredient = salty.getFirstFromType("green leaf", data.getValue().getIngredients());
					return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
				}
		);

		int numRows = table.getItems().size();

		table.setMinHeight((rowHeight + tableMarginBottonByLines) * numRows + tableMinMarginBottom);
		table.setSelectionModel(null);

		return table;

	}

	/**
	 * Cria a tabela de sabores doces na interface gráfica.
	 * @param flavours A lista de sabores doces.
	 * @param contNumFlavors O contador de sabores já exibidos.
	 * @param rowHeight A altura de cada linha na tabela.
	 * @param tableMarginBottonByLines A margem inferior adicional por linha.
	 * @param tableMinMarginBottom A margem inferior mínima da tabela.
	 * @return A tabela criada com os sabores doces.
	 */
	private TableView<Flavour> createSweetFlavorsTable(
			final List<Flavour> flavours,
			final int contNumFlavors,
			final double rowHeight,
			final int tableMarginBottonByLines,
			final double tableMinMarginBottom
	) {
		if (flavours.isEmpty()) {
			return null;
		}

		TableView<Flavour> table = new TableView<>();
		table.setItems(FXCollections.observableArrayList(flavours));

		SugaryMenu sugary = new SugaryMenu();

		TableColumn<Flavour, String> colNum = createColumn(table, "", 0.15,
				data -> {
					int index = table.getItems().indexOf(data.getValue());
					int displayIndex = index + 1 + contNumFlavors;
					return new SimpleStringProperty("Sabor " + displayIndex);
				}
		);

		colNum.setStyle("-fx-font-weight: bold");

		createColumn(table, "Cobertura", 0.21,
				data -> {
					String ingredient = sugary.getFirstFromType("topping", data.getValue().getIngredients());
					return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
				}
		);

		createColumn(table, "Fruta", 0.21,
				data -> {
					String ingredient = sugary.getFirstFromType("fruit", data.getValue().getIngredients());
					return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
				}
		);

		createColumn(table, "Condimento", 0.21,
				data -> {
					String ingredient = sugary.getFirstFromType("condiment", data.getValue().getIngredients());
					return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
				}
		);

		createColumn(table, "", 0.208,
				data -> null
		);


		int numRows = table.getItems().size();

		table.setMinHeight((rowHeight + tableMarginBottonByLines) * numRows + tableMinMarginBottom);
		table.setSelectionModel(null);

		return table;
	}

	/**
	 * Cria uma coluna em uma tabela, configurando o título, a largura e a fábrica de valores das células.
	 * @param table A tabela onde a coluna será adicionada.
	 * @param header O título da coluna.
	 * @param widthPercentage A largura da coluna como uma porcentagem da largura da tabela.
	 * @param cellValueFactory A fábrica de valores para as células da coluna.
	 * @param <T> O tipo dos dados da tabela.
	 * @return A coluna criada.
	 */
	private <T> TableColumn<T, String> createColumn(
			final TableView<T> table,
			final String header,
			final double widthPercentage,
			final Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>> cellValueFactory
	) {
		TableColumn<T, String> column = new TableColumn<>(header);
		column.setCellValueFactory(cellValueFactory);
		column.prefWidthProperty().bind(table.widthProperty().multiply(widthPercentage));
		column.setResizable(false);
		column.setReorderable(false);

		table.getColumns().add(column);

		return column;
	}

	/**
	 * Cria a tabela de pizzas que foi feita no pedido.
	 * @param pizzas Lista de pizzas no pedido.
	 * @param rowHeight A altura das linhas da tabela.
	 * @param tableMarginBottonByLines O valor de margem inferior adicional por linha.
	 * @param tableMinMarginBottom A margem mínima inferior.
	 */
	private void createPizzasListTable(
			final ArrayList<Pizza> pizzas,
			final double rowHeight,
			final int tableMarginBottonByLines,
			final double tableMinMarginBottom
	) {
		ObservableList<Pizza> pizzasList = FXCollections.observableArrayList(pizzas);

		colPizzas.setCellValueFactory(new PropertyValueFactory<Pizza, Void>(""));
		tableViewListagemPizzas.setItems(pizzasList);


		colPizzas.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(final Void item, final boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : "Sabor " + (getIndex() + 1));
			}
		});

		colBorda.setCellValueFactory(new PropertyValueFactory<Pizza, Boolean>("border"));
		colBorda.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(final Boolean item, final boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null) {
					setText(null);
				} else if (item) {
					setText("Com borda");
				} else {
					setText("Sem borda");
				}
			}
		});

		colNumSabores.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("numFlavor"));
		colNumSabores.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(final Integer item, final boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null) {
					setText(null); // Célula vazia
				} else if (item == 1) {
					setText("1 sabor");
				} else {
					setText(item + " sabores");
				}
			}
		});

		colTamanho.setCellValueFactory(new PropertyValueFactory<Pizza, String>("size"));
		colTamanho.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(final String item, final boolean empty) {
				super.updateItem(item, empty);
				setText(item);
			}
		});


		// Adicionando a coluna para os botões de exclusão
		TableColumn<Pizza, Void> colExcluir = new TableColumn<>("Excluir");
		colExcluir.setCellFactory(column -> new TableCell<>() {

			private final HBox buttonsBox = new HBox(5);

			private final Button editButton = createButton(rowHeight, "Editar", "editButton");
			private final Button btnExcluir = createButton(rowHeight, "X", "removeButton");

			{

				editButton.setOnAction(event -> {
					Pizza pizza = getTableView().getItems().get(getIndex());
					sharedControl.setPizza(pizza);
					sharedControl.setEditingAddedPizza(true);

					SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
				});


				if (order.getPizzas().size() == 1) {
					btnExcluir.setDisable(true);
				} else {
					btnExcluir.setDisable(false);
				}

				btnExcluir.setOnAction(event -> {
					// Obtém o índice do item na tabela
					Pizza pizza = getTableView().getItems().get(getIndex());

					// Remove do atributo estático de SharedControl
					order.setTotalPrice(order.getPrice() - pizza.getPrice());
					order.getPizzas().remove(pizza);

					// Atualiza a altura da tabela
					SceneNavigator.navigateTo("/views/tela5.fxml", "/styles/tela5.css");
				});

				buttonsBox.getChildren().addAll(editButton, btnExcluir);
			}

			@Override
			protected void updateItem(final Void item, final boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(buttonsBox);
				}
			}

		});

		tableViewListagemPizzas.getColumns().add(colExcluir);
		int numRows = tableViewListagemPizzas.getItems().size();
		tableViewListagemPizzas.setMinHeight((rowHeight + tableMarginBottonByLines) * numRows + tableMinMarginBottom);
		tableViewListagemPizzas.setSelectionModel(null);

	}

	/**
	 * Configura a ação para o botão de editar dados pessoais, redirecionando para a página de pagamento.
	 */
	private void editPersonalData() {
		editButton.setOnAction(event -> {
			backToPaymentPage();
		});
	}

	/**
	 * Navega para a página de pagamento.
	 */
	@FXML private void backToPaymentPage() {
		SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
	}

	/**
	 * Navega para a página final do processo.
	 */
	@FXML private void goToFinalPage() {
		SceneNavigator.navigateTo("/views/Tela6.fxml", "/styles/Tela6.css");
	}

	/**
	 * Cria uma nova pizza e reinicia os controles de pizza e contador.
	 */
	@FXML private void goCreateNewPizza() {
		sharedControl.resetPizza();
		sharedControl.resetCounter();

		SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
	}

	/**
	 * Cria um botão com altura personalizada, texto e estilo de classe CSS.
	 * @param height A altura do botão.
	 * @param text O texto que será exibido no botão.
	 * @param styleClass A classe de estilo CSS a ser adicionada ao botão.
	 * @return O botão criado.
	 */
	public Button createButton(final double height, final String text, final String styleClass) {

		Button button = new Button(text);


		button.setMinHeight(height);
		button.setMaxHeight(height);

		double fontSize = height * 0.5;

		button.setStyle("-fx-font-size: " + fontSize + "px;");

		button.getStyleClass().add(styleClass);

		return button;
	}

	/**
	 * Adiciona texto ao TextFlow com formatação personalizada.
	 * @param text O texto a ser adicionado ao TextFlow.
	 * @param isBold Indica se o texto deve ser em negrito.
	 * @param fontSize O tamanho da fonte.
	 * @param textAlignment O alinhamento do texto.
	 */
	public void appendToTextFlow(final String text, final boolean isBold, final int fontSize, final Pos textAlignment) {
		Text textNode = new Text(text);
		Font font;

		if (isBold) {
			font = Font.font("System", FontWeight.BOLD, fontSize);
		} else {
			font = Font.font("System", FontWeight.NORMAL, fontSize);
		}

		textNode.setFont(font);

		VBox vbox = new VBox();

		vbox.prefWidthProperty().bind(textFlow2.widthProperty().multiply(0.9));
		vbox.setAlignment(textAlignment);

		vbox.getChildren().add(textNode);
		textFlow2.getChildren().add(vbox);
	}
}
