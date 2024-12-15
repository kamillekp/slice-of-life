
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

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.text.TextFlow;

import javafx.scene.text.FontWeight;
import javafx.util.Callback;


public class ReviewController {

	@FXML private VBox tablesVBox;
	@FXML private TextFlow textFlow2;
	@FXML private TableView<Pizza> tableViewListagemPizzas;
	@FXML private TableColumn<Pizza, Void> colPizzas;
	@FXML private TableColumn<Pizza, String> colTamanho;
	@FXML private TableColumn<Pizza, Integer> colNumSabores;
	@FXML private TableColumn<Pizza, Boolean> colBorda;
	@FXML private Button anotherPizzaButton;
	@FXML private Button editButton;

	@FXML private Label priceText;


	private final SharedControl sharedControl = SharedControl.getInstance();
	private final Order order = sharedControl.getOrder();


	@FXML public void initialize() {
		final double TABLES_VERTICAL_SPACING = 20;
		final double TABLES_ROW_HEIGHT = 25;
		final double TABLES_MIN_MARGIN_BOTTOM = 20;
		final int TABLES_MARGIN_BOTTOM_BY_NUM_LINES = 10;

		tablesVBox.setSpacing(TABLES_VERTICAL_SPACING);

		priceText.setText("TOTAL DO PEDIDO: R$ " + String.format("%.2f", order.getPrice()));

		ArrayList<Pizza> pizzas = order.getPizzas();
		createPizzasListTable(pizzas, TABLES_ROW_HEIGHT, TABLES_MARGIN_BOTTOM_BY_NUM_LINES, TABLES_MIN_MARGIN_BOTTOM);

		for (int idPizza = 1; idPizza <= pizzas.size(); idPizza++)
			createPizzaFlavourTable(pizzas.get(idPizza - 1), idPizza, TABLES_ROW_HEIGHT, TABLES_MARGIN_BOTTOM_BY_NUM_LINES, TABLES_MIN_MARGIN_BOTTOM);
		editPersonalData();
		initializeTextFlow();

		anotherPizzaButton.setDisable(pizzas.size() >= 5);

	}

