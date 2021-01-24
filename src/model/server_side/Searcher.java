package model.server_side;

public interface Searcher<Solution> {
    Solution search(Searchable searchable);

    int getNumberOfNodesEvaluated();
}