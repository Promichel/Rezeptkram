package parser;

import de.schulprojekt.entities.Recipe;
import de.schulprojekt.exceptions.ParserException;
import de.schulprojekt.parsers.chefkoch.ChefkochParser;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Stefan
 * Date: 12.12.11
 * Time: 08:52
 */
public class ChefkochParserTest {
    @Test
    public void aVoid() {
        ChefkochParser parser = new ChefkochParser();
        Recipe recipe = null;
        try {
            recipe = parser.fetchRecipe("http://www.chefkoch.de/rezepte/1830281296676145/Koernerbroetchen-nach-Paniniart.html");
        } catch (ParserException e) {
            assertTrue("Error parsing site", false);
        }
        assertTrue(recipe.getName().equals("Körnerbrötchen nach Paniniart"));
        assertTrue(recipe.getPersonAmount() == 1);
        System.out.println(recipe.getText());


        ArrayList<String> expectedMemberOf = new ArrayList<String>();
        ArrayList<String> expectedIngredients = new ArrayList<String>();
        ArrayList<Double> expectedAmount = new ArrayList<Double>();
        ArrayList<String> expectedUnit = new ArrayList<String>();

        getExpectedIngredients(expectedIngredients);
        getExpectedMemberOf(expectedMemberOf);
        getExpectedAmount(expectedAmount);
        getExpectedUnit(expectedUnit);

        for (int i = 0, size = recipe.getIngredients().size(); i < size; ++i) {
            assertTrue(expectedIngredients.get(i).equals(recipe.getIngredients().get(i).getIngredient().getName()));
            assertTrue(expectedMemberOf.get(i) == recipe.getIngredients().get(i).getMemberOf());
            assertTrue(expectedAmount.get(i) == recipe.getIngredients().get(i).getAmount());
            assertTrue(expectedUnit.get(i).equals(recipe.getIngredients().get(i).getUnit()));
        }

        System.out.println(recipe.getText());
    }

    public void getExpectedMemberOf(ArrayList<String> expectedMemberOf) {
        expectedMemberOf.add("Für den Teig:(Vorteig)");
        expectedMemberOf.add("Für den Teig:(Vorteig)");
        expectedMemberOf.add("Für den Teig:(Vorteig)");
        expectedMemberOf.add("Für den Teig:(Quellstück)");
        expectedMemberOf.add("Für den Teig:(Quellstück)");
        expectedMemberOf.add("Für den Teig:(Quellstück)");
        expectedMemberOf.add("Für den Teig:(Quellstück)");
        expectedMemberOf.add("Für den Teig:(Quellstück)");
        expectedMemberOf.add("Für den Teig:(Quellstück)");
        expectedMemberOf.add("Für den Teig:");
        expectedMemberOf.add("Für den Teig:");
        expectedMemberOf.add("Für den Teig:");
        expectedMemberOf.add("Für den Teig:");
        expectedMemberOf.add("Für den Teig:");
    }

    public void getExpectedIngredients(ArrayList<String> expectedIngredients) {
        expectedIngredients.add("Weizenmehl, 550");
        expectedIngredients.add("Wasser");
        expectedIngredients.add("Hefe");
        expectedIngredients.add("Sonnenblumenkerne");
        expectedIngredients.add("Sesam");
        expectedIngredients.add("Leinsamen");
        expectedIngredients.add("Haferflocken");
        expectedIngredients.add("Salz");
        expectedIngredients.add("Wasser");
        expectedIngredients.add("Weizenmehl, 550");
        expectedIngredients.add("Weizenmehl, 1050");
        expectedIngredients.add("Weizenmehl (Vollkorn-)");
        expectedIngredients.add("Wasser");
        expectedIngredients.add("Hefe");
    }

    public void getExpectedAmount(ArrayList<Double> expectedAmount) {
        expectedAmount.add(160.0);
        expectedAmount.add(160.0);
        expectedAmount.add(2.0);
        expectedAmount.add(50.0);
        expectedAmount.add(40.0);
        expectedAmount.add(40.0);
        expectedAmount.add(40.0);
        expectedAmount.add(18.0);
        expectedAmount.add(200.0);
        expectedAmount.add(250.0);
        expectedAmount.add(250.0);
        expectedAmount.add(100.0);
        expectedAmount.add(250.0);
        expectedAmount.add(10.0);
    }

    public void getExpectedUnit(ArrayList<String> expectedUnit) {
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
        expectedUnit.add("g");
    }
}