package org.keyin.gymproduct;
import java.sql.SQLException;
import java.util.List;


public class GymProductService {
    GymProductDAO gymProductDAO = new GymProductDAO();

    //Add New Product (Admin Only)
    public void addNewProduct(GymProduct gymProduct) throws SQLException{
        gymProductDAO.addNewProduct(gymProduct);
    }

    //View Products for purchase (Trainer and Member). The valuation calculation is in the menu (Admin Only)
    public List<GymProduct> getAllProducts() throws SQLException{
        return gymProductDAO.getAllProducts();
    }

}
