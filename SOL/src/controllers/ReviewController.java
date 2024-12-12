
package controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import application.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.text.TextFlow;

import javafx.scene.text.FontWeight;


public class ReviewController {

    @FXML private VBox tablesVBox;
    @FXML private TextFlow textFlow2;
    @FXML private TableView<Pizza> tableViewListagemPizzas;
    @FXML private TableColumn<Pizza, Void> colPizzas;
    @FXML private TableColumn<Pizza, String> colTamanho;
    @FXML private TableColumn<Pizza, Integer> colNumSabores;
    @FXML private TableColumn<Pizza, Boolean> colBorda;
	@FXML private Button anotherPizzaButton;

    @FXML public void initialize() {
		tablesVBox.setSpacing(20);
		double rowHeight = 25.0;
		ArrayList<Pizza> pizzas = SharedControl.getInstance().getOrder().getPizzas();


		createPizzasListTable(pizzas, rowHeight);

		int idPizza = 1;

		for (Pizza pizza : pizzas) {
			createPizzaFlavourTable(pizza, idPizza, rowHeight);
			idPizza++;
		}

		initializeTextFlow(textFlow2);

		if(SharedControl.getInstance().getOrder().getPizzas().size() >= 5)
			anotherPizzaButton.setDisable(true);
		else
			anotherPizzaButton.setDisable(false);


	}


