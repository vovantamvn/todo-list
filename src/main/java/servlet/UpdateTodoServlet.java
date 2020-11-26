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

@WebServlet("/update")
public class UpdateTodoServlet extends BaseServlet {
    private static final String PAGE = SITE + "update.jsp";
    private static final String REDIRECT_PAGE = "/";

    private CRUDRepository<Todo, Integer> todoRepository;

    @Override
    public void init() throws ServletException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        this.todoRepository = new TodoRepository(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Todo todo = todoRepository.findByID(id);
        request.setAttribute("todo", todo);
        request.getRequestDispatcher(PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        String status = request.getParameter("status");

        try {
            StatusType statusType = StatusType.valueOf(status);
            Todo todo = todoRepository.findByID(id);
            todo.setName(name);
            todo.setContent(content);
            todo.setStatus(statusType);

            todoRepository.update(todo, id);
            response.sendRedirect(REDIRECT_PAGE);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
