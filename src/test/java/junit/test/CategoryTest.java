package junit.test;

import com.emsrepo.domain.Category;

import junit.framework.TestCase;

public class CategoryTest extends TestCase {


	private Category category;

	protected void setUp() throws Exception {
		category = new Category();
	}
	

	public void testSetAndGetCategoryName() {
		String testCategoryName = "aCategoryName";
		assertNull(category.getCategoryName());
		category.setCategoryName(testCategoryName);
		assertEquals(testCategoryName, category.getCategoryName());
	}

	public void testSetAndGetDescription() {
		String testDescription = "aDescription";
		assertNull(category.getCdesc());
		category.setCdesc(testDescription);
		assertEquals(testDescription, category.getCdesc());
	}
}
