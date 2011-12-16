package parser;

import de.schulprojekt.entities.Recipe;
import de.schulprojekt.parsers.dasKochrezept.DasKochrezeptParser;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Asus
 * Date: 16.12.11
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class DasKochrezeptParserTest {
    @Test
    public void aVoid() {
        DasKochrezeptParser parser = new DasKochrezeptParser();
        Recipe recipe = parser.fetchRecipe("http://www.daskochrezept.de/rezepte/kartoffel-blumenkohl-auflauf_79668.html");
        assertTrue(!recipe.getText().equals(""));
        assertTrue(recipe.getName().equals("Kartoffel-Blumenkohl-Auflauf"));


        ArrayList<String> expectedMemberOf = new ArrayList<String>();
        ArrayList<String> expectedIngredients = new ArrayList<String>();
        ArrayList<Double> expectedAmount = new ArrayList<Double>();
        ArrayList<String> expectedUnit = new ArrayList<String>();

        getTestData(expectedMemberOf, expectedIngredients, expectedAmount, expectedUnit);

        boolean createNewTestData = false;
        for (int i = 0, size = recipe.getIngredients().size(); i  < size; ++i)
        {
            if (createNewTestData)
            {
                System.out.println("expectedMemberOf.add(\"" + recipe.getIngredients().get(i).getMemberOf() + "\");");
                System.out.println("expectedIngredients.add(\"" + recipe.getIngredients().get(i).getIngredient().getName() + "\");");
                System.out.println("expectedAmount.add(" + recipe.getIngredients().get(i).getAmount() + ");");
                System.out.println("expectedUnit.add(\"" + recipe.getIngredients().get(i).getUnit() + "\");");
            }
            else
            {
                assertTrue(expectedIngredients.get(i).equals(recipe.getIngredients().get(i).getIngredient().getName()));
                assertTrue(expectedMemberOf.get(i) == recipe.getIngredients().get(i).getMemberOf());
                assertTrue(expectedAmount.get(i) == recipe.getIngredients().get(i).getAmount());
                assertTrue(expectedUnit.get(i).equals(recipe.getIngredients().get(i).getUnit()));
            }
        }

        System.out.println(recipe.getText());
    }

    private void getTestData(ArrayList<String> expectedMemberOf,
                             ArrayList<String> expectedIngredients,
                             ArrayList<Double> expectedAmount,
                             ArrayList<String> expectedUnit)
    {
        expectedMemberOf.add(null);
        expectedIngredients.add("große Kartoffeln");
        expectedAmount.add(5.0);
        expectedUnit.add("St");
        expectedMemberOf.add(null);
        expectedIngredients.add("Blumenkohl");
        expectedAmount.add(0.0);
        expectedUnit.add("St");
        expectedMemberOf.add(null);
        expectedIngredients.add("Süße Sahne");
        expectedAmount.add(1.0);
        expectedUnit.add("Bch");
        expectedMemberOf.add(null);
        expectedIngredients.add("Eier");
        expectedAmount.add(2.0);
        expectedUnit.add("St");
        expectedMemberOf.add(null);
        expectedIngredients.add("Käse zum überbacken");
        expectedAmount.add(0.0);
        expectedUnit.add("etwas");
        expectedMemberOf.add(null);
        expectedIngredients.add("Butter (für die Auflaufform)");
        expectedAmount.add(0.0);
        expectedUnit.add("etwas");
        expectedMemberOf.add(null);
        expectedIngredients.add("Salz, Pfeffer, Muskatnuss");
        expectedAmount.add(0.0);
        expectedUnit.add("etwas");
    }
}