	private void initializeTextFlow() {


		appendToTextFlow(order.getClient().getName() + " " + order.getClient().getSurname() + "\n", false, 20, Pos.CENTER_LEFT);


		Payment payment = order.getClient().getPayment();
		Address adress = order.getClient().getAddress();

		if (payment != null && adress != null) {

			appendToTextFlow( " ".repeat(4) + "Endereço\n", true, 14, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "CEP: " + adress.getZipCode() + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Rua/Avenida " + adress.getStreet() + ", " + adress.getcity() + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Número: " + adress.getNumber() + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Complemento: " + adress.getComplement() + "\n", false, 12, Pos.CENTER_LEFT);


			appendToTextFlow(" ".repeat(4) + "Pagamento\n", true, 14, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "Total do pedido: " + "R$ " + String.format("%.2f", order.getPrice()) + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Tipo: " + payment.getType() + "\n", false, 12, Pos.CENTER_LEFT);

			if (Objects.equals(payment.getType(), "Pix")) {
				appendToTextFlow(" ".repeat(8) + "Código:\nuumDgBX2bVG0MjImljO2GYmnbxIRcX0TX2cr8A93lKp392JG8M4MJyKpPpQsDfEh69NgH3Gfd21HNk", false, 12, Pos.CENTER_LEFT);
			}

		}
	}

	private void createPizzaFlavourTable(Pizza pizza, int contNumPizzas, double rowHeight, int tableMarginBottonByLines, double tableMinMarginBottom){

		VBox pizzaSection = new VBox(5);
		pizzaSection.setAlignment(Pos.CENTER);

		double currentPizzaPrice = order.getPizzas().get(contNumPizzas - 1).getPrice();

		Label pizzaLabel = new Label("Pizza " + contNumPizzas + " - " + "R$ " + String.format("%.2f", currentPizzaPrice));
		pizzaLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16px");

		List<Flavour> savoryFlavours = pizza.getSaltyFlavour();
		List<Flavour> sweetFlavours = pizza.getSugaryFlavour();

		TableView<Flavour> tableSavoryFlavors = createSavoryFlavorsTable(savoryFlavours, 0, rowHeight, tableMarginBottonByLines, tableMinMarginBottom);
		TableView<Flavour> tableSweetFlavors = createSweetFlavorsTable(sweetFlavours, savoryFlavours.size(), rowHeight, tableMarginBottonByLines, tableMinMarginBottom);


		// Nunca deveria acontecer
		if(tableSavoryFlavors == null && tableSweetFlavors == null)
			pizzaSection.getChildren().addAll(pizzaLabel);

		else if (tableSavoryFlavors == null)
			pizzaSection.getChildren().addAll(pizzaLabel, tableSweetFlavors);
		else if(tableSweetFlavors == null)
			pizzaSection.getChildren().addAll(pizzaLabel, tableSavoryFlavors);

		else
			pizzaSection.getChildren().addAll(pizzaLabel, tableSavoryFlavors, tableSweetFlavors);

		tablesVBox.getChildren().add(pizzaSection);

	}

	private TableView<Flavour> createSavoryFlavorsTable(List<Flavour> flavours, int contNumFlavors, double rowHeight, int tableMarginBottonByLines, double tableMinMarginBottom) {

		SaltyMenu salty = new SaltyMenu();

		if(flavours.isEmpty())
			return null;


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


	private TableView<Flavour> createSweetFlavorsTable(List<Flavour> flavours, int contNumFlavors, double rowHeight, int tableMarginBottonByLines, double tableMinMarginBottom) {

		if(flavours.isEmpty())
			return null;

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


	private <T> TableColumn<T, String> createColumn(
			TableView<T> table,
			String header,
			double widthPercentage,
			Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>> cellValueFactory
	) {
		TableColumn<T, String> column = new TableColumn<>(header);
		column.setCellValueFactory(cellValueFactory);
		column.prefWidthProperty().bind(table.widthProperty().multiply(widthPercentage));
		column.setResizable(false);
		column.setReorderable(false);

		table.getColumns().add(column);

		return column;
	}


	private void createPizzasListTable(ArrayList<Pizza> pizzas, double rowHeight, int tableMarginBottonByLines, double tableMinMarginBottom) {
		ObservableList<Pizza> pizzas_list = FXCollections.observableArrayList(pizzas);

		colPizzas.setCellValueFactory(new PropertyValueFactory<Pizza, Void>(""));
		tableViewListagemPizzas.setItems(pizzas_list);


		colPizzas.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : "Sabor " + (getIndex() + 1));
			}
		});

		colBorda.setCellValueFactory(new PropertyValueFactory<Pizza, Boolean>("border"));
		colBorda.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null)
					setText(null);
				else if (item)
					setText("Com borda");
				else
					setText("Sem borda");

			}
		});

		colNumSabores.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("numFlavor"));
		colNumSabores.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(Integer item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null)
					setText(null); // Célula vazia

				else if (item == 1)
					setText("1 sabor");
				else
					setText(item + " sabores");

			}
		});

		colTamanho.setCellValueFactory(new PropertyValueFactory<Pizza, String>("size"));
		colTamanho.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
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

					SceneNavigator.navigateTo("/views/tela2.fxml", "/styles/tela2.css");
				});


				if(order.getPizzas().size() == 1)
					btnExcluir.setDisable(true);
				else
					btnExcluir.setDisable(false);

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
			protected void updateItem(Void item, boolean empty) {
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

	private void editPersonalData(){
		editButton.setOnAction(event -> {
			backToPaymentPage();
		});
	}

	@FXML private void backToPaymentPage() {
		SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
	}

	@FXML private void goToFinalPage() {
		SceneNavigator.navigateTo("/views/Tela6.fxml", "/styles/Tela6.css");
	}

	@FXML private void goCreateNewPizza() {
		sharedControl.resetPizza();
		sharedControl.resetCounter();

		SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
	}



	public Button createButton(double height, String text, String styleClass) {

		Button btnExcluir = new Button(text);


		btnExcluir.setMinHeight(height);
		btnExcluir.setMaxHeight(height);

		double fontSize = height * 0.5;

		btnExcluir.setStyle("-fx-font-size: " + fontSize + "px;");

		btnExcluir.getStyleClass().add(styleClass);

		return btnExcluir;
	}


	public void appendToTextFlow(String texto, boolean isBold, int fontSize, Pos textAlignment) {
		Text textNode = new Text(texto);
		Font font;

		if(isBold)
			font = Font.font("System", FontWeight.BOLD, fontSize);
		else
			font = Font.font("System", FontWeight.NORMAL, fontSize);

		textNode.setFont(font);

		VBox vbox = new VBox();

		vbox.prefWidthProperty().bind(textFlow2.widthProperty().multiply(0.9));
		vbox.setAlignment(textAlignment);

		vbox.getChildren().add(textNode);
		textFlow2.getChildren().add(vbox);
	}
}
