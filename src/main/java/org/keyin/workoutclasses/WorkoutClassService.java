package org.keyin.workoutclasses;

import java.sql.SQLException;
import java.util.List;

public class WorkoutClassService {    
    private WorkoutClassDAO workoutDAO;


    //constructor
    public WorkoutClassService(){
        this.workoutDAO = new WorkoutClassDAO();
    }

    //methods

    //Create class (Admin and Trainer)
    public void createClass(WorkoutClass workoutClass) throws SQLException{
        workoutDAO.createClass(workoutClass);
    }

    //Get all classes (Member only)
    public List<WorkoutClass> getAllClasses() throws SQLException{
        return workoutDAO.getAllClasses();
    }

    //Get classes by trainer id (Trainer Only)
    public List<WorkoutClass> getClassesByTrainerId(int trainerId) throws SQLException {
    return workoutDAO.getClassesByTrainerId(trainerId);
}
    //Delete Class by id (Admin and Trainer)
    public int deleteClass(int id) throws SQLException{
        return workoutDAO.deleteClasses(id);
    }
}
