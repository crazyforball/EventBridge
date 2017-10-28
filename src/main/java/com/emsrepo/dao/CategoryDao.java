package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.domain.Category;

public interface CategoryDao {

	public void saveCategory(Category category);
	
	public Category getCategory(String categoryName);

	public Category getCategory(int cid);
	
	public List<String> getCategoryNames();
	
	public List<Category> getCategoryList();

	public void deleteCategory(Category category);
}
