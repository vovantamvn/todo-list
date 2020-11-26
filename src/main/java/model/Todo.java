package model;

public class Todo {
    private int id;
    private String name;
    private String content;
    private StatusType status;

    public Todo() {
    }

    public Todo(int id, String name, String content, StatusType status) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.status = status;
    }

    public Todo(String name, String content, StatusType status) {
        this.name = name;
        this.content = content;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}
