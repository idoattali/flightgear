package model.server_side;

public interface Solver<Problem, Solution> {
    Solution Solve(Problem problem);
}