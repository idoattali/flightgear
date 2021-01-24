package model.server_side;

import java.io.*;

public class MyTestClientHandler<Problem, Solution> implements ClientHandler {

    @SuppressWarnings("rawtypes")
    private final Solver solver;
    @SuppressWarnings("rawtypes")
    private final CacheManager cacheManager;


    public MyTestClientHandler(CacheManager cacheManager, Solver solver) {
        this.solver = solver;
        this.cacheManager = cacheManager;
    }

    @Override
    public void handleClient(InputStream in, OutputStream out) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(out));

        try {
            Problem Line;
            Solution Solved;

            while (!(Line = (Problem) bufferedReader.readLine()).equals("end")) {
                if (cacheManager.Check(Line)) {
                    Solved = (Solution) cacheManager.Extract(Line);
                } else {
                    Solved = (Solution) solver.Solve(Line);
                    cacheManager.Save(Line, Solved);
                }

                printWriter.println(Solved);
                printWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printWriter.close();
    }
}