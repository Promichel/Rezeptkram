package de.schulprojekt.controller;

import java.util.*;

import org.primefaces.model.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import de.schulprojekt.bean.RecipeSearchBean;
import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.entities.Recipe;

public class RecipeListController {
	@Autowired
	private RecipeDao dao;

	private Logger logger = LoggerFactory.getLogger(RecipeListController.class);

	private LazyDataModel<Recipe> recipes;

	public RecipeListController() {
	}

	private void retrieveRecipes() {
		recipes = new LazyDataModel<Recipe>() {

			RecipeSearchBean rsb = new RecipeSearchBean();

			@Override
			public List<Recipe> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

				rsb.setFirst(first);
				rsb.setPageSize(pageSize);
				List<Recipe> recipes = dao.selectRecipes(rsb);

				this.setPageSize(pageSize);
				this.setRowCount(recipes.size());

				return recipes;

			}

		};

	}

	public LazyDataModel<Recipe> getRecipes() {
		if(recipes == null) {
			retrieveRecipes();
		}

		return recipes;
	}


}
