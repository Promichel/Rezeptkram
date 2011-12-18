package de.schulprojekt.parsers.dasKochrezept;

import de.schulprojekt.bean.parser.ParserParameterBean;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.RecipeIngredient;
import de.schulprojekt.model.parser.IParser;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DasKochrezeptParser implements IParser {

	private URL url;
	private String recipe;
	private List<RecipeIngredient> ingredients;
	private String title;
	private int nOfPortions;

	public DasKochrezeptParser() {
		url = null;
		recipe = null;
		ingredients = null;
		nOfPortions = 0;
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
		parseTitle(htmlContent);
		Pattern pattern = Pattern
				.compile(
						"<div itemprop=\"instructions\" class=\"rezept-zubereitung\">(.*?)</div>",
						Pattern.DOTALL);

		Matcher matcher = pattern.matcher(htmlContent);
		if (matcher.find()) {
			String recipe = matcher.group(1);
			recipe = removeHtmlTags(recipe);
			recipe = recipe.trim();
			this.recipe = recipe;
		} else
			this.recipe = "Error: Match not found!";
	}

	private String removeHtmlTags(String content) {
		boolean isText = true;
		String returnString = "";

		for (int i = 0; i < content.length(); ++i) {
			if (content.charAt(i) == '<') {
				isText = false;
			}
			if (isText) {
				if (returnString.length() > 2
						&& returnString.charAt(returnString.length() - 1) == '\n'
						&& Character.isWhitespace(content.charAt(i)))
                {
					returnString = returnString.substring(0, returnString.length() - 2)	+ content.charAt(i);
				} else {
					returnString += content.charAt(i);
				}
			}
			if (content.charAt(i) == '>') {
				isText = true;
			}
		}
		return returnString;
	}

	private void parseTitle(String htmlContent) {
		Pattern pattern = Pattern.compile("<h2 class=.*?>(.*?)</h2>",
				Pattern.DOTALL);
		Matcher matcher = pattern.matcher(htmlContent);
		matcher.find();
		this.title = matcher.group(1);
	}

	private void parseIngredients(String htmlContent) {
		Pattern pattern = Pattern.compile("(<table class=\"rezept\">)(.*?)"
				+ "(</table>)", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(htmlContent);
		if (matcher.find()) {
			String table = matcher.group(2);
			Pattern tablePattern = Pattern.compile("<tr.*?>(.*?)</tr>",
					Pattern.DOTALL);
			Matcher tableMatcher = tablePattern.matcher(table);
			String header = null;
			ingredients = new ArrayList<RecipeIngredient>();
			while (tableMatcher.find()) {
				Pattern rowPattern = Pattern.compile(
						"<td itemprop=\"amount\">.*?"
								+ "<span .*?>(.*?)</span>.*?"
								+ "<span .*?>(.*?)</span>.*?</td>"
								+ ".*?<td itemprop=\"name\">(.*?)</td>",
						Pattern.DOTALL);

				Matcher rowMatcher = rowPattern.matcher(tableMatcher.group(1));
				if (rowMatcher.find()) {
					RecipeIngredient recipeIngredient = new RecipeIngredient();
					Ingredient article = new Ingredient();
					article.setName(removeHtmlTags(rowMatcher.group(3).trim())
                            .trim());
					recipeIngredient.setIngredient(article);
					recipeIngredient.setUnit(rowMatcher.group(2).trim());
					recipeIngredient.setMemberOf(header);
					recipeIngredient.setAmount(this.getFloatFromQuantity(rowMatcher.group(1).trim()));
					ingredients.add(recipeIngredient);
				}
			}
		}
	}

	private float getFloatFromQuantity(String quantity) {
		Pattern numberPattern = Pattern.compile("(\\d*)?\\s?(\\d)/(\\d)");
		Matcher numberMatcher = numberPattern.matcher(quantity);
		if (numberMatcher.find()) {
            String number1 = numberMatcher.group(1);
            float num1 = 0;
            if (!number1.isEmpty())
            {
                num1 = Integer.parseInt(numberMatcher.group(1));
            }
			float num2 = Integer.parseInt(numberMatcher.group(2));
			float num3 = Integer.parseInt(numberMatcher.group(3));

			return num1 + num2 / num3;
		} else if (quantity.equals("")) {
			return 0;
		}
		Integer number = Integer.parseInt(quantity);
		return number.floatValue();
	}

	private void parseURL(String content) {
        content = HtmlUtils.htmlUnescape(content);
		this.parseFormulation(content);
		this.parseIngredients(content);
		this.parseNOfPortions(content);
        this.parseTitle(content);
	}

	private void parseNOfPortions(String content) {
		Pattern pattern = Pattern
				.compile(
						"<div class=\"rezept_portionen\">"
								+ ".*?<span itemprop=\"yield\" style=\"display: none;\">(\\d*)</span>"
								+ ".*?</div>", Pattern.DOTALL);

		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			this.nOfPortions = Integer.parseInt(matcher.group(1));
		} else {
			this.nOfPortions = -1;
		}
	}

	private void setURL(String url) {
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

	@Override
    public Recipe fetchRecipe(String link) {
   		this.setURL(link);
		Recipe recipe = new Recipe();
		recipe.setName(this.title);
		recipe.setText(this.getRecipe());
		recipe.setIngredients(this.getIngredients());
		recipe.setPersonAmount(this.nOfPortions);


		/** @author Stefan 
		 *  @category debug
		 **/
		// System.out.println(recipe.getName());
		// System.out.println(recipe.getText());
		// System.out.println("Portions: " + recipe.getPersonenAnzahl());
		//
		// System.out.println("Zutaten:");
		//
		// for (RecipeIngredient ingredient : recipe.getIngredients()) {
		// System.out.println(ingredient.getArtikel().getName());
		// System.out.println(ingredient.getEinheit());
		// System.out.println(ingredient.getMenge());
		// }
		return recipe;
	}
}
