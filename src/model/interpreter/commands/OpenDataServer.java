package model.interpreter.commands;

import model.interpreter.Parser;
import model.interpreter.experssions.ShuntingYardPostfix;
import model.server_side.ClientHandler;
import model.server_side.MySerialServer;
import model.server_side.Server;

import java.io.*;

public class OpenDataServer implements Command {
    public static volatile boolean stop = false;
    public static Object wait = new Object();
    Server server;

    @Override
    public void Execute(String[] array) {
        stop = false;

        server = new MySerialServer();

        server.open(Integer.parseInt(array[1]), new ClientHandler() {
            @Override
            public void handleClient(InputStream in, OutputStream out) {
                BufferedReader buuBufferedReader = new BufferedReader(new InputStreamReader(in));
                synchronized (OpenDataServer.wait) {
                    OpenDataServer.wait.notifyAll();
                }
                while (!stop) {
                    try {
                        String Line;
                        Line = buuBufferedReader.readLine();
                        String[] vars = Line.split(",");
                        for (int i = 0; i < vars.length; i++) {
                            if (Double.parseDouble(vars[i]) != Parser.symTbl.get(Parser.vars.get(i)).getV()) {
                                Parser.symTbl.get(Parser.vars.get(i)).setV(Double.parseDouble(vars[i]));
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        long num = (long) ShuntingYardPostfix.calc("1000/" + array[2]);
                        Thread.sleep(num);
                    } catch (InterruptedException e) {
                    }
                }

                server.stop();
            }
        });
    }
}