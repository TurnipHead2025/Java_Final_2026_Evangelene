package org.keyin.workoutclasses;
import org.keyin.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class WorkoutClassDAO {

    //Create a class
    public void createClass(WorkoutClass workoutClass)throws SQLException{
        String sql= "INSERT INTO workout_classes (class_name, description, trainer_id, schedule) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, workoutClass.getClassName());
            pstmt.setString(2, workoutClass.getClassDescription());
            pstmt.setInt(3, workoutClass.getTrainerID());
            pstmt.setString(4, workoutClass.getClassSchedule());
            pstmt.executeUpdate(); 
        }
    }   
        //Get all classes
        public List<WorkoutClass> getAllClasses() throws SQLException {
            List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    WorkoutClass workoutClass = new WorkoutClass(
                        rs.getString("class_name"),
                        rs.getString("description"),
                        rs.getInt("trainer_id"),
                        rs.getString("schedule")
                    );
                    workoutClass.setId(rs.getInt("id"));
                    classes.add(workoutClass);
                }
            }
            return classes;
        }

        //Get Classes by Trainer ID
        public List<WorkoutClass> getClassesByTrainerId(int trainerId) throws SQLException{
             List<WorkoutClass> classes = new ArrayList<>();
            String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    WorkoutClass workoutClass = new WorkoutClass(
                        rs.getString("class_name"),
                        rs.getString("description"),
                        rs.getInt("trainer_id"),
                        rs.getString("schedule")
                    );
                    workoutClass.setId(rs.getInt("id"));
                    classes.add(workoutClass);
                }
            }
        }
        return classes;        
    }

    //Delete Classes by id
    public int deleteClasses(int id) throws SQLException{
        String sql = "DELETE FROM workout_classes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();        
            }
    }    

    //Update Class by id
    public int updateClass(String className, String description, String schedule, int id) throws SQLException{
        String sql = "UPDATE workout_classes SET class_name=?, description=?, schedule=? WHERE id = ?";
         try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, className);
            pstmt.setString(2, description);
            pstmt.setString(3, schedule);
            pstmt.setInt(4, id);
            return pstmt.executeUpdate();        
            }
    }

       
//    CustomLogger.logError("WorkoutClassDAO is not implemented yet"); -- This is how calling the logger will look!

}


