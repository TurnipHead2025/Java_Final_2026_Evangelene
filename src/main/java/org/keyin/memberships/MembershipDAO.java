package org.keyin.memberships;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.keyin.database.DatabaseConnection;

// MembershipDAO is responsible for all database operations related to memberships.
public class MembershipDAO {

        // Add membership (Trainer and Member)
    public void addMemberShip(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (membership_type, price, member_id, purchase_date ) VALUES (?, ?, ?, ?::date)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, membership.getMembershipType());
            pstmt.setDouble(2, membership.getPrice());
            pstmt.setInt(3, membership.getMemberId());
            pstmt.setString(4, membership.getPurchaseDate());       
            pstmt.executeUpdate();
      }
  }
    //Get Membership by ID (Trainer and Member)
    public Membership getMembershipByMemberId(int memberid) throws SQLException{
        String sql = "SELECT * FROM memberships WHERE member_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberid);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Membership member =  new Membership(
                            rs.getString("membership_type"),
                            rs.getDouble("price"),
                            rs.getInt("member_id"),
                            rs.getString("purchase_date")
                            
                    );
                    member.setId(rs.getInt("id"));
                    return member;
                }
            }
        }
        return null;
    }

    //View total Revenue of Memberships (Admin Only)
    public double getTotalRevenue() throws SQLException{
        String sql = "SELECT SUM(price) FROM memberships";
         try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            rs.next();
            return rs.getDouble(1);    
            }
    }



}
