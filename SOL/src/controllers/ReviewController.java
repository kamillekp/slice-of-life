
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
import javafx.scene.control.Label;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.text.TextFlow;

import javafx.scene.text.FontWeight;


public class ReviewController {

    @FXML private VBox tablesVBox;
	
    @FXML
    private TextFlow textFlow2;

    @FXML private TableView<Pizza> tableViewListagemPizzas;
    @FXML private TableColumn<Pizza, Void> colPizzas;
    @FXML private TableColumn<Pizza, String> colTamanho;
    @FXML private TableColumn<Pizza, Integer> colNumSabores;
    @FXML private TableColumn<Pizza, Boolean> colBorda;

    @FXML
    public void initialize() {

		tablesVBox.setSpacing(20);
		double rowHeight = 25.0;
		ArrayList<Pizza> pizzas = SharedControl.getInstance().getOrder().getPizzas();


		createPizzasListTable(pizzas, rowHeight);
		int idPizza = 1;

		for (Pizza pizza : pizzas) {
			createPizzaFlavourTable(pizza, idPizza, rowHeight);
			idPizza++;
		}

			appendToTextFlow2("Dados Pessoais\n\n", true, 15, Pos.CENTER);

			appendToTextFlow2(SharedControl.getInstance().getOrder().getClient().getName() + " " + SharedControl.getInstance().getOrder().getClient().getSurname() + "\n", false, 14, Pos.CENTER);


			Payment payment = SharedControl.getInstance().getOrder().getClient().getPayment();
			Address adress = SharedControl.getInstance().getOrder().getClient().getAddress();

			if (payment != null && adress != null) {

				appendToTextFlow2("     Endereço\n\n", true, 14, Pos.CENTER_LEFT);

				appendToTextFlow2("          CEP: " + adress.getZipCode() + "\n", false, 12, Pos.CENTER_LEFT);
				appendToTextFlow2("          Rua/Avenida " + adress.getStreet() + ", " + adress.getcity() + "\n", false, 12, Pos.CENTER_LEFT);
				appendToTextFlow2("          Número: " + adress.getNumber() + "\n", false, 12, Pos.CENTER_LEFT);
				appendToTextFlow2("          Complemento: " + adress.getComplement() + "\n", false, 12, Pos.CENTER_LEFT);


				appendToTextFlow2("     Pagamento\n\n", true, 14, Pos.CENTER_LEFT);

				appendToTextFlow2("          Total do pedido: " + payment.getValue() + "R$\n", false, 12, Pos.CENTER_LEFT);
				appendToTextFlow2("          Tipo: " + payment.getType() + "\n", false, 12, Pos.CENTER_LEFT);

				if (Objects.equals(payment.getType(), "PIX")) {
					appendToTextFlow2("          Código:\nuumDgBX2bVG0MjImljO2GYmnbxIRcX0TX2cr8A93lKp392JG8M4MJyKpPpQsDfEh69NgH3Gfd21HNk", false, 12, Pos.CENTER_LEFT);
				}

			}
	}

