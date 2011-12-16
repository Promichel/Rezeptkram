package de.schulprojekt.model.parser;

import de.schulprojekt.entities.Recipe;
import de.schulprojekt.exceptions.ParserException;

public interface IParser {

    public Recipe fetchRecipe(String link) throws ParserException;

}
