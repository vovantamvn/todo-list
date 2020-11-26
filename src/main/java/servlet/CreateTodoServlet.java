package servlet;

import jdbc.ConnectionFactory;
import model.StatusType;
import model.Todo;
import repository.CRUDRepository;
import repository.impl.TodoRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/create")
public class CreateTodoServlet extends BaseServlet {
    private static final String PAGE = SITE + "create.jsp";
    private static final String REDIRECT_PAGE = "/";

    private CRUDRepository<Todo, Integer> todoRepository;

    @Override
    public void init() throws ServletException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        this.todoRepository = new TodoRepository(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        String status = request.getParameter("status");

        try {
            StatusType statusType = StatusType.valueOf(status);
            Todo todo = new Todo(name, content, statusType);
            todoRepository.save(todo);
            response.sendRedirect(REDIRECT_PAGE);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
