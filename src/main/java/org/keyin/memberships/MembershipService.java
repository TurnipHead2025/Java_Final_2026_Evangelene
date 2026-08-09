package org.keyin.memberships;

import java.sql.SQLException;

public class MembershipService {
    private MembershipDAO membershipDao;

    //constructor
    public MembershipService(){
        this.membershipDao = new MembershipDAO();
    }

    //methods

    //add membership - Calls addmembership from MembershipDAO
    public void addMembership(Membership membership) throws SQLException{
        membershipDao.addMemberShip(membership);
    }

    //Get membership info by membership ID
    public Membership getMembershipByMemberId(int id) throws SQLException{
        return membershipDao.getMembershipByMemberId(id);
    }

    //Get total Revenue
    public double getTotalRevenue() throws SQLException{
        return  membershipDao.getTotalRevenue();
    }
}
