package model.server_side;

public interface CacheManager<Problem, Solution> {
    Boolean Check(Problem problem);

    Solution Extract(Problem problem);

    void Save(Problem problem, Solution solution);
}