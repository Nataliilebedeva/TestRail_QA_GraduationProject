package ApiRequestAdapters.adapters.endpoints;

public interface CasesEndpoint {
    String ADD_CASE = "index.php?/api/v2/add_case/%d";
    String GET_CASE = "index.php?/api/v2/get_case/%d";
    String GET_HISTORY_FOR_CASES = "index.php?/api/v2/get_history_for_case/%d";
    String UPDATE_CASE = "index.php?/api/v2/update_case/%d";
}
