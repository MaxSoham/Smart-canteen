package com.smartcanteen;

import com.smartcanteen.dao.CategoryDAO;

public class TestCategory {

    public static void main(String[] args) {

        CategoryDAO categoryDAO = new CategoryDAO();

        System.out.println("Categories:");
        categoryDAO.displayCategories();
    }
}
