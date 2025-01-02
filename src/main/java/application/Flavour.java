package application;

import java.util.ArrayList;

/**
 * Representa um sabor com um tipo e uma lista de ingredientes.
 */
public class Flavour {

    /**
     * O tipo do sabor (ex: "Doce", "Salgado").
     */
    private final String type;

    /**
     * A lista de ingredientes associados ao tipo do sabor.
     */
    private final ArrayList<String> ingredients;

    /**
     * Constrói um sabor com o tipo e uma lista de ingredientes fornecida.
     *
     * @param typeParam o tipo do sabor (ex: "Doce", "Salgado")
     * @param ingredientsParam a lista de ingredientes associados ao sabor
     */
    public Flavour(final String typeParam, final ArrayList<String> ingredientsParam) {
        this.ingredients = ingredientsParam;
        this.type = typeParam;
    }

    /**
     * Constrói um sabor com o tipo fornecido, sem ingredientes iniciais.
     *
     * @param typeParam o tipo do sabor
     */
    public Flavour(final String typeParam) {
        this.type = typeParam;
        this.ingredients = new ArrayList<>();
    }

    /**
     * Retorna o tipo do sabor.
     *
     * @return o tipo do sabor
     */
    public String getType() {
        return this.type;
    }

    /**
     * Retorna a lista de ingredientes do sabor.
     *
     * @return a lista de ingredientes
     */
    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }
}
