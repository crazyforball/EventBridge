package com.emsrepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emsrepo.dao.CategoryDao;
import com.emsrepo.domain.Category;
import com.emsrepo.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public boolean registerCategory(Category category) {

		// Step 1: check whether this person is already in the database
		// Step 2: if not, save this person into the database
		if (!isExistingCategory(category)) {
			categoryDao.saveCategory(category);
			return true;
		}
		System.out.println("categoryName already exits.");
		return false;
	}

	@Override
	public boolean isExistingCategory(Category category) {
		return categoryDao.getCategory(category.getCategoryName()) != null;
	}

	@Override
	public List<String> getCategoryNames() {
		return categoryDao.getCategoryNames();
	}

	@Override
	@Transactional
	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	@Override
	public void deleteCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.deleteCategory(category);
	}

	@Override
	public Category getCategory(int cid) {
		// TODO Auto-generated method stub
		return categoryDao.getCategory(cid);
	}
	
}
