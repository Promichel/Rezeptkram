import de.schulprojekt.model.parser.IParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
public class ChefkochParser implements IParser {
    private URL url;
    private String recipe;
    private Vector<Ingredient> ingredients;

    public ChefkochParser() {
        url = null;
        recipe = null;
        ingredients = null;
    }

    private void readURL() {
        InputStream is = null;
        try {
            is = url.openStream();
            String content = new Scanner(is).useDelimiter("\\Z").next();
            parseURL(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    private void parseFormulation(String htmlContent) {
        Pattern pattern = Pattern.compile(
                "(<div id=\"rezept-zubereitung\".*?>)(.*?)(</div>?)",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(htmlContent);
        if (matcher.find()) {
            String recipe = matcher.group(2);
            recipe = recipe.replace("\t", "");
            recipe = recipe.replace("<br />", "");
            recipe = recipe.trim();
            this.recipe = recipe;
        } else
            this.recipe = "Error: Match not found!";
    }

    private void parseIngredients(String htmlContent) {
        Pattern pattern = Pattern.compile("(<table class=\"zutaten\">)(.*?)"
                + "(</table>)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(htmlContent);
        if (matcher.find()) {
            String table = matcher.group(2);
            Pattern tablePattern = Pattern.compile("(<tr>)(.*?)(</tr>)",
                    Pattern.DOTALL);
            Matcher tableMatcher = tablePattern.matcher(table);
            String header = null;
            ingredients = new Vector<Ingredient>();
            while (tableMatcher.find()) {
                String tableContent = tableMatcher.group(2);
                Pattern groupTitle = Pattern.compile(
                        "(<b>)(.*?)(</b>)(.*?)</td>", Pattern.DOTALL);
                Matcher tableContentMatcher = groupTitle.matcher(tableContent);
                if (tableContentMatcher.find()) {
                    header = tableContentMatcher.group(2)
                            + tableContentMatcher.group(4).trim();
                } else {
                    StringTokenizer strTok = new StringTokenizer(tableContent);
                    boolean text = false;
                    String quantity = null;
                    String unit = null;
                    String ingredient = "";

                    while (strTok.hasMoreTokens()) {
                        String token = strTok.nextToken();
                        if (token.contains("<")) {
                            text = false;
                        }
                        if (text == true) {
                            if (token.contains("&nbsp;")) {
                                Matcher match = Pattern.compile(
                                        "(.*?)&nbsp;(.*)").matcher(token);
                                match.find();

                                quantity = match.group(1).trim();
                                unit = match.group(2).trim();
                            } else {
                                ingredient = ingredient + " " + token;
                            }
                        }

                        if (token.endsWith(">")) {
                            text = true;
                        }
                    }
                    ingredients.add(new Ingredient(header, ingredient,
                            quantity, unit));
                }
            }
        }
    }

    private void parseURL(String content) {
        this.parseFormulation(content);
        this.parseIngredients(content);
    }

    public void setURL(String url) {
        Pattern pattern = Pattern.compile("http://\\w.*");
        Matcher matcher = pattern.matcher(url);
        String checkedUrl = url;
        if (matcher.find() == false) {
            checkedUrl = "http://" + url;
        }
        try {
            this.url = new URL(checkedUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.readURL();
    }

    public String getRecipe() {
        return this.recipe;
    }

    public Vector<Ingredient> getIngredients() {
        return this.ingredients;
    }

//	public static void main(String[] args) {
//		ChefkochParser parser = new ChefkochParser();
////		parser.setURL("http://www.chefkoch.de/rezepte/1830281296676145/Koernerbroetchen-nach-Paniniart.html");
//		parser.setURL("http://www.chefkoch.de/rezepte/817681186299057/Birnen-in-Rotwein-an-Vanilleeis.html");
//		Vector<Ingredient> ingredients = parser.getIngredients();
//		System.out.println(parser.getRecipe());
//		for (Ingredient ingredient : ingredients) {
//			if (ingredient.getGroupHeader() != null)
//			{
//				System.out.println(ingredient.getGroupHeader());
//			}
//			System.out.println(ingredient.getType());
//			System.out.println(ingredient.getQuantity());
//			System.out.println(ingredient.getQuantityUnit());
//		}
//	}
}
*/
