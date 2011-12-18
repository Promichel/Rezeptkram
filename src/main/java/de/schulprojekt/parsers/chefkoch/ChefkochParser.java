package de.schulprojekt.parsers.chefkoch;

import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.RecipeIngredient;
import de.schulprojekt.exceptions.ParserException;
import de.schulprojekt.model.parser.IParser;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChefkochParser implements IParser {
	private URL url;
	private String recipe;
	private List<RecipeIngredient> ingredients;
	private String title;
	private int nOfPortions;



    private final static Pattern portionPattern = Pattern.compile(
            "<div.*?id=\"rezept-zutaten\".*?<input type=\"text\" name=\"divisor\".*?value=\"(\\d)\".*?>",
            Pattern.DOTALL);
    private final static Pattern titlePattern = Pattern.compile(
   			"<h1 class=\"big fn\" style=\"margin-bottom: 0px;\">(.*?)</h1>",
            Pattern.DOTALL);
    private final static Pattern tablePattern = Pattern.compile("(<table class=\"zutaten\">)(.*?)"
            + "(</table>)", Pattern.DOTALL);
    private final static Pattern formulationPattern = Pattern.compile(
            "(<div id=\"rezept-zubereitung\".*?>)(.*?)(</div>?)",
            Pattern.DOTALL);
    private final static Pattern tableRowPattern = Pattern.compile("(<tr.*?>)(.*?)(</tr>)",
            Pattern.DOTALL);

	public ChefkochParser() {
		url = null;
		recipe = null;
		ingredients = null;
	}

	private void readURL() throws ParserException {
		InputStream is = null;
		try {
			is = url.openStream();
			String content = new Scanner(is).useDelimiter("\\Z").next();
			parseURL(content);
		} catch (Exception e) {
			e.printStackTrace();
            throw new ParserException();
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
		Matcher matcher = formulationPattern.matcher(htmlContent);
		if (matcher.find()) {
			String recipe = matcher.group(2);
			recipe = recipe.replace("\t", "");
			recipe = recipe.replace("<br>", "");
            recipe = recipe.replace("<br />", "");
			recipe = recipe.trim();
			this.recipe = recipe;
		} else
			this.recipe = "Error: Match not found!";
	}

	private void parseTitle(String htmlContent) {
		Matcher matcher = titlePattern.matcher(htmlContent);
		matcher.find();
		this.title = matcher.group(1);
	}

	private void parseIngredients(String htmlContent) throws ParserException {
		Matcher matcher = tablePattern.matcher(htmlContent);
		if (matcher.find()) {
			String table = matcher.group(2);
			Matcher tableMatcher = tableRowPattern.matcher(table);
			String header = null;
			ingredients = new ArrayList<RecipeIngredient>();
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
						if (text) {
							if (token.contains("&nbsp;")) {
								Matcher match = Pattern.compile(
										"(.*?)&nbsp;(.*)").matcher(token);
								match.find();

								quantity = match.group(1).trim();
								unit = match.group(2).trim();
							} else {
								ingredient = ingredient + " " + token;
								ingredient = ingredient.trim();
							}
						}

						if (token.endsWith(">")) {
							text = true;
						}
					}
					RecipeIngredient recipeIngredient = new RecipeIngredient();
					Ingredient article = new Ingredient();
					article.setName(HtmlUtils.htmlUnescape(ingredient));
					recipeIngredient.setIngredient(article);
					recipeIngredient.setUnit(unit);
					recipeIngredient.setMemberOf(HtmlUtils.htmlUnescape(header));
                    if (quantity.isEmpty()) //hack
                    {
                        recipeIngredient.setAmount(0);
                    }
                    else
                    {
					    recipeIngredient.setAmount(this.getFloatFromQuantity(quantity));
                    }
					ingredients.add(recipeIngredient);
				}
			}
		}
	}

	private float getFloatFromQuantity(String quantity) throws ParserException {
        Integer number = -1;
        try
        {
		    number = Integer.parseInt(quantity);
        }
        catch(NumberFormatException e)
        {
            throw new ParserException();
        }
		return number.floatValue();
	}

	private void parseURL(String content) throws ParserException {
        this.parseIngredients(content);
        content = HtmlUtils.htmlUnescape(content);
        this.parseTitle(content);
		this.parseFormulation(content);
		this.parseNOfPortions(content);
	}

	private void setURL(String url) throws ParserException {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.readURL();
	}

	private String getRecipe() {
		return this.recipe;
	}

	private List<RecipeIngredient> getIngredients() {
		return this.ingredients;
	}

	private void parseNOfPortions(String content) {
		Matcher matcher = portionPattern.matcher(content);
		if (matcher.find()) {
            String group = matcher.group(1);
			this.nOfPortions = Integer.parseInt(group);
		} else {
			this.nOfPortions = -1;
		}
	}

	@Override
    public Recipe fetchRecipe(String link) throws ParserException {
   		this.setURL(link);
		Recipe recipe = new Recipe();
		recipe.setName(this.title);
		recipe.setText(this.getRecipe());
		recipe.setIngredients(this.getIngredients());
		recipe.setPersonAmount(this.nOfPortions);

		/**
		 * @author Stefan
		 * @category debug
		 **/
		// System.out.println(recipe.getName());
		// System.out.println(recipe.getText());
		// System.out.println(recipe.getPersonenAnzahl());
		// for (RecipeIngredient ingredient : recipe.getIngredients()) {
		// System.out.println(ingredient.getArtikel().getName());
		// System.out.println(ingredient.getEinheit());
		// System.out.println(ingredient.getMenge());
		// }
		return recipe;
	}

	/**
	 * @author Stefan
	 * @category debug
	 **/
	// public static void main(String[] args) {
	// ChefkochParser parser = new ChefkochParser();
	// parser.setURL("file:///C:/Users/Asus/Desktop/Koernerbroetchen-nach-Paniniart.html");
	//
	// parser.fetchRecipe(null);
	// }
}
