package school.hei.schoolapi.repository;

import school.hei.schoolapi.model.Groups;
import school.hei.schoolapi.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCImplementation implements StudentSimpleRepository {
    public static Connection connectToDatabase(){
        try{
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/school","postgres","F#7b5/DbJazz");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentsList = new ArrayList<>();
        try{
            Statement stm = connectToDatabase().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM to_do");
            while(res.next()){
                Student temp = new Student();
                Groups group = new Groups();
                temp.setId(res.getInt("id"));
                temp.setName(res.getString("name"));
                group.setId((long) res.getInt("group_id"));
                temp.setGroup(group);
                studentsList.add(temp);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    @Override
    public Student save(Student s) {
        try{
            PreparedStatement insertTemplate =  connectToDatabase().prepareStatement("INSERT INTO student(nom,groups) VALUES (?,?)");
            insertTemplate.setString(1,s.getName());
            insertTemplate.setInt(2,(int) s.getGroup().getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Student output = new Student();
        Groups group = new Groups();
        PreparedStatement select = null;
        try{
            select = connectToDatabase().prepareStatement("SELECT * FROM students WHERE id = ?");
            select.setInt(1,id.intValue());

            ResultSet result = select.executeQuery();

            result.next();

            output.setId(result.getInt("id"));
            output.setName(result.getString("name"));
            group.setId(Long.valueOf(result.getInt("group_id")));
            output.setGroup(group);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(output);
    }

    @Override
    public void deleteById(Long Id) {
        PreparedStatement select = null;
        try{
            select = connectToDatabase().prepareStatement("DELETE FROM students WHERE id = ?");
            select.setInt(1,Id.intValue());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findWhereNameLike(String query) {
        PreparedStatement select = null;
        List<Student> output = new ArrayList<>();
        try{
            select = connectToDatabase().prepareStatement("SELECT * FROM students WHERE name LIKE ?");
            select.setString(1,"%"+query+"%");

            ResultSet result = select.executeQuery();

            while(result.next()){
                Student temp = new Student();
                Groups group = new Groups();
                temp.setId((long) result.getInt("id"));
                temp.setName(result.getString("name"));
                group.setId((long) result.getInt("group_id"));
                temp.setGroup(group);
                output.add(temp);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
}
