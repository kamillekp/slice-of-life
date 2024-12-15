package application;

public class PizzaInfo {
    private final Pair[] sizes = {
            new Pair("Pequena (25 cm)", 25),
            new Pair("Média (30 cm)", 30),
            new Pair("Grande (35 cm)", 35),
            new Pair("Família (40 cm)", 40),
    };

    private final Pair[] numFlavors = {
            new Pair("1", 0),
            new Pair("2", 1),
            new Pair("3", 2),
            new Pair("4", 3),
    };

    private final Pair[] pizzaBorder = {
            new Pair("Com Borda", 1),
            new Pair("Sem Borda", 0),
    };


    public Pair[] getProperties(String type){
        return switch (type) {
            case "border" -> pizzaBorder;
            case "sizes" -> sizes;
            case "number of flavours" -> numFlavors;
            default -> null;
        };
    }

    public double getPrice(String type, String item){
        Pair[] properties = getProperties(type);

        if(properties == null)
            throw new NullPointerException("No properties found for type " + type);

        double price = 0;

        for (Pair property : properties) {
            if(property.getOption().equals(item))
                price = property.getPrice();
        }

        return price;
    }
}