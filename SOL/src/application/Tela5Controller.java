package application;



import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;
 

public class Tela5Controller{

	private ReviewState state;
	
    @FXML private VBox tablesVBox;
	
    @FXML
    private TextFlow textFlow2;

    @FXML private TableView<Pizza> tableViewListagemPizzas;
    @FXML private TableColumn<Pizza, Void> colPizzas;
    @FXML private TableColumn<Pizza, Integer> colTamanho;
    @FXML private TableColumn<Pizza, Integer> colNumSabores;
    @FXML private TableColumn<Pizza, Boolean> colBorda;

    
    @FXML private TableView<Pizza> tableViewPizzasSalgadas;
    @FXML private TableColumn<Pizza,String> colQueijo;
    @FXML private TableColumn<Pizza, String> colProteina;
    @FXML private TableColumn<Pizza, String> colVegetais;
    @FXML private TableColumn<Pizza, String> colFolhas;
    
    @FXML private TableView<Pizza> tableViewPizzasDoces;
    @FXML private TableColumn<Pizza,String> colChocolate;
    @FXML private TableColumn<Pizza, String> colFrutas;
    @FXML private TableColumn<Pizza, String> colCondimentos;
    
    @FXML private Button finalButton;
    
    
    @FXML
    public void initialize(){
    	    	
    	tableViewListagemPizzas.setSelectionModel(null);   
    	tablesVBox.setSpacing(20);
    	
 
    	colPizzas.setCellValueFactory(new PropertyValueFactory<Pizza, Void>(""));
    	
    	
    	colPizzas.setCellFactory((column) -> {
    	    TableCell<Pizza, Void> tablecell = new TableCell<Pizza, Void>() {
    	        @Override
    	        protected void updateItem(Void item, boolean empty) {
    	            super.updateItem(item, empty);
    	            
    	            if (empty) 
    	                setText(null); // Célula vazia
    	            else
    	            	setText("Pizza " + (getIndex() + 1));
 
    	            
    	        }
    	    };
    	     
    	    return tablecell;
    	});
    	
    	
    	 
    	colBorda.setCellValueFactory(new PropertyValueFactory<Pizza, Boolean>("border"));
    	
    	
    	colBorda.setCellFactory((column) -> {
    	    TableCell<Pizza, Boolean> tablecell = new TableCell<Pizza, Boolean>() {
    	        @Override
    	        protected void updateItem(Boolean item, boolean empty) {
    	            super.updateItem(item, empty);
    	            
    	            if (empty || item == null) 
    	                setText(null); // Célula vazia
    	            else if(item) 
    	                setText("Com borda");
    	            else
    	            	setText("Sem borda");
    	            
    	        }
    	    };
    	     
    	    return tablecell;
    	});
    	
    	 
    	
    	
    	
    


    	colNumSabores.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("numFlavor"));
    	
