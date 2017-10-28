package com.emsrepo.service;

import java.util.List;

import com.emsrepo.domain.Category;

public interface CategoryService {

	public List<Category> getCategoryList();

	public boolean registerCategory(Category category);

	public boolean isExistingCategory(Category category);

	public List<String> getCategoryNames();

	public void deleteCategory(Category category);
	
	public Category getCategory(int cid);
}
