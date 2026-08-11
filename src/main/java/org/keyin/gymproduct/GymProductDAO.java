package org.keyin.gymproduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.keyin.database.DatabaseConnection;
public class GymProductDAO {


    //add New Product (Admin Only)
    public void addNewProduct(GymProduct gymProduct) throws SQLException {
        String sql = "INSERT INTO gym_merch (product_name, product_type, price, stock_level) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, gymProduct.getProductName());
            pstmt.setString(2, gymProduct.getProductType());
            pstmt.setDouble(3, gymProduct.getPrice());
            pstmt.setInt(4, gymProduct.getQuantity());
            pstmt.executeUpdate();
        }
    }


    


    //View Products for Purchase (Trainer and Member). The Valuation calculation is in the menu (Admin Only) 
    public List<GymProduct> getAllProducts() throws SQLException{
        List<GymProduct> products = new ArrayList<>();
        String sql = "SELECT * FROM gym_merch";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    GymProduct gymProduct = new GymProduct(
                        rs.getString("product_name"),
                        rs.getString("product_type"),
                        rs.getDouble("price"),
                        rs.getInt("stock_level")
                    );
                    gymProduct.setId(rs.getInt("id"));
                    products.add(gymProduct);
                    }
                }
            return products;
    }

}