	private void initializeTextFlow(TextFlow textFlow) {
		appendToTextFlow("Dados Pessoais\n", true, 15, Pos.CENTER);

		appendToTextFlow(SharedControl.getInstance().getOrder().getClient().getName() + " " + SharedControl.getInstance().getOrder().getClient().getSurname() + "\n", false, 20, Pos.CENTER);


		Payment payment = SharedControl.getInstance().getOrder().getClient().getPayment();
		Address adress = SharedControl.getInstance().getOrder().getClient().getAddress();

		if (payment != null && adress != null) {

			appendToTextFlow( " ".repeat(4) + "Endereço\n", true, 14, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "CEP: " + adress.getZipCode() + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Rua/Avenida " + adress.getStreet() + ", " + adress.getcity() + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Número: " + adress.getNumber() + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Complemento: " + adress.getComplement() + "\n", false, 12, Pos.CENTER_LEFT);


			appendToTextFlow(" ".repeat(4) + "Pagamento\n", true, 14, Pos.CENTER_LEFT);

			appendToTextFlow(" ".repeat(8) + "Total do pedido: " + "R$ " + String.format("%.2f", SharedControl.getInstance().getOrder().getPrice()) + "\n", false, 12, Pos.CENTER_LEFT);
			appendToTextFlow(" ".repeat(8) + "Tipo: " + payment.getType() + "\n", false, 12, Pos.CENTER_LEFT);

			if (Objects.equals(payment.getType(), "PIX")) {
				appendToTextFlow(" ".repeat(8) + "Código:\nuumDgBX2bVG0MjImljO2GYmnbxIRcX0TX2cr8A93lKp392JG8M4MJyKpPpQsDfEh69NgH3Gfd21HNk", false, 12, Pos.CENTER_LEFT);
			}

		}
	}

	private void createPizzaFlavourTable(Pizza pizza, int contNumPizzas, double rowHeight){

			VBox pizzaSection = new VBox(5);
			pizzaSection.setAlignment(Pos.CENTER);

			double currentPizzaPrice = SharedControl.getInstance().getOrder().getPizzas().get(contNumPizzas - 1).getPrice();

			Label pizzaLabel = new Label("Pizza " + contNumPizzas + " - " + "R$ " + String.format("%.2f", currentPizzaPrice));
			pizzaLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16px");

			List<Flavour> savoryFlavours = pizza.getSaboresSalgados();
			List<Flavour> sweetFlavours = pizza.getSaboresDoces();

			TableView<Flavour> tableSavoryFlavors = createSavoryFlavorsTable(savoryFlavours, 0, rowHeight);
			TableView<Flavour> tableSweetFlavors = createSweetFlavorsTable(sweetFlavours, savoryFlavours.size(), rowHeight);


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


  
    private TableView<Flavour> createSavoryFlavorsTable(List<Flavour> flavours, int contNumFlavors, double rowHeight) {

		Salty salty = new Salty();

		if(flavours.isEmpty())
			return null;

    	TableColumn<Flavour, Void> colNum = new TableColumn<>();
    	colNum.setCellValueFactory(new PropertyValueFactory<Flavour, Void>(""));

		colNum.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : "Sabor " + (getIndex() + 1 + contNumFlavors));
			}
		});

    	  
        TableView<Flavour> table = new TableView<>();
        colNum.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
         
        colNum.setStyle("-fx-font-weight: bold");
        colNum.setResizable(false);
        
        table.getColumns().add(colNum);
        
        
        table.setItems(FXCollections.observableArrayList(flavours));

        
        TableColumn<Flavour, String> cheeseCol = new TableColumn<>("Queijo");
        cheeseCol.setCellValueFactory(data -> {
			String ingredient = salty.getFirstFromType("cheese",  data.getValue().getIngredients());

			return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
		});
        
        cheeseCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        cheeseCol.setResizable(false);
        
        table.getColumns().add(cheeseCol);

        TableColumn<Flavour, String> proteinCol = new TableColumn<>("Proteína");
        proteinCol.setCellValueFactory(data -> {
			String ingredient = salty.getFirstFromType("protein",  data.getValue().getIngredients());

			return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
		});
        proteinCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        proteinCol.setResizable(false);
        
        table.getColumns().add(proteinCol);
        	
        TableColumn<Flavour, String> vegetablesCol = new TableColumn<>("Vegetais");
        vegetablesCol.setCellValueFactory(data -> {
			String ingredient = salty.getFirstFromType("vegetable",  data.getValue().getIngredients());

			return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
		});
        vegetablesCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        vegetablesCol.setResizable(false);
        
        table.getColumns().add(vegetablesCol);
        		
        TableColumn<Flavour, String> leafyCol = new TableColumn<>("Folhas");
        leafyCol.setCellValueFactory(data -> {
			String ingredient = salty.getFirstFromType("green leaf",  data.getValue().getIngredients());

			return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
		});
        leafyCol.prefWidthProperty().bind(table.widthProperty().multiply(0.208));
        leafyCol.setResizable(false);
        
        table.getColumns().add(leafyCol);

		int numRows = table.getItems().size();
		final int MARGIN_BOTTOM_BY_NUM_LINES = 10;
		final int MIN_MARGIN_BOTTOM = 20;

		table.setMinHeight((rowHeight + MARGIN_BOTTOM_BY_NUM_LINES) * numRows + MIN_MARGIN_BOTTOM);
		table.setSelectionModel(null);




        return table; 
         
    }



	private TableView<Flavour> createSweetFlavorsTable(List<Flavour> flavours, int contNumFlavors, double rowHeight) {

		if(flavours.isEmpty())
			return null;

		TableView<Flavour> table = new TableView<>();
		Sugary sugary = new Sugary();



		TableColumn<Flavour, Void> colNum = new TableColumn<>();
		colNum.setCellValueFactory(new PropertyValueFactory<Flavour, Void>(""));


		colNum.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : "Sabor " + (getIndex() + 1 + contNumFlavors));
			}
		});


		colNum.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		colNum.setStyle("-fx-font-weight: bold");
		colNum.setResizable(false);


		table.getColumns().add(colNum);


		table.setItems(FXCollections.observableArrayList(flavours));

		TableColumn<Flavour, String> toppingCol = new TableColumn<>("Cobertura");
		toppingCol.setCellValueFactory(data -> {
			String ingredient = sugary.getFirstFromType("topping",  data.getValue().getIngredients());

			return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
		});

		toppingCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
		toppingCol.setResizable(false);

		table.getColumns().add(toppingCol);

		TableColumn<Flavour, String> fruitCol = new TableColumn<>("Fruta");
		fruitCol.setCellValueFactory(data -> {
			String ingredient = sugary.getFirstFromType("fruit",  data.getValue().getIngredients());
			return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
		});

		fruitCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
		fruitCol.setResizable(false);

		table.getColumns().add(fruitCol);

		TableColumn<Flavour, String> condimentCol = new TableColumn<>("Condimento");
		condimentCol.setCellValueFactory(data -> {
			String ingredient = sugary.getFirstFromType("condiment",  data.getValue().getIngredients());
			return new SimpleStringProperty(ingredient == null ? "-" : ingredient);
		});
		condimentCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
		condimentCol.setResizable(false);

		table.getColumns().add(condimentCol);


		TableColumn<Flavour, String> nullCol = new TableColumn<>("");
		nullCol.prefWidthProperty().bind(table.widthProperty().multiply(0.208));
		nullCol.setResizable(false);

		table.getColumns().add(nullCol);


		int numRows = table.getItems().size();
		final int MARGIN_BOTTOM_BY_NUM_LINES = 10;
		final int MIN_MARGIN_BOTTOM = 20;

		table.setMinHeight((rowHeight + MARGIN_BOTTOM_BY_NUM_LINES) * numRows + MIN_MARGIN_BOTTOM);
		table.setSelectionModel(null);


		return table;
	}

	private void createPizzasListTable(ArrayList<Pizza> pizzas, double rowHeight) {
		ObservableList<Pizza> pizzas_list = FXCollections.observableArrayList(pizzas);

		colPizzas.setCellValueFactory(new PropertyValueFactory<Pizza, Void>(""));

		colPizzas.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : "Pizza " + (getIndex() + 1));
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

			private final Button btnExcluir = new Button("X");

			{

				btnExcluir.setMinHeight(rowHeight);
				btnExcluir.setMaxHeight(rowHeight);

				double fontSize = rowHeight * 0.5; // Ajuste o fator conforme necessário

				btnExcluir.setStyle("-fx-font-size: " + fontSize + "px;");
				btnExcluir.getStyleClass().add("removeButton");


				if(SharedControl.getInstance().getOrder().getPizzas().size() == 1)
					btnExcluir.setDisable(true);
				else
					btnExcluir.setDisable(false);

				btnExcluir.setOnAction(event -> {
					// Obtém o índice do item na tabela
					Pizza pizza = getTableView().getItems().get(getIndex());

					Order order = SharedControl.getInstance().getOrder();

					// Remove do atributo estático de SharedControl
					order.setTotalPrice(order.getPrice() - pizza.getPrice());
					order.getPizzas().remove(pizza);


					// Atualiza a altura da tabela
					SceneNavigator.navigateTo("/views/tela5.fxml", "/styles/tela5.css");
				});

			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(btnExcluir);
				}
			}
		});

		tableViewListagemPizzas.getColumns().add(colExcluir);

		tableViewListagemPizzas.setItems(pizzas_list);

		int numRows = tableViewListagemPizzas.getItems().size();
		final int MARGIN_BOTTOM_BY_NUM_OF_LINES = 10;
		tableViewListagemPizzas.setMinHeight((rowHeight + MARGIN_BOTTOM_BY_NUM_OF_LINES) * numRows);

		tableViewListagemPizzas.setSelectionModel(null);

	}



	@FXML private void backToPaymentPage() {
		SceneNavigator.navigateTo("/views/Tela4.fxml", "/styles/Tela4.css");
	}

    @FXML private void goToFinalPage() {
      SceneNavigator.navigateTo("/views/Tela6.fxml", "/styles/Tela6.css");
    }

	@FXML private void goToChoosePizzaPage() {
		SharedControl.getInstance().resetPizza();
		SharedControl.getInstance().resetCounter();

		SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
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
