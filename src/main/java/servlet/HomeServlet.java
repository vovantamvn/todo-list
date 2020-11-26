package servlet;

import jdbc.ConnectionFactory;
import model.Todo;
import repository.CRUDRepository;
import repository.impl.TodoRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends BaseServlet {
    public static final String PAGE =  SITE + "index.jsp";

    private CRUDRepository<Todo, Integer> todoRepository;

    @Override
    public void init() throws ServletException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        this.todoRepository = new TodoRepository(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE);
        List<Todo> todos = todoRepository.findAll();
        request.setAttribute("todos", todos);
        dispatcher.forward(request, response);
    }
}
