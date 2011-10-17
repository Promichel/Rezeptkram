package de.schulprojekt.model.parser;

import de.schulprojekt.bean.parser.ParserParameterBean;
import de.schulprojekt.entities.Recipe;

public interface IParser {

    public Recipe fetchRecipe(ParserParameterBean parameterBean);

}