    	colNumSabores.setCellFactory((column) -> {
    	    TableCell<Pizza, Integer> tablecell = new TableCell<Pizza, Integer>() {
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
    	    };
    	      
    	    return tablecell;
    	});
    	
    	
    	 
    	
    	colTamanho.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("size"));
    	
    	colTamanho.setCellFactory((column) -> {
    	    TableCell<Pizza, Integer> tablecell = new TableCell<Pizza, Integer>() {
    	        @Override
    	        protected void updateItem(Integer item, boolean empty) {
    	            super.updateItem(item, empty);
    	            
    	            if (empty || item == null) 
    	                setText(null); // Célula vazia
    	            else if(item == 0)
    	            	setText("Pequena");
    	            else if(item == 1)
    	            	setText("Média");
    	            else if(item == 2)
    	            	setText("Grande");
    	            else if(item == 3)
    	            	setText("Família");
    	            else
    	            	setText("ERRO!");
    	            
    	        }
    	    };
    	       
    	    return tablecell;
    	});
    	

   
    }
    

    public void initializeComponents() {

    	ArrayList<Pizza> pizzas = this.state.getClient().getPizzas();
    	 
    	ObservableList<Pizza> pizzas_list = FXCollections.observableArrayList(pizzas);

    	
    	tableViewListagemPizzas.setItems(pizzas_list);
    	
    	double rowHeight = 25.0;
    	int numRows = tableViewListagemPizzas.getItems().size();
    	

    	tableViewListagemPizzas.setPrefHeight(rowHeight * numRows);
    	
    	  
    	int contNumPizzas = 1;
    	for(Pizza pizza : pizzas ){

            VBox pizzaSection = new VBox(5);

            pizzaSection.setAlignment(Pos.CENTER);
             
            Label pizzaLabel = new Label("Pizza " + contNumPizzas);
            pizzaLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
            
            List<Flavor> savoryFlavors = pizza.getSaboresSalgados();
            List<Flavor> sweetFlavors = pizza.getSaboresDoces();
            
             
            TableView<Flavor> tableSavoryFlavors;
            TableView<Flavor> tableSweetFlavors;
            
         
            int contNumFlavors = 0;
            
            if(savoryFlavors.size() == 0) {
            	
            	tableSweetFlavors = createSweetFlavorsTable(pizza.getSaboresDoces(), 0);
                numRows = tableSweetFlavors.getItems().size();
                tableSweetFlavors.setPrefHeight(rowHeight * (numRows + 1.5));
                
                tableSweetFlavors.setSelectionModel(null);
                pizzaSection.getChildren().addAll(pizzaLabel, tableSweetFlavors);
                
                
                tablesVBox.getChildren().add(pizzaSection);
            }
            
            else if(sweetFlavors.size() == 0) {
            	tableSavoryFlavors = createSavoryFlavorsTable(pizza.getSaboresSalgados(), 0);
                numRows = tableSavoryFlavors.getItems().size();
                tableSavoryFlavors.setPrefHeight(rowHeight * (numRows + 1.5));
                
                tableSavoryFlavors.setSelectionModel(null);

                
                pizzaSection.getChildren().addAll(pizzaLabel, tableSavoryFlavors);
                tablesVBox.getChildren().add(pizzaSection);

            }
              
            else {
            	tableSavoryFlavors = createSavoryFlavorsTable(pizza.getSaboresSalgados(), 0);
            	numRows = tableSavoryFlavors.getItems().size();
            	tableSavoryFlavors.setPrefHeight(rowHeight * (numRows + 1.5));
            	
            	contNumFlavors = contNumFlavors + numRows;
            	
            	tableSweetFlavors = createSweetFlavorsTable(pizza.getSaboresDoces(), contNumFlavors);
            	
            	numRows = tableSweetFlavors.getItems().size();
            	tableSweetFlavors.setPrefHeight(rowHeight * (numRows + 1.5));
            	
            	
            	tableSweetFlavors.setSelectionModel(null);
            	tableSavoryFlavors.setSelectionModel(null);
            	
                pizzaSection.getChildren().addAll(pizzaLabel, tableSavoryFlavors, tableSweetFlavors);
                tablesVBox.getChildren().add(pizzaSection);
            	
            }

            contNumPizzas = contNumPizzas + 1;
    	}
    	
 
    	  
    	
    	appendToTextFlow2("Dados Pessoais\n\n", true, 15, Pos.CENTER);
    	
    	appendToTextFlow2(this.state.getClient().getName() + " " + this.state.getClient().getSurname() + "\n\n", false, 14, Pos.CENTER);
    	
    	
    	Payment payment = this.state.getClient().getPayment();
    	Address adress = this.state.getClient().getAddress();
    	
    	
    	if(payment != null && adress != null) {
    	
    		appendToTextFlow2("     Endereço\n\n", true, 14, Pos.CENTER_LEFT);
    	
    		appendToTextFlow2("          CEP: " + adress.getZipCode() + "\n", false, 12, Pos.CENTER_LEFT);
    		appendToTextFlow2("          Rua/Avenida " + adress.getStreet() + ", " + adress.getcity() + "\n", false, 12, Pos.CENTER_LEFT);
    		appendToTextFlow2("          Número: " + adress.getNumber() + "\n", false, 12, Pos.CENTER_LEFT);
    		appendToTextFlow2("          Complemento: " + adress.getComplement() + "\n", false, 12, Pos.CENTER_LEFT);
    	
    	
    		appendToTextFlow2("     Pagamento\n\n", true, 14, Pos.CENTER_LEFT);
    	
    		appendToTextFlow2("          Total do pedido: " + payment.getValue() + "R$\n", false, 12, Pos.CENTER_LEFT);
    		appendToTextFlow2("          Tipo: " + payment.getType() + "\n", false, 12, Pos.CENTER_LEFT);
    	
    		if(payment.getType() == "PIX") {
    			appendToTextFlow2("          Código:\nuumDgBX2bVG0MjImljO2GYmnbxIRcX0TX2cr8A93lKp392JG8M4MJyKpPpQsDfEh69NgH3Gfd21HNk", false, 12, Pos.CENTER_LEFT);
    		}
    	
    	}
    	
    	
    	
   
    }
    
  
    private TableView<Flavor> createSavoryFlavorsTable(List<Flavor> flavors, int contNumFlavors) {
    	
    	TableColumn<Flavor, Void> colNum = new TableColumn<>();
    	colNum.setCellValueFactory(new PropertyValueFactory<Flavor, Void>(""));
    	
    	
    	colNum.setCellFactory((column) -> {
    	    TableCell<Flavor, Void> tablecell = new TableCell<Flavor, Void>() {
    	        @Override
    	        protected void updateItem(Void item, boolean empty) {
    	            super.updateItem(item, empty);
    	            
    	            if (empty) 
    	                setText(null); // Célula vazia
    	            else
    	            	setText("Sabor " + (getIndex() + 1 + contNumFlavors));
 
    	            
    	        }
    	    };
    	     
    	    return tablecell;
    	});
   
    	
    	  
        TableView<Flavor> table = new TableView<>();
        colNum.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
         
        colNum.setStyle("-fx-font-weight: bold");
        colNum.setResizable(false);
        
        table.getColumns().add(colNum);
        
        
        table.setItems(FXCollections.observableArrayList(flavors));

        
        TableColumn<Flavor, String> cheeseCol = new TableColumn<>("Queijo");
        cheeseCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().get(0)));
        
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
       

        return table; 
         
    } 
    
    private TableView<Flavor> createSweetFlavorsTable(List<Flavor> flavors, int contNumFlavors) {
    	
    	TableColumn<Flavor, Void> colNum = new TableColumn<>();
    	colNum.setCellValueFactory(new PropertyValueFactory<Flavor, Void>(""));
    	
    	
    	colNum.setCellFactory((column) -> {
    	    TableCell<Flavor, Void> tablecell = new TableCell<Flavor, Void>() {
    	        @Override
    	        protected void updateItem(Void item, boolean empty) {
    	            super.updateItem(item, empty);
    	            
    	            if (empty) 
    	                setText(null); // Célula vazia
    	            else
    	            	setText("Sabor " + (getIndex() + 1 + contNumFlavors));
 
    	            
    	        }
    	    };
    	     
    	    return tablecell;
    	});
    	
    	 
    	 
    	
        TableView<Flavor> table = new TableView<>();
        
        colNum.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colNum.setStyle("-fx-font-weight: bold");
        colNum.setResizable(false);
        
        table.getColumns().add(colNum);
        
          
        table.setItems(FXCollections.observableArrayList(flavors));

        TableColumn<Flavor, String> toppingCol = new TableColumn<>("Cobertura");
        toppingCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngredients().get(0)));
        
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
        return table;
    } 
    	
    	
    
    public void setState(ReviewState state) {
    	this.state = state;
    }
    
    public ReviewState getState(){
    	return this.state;
    }
    

    @FXML
    private void goToFinalPage() {

      // Usar o SceneNavigator para mudar para a página final
      state.nextState();
      
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
