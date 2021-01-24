package model.server_side;

public interface Server {
    void open(int port, ClientHandler clientHandler);

    void stop();
}