package repository.impl;

import model.StatusType;
import model.Todo;
import repository.CRUDRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements CRUDRepository<Todo, Integer> {
    private final Connection connection;

    public TodoRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Todo todo) {
        String sql = String.format("insert into todos(name, content, status) values('%s', '%s', '%s');",
                todo.getName(),
                todo.getContent(),
                todo.getStatus().name()
        );

        try {
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            statement.close();
            return row == 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Todo todo, Integer integer) {
        String sql = String.format("update todos set\n" +
                "name = '%s',\n" +
                "content = '%s',\n" +
                "status = '%s'\n" +
                "where id = %d;",
                todo.getName(),
                todo.getContent(),
                todo.getStatus().name(),
                integer
                );

        System.out.println();

        try {
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            statement.close();
            return row == 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public void delete(Integer integer) {
        String sql = String.format("delete from todos where id = %d;", integer);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Todo findByID(Integer integer) {
        String sql = String.format("select id, name, content, status from todos where id = %d;",
                integer
        );

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                Todo todo = convertTodo(resultSet);
                return todo;
            }
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Todo> findAll() {
        String sql = "select id, name, content, status from todos;";
        List<Todo> todos = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Todo todo = convertTodo(resultSet);
                todos.add(todo);
            }
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return todos;
    }

    private Todo convertTodo(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String content = resultSet.getString("content");
        StatusType status = StatusType.valueOf(resultSet.getString("status"));
        return new Todo(id, name, content, status);
    }
}
