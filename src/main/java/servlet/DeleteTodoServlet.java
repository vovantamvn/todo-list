package servlet;

import jdbc.ConnectionFactory;
import model.Todo;
import repository.CRUDRepository;
import repository.impl.TodoRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/delete")
public class DeleteTodoServlet extends HttpServlet {
    public static final String REDIRECT_PAGE = "/";

    private CRUDRepository<Todo, Integer> todoRepository;

    @Override
    public void init() throws ServletException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        this.todoRepository = new TodoRepository(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoRepository.delete(id);
        response.sendRedirect(REDIRECT_PAGE);
    }
}
