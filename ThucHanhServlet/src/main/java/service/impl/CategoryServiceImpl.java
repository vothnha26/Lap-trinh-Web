package service.impl;

import java.io.File;
import java.util.List;

import model.Category;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private final CategoryDaoImpl categoryDao = new CategoryDaoImpl();
	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category newCategory) {
		Category oldCate = categoryDao.get(newCategory.getCateid());
		oldCate.setCatename(newCategory.getCatename());
		categoryDao.edit(oldCate);
		
		if (newCategory.getIcon() != null) {
		// XOA ANH CU DI
		String fileName = oldCate.getIcon();
		final String dir = "E:\\upload";
		File file = new File(dir + "/category" + fileName);
		if (file.exists()) {
			file.delete();
		}
			oldCate.setIcon(newCategory.getIcon());
		}
		categoryDao.edit(oldCate);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);		
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}

}