	private void createPizzaFlavourTable(Pizza pizza, int contNumPizzas, double rowHeight){

			VBox pizzaSection = new VBox(5);
			pizzaSection.setAlignment(Pos.CENTER);

			Label pizzaLabel = new Label("Pizza " + contNumPizzas);
			pizzaLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");

			List<Flavor> savoryFlavors = pizza.getSaboresSalgados();
			List<Flavor> sweetFlavors = pizza.getSaboresDoces();

			TableView<Flavor> tableSavoryFlavors = createSavoryFlavorsTable(savoryFlavors, 0, rowHeight);
			TableView<Flavor> tableSweetFlavors = createSweetFlavorsTable(sweetFlavors, savoryFlavors.size(), rowHeight);

			pizzaSection.getChildren().addAll(pizzaLabel, tableSavoryFlavors, tableSweetFlavors);

			tablesVBox.getChildren().add(pizzaSection);

		}


  
    private TableView<Flavor> createSavoryFlavorsTable(List<Flavor> flavors, int contNumFlavors, double rowHeight) {

		if(flavors.isEmpty())
			return null;

    	TableColumn<Flavor, Void> colNum = new TableColumn<>();
    	colNum.setCellValueFactory(new PropertyValueFactory<Flavor, Void>(""));

		colNum.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : "Sabor " + (getIndex() + 1 + contNumFlavors));
			}
		});

    	  
        TableView<Flavor> table = new TableView<>();
        colNum.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
         
        colNum.setStyle("-fx-font-weight: bold");
        colNum.setResizable(false);
        
        table.getColumns().add(colNum);
        
        
        table.setItems(FXCollections.observableArrayList(flavors));

        
        TableColumn<Flavor, String> cheeseCol = new TableColumn<>("Queijo");
        cheeseCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().getFirst()));
        
        cheeseCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        cheeseCol.setResizable(false);
        
        table.getColumns().add(cheeseCol);

        TableColumn<Flavor, String> proteinCol = new TableColumn<>("Proteína");
        proteinCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().get(1))); 
        proteinCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        proteinCol.setResizable(false);
        
        table.getColumns().add(proteinCol);
        	
        TableColumn<Flavor, String> vegetablesCol = new TableColumn<>("Vegetais");
        vegetablesCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().get(2)));
        vegetablesCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        vegetablesCol.setResizable(false);
        
        table.getColumns().add(vegetablesCol);
        		
        TableColumn<Flavor, String> leafyCol = new TableColumn<>("Folhas");
        leafyCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().get(3)));
        leafyCol.prefWidthProperty().bind(table.widthProperty().multiply(0.205));
        leafyCol.setResizable(false);
        
        table.getColumns().add(leafyCol);

		int numRows = table.getItems().size();
		table.setPrefHeight(rowHeight * (numRows + 1.5));
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
				else if(item)
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

				else if(item == 1)
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


		tableViewListagemPizzas.setItems(pizzas_list);

		int numRows = tableViewListagemPizzas.getItems().size();
		tableViewListagemPizzas.setPrefHeight(rowHeight * numRows);

		tableViewListagemPizzas.setSelectionModel(null);
	}

    private TableView<Flavor> createSweetFlavorsTable(List<Flavor> flavors, int contNumFlavors, double rowHeight) {

    	if(flavors.isEmpty())
			return null;

    	TableColumn<Flavor, Void> colNum = new TableColumn<>();
    	colNum.setCellValueFactory(new PropertyValueFactory<Flavor, Void>(""));

    	
    	colNum.setCellFactory((column) -> new TableCell<>() {
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : "Sabor " + (getIndex() + 1 + contNumFlavors));
			}
		});

		TableView<Flavor> table = new TableView<>();

        
        colNum.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colNum.setStyle("-fx-font-weight: bold");
        colNum.setResizable(false);


        table.getColumns().add(colNum);
        
          
        table.setItems(FXCollections.observableArrayList(flavors));

        TableColumn<Flavor, String> toppingCol = new TableColumn<>("Cobertura");
        toppingCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().getFirst()));
        
        toppingCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        toppingCol.setResizable(false);
        
        table.getColumns().add(toppingCol);

        TableColumn<Flavor, String> fruitCol = new TableColumn<>("Fruta");
        fruitCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().get(1)));
        fruitCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        fruitCol.setResizable(false);
        
        table.getColumns().add(fruitCol);
        		
        TableColumn<Flavor, String> condimentCol = new TableColumn<>("Condimento");
        condimentCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().get(2)));
        condimentCol.prefWidthProperty().bind(table.widthProperty().multiply(0.21));
        condimentCol.setResizable(false);
        
        table.getColumns().add(condimentCol);
        
        
        TableColumn<Flavor, String> nullCol = new TableColumn<>("");
        nullCol.prefWidthProperty().bind(table.widthProperty().multiply(0.205));
        nullCol.setResizable(false);
        
        table.getColumns().add(nullCol);


		int numRows = table.getItems().size();
		table.setPrefHeight(rowHeight * (numRows + 1.5));
		table.setSelectionModel(null);


        return table;
    } 


    @FXML
    private void goToFinalPage() {
      SceneNavigator.navigateTo("/views/Tela6.fxml", "/styles/Tela6.css");
    }

	@FXML
	private void goToChoosePizzaPage() {
		SceneNavigator.navigateTo("/views/Tela2.fxml", "/styles/Tela2.css");
	}


	public void appendToTextFlow2(String texto, boolean isBold, int fontSize, Pos textAlignment) {
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
